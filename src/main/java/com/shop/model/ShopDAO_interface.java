package com.shop.model;

import java.util.*;


public interface ShopDAO_interface {

	 public void insert(ShopVO shopVO);
     public void update(ShopVO shopVO);
     public ShopVO findByPrimaryKey(Integer shop_id);
     public List<ShopVO> getAll();
}
