package com.booking.model;

import java.util.List;

public interface BookingDAO_interface {
	// 新增預約單
	public void insert(BookingVO bookingVO);

	// 更改預約單日期
	public void updateDate(BookingVO bookingVO);

	// 刪除預約單
	public void delete(Integer bookingId);
	
	// 查詢預約單 by Booking Id
	public BookingVO getByBookingId(Integer bookingId);
	
	// 查詢預約單 by 員工編號
	public BookingVO getByEmpId(Integer empId);

	// 查詢自己預約單 by 狀態
	public BookingVO getbyReturnStatus(Integer returnStatus); 
	
	// 查詢預約單所有欄位
	public List<BookingVO> getAll(); 
}
