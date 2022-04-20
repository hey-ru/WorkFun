package com.booking.model;

import java.util.List;

public interface BookingDAO_interface {
	// �s�W�w����
	public void insert(BookingVO bookingVO);

	// ���w������
	public void updateDate(BookingVO bookingVO);

	// �R���w����
	public void delete(Integer bookingId);
	
	// �d�߹w���� by Booking Id
	public BookingVO getByBookingId(Integer bookingId);
	
	// �d�߹w���� by ���u�s��
	public BookingVO getByEmpId(Integer empId);

	// �d�߹w����Ҧ����
	public List<BookingVO> getAll(); 

	// �d�ߦۤv�w���� by ���A
	public BookingVO getbyReturnStatus(Integer returnStatus); 
}
