package com.groupbuy.model;

import java.sql.Timestamp;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.groupbuylist.model.GroupBuyListService;
import com.groupbuylist.model.GroupBuyListVO;
import com.shop.model.*;

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
	private ShopVO shopVO;
	private EmpVO empVO;//buyer	
	
	
	@Override
	public String toString() {
		return "GroupBuyVO [gb_id=" + gb_id + ", shop_id=" + shop_id + ", shop_name=" + shop_name + ", gb_owner="
				+ gb_owner + ", start_time=" + start_time + ", end_time=" + end_time + ", arr_time=" + arr_time
				+ ", gb_status=" + gb_status + ", min_amt=" + min_amt + "]";
	}
	public GroupBuyVO() {
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
	
	public ShopVO getShopVO() {
		ShopService shopSvc = new ShopService();
		ShopVO shopVO = shopSvc.getOneShop(shop_id);
		return shopVO;
	}
	public EmpVO getEmpVO() {
		EmpService empSvc = new EmpService();
		EmpVO empVO = empSvc.getOneEmp(gb_owner);
		return empVO;
	}

}
