package com.chatRoom.model;

import java.io.Serializable;

public class ChatRoomVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int chat_room_id;
	private int is_group;
	
	public int getChat_room_id() {
		return chat_room_id;
	}
	public void setChat_room_id(int chat_room_id) {
		this.chat_room_id = chat_room_id;
	}
	public int getIs_group() {
		return is_group;
	}
	public void setIs_group(int is_group) {
		this.is_group = is_group;
	}
	
}
