package com.ssafy.ws.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.ws.model.dto.ChatMessage;
import com.ssafy.ws.model.dto.ChatRoom;

public interface ChatDao {
	List<ChatRoom> selectAllRoom();
	
	int insert(ChatRoom dto);
	
	ChatRoom findRoomById(String roomId);
	
	int insertMessage(ChatMessage message);
	
	List<ChatMessage> bringPrevMessage(String roomId);
}
