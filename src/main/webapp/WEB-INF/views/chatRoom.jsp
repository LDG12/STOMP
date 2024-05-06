<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var = "root" value = "${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<style>
.row {
  border: 1px solid #ccc;
}
</style>
</head>
<body>
	${room.roomId} // ${room.name} // ${loginUser.id} // ${loginUser.nickname}
	<div class="row">
		<div class="col-5">
			<input type="text" placeholder="보낼 메세지를 입력하세요." class="form-control content">	
		</div>
		<div class="col-1">
			<button type="button" value="전송" class="btn btn-primary sendBtn" onclick="sendMsg()">전송</button>	
		</div>
		<div class="col-1">
			<button type="button" value="방나가기" class="btn btn-primary quit" onclick="disconnect()">나가기 </button>	
		</div>
		<div class="col-5"></div>
	</div>
	<div class="row">
    	<div class="col-2">메시지</div>
    	<div class="col-2">전송자</div>
    	<div class="col-5">메시지내용</div>
    	<div class="col-3">전송시간</div>
    	<div class="row msgArea">'
    	
    	</div>
    	
	</div>
</body>
<script>
	var stompClient = null;
	
	document.addEventListener("DOMContentLoaded", function(){
		connect();
	});
	
	function connect(){
		var socket = new SockJS("/ws/chat");
		console.log("user Id = ${loginUser.id}");
		var header = {
			"userId" : "${loginUser.id}"	
		};
		stompClient = Stomp.over(socket);
		stompClient.connect(header, function(frame){
			console.log("Connected:" + frame);
			sendEnterMessage();
			stompClient.subscribe("/sub/${room.roomId}", function(greeting){
				console.log("greeting is ="+greeting);
				console.log("greeings body and content = "+JSON.parse(greeting.body).content);
				console.log("greetings body = "+JSON.parse(greeting.body));
				showGreeting(JSON.parse(greeting.body));
			});
			stompClient.subscribe("/user/${loginUser.id}/sub/${room.roomId}", function(greeting){
				console.log("test");
				showGreeting(JSON.parse(greeting.body));
			});
		});
	}
	
	function disconnect(){
		if(stompClient !== null){
			stompClient.disconnect();
		}
		console.log("Disconnect");
		location.href="${root}/";
	}
	
	function sendEnterMessage() {
		  var enterMessage = {
		    type: "ENTER",
		    roomId: "${room.roomId}",
		    sender: "${loginUser.id}",
		    message: ""
		  };
		  stompClient.send("/pub/${room.roomId}", {}, JSON.stringify(enterMessage));
		}
	
	function sendMsg(){
		let content = document.querySelector(".content").value;
		var talkMsg = {"type" : "TALK", "roomId" : "${room.roomId}", "sender" : "${loginUser.id}", "message":content};
		console.log(talkMsg);
		stompClient.send("/pub/${room.roomId}",{},JSON.stringify(talkMsg));
	}

	
	function showGreeting(data) {
		  let msgArea = document.querySelector('.msgArea');

		  // 받은 데이터가 배열인지 확인
		  if (Array.isArray(data)) {
		    // 배열인 경우 (메시지 리스트)
		    if (data.length === 0) {
		      return; // 비어있다면 함수 종료
		    }

		    // 메시지 리스트 순회하면서 요소 생성 및 추가
		    data.forEach(message => {
		      let row = document.createElement('div');
		      row.className = 'row';

		      let msgType = document.createElement('div');
		      msgType.className = 'col-2';
		      msgType.innerText = message.type;

		      let msgSender = document.createElement('div');
		      msgSender.className = 'col-2';
		      msgSender.innerText = message.sender;

		      let msgContent = document.createElement('div');
		      msgContent.className = 'col-5';
		      msgContent.innerText = message.message;

		      let msgTime = document.createElement('div');
		      msgTime.className = 'col-3';
		      msgTime.innerText = message.messageDate;

		      row.appendChild(msgType);
		      row.appendChild(msgSender);
		      row.appendChild(msgContent);
		      row.appendChild(msgTime);

		      msgArea.appendChild(row);
		    });
		  } else {
		    // 단일 ChatMessage 객체인 경우
		    let row = document.createElement('div');
		    row.className = 'row';

		    let msgType = document.createElement('div');
		    msgType.className = 'col-2';
		    msgType.innerText = data.type;

		    let msgSender = document.createElement('div');
		    msgSender.className = 'col-2';
		    msgSender.innerText = data.sender;

		    let msgContent = document.createElement('div');
		    msgContent.className = 'col-5';
		    msgContent.innerText = data.message;

		    let msgTime = document.createElement('div');
		    msgTime.className = 'col-3';
		    msgTime.innerText = data.messageDate;

		    row.appendChild(msgType);
		    row.appendChild(msgSender);
		    row.appendChild(msgContent);
		    row.appendChild(msgTime);

		    msgArea.appendChild(row);
		  }
		}
	
</script>
</html>