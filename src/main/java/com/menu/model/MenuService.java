package com.menu.model;

import java.sql.Timestamp;
import java.util.List;

public class MenuService {

	private MenuDAO_interface dao;

	public MenuService() {
		dao = new MenuJDBCDAO();
	}

	public MenuVO addMenuItem(Integer shop_id, String item, Integer price) {

		MenuVO menuVO = new MenuVO();

		menuVO.setShop_id(shop_id);
		menuVO.setItem(item);
		menuVO.setPrice(price);

		dao.insert(menuVO);

		return menuVO;
	}

	public MenuVO updateMenuItem(Integer menu_id, String item, Integer price, Integer is_item) {

		MenuVO menuVO = new MenuVO();

		menuVO.setMenu_id(menu_id);
		menuVO.setItem(item);
		menuVO.setPrice(price);
		menuVO.setIs_item(is_item);

		dao.update(menuVO);

		return menuVO;
	}

	public MenuVO getOneMenuItem(Integer menu_id) {
		return dao.findByPrimaryKey(menu_id);
	}

	public List<MenuVO> getByShopId(Integer shop_id) {
		return dao.getByShopId(shop_id);
	}

	public List<MenuVO> getAll() {
		return dao.getAll();
	}

}
