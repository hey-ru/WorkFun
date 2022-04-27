package com.secondHand.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		System.out.println(action);

//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("empno");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.put("empno","請輸入員工編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer empno = null;
//				try {
//					empno = Integer.valueOf(str);
//				} catch (Exception e) {
//					errorMsgs.put("empno","員工編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);
//				if (empVO == null) {
//					errorMsgs.put("empno","查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.put("無法取得資料",e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}

//		if ("getOne_For_Update".equals(action)) { // 來自secondHandHome.jsp的請求
//
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				Integer empno = Integer.valueOf(req.getParameter("empno"));
//				
//				/***************************2.開始查詢資料****************************************/
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				String param = "?empno="  +empVO.getEmpno()+
//						       "&ename="  +empVO.getEname()+
//						       "&job="    +empVO.getJob()+
//						       "&hiredate="+empVO.getHiredate()+
//						       "&sal="    +empVO.getSal()+
//						       "&comm="   +empVO.getComm()+
//						       "&deptno=" +empVO.getDeptno();
//				String url = "/emp/update_emp_input.jsp"+param;
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.put("無法取得要修改的資料",e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}

//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer empno = Integer.valueOf(req.getParameter("empno").trim());
//				
//				String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.put("ename","員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.put("ename","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//				String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.put("job","職位請勿空白");
//				}
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					errorMsgs.put("hiredate","請輸入日期");
//				}
//				
//				Double sal = null;
//				try {
//					sal = Double.valueOf(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("sal","薪水請填數字");
//				}
//				
//				Double comm = null;
//				try {
//					comm = Double.valueOf(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("comm","獎金請填數字");
//				}
//				
//				Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.updateEmp(empno, ename, job, hiredate, sal,comm, deptno);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.put("修改資料失敗",e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/update_emp_input.jsp");
//				failureView.forward(req, res);
//			}
//		}

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
				
				SecondHandVO secondHandVO = new SecondHandVO();
				secondHandVO.setImg1(img1);
				secondHandVO.setImg2(img2);
				secondHandVO.setImg3(img3);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/secondhand/addSecondHand.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				SecondHandService secondHandService = new SecondHandService();
				secondHandService.addSecondHand(saler, name, bottom_price, top_price, start_time, end_time, img1, img2, img3);

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

//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer empno = Integer.valueOf(req.getParameter("empno"));
//				
//				/***************************2.開始刪除資料***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		// System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		// System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

	public static byte[] getByteArrayFromPart(Part part) throws IOException {
		InputStream fis = part.getInputStream();
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}