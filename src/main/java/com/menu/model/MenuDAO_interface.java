package com.menu.model;

import java.util.*;

public interface MenuDAO_interface {

	 public void insert(MenuVO menuVO); //前台 新增店家單品項目
	 
	 public void update(MenuVO menuVO); //前台 修改店家單品項目
	 
     public void updatestatus(MenuVO menuVO);//前台 下架改成上架
     
     public List<MenuVO> getByShopId(Integer shop_id);  //前台 查詢一間店家菜單(上架)
     
     public List<MenuVO> getByShopIdDisable(Integer shop_id);  //前台 查詢一間店家菜單(下架)
     public void update(MenuVO menuVO); //前台 修改店家單品項目
     public void updatestatus(MenuVO menuVO);//前台 下架改成上架
     
     public List<MenuVO> getByShopId(Integer shop_id);  //前台 查詢一間店家菜單(上架)
     public List<MenuVO> getByShopIdDisable(Integer shop_id);  //前台 查詢一間店家菜單(下架)
     
     
     public List<MenuVO> getAll(); //後台 查詢各店家菜單
     
     public MenuVO findByPrimaryKey(Integer menu_id); 
     
     //讀取csv檔
//     public List<MenuVO> loadAllMenu(File csvFile);

     
}

