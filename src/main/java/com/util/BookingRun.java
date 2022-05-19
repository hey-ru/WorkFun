package com.util;


import java.sql.Date;
import java.sql.Timestamp;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import com.booking.model.BookingService;
import com.booking.model.BookingVO;


public class BookingRun implements Runnable {

	public BookingRun() {

	}

	@Override
	public void run() {
		BookingService bookingService = new BookingService();
		List<BookingVO> list = bookingService.getAllDate();

		Iterator<BookingVO> iterator = list.iterator();
		while (iterator.hasNext()) {
			BookingVO bookingVO = (BookingVO) iterator.next();

//			Integer booking_id = bookingVO.getBookingId();
//			Long targetTime=new GregorianCalendar().getTime().getTime();
			// System.out.println(secondHandVO.getStart_time());
			
			if (bookingVO.getReturnStatus() == 1) {
				if (bookingVO.getEndDate().before(new Timestamp(new GregorianCalendar().getTime().getTime()))) {
					// System.out.println(secondHandVO.getStart_time().before(new Timestamp(new
					// GregorianCalendar().getTime().getTime())));
					bookingVO.setReturnStatus(3);
					bookingVO.setOverdueDate(new Timestamp(new GregorianCalendar().getTime().getTime()));
					bookingService.updateBooking(bookingVO);
				System.out.println("改變狀態");
				}
			}
			
			
			if(bookingVO.getReturnStatus() == 5) {
				if(bookingVO.getStartDate().before(new Timestamp(new GregorianCalendar().getTime().getTime()))) {
//					System.out.println(bookingVO.getStartDate().before(new Timestamp(new GregorianCalendar().getTime().getTime()+86400000)));
					bookingVO.setReturnStatus(2);
					bookingService.updateBooking(bookingVO);
				}
			}

//			if(secondHandVO.getIs_deal()==1) {
//				if(secondHandVO.getEnd_time().before(new Timestamp(new GregorianCalendar().getTime().getTime()))) {
//				//	System.out.println(secondHandVO.getStart_time().before(new Timestamp(new GregorianCalendar().getTime().getTime())));
//					
//					if(secondHandService.getOneById(second_hand_id).getBidVO().getBidder()==0) {
//					
//					System.out.println("設定成3");
//					
//					secondHandVO.setIs_deal(3);
//					secondHandService.updateSecondHand(secondHandVO);
//					
//					
//					}
//					else {
//						secondHandVO.setIs_deal(2);
//						secondHandService.updateSecondHand(secondHandVO);
//						System.out.println("設定成2");
//						secondHandVO.setBid_winner(secondHandService.getOneById(second_hand_id).getBidVO().getBidder());
//						secondHandVO.setDeal_price(secondHandService.getOneById(second_hand_id).getBidVO().getPrice());
//					
//					}	
//			}
//		
//		
		}
	}	
//public static void main(String[] args) {
//	BookingService bookingService = new BookingService();
//	BookingVO bookingVO = bookingService.getByBookingId(1132);
////	System.out.println(bookingVO.getStartDate());
//	System.out.println(new Timestamp(new GregorianCalendar().getTime().getTime()));
//	System.out.println(new Timestamp (System.currentTimeMillis()));
//}
}


