package com.menu.model;

import java.util.ArrayList;
import java.util.List;

public class MenuService {

	private MenuDAO_interface dao;

	
	public MenuService() {
//		dao = new MenuJDBCDAO();
		dao = new MenuDAO(); //連線池
	}

	//一次新增多筆菜單項目
	public void addMenuMany(String[] newitem, String[] newprice, String shop_id){
		
		/*************************** 2. 輸入格式的錯誤處理 - 開始新增資料 *****************************************/

		MenuVO menuVO;
		List<MenuVO> menuAddlist = new ArrayList<>();

		for (int i = 0; i < newitem.length; i++) {
			menuVO = new MenuVO();
			menuVO.setItem(newitem[i]);
			menuVO.setPrice(Integer.valueOf(newprice[i]));
			menuVO.setShop_id(Integer.valueOf(shop_id));
			
			menuAddlist.add(menuVO);
		}
		dao.insertMany(menuAddlist);
		
		System.out.println(menuAddlist.toString());
		System.out.println("新增多筆菜單完成");
	}
	
	//主揪新增品項,金額
	public MenuVO addMenuItem(String item, Integer price, Integer shop_id) {

		MenuVO menuVO = new MenuVO();

		menuVO.setItem(item);
		menuVO.setPrice(price);
		menuVO.setShop_id(shop_id);

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
	public MenuVO updateStatus(Integer menu_id, Integer shop_id) {

		MenuVO menuVO = new MenuVO();
		menuVO.setMenu_id(menu_id);
		menuVO.setShop_id(shop_id);

		dao.updatestatus(menuVO);

		return menuVO;
	}
	
	//搜尋該筆菜單,再進一步修改品名或金額
	public MenuVO getOneMenuItem(Integer menu_id) {
		return dao.findByPrimaryKey(menu_id);
	}

	//以店家搜尋該店家菜單(上架中)
	public List<MenuVO> getByShopId(Integer shop_id) {
		return dao.getByShopId(shop_id);
	}
	
	//以店家搜尋該店家菜單(下架)
	public List<MenuVO> getByShopIdDisable(Integer shop_id) {
			return dao.getByShopIdDisable(shop_id);
		}

	//後台查詢所有店家菜單
	public List<MenuVO> getAll() {
		return dao.getAll();
	}

}
