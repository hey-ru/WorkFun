package com.shop.model;

import java.util.*;

import com.menu.model.MenuVO;
import com.secondHand.model.SecondHandVO;

public interface ShopDAO_interface {

	public void insert(ShopVO shopVO);

	public void update(ShopVO shopVO);

	public ShopVO findByPrimaryKey(Integer shop_id);

	public List<ShopVO> findByShopName(String shop_name);

	public List<ShopVO> findByShopType(Integer shop_type);

	public List<ShopVO> getAll();
	
	public List<ShopVO> getNOWAll();
	
	public Set<MenuVO> getMenusByShopid(Integer shop_id);
	
	public void insertWithMenus(ShopVO shopVO , List<MenuVO> list);
	
	public List<ShopVO> getAll(Map<String, String[]> map);

	public void updateShopStatus(ShopVO shopVO);
}
