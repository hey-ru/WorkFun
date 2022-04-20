package com.menu.model;

import java.util.*;


public interface MenuDAO_interface {

	 public void insert(MenuVO menuVO);
     public void update(MenuVO menuVO);
     public MenuVO findByPrimaryKey(Integer menu_id);
     public List<MenuVO> getAll();
}
