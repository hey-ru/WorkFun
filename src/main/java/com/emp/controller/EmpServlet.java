package com.emp.controller;

import java.io.*;
import java.sql.Connection;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.emp.model.*;
@MultipartConfig
@WebServlet("/empServlet")
public class EmpServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ServletContext context=getServletContext();
	Connection con=(Connection)context.getAttribute("con");

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("empId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("empId","請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer empId = null;
				try {
					empId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("empno","員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empId,con);
				if (empVO == null) {
					errorMsgs.put("login","查無帳號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.put("無法取得資料",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
				/***************************1.接收請求參數****************************************/
				Integer empId = Integer.valueOf(req.getParameter("empId"));
				
				/***************************2.開始查詢資料****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empId,con);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?empId="  +empVO.getEmpId()+
						 "&depId="  +empVO.getDepId()+
						       "&empName="  +empVO.getEmpName()+
						       "&hiredate="+empVO.getHiredate()+
						       "&resigndate="+empVO.getResigndate()+
						       "&phone="    +empVO.getPhone()+
						       "&extension="   +empVO.getExtension()+
						       "&hobby="   +empVO.getHobby()+
						       "&skill="   +empVO.getSkill()+
						       "&extension="   +empVO.getExtension()+
						       "&mail="   +empVO.getMail()+
						       "&birthday=" +empVO.getBirthday();
				String url = "/emp/update_emp_input.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			 
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer empId = Integer.valueOf(req.getParameter("empId").trim());
				String empName = req.getParameter("empName");
				String empPassword = req.getParameter("empPassword");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empName == null || empName.trim().length() == 0) {
					errorMsgs.put("empName","員工姓名: 請勿空白");
				} else if(!empName.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("empName","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				Integer depId = Integer.valueOf(req.getParameter("depId").trim());

				java.sql.Date hiredate = null;
				try {
					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("hiredate","請輸入日期");
				}
				
//				if (req.getParameter("hiredate").trim() == null || req.getParameter("hiredate").trim().trim().length() == 0) {
//					errorMsgs.put("hiredate","雇用日期請勿空白");
//				}
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					errorMsgs.put("hiredate","請輸入雇用日期");
//				}
				
				
				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.put("phone","手機請勿空白");
				}
				String extension = req.getParameter("extension").trim();
				if (extension == null || extension.trim().length() == 0) {
					errorMsgs.put("extension","分機請勿空白");
				}
				String hobby = req.getParameter("hobby").trim();
				String skill = req.getParameter("skill").trim();
			
				
//				String empProfile = req.getParameter("empProfile");
				
				String mail = req.getParameter("mail").trim();
				if (mail == null || mail.trim().length() == 0) {
					errorMsgs.put("mail","信箱請勿空白");
				}
				
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("birthday","請輸入生日");
				}
				EmpService empSvc = new EmpService();

				Part empProfile=req.getPart("empProfile");
				
		String fileName=empSvc.getFileNameFromPart(empProfile);
		
				
				
				
				byte[] headimg=empSvc.getByteArrayFromPart(empProfile);
				
				EmpVO oldempVO = new EmpVO();
				oldempVO.setEmpId(empId);
				oldempVO.setEmpName(empName);
				oldempVO.setDepId(depId);
				oldempVO.setHiredate(hiredate);
				oldempVO.setPhone(phone);
				oldempVO.setExtension(extension);
				oldempVO.setHobby(hobby);
				oldempVO.setSkill(skill);
				if(empPassword != null ) {
					oldempVO.setEmpPassword(empPassword);
				}
				if (fileName == null || fileName.trim().length() == 0) {
				
				}
				else {
					oldempVO.setEmpProfile(headimg);
				}
				
			
				oldempVO.setMail(mail);
				oldempVO.setBirthday(birthday);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				empSvc.updateEmp(oldempVO,con);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
        	
        	EmpService empSvc = new EmpService();
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String empName = req.getParameter("empName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empName == null || empName.trim().length() == 0) {
					errorMsgs.put("empName","員工姓名: 請勿空白");
				} else if(!empName.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("empName","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				Integer depId = Integer.valueOf(req.getParameter("depId").trim());
				
				Part empProfile=req.getPart("empProfile");
				byte[] headimg=empSvc.getByteArrayFromPart(empProfile);
				
				
				
				java.sql.Date hiredate = null;
				
				if (req.getParameter("hiredate").trim() == null || req.getParameter("hiredate").trim().trim().length() == 0) {
					errorMsgs.put("extension","雇用日期請勿空白");
				}
				try {
					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("hiredate","請輸入雇用日期");
				}
				
				
				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.put("phone","手機請勿空白");
				}
				String extension = req.getParameter("extension").trim();
				if (extension == null || extension.trim().length() == 0) {
					errorMsgs.put("extension","分機請勿空白");
				}
				String hobby = req.getParameter("hobby").trim();
				String skill = req.getParameter("skill").trim();
			
				
//				String empProfile = req.getParameter("empProfile");
				
				String mail = req.getParameter("mail").trim();
				if (mail == null || mail.trim().length() == 0) {
					errorMsgs.put("mail","信箱請勿空白");
				}
				
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("birthday","請輸入生日");
				}
				

				EmpVO empVO = new EmpVO();
				empVO.setEmpName(empName);
				empVO.setDepId(depId);
				empVO.setHiredate(hiredate);
				empVO.setPhone(phone);
				empVO.setExtension(extension);
				empVO.setHobby(hobby);
				empVO.setSkill(skill);
				empVO.setEmpPassword(req.getParameter("hiredate"));
			empVO.setEmpProfile(headimg);
				empVO.setMail(mail);
				empVO.setBirthday(birthday);
				
				
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				empSvc.addEmp(empVO,con);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/addEmp.jsp");
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
				empSvc.deleteEmp(empId,con);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("logout".equals(action)) { // 來自listAllEmp.jsp
			
			  HttpSession session = req.getSession();
		      session.removeAttribute("empVO"); 
		      
String url = "/login/login.jsp";
RequestDispatcher successView = req.getRequestDispatcher(url); 
successView.forward(req, res);	
return;
		
		}
		
		
		
		
		
		   if ("login".equals(action)) { // 來自addEmp.jsp的請求  
				
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);

//				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String empIds = (req.getParameter("empId"));
				
					if (empIds == null || empIds.trim().length() == 0) {
						errorMsgs.put("empId","帳號請勿空白");
					}
				
					
					


					
					
					
					
					String empPassword = req.getParameter("empPassword").trim();
					if (empPassword == null || empPassword.trim().length() == 0) {
						errorMsgs.put("empPassword","密碼請勿空白");
					}
					
					
				

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/login/login.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始檢查帳號密碼***************************************/
					Integer empId=Integer.valueOf(empIds);
					EmpService empSvc = new EmpService();
					 
					EmpVO empVO=empSvc.login(empId,empPassword,con);
					  if (empVO == null) {
						
							  errorMsgs.put("login","帳號密碼輸入錯誤");
							   String url = "/login/login.jsp";
							   
								RequestDispatcher successView = req.getRequestDispatcher(url); 
								successView.forward(req, res);	
								
							return;
					  }
					
					  else {
						  HttpSession session = req.getSession();
					      session.setAttribute("empVO", empVO);   //*工作1: 才在session內做已經登入過的標識
					      String location=(String)session.getAttribute("location");
					      if (location != null) {
					           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					           res.sendRedirect(location);            
					           return;
					         }
					      
					      String url = "/emp/backmain.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
							successView.forward(req, res);	
							System.out.println(empVO);
							return;
					  }
	
			}
		
		
		
		   if ("frontLogin".equals(action)) { // 來自addEmp.jsp的請求  
				
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);

//				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String empIds = (req.getParameter("empId"));
				
					if (empIds == null || empIds.trim().length() == 0) {
						errorMsgs.put("empId","帳號請勿空白");
					}
				
					
					


					
					
					
					
					String empPassword = req.getParameter("empPassword").trim();
					if (empPassword == null || empPassword.trim().length() == 0) {
						errorMsgs.put("empPassword","密碼請勿空白");
					}
					
					
				

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/login/login.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始檢查帳號密碼***************************************/
					Integer empId=Integer.valueOf(empIds);
					EmpService empSvc = new EmpService();
					 
					EmpVO empVO=empSvc.login(empId,empPassword,con);
					  if (empVO == null) {
						
							  errorMsgs.put("login","帳號密碼輸入錯誤");
							   String url = "/login/login.jsp";
							   
								RequestDispatcher successView = req.getRequestDispatcher(url); 
								successView.forward(req, res);	
								
							return;
					  }
					
					  else {
						  HttpSession session = req.getSession();
					      session.setAttribute("empVO", empVO);   //*工作1: 才在session內做已經登入過的標識
					      String location=(String)session.getAttribute("location");
					      if (location != null) {
					           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					           res.sendRedirect(location);            
					           return;
					         }
					      
					      String url = "/home/home.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
							successView.forward(req, res);	
							System.out.println(empVO);
							return;
					  }
	
			}
		   
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
