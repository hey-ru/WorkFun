package com.emp.controller;


import static com.util.String2SQLDate.strToDate;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
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

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.permission.model.PermissionService;
import com.permissionmapping.model.PermissionMappingService;
import com.util.JavaMail;
import static com.util.JavaMail.genAuthCode;





@MultipartConfig
@WebServlet("/empServlet")
public class EmpServlet extends HttpServlet {

	
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession session=req.getSession();
//		 Connection con=(Connection)session.getAttribute("con");
		res.setContentType("text/html;charset=UTF-8");
		
	
		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		
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
							.getRequestDispatcher("/back/backmain.jsp");
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
							.getRequestDispatcher("/back/backmain.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empId);
				EmpVO empVO = empSvc.getOneEmp(empId);
				if (empVO == null) {
					errorMsgs.put("empId","查無帳號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/backmain.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.put("無法取得資料",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/backmain.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
				/***************************1.接收請求參數****************************************/
				Integer empId = Integer.valueOf(req.getParameter("empId"));
				String resigndate;
				/***************************2.開始查詢資料****************************************/
				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empId);
				EmpVO empVO = empSvc.getOneEmp(empId);
				if(empVO.getResigndate()==null) {
					resigndate="";
					
					
				}				else {
					resigndate=String.valueOf(empVO.getResigndate());
					
				}
				
				
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?empId="  +empVO.getEmpId()+
						 "&depId="  +empVO.getDepId()+
						       "&empName="  +empVO.getEmpName()+
						       "&hiredate="+empVO.getHiredate()+
						       "&resigndate="+resigndate+
						       "&phone="    +empVO.getPhone()+
						       "&extension="   +empVO.getExtension()+
						       "&hobby="   +empVO.getHobby()+
						       "&skill="   +empVO.getSkill()+
						       "&extension="   +empVO.getExtension()+
						       "&mail="   +empVO.getMail()+
						       "&birthday=" +empVO.getBirthday();
				String url = "/back/update_emp_input.jsp"+param;
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
				
				String mail = (req.getParameter("mail"));
				String checkEmail = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@gmail.com";
				
				if (mail == null || mail.trim().length() == 0) {
					errorMsgs.put("mail","信箱請勿空白");
				}
				else if(!mail.trim().matches(checkEmail)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("mailcheck","請輸入xxx@gmail");
	            }
			
				
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("birthday","請輸入生日");
				}
				EmpService empSvc = new EmpService();

				Part empProfile=req.getPart("empProfile");
				java.sql.Date resigndate = null;

				
				
				
				byte[] headimg=empSvc.getByteArrayFromPart(empProfile);
				
				EmpVO newempVO = new EmpVO();
				newempVO.setEmpId(empId);
				newempVO.setEmpName(empName);
				newempVO.setDepId(depId);
				newempVO.setHiredate(hiredate);
				newempVO.setPhone(phone);
				newempVO.setExtension(extension);
				newempVO.setHobby(hobby);
				newempVO.setSkill(skill);
				if(empPassword != null ) {
					newempVO.setEmpPassword(empPassword);
				}
				
					newempVO.setEmpProfile(headimg);
					if(req.getParameter("resigndate")==null || req.getParameter("resigndate").trim().length()==0) {
						Date date=null;
						System.out.println(date);
						newempVO.setResigndate(date);
						newempVO.setEmpStatus((byte)1);
					}
					else {
						newempVO.setResigndate(java.sql.Date.valueOf(req.getParameter("resigndate")));
						newempVO.setEmpStatus((byte)2);
					}
			
				
				newempVO.setBirthday(birthday);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
			if(	empSvc.selectMail(mail,empId)==1) {
				errorMsgs.put("dupmail","信箱重複");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/update_emp_input.jsp");
				failureView.forward(req, res);
				return; //程式中斷
				
			}
			if(	empSvc.selectExtension(extension,empId)==1) {
				errorMsgs.put("dupextension","信箱重複");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/update_emp_input.jsp");
				failureView.forward(req, res);
				return; //程式中斷
				
			}
			
			
			newempVO.setMail(mail);
				
				
				
				
				/***************************2.開始修改資料*****************************************/
				
//				empSvc.updateEmp(newempVO);
				empSvc.updateEmp(newempVO);
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			
		}

if ("updateFront".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			EmpService empSvc = new EmpService();
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer empId = Integer.valueOf(req.getParameter("empId").trim());
				EmpVO oldempVO=empSvc.getOneEmp(empId);
				
				String empName = req.getParameter("empName");
				String empPassword = req.getParameter("empPassword");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empName == null || empName.trim().length() == 0) {
					errorMsgs.put("empName","員工姓名: 請勿空白");
				} else if(!empName.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("empName","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				Integer depId = Integer.valueOf(req.getParameter("depId").trim());

//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					errorMsgs.put("hiredate","請輸入日期");
//				}
				
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
				
				String mail = (req.getParameter("mail"));
				String checkEmail = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@gmail.com";
				
				if (mail == null || mail.trim().length() == 0) {
					errorMsgs.put("mail","信箱請勿空白");
				}
				else if(!mail.trim().matches(checkEmail)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("mailcheck","請輸入xxx@gmail格式");
	            }
				
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("birthday","請輸入生日");
				}
			

				Part empProfile=req.getPart("empProfile");
				
		
		
				
				
				
				byte[] headimg=empSvc.getByteArrayFromPart(empProfile);
				
				
				oldempVO.setEmpId(empId);
				oldempVO.setEmpName(empName);
				oldempVO.setDepId(depId);
//				oldempVO.setHiredate(hiredate);
				oldempVO.setPhone(phone);
			
				oldempVO.setHobby(hobby);
				oldempVO.setSkill(skill);
				if(empPassword != null ) {
					oldempVO.setEmpPassword(empPassword);
				}
				
				oldempVO.setEmpProfile(headimg);
			
			
				
				oldempVO.setBirthday(birthday);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/frontProfile.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				

				if(	empSvc.selectMail(mail,empId)==1) {
					errorMsgs.put("dupmail","信箱重複");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/frontProfile.jsp");
					failureView.forward(req, res);
					return; //程式中斷
					
				}
				if(	empSvc.selectExtension(extension,empId)==1) {
					errorMsgs.put("dupextension","分機重複");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/frontProfile.jsp");
					failureView.forward(req, res);
					return; //程式中斷
					
				}
				
				oldempVO.setMail(mail);
				oldempVO.setExtension(extension);
				
				/***************************2.開始修改資料*****************************************/
				
				EmpVO empVO=(EmpVO) session.getAttribute("empVO");
						
						empVO=oldempVO;
				
						session.setAttribute("empVO", empVO);
						
						
//				empSvc.updateEmp(newempVO);
				empSvc.updateEmp(oldempVO);
				
			
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/emp/frontProfile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			
		}

if ("selectByExtension".equals(action)) {
	Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
	req.setAttribute("errorMsgs", errorMsgs);
	String extension= req.getParameter("extension");
	String extensionReg = "^\\d{4}$";
	if (extension == null || extension.trim().length() == 0) {
		errorMsgs.put("extensionwhite","請勿空白");
	} else if(!extension.trim().matches(extensionReg)) { //以下練習正則(規)表示式(regular-expression)
		errorMsgs.put("extensionReg","請輸入4個數字");
    }
	
	if (!errorMsgs.isEmpty()) {
		RequestDispatcher failureView = req
				.getRequestDispatcher("/home/home.jsp");
		failureView.forward(req, res);
		return; //程式中斷
	}

	EmpService empService=new EmpService();
	List<EmpVO> list=empService.selectByExtension(extension);
	System.out.println(list.size());
	
	if(list.size()==0) {
//		PrintWriter out=res.getWriter();
//	
//
//		out.print("<script type='text/javascript'>alert('alert提示框2!');</script>");
//		out.flush();
		
	
		
		RequestDispatcher failureView = req
				.getRequestDispatcher("/home/home.jsp");
		failureView.forward(req, res);
	
		return; 
	}
	req.setAttribute("list", list);
	String url = "/home/selectExtension.jsp";
	RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
	successView.forward(req, res);
	

//	if (empName == null || empName.trim().length() == 0) {
//		errorMsgs.put("empName","員工姓名: 請勿空白");
//	} else if(!empName.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//		errorMsgs.put("empName","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//    }
	
	
	
}
if ("selectByEmpName".equals(action)) {
	Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
	req.setAttribute("errorMsgs", errorMsgs);
	String empName= req.getParameter("empName");
	
	if (empName == null || empName.trim().length() == 0) {
		errorMsgs.put("empNamewhite","請勿空白");
	} 
	
	if (!errorMsgs.isEmpty()) {
		RequestDispatcher failureView = req
				.getRequestDispatcher("/home/home.jsp");
		failureView.forward(req, res);
		return; //程式中斷
	}

	EmpService empService=new EmpService();
	List<EmpVO> list=empService.selectByEmpName(empName);
	System.out.println(list.size());
	
	if(list.size()==0) {
//		PrintWriter out=res.getWriter();
//	
//
//		out.print("<script type='text/javascript'>alert('alert提示框2!');</script>");
//		out.flush();
		
	
		
		RequestDispatcher failureView = req
				.getRequestDispatcher("/home/home.jsp");
		failureView.forward(req, res);
	
		return; 
	}
	req.setAttribute("list", list);
	String url = "/home/selectExtension.jsp";
	RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
	successView.forward(req, res);
	
	
	
	
	
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
				if ( extension.trim().length() != 4) {
					errorMsgs.put("extension","分機長度需為四");
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
				String password=req.getParameter("birthday").trim().replaceAll("\\pP","");//完全清除標點
				String afterpassword="Birth"+password;
				System.out.println(afterpassword);

				EmpVO newempVO = new EmpVO();
				newempVO.setEmpName(empName);
				newempVO.setDepId(depId);
				newempVO.setHiredate(hiredate);
				newempVO.setPhone(phone);
			
				newempVO.setHobby(hobby);
				newempVO.setSkill(skill);
				newempVO.setEmpPassword(afterpassword);
				newempVO.setEmpProfile(headimg);
				
				newempVO.setBirthday(birthday);
				
				
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				if(	empSvc.selectMail(mail)==1) {
					errorMsgs.put("dupmail","信箱重複");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/addEmp.jsp");
					failureView.forward(req, res);
					return; //程式中斷
					
				}
				if(	empSvc.selectExtension(extension)==1) {
					errorMsgs.put("dupextension","分機重複");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/addEmp.jsp");
					failureView.forward(req, res);
					return; //程式中斷
					
				}
				newempVO.setMail(mail);
				newempVO.setExtension(extension);
				/***************************2.開始新增資料***************************************/
				
//				empSvc.addEmp(empVO);
				empSvc.addEmp(newempVO);
			
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/addEmp.jsp");
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
//				empSvc.deleteEmp(empId);
				empSvc.deleteEmp(empId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("logout".equals(action)) { // 來自listAllEmp.jsp
			
	
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
					String mail = (req.getParameter("mail"));
				
					if (mail == null || mail.trim().length() == 0) {
						errorMsgs.put("mail","帳號請勿空白");
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
					
					EmpService empSvc = new EmpService();
//					EmpVO empVO=empSvc.login(empId,empPassword);
					EmpVO empVO=empSvc.login(mail,empPassword);
					  if (empVO == null) {
						
							  errorMsgs.put("login","帳號密碼輸入錯誤");
							   String url = "/login/login.jsp";
							   
								RequestDispatcher successView = req.getRequestDispatcher(url); 
								successView.forward(req, res);	
								
							return;
					  }
					
					  else {
						 
					      session.setAttribute("empVO", empVO);   //*工作1: 才在session內做已經登入過的標識
					      String location=(String)session.getAttribute("location");
					      if (location != null) {
					           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					           res.sendRedirect(location);            
					           return;
					         }
					      
					      String url = "/back/backmain.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
							successView.forward(req, res);	
						
							return;
					  }
	
			}
		
		
		
		   if ("frontLogin".equals(action)) { // 來自addEmp.jsp的請求  
				
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);

//				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String mail = (req.getParameter("mail"));
					String checkEmail = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@gmail.com";
					
					if (mail == null || mail.trim().length() == 0) {
						errorMsgs.put("mail","帳號請勿空白");
					}
					else if(!mail.trim().matches(checkEmail)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.put("mailcheck","請輸入gmail帳號");
		            }
				
					
					
					String checkPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

					
					
					
					
					String empPassword = req.getParameter("empPassword").trim();
					if (empPassword == null || empPassword.trim().length() == 0) {
						errorMsgs.put("empPassword","密碼請勿空白");
					}
					else if(!empPassword.trim().matches(checkPassword)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.put("checkPassword","請輸入包含英文大小寫和數字，最少8個字最多16個字");
		            }
					
					
				

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/login/login.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始檢查帳號密碼***************************************/
					
					EmpService empSvc = new EmpService();
					EmpVO empVO=empSvc.login(mail,empPassword);
				
				
					 
//				EmpVO empVO=empSvc.login(empId,empPassword);
					  if (empVO == null) {
						
							  errorMsgs.put("login","帳號密碼輸入錯誤");
							   String url = "/login/login.jsp";
							   
								RequestDispatcher successView = req.getRequestDispatcher(url); 
								successView.forward(req, res);	
								
							return;
					  }
					
					  else {
							PermissionMappingService pmSrv=new PermissionMappingService();
							 List<Integer> empPm=pmSrv.getOneEmpPermissions(empVO.getEmpId());
						  session.setAttribute("empPm", empPm); //hint 特別標記 用來取得permission
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
						
							return;
					  }
	
			}
		   
		   
		   
		   
		   
		   

		   if ("changePermission".equals(action)) { // 來自addEmp.jsp的請求  
			   
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
			   
			PermissionMappingService pmmSvc=new PermissionMappingService();
			   PermissionService pmSvc=new PermissionService();
//			List<String> allPermissionList=pmSvc.getAllPermissionName();
			  String[] newpm=req.getParameterValues("permissionId");
			  Integer empId=Integer.valueOf(req.getParameter("empId"));
			  
			 List<Integer> oldpm =pmmSvc.getOneEmpPermissions(empId);
		//	 System.out.println("原本權限"+oldpm);
	         
			 
			 
			 if(newpm==null) {
				 
				 if(oldpm==null) {
					 
					// System.out.println("先後都沒有權限");
					 String url = "/back/permission.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
						successView.forward(req, res);	
					
						return; 
					 
				 }
				 else {
					 
				
				 
				 
				// System.out.println("消除全部權限");
				 
				  Iterator<Integer> it=oldpm.iterator();
				  while (it.hasNext()) {
					  Integer aInteger=it.next();
				 pmmSvc.deleteEmpPm(empId,aInteger);
				  }
				 
				   String url = "/back/permission.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);	
				
					return;
				  }
//					else if(newpm==null  && oldpm==null && oldpm.size()==0)			 
				 
				  }
			 
		
			 else {
			
				 
				 
				  for(int j=0; j < newpm.length; j++){
					
					  Integer pmId=Integer.valueOf(newpm[j]);
					  if(!oldpm.contains(pmId)) {
						  pmmSvc.addpmId2emp(empId,pmId);
						//  System.out.println("加入權限"+pmId);
					  }
					  else {
					//	System.out.println("權限重複"+pmId);
					}
					 
							
						}		
				  
				  Iterator<Integer> it=oldpm.iterator();
				  while (it.hasNext()) {
					Integer aInteger=it.next();
				String aString=String.valueOf(aInteger);
					
			if(!Arrays.asList(newpm).contains(aString)) {
				pmmSvc.deleteEmpPm(empId,aInteger);
				//System.out.println("刪除權限"+aInteger);
			}
					
					
					
				}
				  

				  
				  
				  
				  
				  
				  
			      String url = "/back/permission.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);	
					return;
			            
			            
			            
			        
			}
			 
			 
			 
			 
			 
			 
			    
		            
		            
		        }

		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   //測試
		   //
		   
		   
//		   if ("testTimer".equals(action)) { // 來自addEmp.jsp的請求  
//			   
//
//				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//				
//				
//				req.setAttribute("errorMsgs", errorMsgs);
//			
//				
//				EmpVO empVO=new EmpService().getOneEmp(Integer.valueOf(req.getParameter("empId")));
//				String aString="改變員工狀態";
//					
//				TimerActionMedthod timerActionMedthod=new TimerActionMedthod();	
//					
//					timerActionMedthod.timerTest2(empVO,aString,2,10);
//					
//
//				      String url = "/back/backtest.jsp";
//						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//						successView.forward(req, res);	
//						return;
//				
//				    
//		        }
//			   
			   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
					
		   if ("listEmps_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					
					/***************************1.將輸入資料轉為Map**********************************/ 
					//採用Map<String,String[]> getParameterMap()的方法 
					//注意:an immutable java.util.Map 
					//Map<String, String[]> map = req.getParameterMap();
					
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					
					// 以下的 if 區塊只對第一次執行時有效
					if (req.getParameter("whichPage") == null){
						Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
						session.setAttribute("map",map1);
						map = map1;
					} 
					
					/***************************2.開始複合查詢***************************************/
					EmpService empSvc = new EmpService();
					List<EmpVO> list  = empSvc.getAll(map);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("listEmps_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
					RequestDispatcher successView = req.getRequestDispatcher("/back/empCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/empCompositeQuery.jsp");
					failureView.forward(req, res);
				}
			}
		   
		   
		   
		   if ("forgotpassword".equals(action)) { // 來自addEmp.jsp的請求  
			   

				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				if (req.getParameter("birthday") == null || req.getParameter("birthday").trim().length() == 0) {
					errorMsgs.put("birthday","生日請勿空白");
				}
				String mail = (req.getParameter("mail"));
				String checkEmail = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@gmail.com";
				
				if (mail == null || mail.trim().length() == 0) {
					errorMsgs.put("mail","信箱請勿空白");
				}
				else if(!mail.trim().matches(checkEmail)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("mailcheck","請輸入xxx@gmail格式");
	            }
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/login/forgot.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				
				
				
	java.sql.Date birthday=strToDate(req.getParameter("birthday"));
System.out.println(birthday);
			
				EmpService empSvc = new EmpService();
				EmpVO empVO=empSvc.getbymailandbirthday(mail, birthday);
			   if(empVO==null) {
				   errorMsgs.put("forgot","信箱或生日錯誤");
				   
				   RequestDispatcher successView = req.getRequestDispatcher("/login/forgot.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					return;
			   }
			  
			   else { 
				   
			
					JavaMail javaMail=new JavaMail();
					javaMail.sendMail();
				
				  
				   javaMail.setRECIPIENT(mail);
				   String authCode=genAuthCode();
				String text="您的驗證碼為["+authCode+"]請在修改頁面重新設定您的密碼";
				   javaMail.setTXT(text);
				   javaMail.sendMail();
			session.setAttribute("authCode", authCode);
				 
//				   
//				   
				   String param = "?empId="+empVO.getEmpId();
				//   RequestDispatcher successView = req.getRequestDispatcher("/login/verify.jsp"); 
				   RequestDispatcher successView = req.getRequestDispatcher("/login/verify.jsp"+param); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					return;
			   }
//		
		   }
		   
		   
		   
		   if ("verifyAuthcode".equals(action)) { // 來自addEmp.jsp的請求  
			   

				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
			String authCode=(String)session.getAttribute("authCode");
				String inputauthCode=req.getParameter("authCode");
				if(authCode.equals(inputauthCode)) {
					
					   RequestDispatcher successView = req.getRequestDispatcher("/login/reset.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
						successView.forward(req, res);
						return;
					
					
				}
				else {
	
				 errorMsgs.put("authCode","驗證碼錯誤");
		   
		   
				   RequestDispatcher successView = req.getRequestDispatcher("/login/verify.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					return;
	
				}
		        }
		   
		   
		   
		   
		   if ("forgotchangepassword".equals(action)) {

				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
		Integer empId=Integer.valueOf(req.getParameter("empId"));
		
				String newpassword1=req.getParameter("newpassword1");
				EmpService empSvc=new EmpService();
				String checkPassword="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
				String newpassword2=req.getParameter("newpassword2");
				EmpVO empVO=empSvc.getOneEmp(empId);
				if (newpassword1 == null || newpassword1.trim().length() == 0 || newpassword2 == null || newpassword2.trim().length() == 0) {
					errorMsgs.put("password","密碼請勿空白");
				}
				else if(!newpassword1.trim().matches(checkPassword)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("checkPassword","請輸入包含英文大小寫和數字，最少8個字");
			    }
				else if(!newpassword2.trim().matches(checkPassword)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("checkPassword","請輸入包含英文大小寫和數字，最少8個字");
			    }
				else if (!newpassword1.equals(newpassword2)) {
					errorMsgs.put("checkPassword","密碼不一致");
				}
			
				
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/login/reset.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				empVO.setEmpPassword(newpassword2);
				empSvc.updateEmp(empVO);
				
				
				
				  RequestDispatcher successView = req.getRequestDispatcher("/login/login.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
				
			
		        }
		   
		   
		   if ("frontchangepassword".equals(action)) { // 來自addEmp.jsp的請求  
			   EmpService empSvc=new EmpService();

				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
	EmpVO sessEmpVO=(EmpVO)session.getAttribute("empVO");
		
	String nowpassword=req.getParameter("nowpassword");

	String checkPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
	
	if(!sessEmpVO.getEmpPassword().equals(nowpassword)) {
		errorMsgs.put("nowpassword","密碼錯誤");
	}
	
	
	
	
	
	
	
	
	

	
	
	
	
	
				String newpassword1=req.getParameter("newpassword1");
				
			
				String newpassword2=req.getParameter("newpassword2");
//				EmpVO empVO=empSvc.getOneEmp(empId);
				if (newpassword1 == null || newpassword1.trim().length() == 0 || newpassword2 == null || newpassword2.trim().length() == 0) {
					errorMsgs.put("password","密碼請勿空白");
				}
				else if(!newpassword1.trim().matches(checkPassword)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("checkPassword","請輸入包含英文大小寫和數字，最少8個字");
			    }
				else if(!newpassword2.trim().matches(checkPassword)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("checkPassword","請輸入包含英文大小寫和數字，最少8個字");
			    }
				else if (!newpassword1.equals(newpassword2)) {
					errorMsgs.put("comparepassword","密碼不一致");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/frontProfile.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				
				
				
				EmpVO empVO=new EmpVO();
				empVO.setEmpId(sessEmpVO.getEmpId());
//				sessEmpVO.setEmpPassword(newpassword2);
				empVO.setEmpPassword(newpassword2);
				empSvc.updateEmp(sessEmpVO);
				
				
				
				  RequestDispatcher successView = req.getRequestDispatcher("/emp/frontProfile.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
				
			
		        }
		   
		   
		   
		   
		   
		   
		   
		   
		   
	}

					
				
				
					
					
				

					// Send the use back to the form, if there were errors
				
					
				
					 
//				EmpVO empVO=empSvc.login(empId,empPassword);
					
				
	
			}
		   
		
	

