package com.menu.model;

import java.io.File;
import java.util.*;

public interface MenuDAO_interface {

	 public void insert(MenuVO menuVO); //前台 新增店家單品項目
     public void update(MenuVO menuVO); //前台 修改店家單品項目
     
     public List<MenuVO> getByShopId(Integer shop_id);  //前台 查詢一間店家菜單
     
     public List<MenuVO> getAll(); //後台 查詢各店家菜單
     
     public MenuVO findByPrimaryKey(Integer menu_id); 
     
     
     //讀取csv檔
//     public List<MenuVO> loadAllMenu(File csvFile);

     
}

