package com.bid.model;

import java.util.List;

public class BidService {
	private BidDAO_interface dao;

	public BidService() {
		dao = new BidJDBCDAO();
	}
	
	public BidVO addBid(Integer second_hand_id, Integer price) {
		
		BidVO bidVO = new BidVO();
		
		bidVO.setsecond_hand_id(second_hand_id);
		bidVO.setsecond_hand_id(price);
		dao.insert(bidVO);
		
		return bidVO;
	}
	
	public BidVO updateBid(BidVO bidVO) {
	
		dao.update(bidVO);
		
		return bidVO;
	}
	
	public BidVO getOneById(Integer bid_id) {
		return dao.getById(bid_id);
	}
	
	public List<BidVO> getAll() {
		return dao.getAll();
	}
}
