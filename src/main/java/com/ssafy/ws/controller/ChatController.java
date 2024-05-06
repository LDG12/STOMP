package com.ssafy.ws.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import com.ssafy.ws.model.dto.ChatMessage;
import com.ssafy.ws.model.service.ChatService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
  private final ChatService service;
  private final SimpMessageSendingOperations messageTemplate;
  private final SimpUserRegistry userRegistry;
  
  
  @MessageMapping("/{chatRoomId}")
  public void sendMessage(@DestinationVariable("chatRoomId")String roomId, @Payload ChatMessage chatMessage) {
	  String userId = chatMessage.getSender();
	  if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
		  List<ChatMessage> list = service.bringPrevMessage(roomId);
		  System.out.println("chat message is here");
		  for(ChatMessage message : list) {
			  System.out.println("message = "+message);
		  }
		  messageTemplate.convertAndSend("/user/" + chatMessage.getSender() + "/sub/" + roomId, list);
		  chatMessage.setMessage(chatMessage.getSender()+"님이 입장하셨습니다.");
		  messageTemplate.convertAndSend("/sub/"+roomId, chatMessage);
	  }
	  else if(chatMessage.getType().equals(ChatMessage.MessageType.QUIT)) {
		  chatMessage.setMessage(chatMessage.getSender()+"님이 퇴장하셨습니다.");
		  messageTemplate.convertAndSend("/sub/"+roomId, chatMessage);
	  }
	  else if(chatMessage.getType().equals(ChatMessage.MessageType.TALK)) {
		  int result = service.insertMessage(chatMessage);
		  messageTemplate.convertAndSend("/sub/"+roomId, chatMessage);
	  }
  }
}
