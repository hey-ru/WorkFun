package com.chatRoom.model;

import java.util.List;

import com.secondHand.model.SecondHandVO;

public interface ChatRoomDAO_interface {
	public void insert(ChatRoomVO chatroomVO);
    public void update(ChatRoomVO chatroomVO);
    public void delete(Integer chat_room_id);
    public ChatRoomVO findByPrimaryKey(Integer chat_room_id);
    public List<ChatRoomVO> getAll();
}
