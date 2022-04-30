package com.groupbuy.model;

import java.sql.Timestamp;
import java.util.List;

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

	public GroupBuyVO updateShop(Integer gb_id,Integer shop_id,String shop_name,Integer gb_owner,Timestamp start_time,Timestamp end_time,Timestamp arr_time,Integer gb_status,Integer min_amt) {
		GroupBuyVO groupBuyVO = new GroupBuyVO();

		groupBuyVO.setGb_id(gb_id);
		groupBuyVO.setShop_id(shop_id);
		groupBuyVO.setShop_name(shop_name);
		groupBuyVO.setGb_owner(gb_status);
		groupBuyVO.setStart_time(start_time);
		groupBuyVO.setEnd_time(end_time);
		groupBuyVO.setArr_time(arr_time);
		groupBuyVO.setGb_status(gb_status);
		groupBuyVO.setMin_amt(min_amt);
		dao.update(groupBuyVO);

		return groupBuyVO;
	}

	public GroupBuyVO getOneGB(Integer gb_id) {
		return dao.findByPrimaryKey(gb_id);
	}

	public List<GroupBuyVO> getAll() {
		return dao.getAll();
	}
}
