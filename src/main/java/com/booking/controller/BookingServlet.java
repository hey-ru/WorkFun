package com.booking.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.booking.model.BookingDAO_interface;
import com.booking.model.BookingService;
import com.booking.model.BookingVO;
import com.emp.model.EmpVO;
import com.equipment.model.EquipmentService;
import com.equipment.model.EquipmentVO;

@WebServlet("/booking/booking.do")
public class BookingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");

		if ("getOne_For_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("bookingId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("bookingId", "請輸入訂單編號");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/booking/booking_select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer bookingId = null;
			try {
				bookingId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("bookingId", "訂單編號格式不正確");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/booking/booking_select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			BookingService bookingSvc = new BookingService();
			BookingVO bookingVO = bookingSvc.getByBookingId(bookingId);
			if (bookingVO == null) {
				errorMsgs.put("bookingId", "查無此筆訂單");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/booking/booking_select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("bookingVO", bookingVO);
			String url = "/booking/listOneBookingId.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_returnStatus".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("returnStatus");
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
				return;
			}

			Integer returnStatus = null;
			try {
				returnStatus = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("returnStatus", "狀態格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			BookingService bookingSvc = new BookingService();
			BookingVO bookingVO = bookingSvc.getbyReturnStatus(returnStatus);
			if (bookingVO != null) {
				errorMsgs.put("returnStatus", "查無此資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("bookingVO", bookingVO);
			String url = "";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("get_For_empId".equals(action)) { // 來自eq_select_page.jsp的請求

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			EmpVO empVO = (EmpVO) (req.getSession().getAttribute("empVO"));
			BookingService bookingService = new BookingService();
			List<BookingVO> list = bookingService.getByEmpId(empVO.getEmpId());
			req.setAttribute("list", list);
			String url = "/equipment/bookingList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 2.開始查詢資料 *****************************************/

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

		}

		if ("updateReturnStatus".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer bookingId = Integer.valueOf(req.getParameter("bookingId").trim());

			Integer returnStatus = Integer.valueOf(req.getParameter("returnStatus").trim());

			/*************************** 2.開始修改資料 *****************************************/
			BookingService bookingSvc = new BookingService();
			BookingVO bookingVO = bookingSvc.updateReturnStatus(bookingId, returnStatus);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("bookingVO", bookingVO);
			String url = "/booking/backBookingAllList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

//		Integer bookingId = Integer.valueOf(req.getParameter("bookingId").trim());
			Integer equipmentId = Integer.valueOf(req.getParameter("equipmentId").trim());
			Integer empId = Integer.valueOf(req.getParameter("empId").trim());
			String startString = req.getParameter("startDate") + " 00:00:00";
			Timestamp startDate = Timestamp.valueOf(startString);
			String endString = req.getParameter("endDate") + " 00:00:00";
			Timestamp endDate = Timestamp.valueOf(endString);
			Integer returnStatus = 5;

			/*************************** 2.開始新增資料 ***************************************/
			BookingService bookingSvc = new BookingService();
			bookingSvc.addBooking(equipmentId, empId, startDate, endDate, returnStatus);

			System.out.println(bookingSvc.toString());

			String url = "/booking/bookingList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
//
//		if ("getOverdueDate".equals(action)) {
//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//
//			/*************************** 2.開始新增資料 ***************************************/
//			BookingService bookingSvc = new BookingService();
//			List<BookingVO> list = bookingSvc.getByEmpId(empId);
//			
//			/*************************** 逾期日期及金額計算 ***************************************/
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
//	        Date firstDate = null;
//			Date secondDate = null;
//			try {
//				firstDate = sdf.parse(start);
//				secondDate = sdf.parse(end);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//
//	        long diff = secondDate.getTime() - firstDate.getTime();
//	        TimeUnit time = TimeUnit.DAYS; 
//	        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS); 
//	        System.out.println("The difference in days is : "+diffrence); //取得相減日期，是要放進逾期日期的
//			
//	        
//	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
//	        Date date = null;
//			Date newDate = null;
//			try {
//				date = dateFormat.parse(end);
//				newDate = addDate(date, diffrence);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//	        System.out.println(dateFormat.format(date));// 輸出格式化後的日期
//	        System.out.println(dateFormat.format(newDate));
//
////	        for(int i = 0; i < list.size(); i++) {
////	        	list.get(i).setOverdueDate(newDate);
////	        }
//	        
//	        
//	       
////			req.setAttribute("list", list);
//			String url = "/booking/bookingList.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//			
//		}
//	}
//	public static Date addDate(Date date, long day) throws ParseException {
//		long time = date.getTime(); // 得到指定日期的毫秒數
//		day = day * 24 * 60 * 60 * 1000; // 要加上的天數轉換成毫秒數
//		time += day; // 相加得到新的毫秒數
//		return new Date(time); // 將毫秒數轉換成日期
//	}
	}
}
