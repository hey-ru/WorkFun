package com.emp.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.google.gson.Gson;
import com.permissionmapping.model.PermissionMappingService;

/**
 * Servlet implementation class AjaxEmpServlet
 */
@WebServlet("/login/empServlet")
public class AjaxEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxEmpServlet() {
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
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");

		   if ("login".equals(action)) { // 來自addEmp.jsp的請求  
			   EmpService empSvc = new EmpService();
			   EmpVO empVO=new EmpVO();
				

//				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String empIds = (req.getParameter("empId"));
					
				
					if (empIds == null || empIds.trim().length() == 0) {
						empVO.setMessage("請輸入帳號");
						empVO.setSuccessful(false);
						 String empVOJSON=new Gson().toJson(empVO);
						   
					      res.getWriter().write(empVOJSON);
						return;
					}
				
					String empPassword = req.getParameter("empPassword").trim();
					if (empPassword == null || empPassword.trim().length() == 0) {
						empVO.setMessage("請輸入密碼");
						empVO.setSuccessful(false);
						 String empVOJSON=new Gson().toJson(empVO);
						   
					      res.getWriter().write(empVOJSON);
						return;
					}

					// Send the use back to the form, if there were errors
				
					
					/***************************2.開始檢查帳號密碼***************************************/
					Integer empId=Integer.valueOf(empIds);
					
//					empVO=empSvc.login(empId,empPassword);
					PermissionMappingService pmSrv=new PermissionMappingService();
					 List<Integer> empPm=pmSrv.getOneEmpPermissions(empId);
				
					 
//				EmpVO empVO=empSvc.login(empId,empPassword);
					  if (empSvc.login(empId,empPassword) == null) {
						
//							  errorMsgs.put("login","帳號密碼輸入錯誤");
//							   String url = "/login/login.jsp";
							  empVO.setMessage("密碼錯誤");
							empVO.setSuccessful(false);
//								RequestDispatcher successView = req.getRequestDispatcher(url); 
//								successView.forward(req, res);	
							String empVOJSON=new Gson().toJson(empVO);
							   
						      res.getWriter().write(empVOJSON);
							return;
					  }
					
					  else {
						 empVO=empSvc.login(empId,empPassword);
						empVO.setSuccessful(true);
						
						  session.setAttribute("empPm", empPm); //hint 特別標記 用來取得permission
					      session.setAttribute("empVO", empVO);   //*工作1: 才在session內做已經登入過的標識
					      String empVOJSON=new Gson().toJson(empVO);
					   
					      res.getWriter().write(empVOJSON);
//					      String location=(String)session.getAttribute("location");
//					      if (location != null) {
//					           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
//					           res.sendRedirect(location);            
//					           return;
//					         }
//					   					   
					    //  String url = "login.jsp"; foward
//					      res.sendRedirect(req.getContextPath() + "/home/home.jsp");
					      return;
					  }	
			}

			}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


