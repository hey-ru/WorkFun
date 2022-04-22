package com.secondHand.model;

import java.util.List;

public interface SecondHandDAO_interface {
	public void insert(SecondHandVO secondhandVO);

	public void update(SecondHandVO secondhandVO);

//	public void delete(Integer second_hand_id);

	public SecondHandVO findByPrimaryKey(Integer second_hand_id);

	public List<SecondHandVO> getAll();
}
