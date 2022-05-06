package com.bid.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.emp.model.EmpVO;

public class BidVO implements Serializable{

	private static final long serialVersionUID = 1;
	private Integer bid_id;
	private Integer second_hand_id;
	private Integer bidder;
	private Integer price;
	private Timestamp create_time;
	private Timestamp update_time;
	private EmpVO empVO;
	
	
	public Integer getBid_id() {
		return bid_id;
	}
	public void setBid_id(Integer bid_id) {
		this.bid_id = bid_id;
	}
	public Integer getsecond_hand_id() {
		return second_hand_id;
	}
	public void setsecond_hand_id(Integer second_hand_id) {
		this.second_hand_id = second_hand_id;
	}
	public Integer getBidder() {
		return bidder;
	}
	public void setBidder(Integer bidder) {
		this.bidder = bidder;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public EmpVO getEmpVO() {
		return empVO;
	}
	public void setEmpVO(EmpVO empVO) {
		this.empVO = empVO;
	}
	
}
