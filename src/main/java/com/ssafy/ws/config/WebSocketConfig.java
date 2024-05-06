package com.ssafy.ws.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/sub", "/user");
		registry.setApplicationDestinationPrefixes("/pub");
		registry.setUserDestinationPrefix("/user");
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
	    registry.addEndpoint("/ws/chat")
	            .setAllowedOrigins("http://localhost:8080", "http://example.com")
	            .withSockJS();
	}
}
