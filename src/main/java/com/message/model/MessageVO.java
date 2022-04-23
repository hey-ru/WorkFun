package com.message.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int message_id;
	private int chat_room_id;
	private int chat_room_member_id;
	private String content;
	private Timestamp create_time;

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public int getChat_room_id() {
		return chat_room_id;
	}

	public void setChat_room_id(int chat_room_id) {
		this.chat_room_id = chat_room_id;
	}

	public int getChat_room_member_id() {
		return chat_room_member_id;
	}

	public void setChat_room_member_id(int chat_room_member_id) {
		this.chat_room_member_id = chat_room_member_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

}
