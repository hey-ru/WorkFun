package com.bid.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class BidVO implements Serializable{

	private static final long serialVersionUID = 1;
	private int bid_id;
	private int second_hand_id;
	private int bidder;
	private int price;
	private Timestamp create_time;
	private Timestamp update_time;
	
	
	public int getBid_id() {
		return bid_id;
	}
	public void setBid_id(int bid_id) {
		this.bid_id = bid_id;
	}
	public int getsecond_hand_id() {
		return second_hand_id;
	}
	public void setsecond_hand_id(int second_hand_id) {
		this.second_hand_id = second_hand_id;
	}
	public int getBidder() {
		return bidder;
	}
	public void setBidder(int bidder) {
		this.bidder = bidder;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
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
	
}
