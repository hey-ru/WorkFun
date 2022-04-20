package com.shop.model;

import java.security.Timestamp;
import java.util.Arrays;

public class ShopVO implements java.io.Serializable {
	private Integer shop_id;
	private String shop_name;
	private Integer shop_type;
	private String address;
	private String tel;
	private String website;
	private Integer min_amt;
	private byte[] shop_img1;
	private byte[] shop_img2;
	private byte[] shop_img3;
	private Integer is_disable;
	private Timestamp shop_upd;
	
	
	
	public ShopVO() {
		super();
	}
	
	
	
	public ShopVO(Integer shop_id, String shop_name, Integer shop_type, String address, String tel, String website,
			Integer min_amt, byte[] shop_img1, byte[] shop_img2, byte[] shop_img3, Integer is_disable,
			Timestamp shop_upd) {
		super();
		this.shop_id = shop_id;
		this.shop_name = shop_name;
		this.shop_type = shop_type;
		this.address = address;
		this.tel = tel;
		this.website = website;
		this.min_amt = min_amt;
		this.shop_img1 = shop_img1;
		this.shop_img2 = shop_img2;
		this.shop_img3 = shop_img3;
		this.is_disable = is_disable;
		this.shop_upd = shop_upd;
	}

	@Override
	public String toString() {
		return shop_id + "," + shop_name + "," + shop_type + ","
				+ address + ", " + tel + ", " + website + ", " + min_amt + ", "
				+ shop_img1 + ", " + shop_img2 + ", "
				+ shop_img3 + ", " + is_disable + ", " + shop_upd;
	}

	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public Integer getShop_type() {
		return shop_type;
	}
	public void setShop_type(Integer shop_type) {
		this.shop_type = shop_type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Integer getMin_amt() {
		return min_amt;
	}
	public void setMin_amt(Integer min_amt) {
		this.min_amt = min_amt;
	}
	public byte[] getShop_img1() {
		return shop_img1;
	}
	public void setShop_img1(byte[] shop_img1) {
		this.shop_img1 = shop_img1;
	}
	public byte[] getShop_img2() {
		return shop_img2;
	}
	public void setShop_img2(byte[] shop_img2) {
		this.shop_img2 = shop_img2;
	}
	public byte[] getShop_img3() {
		return shop_img3;
	}
	public void setShop_img3(byte[] shop_img3) {
		this.shop_img3 = shop_img3;
	}
	public Integer getIs_disable() {
		return is_disable;
	}
	public void setIs_disable(Integer is_disable) {
		this.is_disable = is_disable;
	}
	public Timestamp getShop_upd() {
		return shop_upd;
	}
	public void setShop_upd(Timestamp shop_upd) {
		this.shop_upd = shop_upd;
	}
		
}
