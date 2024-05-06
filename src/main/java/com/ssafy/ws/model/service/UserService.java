package com.ssafy.ws.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.ws.model.dao.UserDao;
import com.ssafy.ws.model.dto.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserDao dao;
	
	public User login(User user) {
		User dbUser = findUserById(user.getId());
		if(dbUser.getPassword().equals(user.getPassword())) {
			return dbUser;
		}
		return null;
	}
	
	public User findUserById(String id) {
		return dao.findUserById(id);
	}
}
