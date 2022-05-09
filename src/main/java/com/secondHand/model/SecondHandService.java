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

	public SecondHandVO updateSecondHand(SecondHandVO secondHandVO) {

		
		dao.update(secondHandVO);

		return secondHandVO;
	}

	public SecondHandVO getOneById(Integer second_hand_id) {
		return dao.getById(second_hand_id);
	}
	
	public List<SecondHandVO> getAllByName(String name){
		return dao.getByName(name);
	}
	
	public List<SecondHandVO> getAllByIsDeal(Integer is_deal){
		return dao.getByIsDeal(is_deal);
	}

	public List<SecondHandVO> getAll() {
		return dao.getAll();
	}
	
	public List<SecondHandVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	public List<SecondHandVO> getAllDate() {
		return dao.getAllDate();
	}
}
