package com.ssafy.ws.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ChatMessage {
	public enum MessageType{
		ENTER, TALK, QUIT
	}
	private MessageType type;
	private String roomId;
	private String sender;
	private String message;
	private String messageDate;
}
