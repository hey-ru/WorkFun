package com.secondHand.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.bid.model.BidVO;

public class SecondHandService {
	private SecondHandDAO_interface dao;

	public SecondHandService() {
		dao = new SecondHandJDBCDAO();
	}

	public SecondHandVO addSecondHand(Integer saler, String name, Integer bottom_price, Integer top_price, Timestamp start_time, Timestamp end_time, byte[] img1, byte[] img2, byte[] img3) {

		SecondHandVO secondHandVO = new SecondHandVO();

		secondHandVO.setSaler(saler);
		secondHandVO.setName(name);
		secondHandVO.setBottom_price(bottom_price);
		secondHandVO.setTop_price(top_price);
		secondHandVO.setStart_time(start_time);
		secondHandVO.setEnd_time(end_time);
		secondHandVO.setImg1(img1);
		secondHandVO.setImg2(img2);
		secondHandVO.setImg3(img3);
		dao.insert(secondHandVO);

		return secondHandVO;
	}
	
	public SecondHandVO addSecondHandWithBid(Integer saler, String name, Integer bottom_price, Integer top_price, Timestamp start_time, Timestamp end_time, byte[] img1, byte[] img2, byte[] img3, Integer price) {

		SecondHandVO secondHandVO = new SecondHandVO();

		secondHandVO.setSaler(saler);
		secondHandVO.setName(name);
		secondHandVO.setBottom_price(bottom_price);
		secondHandVO.setTop_price(top_price);
		secondHandVO.setStart_time(start_time);
		secondHandVO.setEnd_time(end_time);
		secondHandVO.setImg1(img1);
		secondHandVO.setImg2(img2);
		secondHandVO.setImg3(img3);
		
		BidVO bidVO = new BidVO();
		
		bidVO.setPrice(new Integer(0));
		dao.insertWithBid(secondHandVO,bidVO);

		return secondHandVO;
	}

	public SecondHandVO updateSecondHand(Integer bid_winner, Integer deal_price, String name, Integer bottom_price, Integer top_price, Timestamp start_time, Timestamp end_time, Integer is_deal, byte[] img1, byte[] img2, byte[] img3, Integer second_hand_id) {

		SecondHandVO secondHandVO = new SecondHandVO();

		secondHandVO.setBid_winner(bid_winner);
		secondHandVO.setDeal_price(deal_price);
		secondHandVO.setName(name);
		secondHandVO.setBottom_price(bottom_price);
		secondHandVO.setTop_price(top_price);
		secondHandVO.setStart_time(start_time);
		secondHandVO.setEnd_time(end_time);
		secondHandVO.setIs_deal(is_deal);
		secondHandVO.setImg1(img1);
		secondHandVO.setImg2(img2);
		secondHandVO.setImg3(img3);
		secondHandVO.setsecond_hand_id(second_hand_id);
		dao.update(secondHandVO);

		return secondHandVO;
	}

	public SecondHandVO getOneById(Integer second_hand_id) {
		return dao.getById(second_hand_id);
	}
	
	public List<SecondHandVO> getALLByName(String name){
		return dao.getByName(name);
	}

	public List<SecondHandVO> getAll() {
		return dao.getAll();
	}
	
	public List<SecondHandVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
