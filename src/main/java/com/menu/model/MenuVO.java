package com.menu.model;

import java.sql.Timestamp;
import java.util.Objects;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.shop.model.ShopService;
import com.shop.model.ShopVO;

public class MenuVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
    // for join shop_name from shop_id
    public com.shop.model.ShopVO getShopVO() {
	    com.shop.model.ShopService shopSvc = new com.shop.model.ShopService();
	    com.shop.model.ShopVO shopVO = shopSvc.getOneShop(shop_id);
	    return shopVO;
    }
	
	private Integer menu_id;
	
	@CsvBindByName(column = "shop_id")
	private Integer shop_id;
	
	@CsvBindByName(column = "item")
	private String item;
	
	@CsvBindByName(column = "price")
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
	
	@Override
	public int hashCode() {
		return Objects.hash(is_item, item, menu_id, menu_upd, price, shop_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuVO other = (MenuVO) obj;
		return Objects.equals(is_item, other.is_item) && Objects.equals(item, other.item)
				&& Objects.equals(menu_id, other.menu_id) && Objects.equals(menu_upd, other.menu_upd)
				&& Objects.equals(price, other.price) && Objects.equals(shop_id, other.shop_id);
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
