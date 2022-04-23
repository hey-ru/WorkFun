package com.menu.model;

import java.sql.Timestamp;
import java.util.List;

public class MenuService {

	private MenuDAO_Interface dao;

	public MenuService() {
		dao = new MenuJDBCDAO();
	}

	public MenuVO addMenuItem(Integer shop_id, String item, Integer price, Integer is_item, Timestamp menu_upd) {

		MenuVO menuVO = new MenuVO();

		menuVO.setShop_id(shop_id);
		menuVO.setItem(item);
		menuVO.setPrice(price);
		menuVO.setIs_item(is_item);
		menuVO.setMenu_upd(menu_upd);

		dao.insert(menuVO);

		return menuVO;
	}

	public MenuVO updateGbItem(Integer menu_id, Integer shop_id, String item, Integer price, Integer is_item, Timestamp menu_upd) {

		MenuVO menuVO = new MenuVO();

		menuVO.setMenu_id(menu_id);
		menuVO.setShop_id(shop_id);
		menuVO.setItem(item);
		menuVO.setPrice(price);
		menuVO.setIs_item(is_item);
		menuVO.setMenu_upd(menu_upd);
		
		dao.update(menuVO);

		return menuVO;
	}

	 public List<MenuVO> getByShopId(Integer shop_id) {
		 return dao.getByShopId(shop_id);
	}
	 
	 public List<MenuVO> getAll(){
		 return dao.getAll();
	 }
	
}
