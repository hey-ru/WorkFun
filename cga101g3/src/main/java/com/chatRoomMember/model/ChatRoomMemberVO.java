package com.chatRoomMember.model;

import java.io.Serializable;

public class ChatRoomMemberVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int chat_room_member_id;
	private int chat_room_id;

	public int getChat_room_member_id() {
		return chat_room_member_id;
	}

	public void setChat_room_member_id(int chat_room_member_id) {
		this.chat_room_member_id = chat_room_member_id;
	}

	public int getChat_room_id() {
		return chat_room_id;
	}

	public void setChat_room_id(int chat_room_id) {
		this.chat_room_id = chat_room_id;
	}

}
