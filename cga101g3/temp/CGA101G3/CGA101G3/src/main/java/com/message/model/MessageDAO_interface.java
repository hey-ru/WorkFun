package com.message.model;

public interface MessageDAO_interface {
	public void insert(MessageVO messageVO);

//	public void update(messageVO messageVO);

//	public void delete(Integer message_id);

	public MessageVO findByPrimaryKey(Integer message_id);

//	public List<messageVO> getAll();
}
