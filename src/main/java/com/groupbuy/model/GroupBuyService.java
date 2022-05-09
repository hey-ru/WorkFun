package com.groupbuy.model;

import java.sql.Timestamp;
import java.util.*;

import com.groupbuylist.model.*;
import com.shop.model.ShopService;



public class GroupBuyService {

	private GroupBuyDAO_interface dao;

	public GroupBuyService() {
		dao = new GroupBuyDAO();
	}

	public GroupBuyVO addGroupBuy(Integer shop_id,String shop_name,Integer gb_owner,Timestamp start_time,Timestamp end_time,Timestamp arr_time,Integer gb_status,Integer min_amt) {

		GroupBuyVO groupBuyVO = new GroupBuyVO();

		groupBuyVO.setShop_id(shop_id);
		groupBuyVO.setShop_name(shop_name);
		groupBuyVO.setGb_owner(gb_status);
		groupBuyVO.setStart_time(start_time);
		groupBuyVO.setEnd_time(end_time);
		groupBuyVO.setArr_time(arr_time);
		groupBuyVO.setGb_status(gb_status);
		groupBuyVO.setMin_amt(min_amt);
		
		dao.insert(groupBuyVO);

		return groupBuyVO;
	}

	public void updateArrTime(Integer gb_id,Timestamp arr_time) {
		GroupBuyVO groupBuyVO = new GroupBuyVO();

		groupBuyVO.setGb_id(gb_id);
		groupBuyVO.setArr_time(arr_time);

		dao.updateArrTime(groupBuyVO);

	}

	public void updateGBStatusBygbId(Integer gb_id,Integer gb_status) {
		GroupBuyVO groupBuyVO = new GroupBuyVO();

		groupBuyVO.setGb_id(gb_id);
		groupBuyVO.setGb_status(gb_status);

		dao.updateGBStatusBygbId(groupBuyVO);

	}
	
	public GroupBuyVO getOneGB(Integer gb_id) {
		return dao.findByPrimaryKey(gb_id);
	}

	public List<GroupBuyVO> getAll() {
		return dao.getAll();
	}
	
	public List<GroupBuyVO> getNowAll() {
		return dao.getNowAll();
	}
	
	public List<GroupBuyVO> getMyGBAll(Integer gb_owner) {
		return dao.getMyGBAll(gb_owner);
	}
	
	public Set<GroupBuyListVO> getGroupBuyListBygbid(Integer gb_id) {
		return dao.getGBListBygbid(gb_id);
	}
	
	
	public  Set<GroupBuyListVO> getBuyerBygbid(Integer gb_id) {
		return dao.getBuyerBygbid(gb_id);
	}

	public List<GroupBuyVO> getAll(Map<String, String[]> map) {
		
		return dao.getAll(map);
	}
}
