package com.menu.model;

import java.sql.Timestamp;

public class MenuVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer menu_id;
	private Integer shop_id;
	private String item;
	private Integer price;
	private Integer is_item;
	private Timestamp menu_upd;
	
	
	public MenuVO() {
		super();
	}
		
	public MenuVO(Integer menu_id, Integer shop_id, String item, Integer price, Integer is_item, Timestamp menu_upd) {
		super();
		this.menu_id = menu_id;
		this.shop_id = shop_id;
		this.item = item;
		this.price = price;
		this.is_item = is_item;
		this.menu_upd = menu_upd;
	}
	
	@Override
	public String toString() {
		return "MenuVO [menu_id=" + menu_id + ", shop_id=" + shop_id + ", item=" + item + ", price=" + price
				+ ", is_item=" + is_item + ", menu_upd=" + menu_upd + "]";
	}

	public Integer getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getIs_item() {
		return is_item;
	}
	public void setIs_item(Integer is_item) {
		this.is_item = is_item;
	}
	public Timestamp getMenu_upd() {
		return menu_upd;
	}
	public void setMenu_upd(Timestamp menu_upd) {
		this.menu_upd = menu_upd;
	}


}
