package com.ssafy.ws.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.ws.model.dto.ChatRoom;
import com.ssafy.ws.model.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/chat")
public class ChatRoomController {
	private final ChatService service;
	
	@GetMapping("/list")
	public String room(Model m) {
		List<ChatRoom> list = service.selectAllRoom();
		for(ChatRoom room : list) {
			System.out.println("room = "+room);
		}
		m.addAttribute("list", list);
		return "chatList";
	}
	
	@PostMapping("/createRoom")
	public String createRoom(@RequestParam("name") String roomName) {
		ChatRoom dto = service.createRoom(roomName);
		int result = service.insert(dto);
		return "redirect:/chat/list";
	}
	
	@GetMapping("/room/{chatRoomId}")
	public String moveRoom(@PathVariable("chatRoomId") String roomId, Model m) {
		ChatRoom room = service.findRoomById(roomId);
		System.out.println("room = "+room);
		m.addAttribute("room", room);
		return "chatRoom";
	}
}
