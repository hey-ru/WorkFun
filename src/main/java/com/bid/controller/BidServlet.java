package com.bid.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.bid.model.BidService;
import com.bid.model.BidVO;
import com.secondHand.model.SecondHandService;
import com.secondHand.model.SecondHandVO;

@WebServlet("/bid/BidServlet")
public class BidServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

//		if ("getOneForUpdate".equals(action)) { // 來自secondHandHome.jsp的請求
//
//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ****************************************/
//				Integer second_hand_id = Integer.valueOf(req.getParameter("second_hand_id"));
//
//				/*************************** 2.開始查詢資料 ****************************************/
//				SecondHandService secondHandService = new SecondHandService();
//				SecondHandVO secondHandVO = secondHandService.getOneById(second_hand_id);
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//				String param = "?second_hand_id=" + secondHandVO.getsecond_hand_id() + "&saler="
//						+ secondHandVO.getSaler() + "&name=" + secondHandVO.getName() + "&bottom_price="
//						+ secondHandVO.getBottom_price() + "&top_price=" + secondHandVO.getTop_price() + "&start_time="
//						+ secondHandVO.getStart_time() + "&end_time=" + secondHandVO.getEnd_time() + "&img1="
//						+ secondHandVO.getImg1() + "&img2=" + secondHandVO.getImg2() + "&img3="
//						+ secondHandVO.getImg3();
//				String url = "/secondhand/updateSecondHand.jsp" + param;
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.put("無法取得要修改的資料", e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/secondhand/secondHandHome.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
		if ("update".equals(action)) { // 來自bidHome.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer price = null;
			System.out.println("這邊還有吧?");
			System.out.println(req.getParameter("price").trim());
			System.out.println(Integer.valueOf(req.getParameter("price").trim()));
			System.out.println(Integer.valueOf(req.getParameter("price").trim()));
			try {
				price = Integer.valueOf(req.getParameter("price").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("price","價格請填數字");
			}
			if (price < 0) {
				errorMsgs.put("price", "請輸入大於0的數字");
			}
			if (price > Integer.valueOf(req.getParameter("top_price"))) {
				errorMsgs.put("price", "若要直購請點選下方直購按鈕");
			}

			System.out.println(price);
			System.out.println(req.getParameter("bidder").trim());
			System.out.println(req.getParameter("bid_id").trim());
//			Integer second_hand_id = Integer.valueOf(req.getParameter("second_hand_id").trim());
			Integer bidder = Integer.valueOf(req.getParameter("bidder").trim());
			Integer bid_id = Integer.valueOf(req.getParameter("bid_id").trim());
			Integer second_hand_id = Integer.valueOf(req.getParameter("second_hand_id").trim());
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/bid/bidHome.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			BidService bidService = new BidService();
			BidVO bidVO = bidService.updateBid(bidder, price, bid_id);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("bidVO", bidVO); // 資料庫update成功後,正確的的bidVO物件,存入req
//			String param = "?second_hand_id=" + bidVO.getsecond_hand_id();
			String url = "/bid/bidHome.jsp";
			req.setAttribute("second_hand_id", second_hand_id);
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交bidHome.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.put("修改資料失敗",e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/bid/bidHome.jsp");
//				failureView.forward(req, res);
//			}
		}
//
//		if ("insert".equals(action)) { // 來自addSecondHand.jsp的請求
//
//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
////			try {
//			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//
//			// 下面之後要刪掉
//
//			Integer saler = null;
//			try {
//				saler = Integer.valueOf(req.getParameter("saler").trim());
//			} catch (Exception e) {
//				errorMsgs.put("saler", "請填入暫時的拍賣者id");
//			}
//			System.out.println(saler);
//			// 上面之後要刪掉
//
//			String name = req.getParameter("name");
//			String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
//			if (name == null || name.trim().length() == 0) {
//				errorMsgs.put("name", "商品名稱請勿空白");
//			} else if (!name.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("name", "商品名稱只能是中、英文字母、數字和_ , 且長度必需在1到20之間");
//			}
//			System.out.println(name);
//
//			Integer bottom_price = null;
//			try {
//				bottom_price = Integer.valueOf(req.getParameter("bottom_price").trim());
//			} catch (Exception e) {
//				errorMsgs.put("bottom_price", "請填入數字");
//			}
//			if (bottom_price < 0) {
//				errorMsgs.put("bottom_price", "請輸入大於0的數字");
//			}
//			if (bottom_price > 100000000) {
//				errorMsgs.put("bottom_price", "請輸入小於50,000,000的數字");
//			}
//			System.out.println(bottom_price);
//
//			Integer top_price = null;
//			try {
//				top_price = Integer.valueOf(req.getParameter("top_price").trim());
//			} catch (Exception e) {
//				errorMsgs.put("top_price", "請填入數字");
//			}
//			if (top_price <= bottom_price) {
//				errorMsgs.put("bottom_price", "請輸入大於起標價格的數字");
//			}
//			if (top_price > 100000000) {
//				errorMsgs.put("bottom_price", "請輸入小於100,000,000的數字");
//			}
//			System.out.println(top_price);
//
//			java.sql.Timestamp start_time = null;
//			try {
//				start_time = java.sql.Timestamp.valueOf(req.getParameter("start_time").trim());
//			} catch (IllegalArgumentException e) {
//				errorMsgs.put("start_time", "請輸入開始競標日期時間");
//			}
//			System.out.println(start_time);
//
//			java.sql.Timestamp end_time = null;
//			try {
//				end_time = java.sql.Timestamp.valueOf(req.getParameter("end_time").trim());
//			} catch (IllegalArgumentException e) {
//				errorMsgs.put("end_time", "請輸入開始競標日期時間");
//			}
////				if (start_time >= end_time) {
////					errorMsgs.put("bottom_price", "請輸入大於起標價格的數字");
////				}
//			System.out.println(end_time);
//
//			
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/secondhand/addSecondHand.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/*************************** 2.開始新增資料 ***************************************/
//			SecondHandService secondHandService = new SecondHandService();
//			secondHandService.addSecondHand(saler, name, bottom_price, top_price, start_time, end_time, img1, img2,
//					img3);
//
//			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			String url = "/secondhand/secondHandHome.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//			successView.forward(req, res);
//
//			/*************************** 其他可能的錯誤處理 **********************************/
////			} catch (Exception e) {
////				errorMsgs.put("Exception", e.getMessage());
////				RequestDispatcher failureView = req.getRequestDispatcher("/secondhand/addSecondHand.jsp");
////				failureView.forward(req, res);
////				System.out.println("error");
////			}
//		}
//
//		if ("listSecondHandsByName".equals(action)) { // 來自secondHandHome.jsp的名字模糊查詢請求
//
//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
////			try {
//
//			/*************************** 1.接收請求參數 ****************************************/
//			String name = new String(req.getParameter("name"));
//			System.out.println(name);
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/secondhand/secondHandHome.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			/*************************** 2.開始查詢資料 ****************************************/
//			SecondHandService secondHandService = new SecondHandService();
//			List<SecondHandVO> list = secondHandService.getALLByName(name);
//			for (SecondHandVO secondHandVO : list) {
//				System.out.println(secondHandVO.toString());
//			}
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/secondhand/secondHandHome.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			req.setAttribute("list", list); // 資料庫取出的list物件,存入request
//
//			String url = "/secondhand/listBySecondHandName.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//			successView.forward(req, res);
//
//			/*************************** 其他可能的錯誤處理 **********************************/
////			} catch (Exception e) {
////				errorMsgs.put("Exception", e.getMessage());
////				RequestDispatcher failureView = req.getRequestDispatcher("/secondhand/addSecondHand.jsp");
////				failureView.forward(req, res);
////				System.out.println("error");
////			}		
//		}
//
//		if ("listByCompositeQuery".equals(action)) { // 來自secondHandHome.jsp的複合查詢請求
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.將輸入資料轉為Map **********************************/
//			// 採用Map<String,String[]> getParameterMap()的方法
//			// 注意:an immutable java.util.Map
//			// Map<String, String[]> map = req.getParameterMap();
//			HttpSession session = req.getSession();
//			Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
//
//			// 以下的 if 區塊只對第一次執行時有效
//			if (req.getParameter("whichPage") == null) {
//				Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
//				session.setAttribute("map", map1);
//				map = map1;
//			}
//
//			/*************************** 2.開始複合查詢 ***************************************/
//			SecondHandService secondHandService = new SecondHandService();
//			List<SecondHandVO> list = secondHandService.getAll(map);
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			req.setAttribute("listByCompositeQuery", list); // 資料庫取出的list物件,存入request
////				session.setAttribute("listByCompositeQuery", list);
//			RequestDispatcher successView = req.getRequestDispatcher("/secondhand/listByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
//			successView.forward(req, res);
//		}

	}
}