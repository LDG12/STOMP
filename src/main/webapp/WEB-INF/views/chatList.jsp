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
</head>
<body>
	<form action="${root}/chat/createRoom" method="post">
		<div class="row">
			<div class="col-4">
		    	<input type="text" name="name" class="form-control" placeholder="채팅방 이름">		
			</div>
			<div class="col-2">
	    		<button type="submit" class="btn btn-primary" >방 만들기</button>		
			</div>
			<div class="col-6"></div>
		</div>
	</form>

	<ul class="list-group">
		<c:forEach items="${list}" var="item" varStatus="index">
			<div class="row">
				<div class="col-6"> 
					<li class="list-group-item"><a href="${root}/chat/room/${item.roomId}">[방 이름] : ${item.name}</a>${item.roomId}</li>
				</div>
				<div class="col-6"></div>
			</div>
		</c:forEach>
	</ul>
</body>
</html>