package com.booking.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.equipment.model.EquipmentVO;

public class BookingService {

	private BookingDAO_interface dao;

	public BookingService() {
		dao = new BookingJDBCDAO();
	}

	public BookingVO addBooking(Integer equipmentId, Integer empId, Timestamp startDate, Timestamp endDate,
			Integer returnStatus) {

		BookingVO bookingVO = new BookingVO();

		bookingVO.setEquipmentId(equipmentId);
		bookingVO.setEmpId(empId);
		bookingVO.setStartDate(startDate);
		bookingVO.setEndDate(endDate);
		bookingVO.setReturnStatus(returnStatus);
		dao.insertBooking(bookingVO);

		return bookingVO;
	}

	public BookingVO updateDate(Integer bookingId, Timestamp startDate, Timestamp endDate) {

		BookingVO bookingVO = new BookingVO();

		bookingVO.setBookingId(bookingId);
		bookingVO.setStartDate(startDate);
		bookingVO.setEndDate(endDate);
		dao.updateDate(bookingVO);

		return bookingVO;
	}

	public BookingVO updateReturnStatus(Integer bookingId, Integer returnStatus) {

		BookingVO bookingVO = new BookingVO();

		bookingVO.setBookingId(bookingId);
		bookingVO.setReturnStatus(returnStatus);
		dao.updateReturnStatus(bookingVO);

		return bookingVO;
	}

	public void deleteBooking(Integer bookingId) {
		dao.delete(bookingId);
	}

	public BookingVO getByBookingId(Integer bookingId) {
		return dao.getByBookingId(bookingId);
	}

	public List<BookingVO> getAll() {
		return dao.getAll();
	}

	public BookingVO getbyReturnStatus(Integer returnStatus) {
		return dao.getbyReturnStatus(returnStatus);
	}

	public List<BookingVO> getByEmpId(Integer empId) {
		return dao.getByEmpId(empId);
	}

	public List<BookingVO> getOverdueDate(Integer empId) {
		return dao.getOverdueDate(empId);
	}

	public List<BookingVO> getStartDateByEqptId(Integer equipmentId) {
		return dao.getStartDateByEqptId(equipmentId);
	}

	public List<BookingVO> getBookingAllDate(Integer equipmentId) {
		return dao.getBookingAllDate(equipmentId);
	}
	
	public List<BookingVO> getBanDate(Integer equipmentId){
		return dao.getBanDate(equipmentId);
	}

	public List<BookingVO> getAllByCQ(Map<String, String[]> map) {
		return dao.getAllByCQ(map);
	}

	public List<BookingVO> getAllDate(){
		return dao.getAllDate();
	}

	public BookingVO updateBooking(BookingVO bookingVO) {
		
		dao.update(bookingVO);
		
		return bookingVO;
	}
}
