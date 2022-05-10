package com.dep.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dep.model.DepService;
import com.dep.model.DepVO;
import com.emp.model.*;
@WebServlet("/dep/dep.do")
public class DepServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("depId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("depId","請輸入部門編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/dep/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer depId = null;
				try {
					depId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("depId","部門編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/dep/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				DepService empSvc = new DepService();
				DepVO depVO = empSvc.getOneDep(depId);
				if (depVO == null) {
					errorMsgs.put("depno","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/dep/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("depVO", depVO); // 資料庫取出的empVO物件,存入req
				String url = "/dep/listOneDep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.put("無法取得資料",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dep/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer depId = Integer.valueOf(req.getParameter("depId"));
				
				/***************************2.開始查詢資料****************************************/
				DepService depSvc = new DepService();
				DepVO depVO = depSvc.getOneDep(depId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?depId="  +depVO.getDepId()+
						 "&depName="  +depVO.getDepName();
						      
				String url = "/dep/update_dep_input.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("無法取得要修改的資料",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dep/listAllDep.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer depId = Integer.valueOf(req.getParameter("depId").trim());
				String depName = req.getParameter("depName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (depName == null || depName.trim().length() == 0) {
					errorMsgs.put("depName","部門名稱: 請勿空白");
				} else if(!depName.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("depName","部門名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				DepVO olddepVO = new DepVO();
				
				olddepVO.setDepName(depName);
				olddepVO.setDepId(depId);
				
				
//			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/dep/update_dep_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				DepService depSvc = new DepService();
				depSvc.updateDep(olddepVO);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/dep/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.put("修改資料失敗",e.getMessage());
			
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dep/update_dep_input.jsp");
				failureView.forward(req, res);
				
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String depName = req.getParameter("depName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (depName == null || depName.trim().length() == 0) {
					errorMsgs.put("empName","員工姓名: 請勿空白");
				} else if(!depName.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("empName","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
			
				

				DepVO depVO = new DepVO();
				depVO.setDepName(depName);
				
				
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/dep/addDep.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				DepService depSvc = new DepService();
				depSvc.insertDep(depVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/dep/listAllDep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dep/addDep.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer empId = Integer.valueOf(req.getParameter("empId"));
				
				/***************************2.開始刪除資料***************************************/
				EmpService empSvc = new EmpService();
				empSvc.deleteEmp(empId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dep/listAllDep.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
