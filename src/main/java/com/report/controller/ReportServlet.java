package com.report.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
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
import javax.servlet.http.Part;

import com.report.model.ReportService;
import com.report.model.ReportVO;

@WebServlet("/report/ReportServlet")
public class ReportServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	System.out.println("post");
	if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

	try {
	/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		String str = req.getParameter("report_id");
		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.add("請輸入回報編號");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/report/listAllReport.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		}
		Integer report_id = null;
		try {
			report_id = Integer.parseInt(str);
		} catch (Exception e) {
			errorMsgs.add("回報編號格式不正確");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/report/listAllReport.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		}
	/***************************2.開始查詢資料*****************************************/
	 ReportService repSvc = new ReportService();
	 ReportVO reportVO = repSvc.getOneReport(report_id);
	 
	 if (reportVO == null) {
			errorMsgs.add("查無資料");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/report/listAllReport.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		}
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*************/
		req.setAttribute("reportVO", reportVO); // 資料庫取出的empVO物件,存入req
		String url = "/report/listAllReport.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
		successView.forward(req, res);

		/***************************其他可能的錯誤處理*************************************/
	} catch (Exception e) {
		errorMsgs.add("無法取得資料:" + e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/report/listAllReport.jsp");
		failureView.forward(req, res);
	}
}
	
	if ("getOne".equals(action)) { // 來自listAllReport.jsp的請求

		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			/***************************1.接收請求參數****************************************/
			Integer report_id = Integer.valueOf(req.getParameter("report_id"));
			
			/***************************2.開始查詢資料****************************************/
			ReportService repSvc = new ReportService();
			ReportVO repVO = repSvc.getOneReport(report_id);
							
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
//			String param = "?report_id="  +repVO.getReport_id()+
//					       "&title="  +repVO.getTitle()+
//					       "&report_type="  +repVO.getReport_type()+
//					       "&status="+repVO.getStatus()+
//					       "&starttime="+repVO.getStarttime()+
//					       "&updatetime="   +repVO.getUpdatetime()+
//					       "&endtime=" +repVO.getEndtime()+
//							"&reporter=" +repVO.getReporter()+
//							"&handler=" +repVO.getHandler()+
//							"&content=" +repVO.getContent()+
//							"&report_image=" +repVO.getReport_image();
			
			String url = "/report/listOneReport.jsp";
			req.setAttribute("reportVO", repVO);
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listOneReport.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			System.out.println(e.getMessage());
			errorMsgs.put("無法取得要修改的資料",e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/report/listAllReport.jsp");
			failureView.forward(req, res);
		}
	}
	
	  if ("insert".equals(action)) { // 來自addReport.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer reporter = Integer.valueOf(req.getParameter("reporter").trim());
				Integer handler = Integer.valueOf(req.getParameter("handler").trim());
				String content = req.getParameter("content");
//				String contentReg = "^[(\u4e00-\u9fa5_a-zA-Z0-9)]$";
//				if (content == null || content.trim().length() == 0) {
//					errorMsgs.put("content","回報內容: 請勿空白");
//				} else if(!content.trim().matches(contentReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.put("content","回報內容: 只能是中、英文字母、數字");
//	            }
				Integer status = Integer.valueOf(req.getParameter("status").trim());
				byte[] report_image=null;
				System.out.println(report_image);
				Integer report_type = Integer.valueOf(req.getParameter("report_type").trim());
				
				String title = req.getParameter("title");
//				String titleReg = "^[(\u4e00-\u9fa5_a-zA-Z0-9)]$";
//				if (title == null || title.trim().length() == 0) {
//					errorMsgs.put("title","標題: 請勿空白");
//				} else if(!title.trim().matches(titleReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.put("title","標題: 只能是中、英文字母、數字");
//	            }
				System.out.println(title);
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/report/addReport.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				System.out.println(errorMsgs);
				/***************************2.開始新增資料***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);
				//Collection<Part> parts = req.getParts();
				
				ReportService repSvc= new ReportService();
				repSvc.addReport(reporter, handler, content,
						status, report_image, report_type , title);
//				
//				for(Part part :parts) {
//					
//					ReportVO reportVO = repSvc.addReport(reporter,handler,content,
//							status,report_image,report_type ,title);
//				}
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/report/listAllReport.jsp";
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/report/addReport.jsp");
				failureView.forward(req, res);
			}
		}
}
}
