package com.emp.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.google.gson.Gson;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/emp/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=req.getSession();
		EmpService empSvc=new EmpService();
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
//		String action = req.getParameter("action");
	
		
	
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
	
		EmpVO empVO=(EmpVO)session.getAttribute("empVO");
		
			
			String empName = req.getParameter("empName");
			String empPassword = req.getParameter("empPassword");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (empName == null || empName.trim().length() == 0) {
				empVO.setMessage("員工姓名請勿空白");
				empVO.setSuccessful(false);
				String empVOJSON=new Gson().toJson(empVO);
				   
			      res.getWriter().write(empVOJSON);
				return;
				
			} else if(!empName.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
				empVO.setMessage("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				empVO.setSuccessful(false);
				String empVOJSON=new Gson().toJson(empVO);
				   
			      res.getWriter().write(empVOJSON);
			return;
				
            }
			
			
			Integer depId = Integer.valueOf(req.getParameter("depId").trim());

//			java.sql.Date hiredate = null;
//			try {
//				hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//			} catch (IllegalArgumentException e) {
//				errorMsgs.put("hiredate","請輸入日期");
//			}
			
//			if (req.getParameter("hiredate").trim() == null || req.getParameter("hiredate").trim().trim().length() == 0) {
//				errorMsgs.put("hiredate","雇用日期請勿空白");
//			}
//			try {
//				hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//			} catch (IllegalArgumentException e) {
//				errorMsgs.put("hiredate","請輸入雇用日期");
//			}
			
			
			String phone = req.getParameter("phone").trim();
			if (phone == null || phone.trim().length() == 0) {
				empVO.setMessage("手機請勿空白");
				empVO.setSuccessful(false);
				String empVOJSON=new Gson().toJson(empVO);
				   
			      res.getWriter().write(empVOJSON);
			return;
			}
			String extension = req.getParameter("extension").trim();
			if (extension == null || extension.trim().length() == 0) {
				empVO.setMessage("分機請勿空白");
				empVO.setSuccessful(false);
				String empVOJSON=new Gson().toJson(empVO);
				   
			      res.getWriter().write(empVOJSON);
			return;
			}
			String hobby = req.getParameter("hobby").trim();
			String skill = req.getParameter("skill").trim();
		
			
//			String empProfile = req.getParameter("empProfile");
			
			String mail = req.getParameter("mail").trim();
			if (mail == null || mail.trim().length() == 0) {
				empVO.setMessage("信箱請勿空白");
				empVO.setSuccessful(false);
				String empVOJSON=new Gson().toJson(empVO);
				   
			      res.getWriter().write(empVOJSON);
				return;
			}
			
			java.sql.Date birthday = null;
			try {
				birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
			} catch (IllegalArgumentException e) {
				empVO.setMessage("請輸入生日");
				empVO.setSuccessful(false);
				String empVOJSON=new Gson().toJson(empVO);
				   
			      res.getWriter().write(empVOJSON);
				return;
			}
		

			
		
			empVO.setEmpName(empName);
			empVO.setDepId(depId);

			empVO.setPhone(phone);
			empVO.setExtension(extension);
			empVO.setHobby(hobby);
			empVO.setSkill(skill);
			if(empPassword != null ) {
				empVO.setEmpPassword(empPassword);
			}
			

		
		
			empVO.setMail(mail);
			empVO.setBirthday(birthday);
			// Send the use back to the form, if there were errors
		
			
			/***************************2.開始修改資料*****************************************/
			
		
					
			
					
					session.setAttribute("empVO", empVO);
					
					
//			empSvc.updateEmp(newempVO);
			empSvc.updateEmp(empVO);
			String empVOJSON=new Gson().toJson(empVO);
			empVO.setSuccessful(true);
		      res.getWriter().write(empVOJSON);
		
			
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
//			req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
		return;

		
		
		
		
		
		
		
		
		
		
	}

}
