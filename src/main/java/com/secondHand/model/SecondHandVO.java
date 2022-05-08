package com.secondHand.model;

import java.sql.Timestamp;

import com.bid.model.BidVO;
import com.emp.model.EmpVO;



public class SecondHandVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer second_hand_id;
	private Integer saler;
	private Integer bid_winner;
	private Integer deal_price;
	private String name;
	private Integer bottom_price;
	private Integer top_price;
	private Timestamp start_time;
	private Timestamp end_time;
	private Integer is_deal;
	private byte[] img1;
//	private String img1;
	private byte[] img2;
	private byte[] img3;
	private Timestamp create_time;
	private Timestamp update_time;
	private EmpVO empVO1;
	private EmpVO empVO2;
	private BidVO bidVO;

	public Integer getsecond_hand_id() {
		return second_hand_id;
	}

	public void setsecond_hand_id(Integer second_hand_id) {
		this.second_hand_id = second_hand_id;
	}

	public Integer getSaler() {
		return saler;
	}

	public void setSaler(Integer saler) {
		this.saler = saler;
	}

	public Integer getBid_winner() {
		return bid_winner;
	}

	public void setBid_winner(Integer bid_winner) {
		this.bid_winner = bid_winner;
	}

	public Integer getDeal_price() {
		return deal_price;
	}

	public void setDeal_price(Integer deal_price) {
		this.deal_price = deal_price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBottom_price() {
		return bottom_price;
	}

	public void setBottom_price(Integer bottom_price) {
		this.bottom_price = bottom_price;
	}

	public Integer getTop_price() {
		return top_price;
	}

	public void setTop_price(Integer top_price) {
		this.top_price = top_price;
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

	public Integer getIs_deal() {
		return is_deal;
	}

	public void setIs_deal(Integer is_deal) {
		this.is_deal = is_deal;
	}
	
	public byte[] getImg1() {
		return img1;
	}

	public void setImg1(byte[] img1) {
		this.img1 = img1;
	}

//	public String getImg1() {
//		return img1;
//	}
//
//	public void setImg1(String img1) {
//		this.img1 = img1;
//	}

	public byte[] getImg2() {
		return img2;
	}

	public void setImg2(byte[] img2) {
		this.img2 = img2;
	}

	public byte[] getImg3() {
		return img3;
	}

	public void setImg3(byte[] img3) {
		this.img3 = img3;
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
	
	public EmpVO getEmpVO1() {
		return empVO1;
	}

	public void setEmpVO1(EmpVO empVO1) {
		this.empVO1 = empVO1;
	}
	
	public EmpVO getEmpVO2() {
		return empVO2;
	}

	public void setEmpVO2(EmpVO empVO2) {
		this.empVO2 = empVO2;
	}

	public BidVO getBidVO() {
		return bidVO;
	}

	public void setBidVO(BidVO bidVO) {
		this.bidVO = bidVO;
	}
	
}
