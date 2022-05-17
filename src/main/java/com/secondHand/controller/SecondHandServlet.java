package com.secondHand.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.secondHand.model.SecondHandService;
import com.secondHand.model.SecondHandVO;

@WebServlet("/secondhand/SecondHandServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class SecondHandServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println(action);
		
		if ("getOneForUpdate".equals(action)) { // 來自secondHandHome.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer second_hand_id = Integer.valueOf(req.getParameter("second_hand_id"));
				
				/***************************2.開始查詢資料****************************************/
				SecondHandService secondHandService = new SecondHandService();
				SecondHandVO secondHandVO = secondHandService.getOneById(second_hand_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?second_hand_id="  +secondHandVO.getsecond_hand_id()+
							   "&saler="  +secondHandVO.getSaler()+
						       "&name="  +secondHandVO.getName()+
						       "&bottom_price="    +secondHandVO.getBottom_price()+
						       "&top_price="+secondHandVO.getTop_price()+
						       "&start_time="    +secondHandVO.getStart_time()+
						       "&end_time="   +secondHandVO.getEnd_time()+
						       "&img1="   +secondHandVO.getImg1()+
						       "&img2="   +secondHandVO.getImg2()+
						       "&img3=" +secondHandVO.getImg3();
				String url = "/secondhand/updateSecondHand.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 /secondhand/updateSecondHand.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("無法取得要修改的資料",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/secondhand/secondHandHome.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自updateSecondHand.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer bid_winner = null;
				
				Integer deal_price = null;
				
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.put("name", "商品名稱請勿空白");
				} else if (!name.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("name", "商品名稱只能是中、英文字母、數字和_ , 且長度必需在1到20之間");
				}
				System.out.println(name);
				
				Integer bottom_price = null;
				try {
					bottom_price = Integer.valueOf(req.getParameter("bottom_price").trim());
				} catch (Exception e) {
					errorMsgs.put("bottom_price", "請填入數字");
				}
				if (bottom_price < 0) {
					errorMsgs.put("bottom_price", "請輸入大於0的數字");
				}
				if (bottom_price > 100000000) {
					errorMsgs.put("bottom_price", "請輸入小於50,000,000的數字");
				}
				System.out.println(bottom_price);

				Integer top_price = null;
				try {
					top_price = Integer.valueOf(req.getParameter("top_price").trim());
				} catch (Exception e) {
					errorMsgs.put("top_price", "請填入數字");
				}
				if (top_price <= bottom_price) {
					errorMsgs.put("bottom_price", "請輸入大於起標價格的數字");
				}
				if (top_price > 100000000) {
					errorMsgs.put("bottom_price", "請輸入小於100,000,000的數字");
				}
				System.out.println(top_price);

				java.sql.Timestamp start_time = null;
				try {
					start_time = java.sql.Timestamp.valueOf(req.getParameter("start_time").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("start_time", "請輸入開始競標日期時間");
				}
				System.out.println(start_time);

				java.sql.Timestamp end_time = null;
				try {
					end_time = java.sql.Timestamp.valueOf(req.getParameter("end_time").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("end_time", "請輸入開始競標日期時間");
				}
//				if (start_time >= end_time) {
//					errorMsgs.put("bottom_price", "請輸入大於起標價格的數字");
//				}
				System.out.println(end_time);
				
				Integer is_deal = 0;

				byte[] img1 = null;

				Part pic1 = req.getPart("img1");
//				img1= getByteArrayFromPart(pic1);
				String filename1 = getFileNameFromPart(pic1);
				if (filename1 != null && pic1.getContentType() != null) {
					img1 = getByteArrayFromPart(pic1);
				}
				System.out.println(1);

//				String img2 = req.getParameter("img2");//base64
				byte[] img2 = null;
				
				Part pic2 = req.getPart("img2");
				String filename2 = getFileNameFromPart(pic2);
				if (filename2 != null && pic2.getContentType() != null) {
					img2 = getByteArrayFromPart(pic2);
				}
				System.out.println(2);
				
//				String img3 = req.getParameter("img3");//base63
				byte[] img3 = null;
				
				Part pic3 = req.getPart("img3");
				String filename3 = getFileNameFromPart(pic3);
				if (filename3 != null && pic3.getContentType() != null) {
					img3 = getByteArrayFromPart(pic3);
				}
				System.out.println(3);
				
				Integer second_hand_id = Integer.valueOf(req.getParameter("second_hand_id").trim());
				
				SecondHandVO newSecondHandVO = new SecondHandVO();
				newSecondHandVO.setBid_winner(bid_winner);
				newSecondHandVO.setDeal_price(deal_price);
				newSecondHandVO.setName(name);
				newSecondHandVO.setBottom_price(bottom_price);
				newSecondHandVO.setTop_price(top_price);
				newSecondHandVO.setStart_time(start_time);
				newSecondHandVO.setEnd_time(end_time);
				newSecondHandVO.setIs_deal(is_deal);
				newSecondHandVO.setImg1(img1);
				newSecondHandVO.setImg2(img2);
				newSecondHandVO.setImg3(img3);
				newSecondHandVO.setsecond_hand_id(second_hand_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/secondhand/updateSecondHand.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
//				SecondHandService secondHandService = new SecondHandService();
//				SecondHandVO secondHandVO = secondHandService.updateSecondHand(bid_winner, deal_price, name, bottom_price, top_price, start_time, end_time, is_deal, img1, img2, img3, second_hand_id);
				SecondHandService secondHandService = new SecondHandService();
				secondHandService.updateSecondHand(newSecondHandVO);

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("secondHandVO", newSecondHandVO); // 資料庫update成功後,正確的的secondHandVO物件,存入req
				String url = "/secondhand/secondHandHome.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交seondHandHome.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.put("修改資料失敗",e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/secondhand/updateSecondHand.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("insert".equals(action)) { // 來自addSecondHand.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				// 下面之後要刪掉

				Integer saler = null;
				try {
					saler = Integer.valueOf(req.getParameter("saler").trim());
				} catch (Exception e) {
					errorMsgs.put("saler", "請填入暫時的拍賣者id");
				}
				System.out.println(saler);
				// 上面之後要刪掉

				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.put("name", "商品名稱請勿空白");
				} else if (!name.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("name", "商品名稱只能是中、英文字母、數字和_ , 且長度必需在1到20之間");
				}
				System.out.println(name);

				Integer bottom_price = null;
				try {
					bottom_price = Integer.valueOf(req.getParameter("bottom_price").trim());
				} catch (Exception e) {
					errorMsgs.put("bottom_price", "請填入數字");
				}
				if (bottom_price < 0) {
					errorMsgs.put("bottom_price", "請輸入大於0的數字");
				}
				if (bottom_price > 10000000) {
					errorMsgs.put("bottom_price", "請輸入小於10,000,000的數字");
				}
				System.out.println(bottom_price);

				Integer top_price = null;
				try {
					top_price = Integer.valueOf(req.getParameter("top_price").trim());
				} catch (Exception e) {
					errorMsgs.put("top_price", "請填入數字");
				}
				if (top_price <= bottom_price) {
					errorMsgs.put("bottom_price", "請輸入大於起標價格的數字");
				}
				if (top_price > 100000000) {
					errorMsgs.put("bottom_price", "請輸入小於100,000,000的數字");
				}
				System.out.println(top_price);

				java.sql.Timestamp start_time = null;
				try {
					start_time = java.sql.Timestamp.valueOf(req.getParameter("start_time").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("start_time", "請輸入開始競標日期時間");
				}
				System.out.println(start_time);

				java.sql.Timestamp end_time = null;
				try {
					end_time = java.sql.Timestamp.valueOf(req.getParameter("end_time").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("end_time", "請輸入開始競標日期時間");
				}
//				if (start_time >= end_time) {
//					errorMsgs.put("bottom_price", "請輸入大於起標價格的數字");
//				}
				System.out.println(end_time);

				byte[] img1 = null;

				Part pic1 = req.getPart("img1");
//				img1= getByteArrayFromPart(pic1);
				String filename1 = getFileNameFromPart(pic1);
				if (filename1 != null && pic1.getContentType() != null) {
					img1 = getByteArrayFromPart(pic1);
				}
				System.out.println(1);

//				String img2 = req.getParameter("img2");//base64
				byte[] img2 = null;
				
				Part pic2 = req.getPart("img2");
				String filename2 = getFileNameFromPart(pic2);
				if (filename2 != null && pic2.getContentType() != null) {
					img2 = getByteArrayFromPart(pic2);
				}
				System.out.println(2);
				
//				String img3 = req.getParameter("img3");//base63
				byte[] img3 = null;
				
				Part pic3 = req.getPart("img3");
				String filename3 = getFileNameFromPart(pic3);
				if (filename3 != null && pic3.getContentType() != null) {
					img3 = getByteArrayFromPart(pic3);
				}
				System.out.println(3);	
				
				Integer price = 0;

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/secondhand/addSecondHand.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/				
				SecondHandService secondHandService = new SecondHandService();
				secondHandService.addSecondHandWithBid(saler, name, bottom_price, top_price, start_time, end_time, img1, img2, img3, price);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/secondhand/secondHandHome.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.put("Exception", e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/secondhand/addSecondHand.jsp");
//				failureView.forward(req, res);
//				System.out.println("error");
//			}
		}
		
		if ("listSecondHandsByName".equals(action)) { // 來自secondHandHome.jsp的名字模糊查詢請求
			
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				
				/*************************** 1.接收請求參數 ****************************************/
				String name = new String(req.getParameter("name"));
				System.out.println(name);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/secondhand/secondHandHome.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 ****************************************/
				SecondHandService secondHandService = new SecondHandService();
				List<SecondHandVO> list = secondHandService.getAllByName(name);
				for (SecondHandVO secondHandVO : list) {
					System.out.println(secondHandVO.toString());
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/secondhand/secondHandHome.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("list", list);    // 資料庫取出的list物件,存入request

				String url = "/secondhand/listBySecondHandName.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.put("Exception", e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/secondhand/addSecondHand.jsp");
//				failureView.forward(req, res);
//				System.out.println("error");
//			}		
		}
		
		if ("listSecondHandsBySaler".equals(action)) { // 來自frontheader.jsp的自身售出查詢請求
			
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				
				/*************************** 1.接收請求參數 ****************************************/
				Integer saler = new Integer(req.getParameter("saler"));
				System.out.println(saler);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/secondhand/secondHandHome.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 ****************************************/
				SecondHandService secondHandService = new SecondHandService();
				List<SecondHandVO> list = secondHandService.getAllBySaler(saler);
				for (SecondHandVO secondHandVO : list) {
					System.out.println(secondHandVO.toString());
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/secondhand/secondHandHome.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("list", list);    // 資料庫取出的list物件,存入request

				String url = "/secondhand/listByMySaleSecondHand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.put("Exception", e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/secondhand/addSecondHand.jsp");
//				failureView.forward(req, res);
//				System.out.println("error");
//			}		
		}
		
		if ("listByCompositeQuery".equals(action)) { // 來自secondHandHome.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				
				// 以下的 if 區塊只對第一次執行時有效
				if (req.getParameter("whichPage") == null){
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				SecondHandService secondHandService = new SecondHandService();
				List<SecondHandVO> list  = secondHandService.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listByCompositeQuery", list); // 資料庫取出的list物件,存入request
//				session.setAttribute("listByCompositeQuery", list);
				RequestDispatcher successView = req.getRequestDispatcher("/secondhand/listByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
		}
		
		if ("listByCompositeQuerySaled".equals(action)) { // 來自listByMySaled.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				
				// 以下的 if 區塊只對第一次執行時有效
				if (req.getParameter("whichPage") == null){
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				SecondHandService secondHandService = new SecondHandService();
				List<SecondHandVO> list  = secondHandService.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listByCompositeQuery", list); // 資料庫取出的list物件,存入request
//				session.setAttribute("listByCompositeQuery", list);
				RequestDispatcher successView = req.getRequestDispatcher("/secondhand/listByCompositeQuerySaled.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
		}
		
		if ("listByCompositeQueryWon".equals(action)) { // 來自listByMyWon.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				
				// 以下的 if 區塊只對第一次執行時有效
				if (req.getParameter("whichPage") == null){
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				SecondHandService secondHandService = new SecondHandService();
				List<SecondHandVO> list  = secondHandService.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listByCompositeQuery", list); // 資料庫取出的list物件,存入request
//				session.setAttribute("listByCompositeQuery", list);
				RequestDispatcher successView = req.getRequestDispatcher("/secondhand/listByCompositeQueryWon.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
		}

	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

	public static byte[] getByteArrayFromPart(Part part) throws IOException {
		InputStream is = part.getInputStream();
		byte[] buffer = new byte[is.available()];
		is.read(buffer);
		is.close();
		return buffer;
	}
}