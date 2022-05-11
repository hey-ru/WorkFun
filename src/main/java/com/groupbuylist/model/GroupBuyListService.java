package com.groupbuylist.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.menu.model.MenuVO;

public class GroupBuyListService {

	private GroupBuyListDAO_interface dao;

	public GroupBuyListService() {
//		dao = new GroupBuyListJDBCDAO();
		dao = new GroupBuyListDAO();// 連線池
	}

//揪團:修改付款取貨
	public void updatePayPickUP(Integer gb_id, Integer buyer, Integer is_pay, Integer is_pickup) {

		GroupBuyListVO groupBuyListVO = new GroupBuyListVO();

		groupBuyListVO.setGb_id(gb_id);
		groupBuyListVO.setBuyer(buyer);
		groupBuyListVO.setIs_pay(is_pay);
		groupBuyListVO.setIs_pickup(is_pickup);

		dao.updateIsPayIsPickUp(groupBuyListVO);

	}

//參團:下單多筆	
	public void insertMany(String gb_id, String buyer, String buyer_name, String[] menu_id, String[] item,
			String[] price, String[] qty, String[] remark) {

		GroupBuyListVO groupBuyListVO;
		List<GroupBuyListVO> orderlist = new ArrayList<>();

		for (int i = 0; i < gb_id.length(); i++) {
			if (!"0".equals(qty[i])) {
				groupBuyListVO = new GroupBuyListVO();
				groupBuyListVO.setGb_id(Integer.valueOf(gb_id));
				groupBuyListVO.setBuyer(Integer.valueOf(buyer));
				groupBuyListVO.setBuyer_name(buyer_name);

				groupBuyListVO.setMenu_id(Integer.valueOf(menu_id[i]));
				groupBuyListVO.setItem(item[i]);
				groupBuyListVO.setPrice(Integer.valueOf(price[i]));
				groupBuyListVO.setQty(Integer.valueOf(qty[i]));
				groupBuyListVO.setRemark(remark[i]);

				orderlist.add(groupBuyListVO);
			}
		}
		dao.insertMany(orderlist);
	}

//參團:修改多筆	
	public void updateMany(List<GroupBuyListVO> listGBorder) {
		dao.updateMany(listGBorder);
	}

	public GroupBuyListVO addGbItem(Integer gb_id, Integer buyer, String buyer_name, Integer menu_id, String item,
			Integer price, Integer qty, String remark) {

		GroupBuyListVO groupBuyListVO = new GroupBuyListVO();

		groupBuyListVO.setGb_id(gb_id);
		groupBuyListVO.setBuyer(buyer);
		groupBuyListVO.setBuyer_name(buyer_name);
		groupBuyListVO.setMenu_id(menu_id);
		groupBuyListVO.setItem(item);
		groupBuyListVO.setPrice(price);
		groupBuyListVO.setQty(qty);
//		groupBuyListVO.setTotal(total);
		groupBuyListVO.setRemark(remark);

		dao.insertItem(groupBuyListVO);

		return groupBuyListVO;
	}

	public GroupBuyListVO updateGbItem(Integer qty, String remark, Integer buyer, Integer gbList_id) {

		GroupBuyListVO groupBuyListVO = new GroupBuyListVO();

		groupBuyListVO.setGbList_id(gbList_id);
		groupBuyListVO.setBuyer(buyer);
		groupBuyListVO.setQty(qty);
		groupBuyListVO.setRemark(remark);

		dao.updateItem(groupBuyListVO);

		return groupBuyListVO;
	}

	public void deleteItem(Integer gbList_id) {
		dao.deleteItem(gbList_id);
	}

	public void deleteMyGb(Integer buyer, Integer gb_id) {
		dao.deleteMyGb(buyer, gb_id);
	}

	public List<GroupBuyListVO> getMyGB(Integer buyer) {
		return dao.getMyGB(buyer);
	}

	public List<GroupBuyListVO> getOne(Integer buyer, Integer gb_id) {
		return dao.getOne(buyer, gb_id);
	}

	public List<GroupBuyListVO> getAll() {
		return dao.getAll();
	}

	public GroupBuyListVO findByPrimaryKey(Integer gbList_id) {
		return dao.findByPrimaryKey(gbList_id);

	}

	// 萬用查詢
	public List<GroupBuyListVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
