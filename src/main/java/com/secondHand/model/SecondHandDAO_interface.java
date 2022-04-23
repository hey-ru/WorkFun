package com.secondHand.model;

import java.util.List;

public interface SecondHandDAO_interface {
	public void insert(SecondHandVO secondhandVO);

	public void update(SecondHandVO secondhandVO);

	public SecondHandVO getById(Integer second_hand_id);

	public List<SecondHandVO> getByName(String name);

	public List<SecondHandVO> getAll();
}
