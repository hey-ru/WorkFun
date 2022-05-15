package com.secondHand.model;

import java.util.List;
import java.util.Map;

import com.bid.model.BidVO;

public interface SecondHandDAO_interface {
	public void insert(SecondHandVO secondhandVO);
	
	public void insertWithBid(SecondHandVO secondHandVO, BidVO bidVO);

	public void update(SecondHandVO secondhandVO);

	public SecondHandVO getById(Integer second_hand_id);

	public List<SecondHandVO> getBySaler(Integer saler);

	public List<SecondHandVO> getByBidWinner(Integer bid_winner);

	public List<SecondHandVO> getByName(String name);
	
	public List<SecondHandVO> getByIsDeal(Integer is_deal);

	public List<SecondHandVO> getAll();
	
	public List<SecondHandVO> getAllBySaler(Map<String, String[]> map); 

	public List<SecondHandVO> getAllByBidWinner(Map<String, String[]> map); 

	public List<SecondHandVO> getAll(Map<String, String[]> map); 
	
	public List<SecondHandVO> getAllDate();
	
}
