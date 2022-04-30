package com.groupbuy.model;

import java.sql.Timestamp;

public class GroupBuyVO implements java.io.Serializable {
	private Integer gb_id;
	private Integer shop_id;
	private String shop_name;
	private Integer gb_owner;
	private Timestamp start_time;
	private Timestamp end_time;
	private Timestamp arr_time;
	private Integer gb_status;
	private Integer min_amt;
	
	
	
	@Override
	public String toString() {
		return "GroupBuyVO [gb_id=" + gb_id + ", shop_id=" + shop_id + ", shop_name=" + shop_name + ", gb_owner="
				+ gb_owner + ", start_time=" + start_time + ", end_time=" + end_time + ", arr_time=" + arr_time
				+ ", gb_status=" + gb_status + ", min_amt=" + min_amt + "]";
	}
	public GroupBuyVO() {
	}
	public GroupBuyVO(Integer gb_id, Integer shop_id, String shop_name, Integer gb_owner, Timestamp start_time,
			Timestamp end_time, Timestamp arr_time, Integer gb_status, Integer min_amt) {
		super();
		this.gb_id = gb_id;
		this.shop_id = shop_id;
		this.shop_name = shop_name;
		this.gb_owner = gb_owner;
		this.start_time = start_time;
		this.end_time = end_time;
		this.arr_time = arr_time;
		this.gb_status = gb_status;
		this.min_amt = min_amt;
	}
	public Integer getGb_id() {
		return gb_id;
	}
	public void setGb_id(Integer gb_id) {
		this.gb_id = gb_id;
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
	public Integer getGb_owner() {
		return gb_owner;
	}
	public void setGb_owner(Integer gb_owner) {
		this.gb_owner = gb_owner;
	}
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	public Timestamp getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	public Timestamp getArr_time() {
		return arr_time;
	}
	public void setArr_time(Timestamp arr_time) {
		this.arr_time = arr_time;
	}
	public Integer getGb_status() {
		return gb_status;
	}
	public void setGb_status(Integer gb_status) {
		this.gb_status = gb_status;
	}
	public Integer getMin_amt() {
		return min_amt;
	}
	public void setMin_amt(Integer min_amt) {
		this.min_amt = min_amt;
	}



}
