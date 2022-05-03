package com.booking.model;

import java.sql.Timestamp;
import java.util.List;

import com.equipment.model.EquipmentVO;

public class BookingService {

	private BookingDAO_interface dao;

	public BookingService() {
		dao = new BookingJDBCDAO();
	}

	public BookingVO addBooking(Integer bookingId, Integer equipmentId, Integer empId, Timestamp startDate,
			Timestamp endDate, Integer returnStatus, Timestamp overdueDate, Integer overduePrice) {

		BookingVO bookingVO = new BookingVO();

		bookingVO.setBookingId(bookingId);
		bookingVO.setEquipmentId(equipmentId);
		bookingVO.setEmpId(empId);
		bookingVO.setStartDate(startDate);
		bookingVO.setEndDate(endDate);
		bookingVO.setReturnStatus(returnStatus);
		bookingVO.setOverdueDate(overdueDate);
		bookingVO.setOverduePrice(overduePrice);
		dao.insert(bookingVO);

		return bookingVO;
	}
	
	public BookingVO updateDate(Integer bookingId, Timestamp startDate,Timestamp endDate) {
		
		BookingVO bookingVO = new BookingVO();
		
		bookingVO.setBookingId(bookingId);
		bookingVO.setStartDate(startDate);
		bookingVO.setEndDate(endDate);
		dao.updateDate(bookingVO);
	
		return dao.getByBookingId(bookingId);
	}

	public void deleteBooking(Integer bookingId) {
		dao.delete(bookingId);
	}
	
	public BookingVO getByBookingId(Integer bookingId) {
		return dao.getByBookingId(bookingId);
	}
	
	public List<BookingVO> getAll(){
		return dao.getAll();
	}
}