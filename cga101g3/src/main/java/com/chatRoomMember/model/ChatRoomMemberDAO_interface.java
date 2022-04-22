package com.chatRoomMember.model;

public interface ChatRoomMemberDAO_interface {
	public void insert(ChatRoomMemberVO chatroommemberVO);

//	public void update(ChatRoomMemberVO chatroommemberVO);

//	public void delete(ChatRoomMemberVO chatroommemberVO);

	public ChatRoomMemberVO findByPrimaryKey(Integer chat_room_member_id, Integer chat_room_id);

//	public List<ChatRoomMemberVO> getAll();
}
