package com.booking.model;

import java.sql.Date;
import java.sql.Timestamp;

public class BookingVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer bookingId;
	private Integer equipmentId;
	private Integer empId;
	private Timestamp startDate;
	private Timestamp endDate;
	private Integer returnStatus;
	private Timestamp overdueDate;
	private Integer overduePrice;
	private Long dateDiff;//計算日期差

	@Override
	public String toString() {
		return "BookingVO [bookingId=" + bookingId + ", equipmentId=" + equipmentId + ", empId=" + empId
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", returnStatus=" + returnStatus
				+ ", overdueDate=" + overdueDate + ", overduePrice=" + overduePrice + "]";
	}
	

	public Long getDateDiff() {
		return dateDiff;
	}


	public void setDateDiff(Long dateDiff) {
		this.dateDiff = dateDiff;
	}


	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Integer getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(Integer returnStatus) {
		this.returnStatus = returnStatus;
	}

	public Timestamp getOverdueDate() {
		return overdueDate;
	}

	public void setOverdueDate(Timestamp overdueDate) {
		this.overdueDate = overdueDate;
	}

	public Integer getOverduePrice() {
		return overduePrice;
	}

	public void setOverduePrice(Integer overduePrice) {
		this.overduePrice = overduePrice;
	}

	
	// for join eqName from equipmentId
	public com.equipment.model.EquipmentVO getEquipmentVO() {
		com.equipment.model.EquipmentService equipmentSvc = new com.equipment.model.EquipmentService();
		com.equipment.model.EquipmentVO equipmentVO = equipmentSvc.getByEqId(equipmentId);
		return equipmentVO;
	}
}
