package com.groupbuylist.model;

import java.sql.Timestamp;
import java.util.List;

public class GroupBuyListService {

	private GroupBuyListDAO_interface dao;

	public GroupBuyListService() {
//		dao = new GroupBuyListJDBCDAO();
		dao = new GroupBuyListDAO();//連線池
	}

	public GroupBuyListVO addGbItem(Integer gb_id, Integer buyer, String buyer_name, Integer menu_id, String item,
			Integer price, Integer qty, Integer total, String remark) {

		GroupBuyListVO groupBuyListVO = new GroupBuyListVO();

		groupBuyListVO.setGb_id(gb_id);
		groupBuyListVO.setBuyer(buyer);
		groupBuyListVO.setBuyer_name(buyer_name);
		groupBuyListVO.setMenu_id(menu_id);
		groupBuyListVO.setItem(item);
		groupBuyListVO.setPrice(price);

		dao.insertItem(groupBuyListVO);

		return groupBuyListVO;
	}

	public GroupBuyListVO updateGbItem(Integer gbList_id, Integer gb_id, Integer buyer, String buyer_name,
			Integer menu_id, String item, Integer price, Integer qty, Integer total, String remark, Integer is_pay,
			Integer is_pickup, Timestamp gbList_upd) {

		GroupBuyListVO groupBuyListVO = new GroupBuyListVO();

		groupBuyListVO.setGbList_id(gbList_id);
		groupBuyListVO.setGb_id(gb_id);
		groupBuyListVO.setBuyer(buyer);
		groupBuyListVO.setBuyer_name(buyer_name);
		groupBuyListVO.setMenu_id(menu_id);
		groupBuyListVO.setItem(item);
		groupBuyListVO.setPrice(price);
		groupBuyListVO.setQty(qty);
		groupBuyListVO.setTotal(total);
		groupBuyListVO.setRemark(remark);
		groupBuyListVO.setIs_pay(is_pay);
		groupBuyListVO.setIs_pickup(is_pickup);
		groupBuyListVO.setGbList_upd(gbList_upd);

		dao.updateItem(groupBuyListVO);

		return groupBuyListVO;
	}

	public void deleteItem(Integer gbList_id) {
		dao.deleteItem(gbList_id);
	}

	public void deleteMyGb(Integer buyer, Integer gb_id) {
		dao.deleteMyGb(buyer, gb_id);
	}

	public List<GroupBuyListVO> getMyGb(Integer buyer, Integer gb_id) {
		return dao.getMyGb(buyer, gb_id);
	}

	public List<GroupBuyListVO> getAll() {
		return dao.getAll();
	}

	public GroupBuyListVO findByPrimaryKey(Integer gbList_id) {
		return dao.findByPrimaryKey(gbList_id);
	
	}
	
}
