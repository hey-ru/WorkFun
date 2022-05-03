package com.booking.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.booking.model.BookingDAO;
import com.booking.model.BookingDAO_interface;
import com.booking.model.BookingService;
import com.booking.model.BookingVO;

public class BookingServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("booking");
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
				errorMsgs.put("bookingId", "查無此訂單");
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
	}
}