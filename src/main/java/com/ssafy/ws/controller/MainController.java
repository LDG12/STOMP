package com.ssafy.ws.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.ws.model.dto.ChatRoom;
import com.ssafy.ws.model.dto.User;
import com.ssafy.ws.model.service.ChatService;
import com.ssafy.ws.model.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
	private final ChatService service;
	private final UserService uservice;
	
	@PostMapping("/user/login")
	public String login(@ModelAttribute User dto, HttpSession session) {
		User user = uservice.login(dto);
		session.setAttribute("loginUser", user);
		return "redirect:/";
	}
}
