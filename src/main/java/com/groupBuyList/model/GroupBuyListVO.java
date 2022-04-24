package com.groupBuyList.model;

import java.sql.Timestamp;

import com.groupBuy.model.GroupBuyDAO_interface;

public class GroupBuyListVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer gbList_id;
	private Integer gb_id;
	private Integer buyer;
	private String buyer_name;
	private Integer menu_id;
	private String item;
	private Integer price;
	private Integer qty;
	private Integer total; //金額
	private String remark;
	private Integer is_pay;
	private Integer is_pickup;
	private Timestamp gbList_upd;
	
			
	public GroupBuyListVO() {
		super();
	}
	
	public GroupBuyListVO(Integer gbList_id, Integer gb_id, Integer buyer, String buyer_name, Integer menu_id,
			String item, Integer price, Integer qty, Integer total, String remark, Integer is_pay, Integer is_pickup,
			Timestamp gbList_upd) {
		super();
		this.gbList_id = gbList_id;
		this.gb_id = gb_id;
		this.buyer = buyer;
		this.buyer_name = buyer_name;
		this.menu_id = menu_id;
		this.item = item;
		this.price = price;
		this.qty = qty;
		this.total = total;
		this.remark = remark;
		this.is_pay = is_pay;
		this.is_pickup = is_pickup;
		this.gbList_upd = gbList_upd;
	}

	@Override
	public String toString() {
		return "GroupBuyListVO [gbList_id=" + gbList_id + ", gb_id=" + gb_id + ", buyer=" + buyer + ", buyer_name="
				+ buyer_name + ", menu_id=" + menu_id + ", item=" + item + ", price=" + price + ", qty=" + qty
				+ ", total=" + total + ", remark=" + remark + ", is_pay=" + is_pay + ", is_pickup=" + is_pickup
				+ ", gbList_upd=" + gbList_upd + "]";
	}

	public Integer getGbList_id() {
		return gbList_id;
	}
	public void setGbList_id(Integer gbList_id) {
		this.gbList_id = gbList_id;
	}
	public Integer getGb_id() {
		return gb_id;
	}
	public void setGb_id(Integer gb_id) {
		this.gb_id = gb_id;
	}
	public Integer getBuyer() {
		return buyer;
	}
	public void setBuyer(Integer buyer) {
		this.buyer = buyer;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public Integer getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
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
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIs_pay() {
		return is_pay;
	}
	public void setIs_pay(Integer is_pay) {
		this.is_pay = is_pay;
	}
	public Integer getIs_pickup() {
		return is_pickup;
	}
	public void setIs_pickup(Integer is_pickup) {
		this.is_pickup = is_pickup;
	}
	public Timestamp getGbList_upd() {
		return gbList_upd;
	}
	public void setGbList_upd(Timestamp gbList_upd) {
		this.gbList_upd = gbList_upd;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	   // for join gb_id from groupbuy
    public com.groupBuy.model.GroupBuyVO getGroupBuyVO() {
	    com.groupBuy.model.GroupBuyService gbSvc = new com.groupBuy.model.GroupBuyService();
	    com.groupBuy.model.GroupBuyVO GroupBuyVO = gbSvc.getOneGB(gb_id);
	    return GroupBuyVO;
    }
	
}
