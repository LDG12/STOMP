package com.ssafy.ws.model.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ws.model.dao.ChatDao;
import com.ssafy.ws.model.dto.ChatMessage;
import com.ssafy.ws.model.dto.ChatRoom;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
	private final ObjectMapper objectMapper;
	private Map<String, ChatRoom> chatRooms;
	private final ChatDao dao;
	
	@PostConstruct
	private void init() {
		chatRooms = new LinkedHashMap<>();
		List<ChatRoom> list = dao.selectAllRoom();
		for(ChatRoom room : list) {
			chatRooms.put(room.getRoomId(), room);
		}
	}
	
	public List<ChatRoom> findAllRoom(){
		return new ArrayList<>(chatRooms.values());
	}
	
	public ChatRoom findRoomById(String roomId) {
		return dao.findRoomById(roomId);
	}
	
	public ChatRoom createRoom(String name) {
		String randomId = UUID.randomUUID().toString();
		ChatRoom chatRoom = ChatRoom.builder().roomId(randomId).name(name).build();
		return chatRoom;
	}
	
	public List<ChatRoom> selectAllRoom(){
		return dao.selectAllRoom();
	}
	public int insert(ChatRoom room) {
		return dao.insert(room);
	}
	
	public int insertMessage(ChatMessage message) {
		message.setMessageDate(returnNow());
		return dao.insertMessage(message);
	}
	
	public String returnNow() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
		String formatDate = now.format(format);
		return formatDate;
	}
	
	public List<ChatMessage> bringPrevMessage(String roomId){
		List<ChatMessage> list = dao.bringPrevMessage(roomId);
		for(ChatMessage message : list) {
			message.setType(ChatMessage.MessageType.ENTER);
		}
		return list;
	}
}
