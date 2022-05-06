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
		dao = new GroupBuyListDAO();//連線池
	}
	
	
//揪團:修改付款取貨
	public void updatePayPickUP(Integer gb_id, Integer buyer,Integer is_pay,Integer is_pickup) {

		GroupBuyListVO groupBuyListVO = new GroupBuyListVO();
		
		
		groupBuyListVO.setGb_id(gb_id);
		groupBuyListVO.setBuyer(buyer);
		groupBuyListVO.setIs_pay(is_pay);
		groupBuyListVO.setIs_pickup(is_pickup);

		dao.updateIsPayIsPickUp(groupBuyListVO);

	}

	

	public List<GroupBuyListVO> insertAll(Map<String, String[]> map){

		Set<String> menuKey= map.keySet();
		GroupBuyListVO groupBuyListVO = new GroupBuyListVO();
//		MenuVO menuVO = new MenuVO();
		List<GroupBuyListVO> list = new ArrayList<GroupBuyListVO>();
		
//		  int count = 0;
//          int talLength = 0;
//          do {
//          for (String key : menuKey) {
//
//          if ("qty".equals(key)) {
//          talLength = map.get(key).length;
//          groupBuyListVO.setQty((Integer) map.get(key)[count]);
//          }
//          if ("remark".equals(key)) {
//        	  groupBuyListVO.setRemark((String) map.get(key)[count]);
//          }
//
//          }
//          count++;
//          dao.insertItem(groupBuyListVO);
//          list.add(groupBuyListVO);
//          } while (count < talLength); 
		return list; 
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
	
	//萬用查詢
//	public List<GroupBuyListVO> getAll(Map<String, String[]> map) {
//		return dao.getAll(map);
//	}
}
