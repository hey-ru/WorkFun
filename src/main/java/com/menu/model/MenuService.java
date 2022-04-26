package com.menu.model;

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

	//主揪修改品名,金額
	public MenuVO updateMenuItem(Integer menu_id, String item, Integer price, Integer is_item, Integer shop_id) {

		MenuVO menuVO = new MenuVO();

		menuVO.setMenu_id(menu_id);
		menuVO.setItem(item);
		menuVO.setPrice(price);
		menuVO.setIs_item(is_item);
		menuVO.setShop_id(shop_id);//FK

		dao.update(menuVO);

		return menuVO;
	}
	
	//主揪查詢後可變更該項目狀態(上架或下架)
	public MenuVO updateMenuItem(Integer menu_id, Integer is_item) {

		MenuVO menuVO = new MenuVO();

		menuVO.setMenu_id(menu_id);
		menuVO.setIs_item(is_item);

		dao.update(menuVO);

		return menuVO;
	}
	

	//搜尋該筆菜單,再進一步修改品名或金額
	public MenuVO getOneMenuItem(Integer menu_id) {
		return dao.findByPrimaryKey(menu_id);
	}

	//以店家搜尋該店家菜單
	public List<MenuVO> getByShopId(Integer shop_id) {
		return dao.getByShopId(shop_id);
	}

	//後台查詢所有店家菜單
	public List<MenuVO> getAll() {
		return dao.getAll();
	}

}
