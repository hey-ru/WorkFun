package com.booking.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
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

//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			String str = req.getParameter("empId");
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.put("equipmentId", "請輸入器材編號");
//			}
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/booking/bookingList.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			Integer empId = null;
//			try {
//				empId = Integer.valueOf(str);
//			} catch (Exception e) {
//				errorMsgs.put("empId", "器材編號格式不正確");
//			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/booking/bookingList.jsp");
//				failureView.forward(req, res);
//				return;
//			}

			EmpVO empVO = (EmpVO) (req.getSession().getAttribute("empVO"));
			BookingService bookingService = new BookingService();
			List<BookingVO> list = bookingService.getByEmpId(empVO.getEmpId());
			req.setAttribute("list", list);
			String url = "/equipment/bookingList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 2.開始查詢資料 *****************************************/
//			BookingService bookingSvc = new BookingService();
//			List<BookingVO> bookingVO = bookingSvc.getByEmpId(empId);
//			if (empId == null) {
//				errorMsgs.put("equipmentId", "查無此資料");
//			}
//
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/booking/bookingList.jsp");
//				failureView.forward(req, res);
//				return;
//			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("bookingVO", bookingVO);
//			String url = "/equipment/bookingList.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
		}

		if ("updateReturnStatus".equals(action)) {
//			System.out.println("111111111");
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer bookingId = Integer.valueOf(req.getParameter("bookingId").trim());
//			System.out.println("bookingId= " + bookingId);

			Integer returnStatus = Integer.valueOf(req.getParameter("returnStatus").trim());
//			System.out.println("returnStatus= " + returnStatus);

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
		}

		System.out.println("我進來了");

//		Integer bookingId = Integer.valueOf(req.getParameter("bookingId").trim());

		Integer equipmentId = Integer.valueOf(req.getParameter("equipmentId").trim());

		System.out.println(equipmentId + " 216");

		Integer empId = Integer.valueOf(req.getParameter("empId").trim());

		System.out.println(empId + " 220");

		String startString = req.getParameter("startDate");
		System.out.println(startString + " 223");
		Timestamp startDate = Timestamp.valueOf(startString);

		System.out.println(startDate);

		String endString = req.getParameter("endDate");
		System.out.println(endString + " 231");
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
}
