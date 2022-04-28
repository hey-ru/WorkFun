package com.report.controller;

import java.io.IOException;
import java.sql.Timestamp;
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

import com.report.model.ReportService;
import com.report.model.ReportVO;

@WebServlet("/reportServlet")
@MultipartConfig
(
	 fileSizeThreshold   = 1024 * 1024 * 10,
		        maxFileSize         = 1024 * 1024 * 50,
		        maxRequestSize      = 1024 * 1024 * 100
)
public class ReportServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

//-----------------------------------------------------------------------	
	
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
				Integer report_type = Integer.valueOf(req.getParameter("report_type").trim());
				
				String title = req.getParameter("title");
//				String titleReg = "^[(\u4e00-\u9fa5_a-zA-Z0-9)]$";
//				if (title == null || title.trim().length() == 0) {
//					errorMsgs.put("title","標題: 請勿空白");
//				} else if(!title.trim().matches(titleReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.put("title","標題: 只能是中、英文字母、數字");
//	            }
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/report/addReport.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				System.out.println(errorMsgs);
				/***************************2.開始新增資料***************************************/
				ReportService repSvc= new ReportService();
				Part part = req.getPart("report_image");
//				repSvc.addReport(reporter, handler, content,
//						status, report_image, report_type , title);			
//				for(Part part :parts) {					
				byte[]report_image = repSvc.Image(part);
				System.out.println(report_image);
					ReportVO reportVO = repSvc.addReport(reporter,handler,content,
							status,report_image,report_type ,title);
//				}
//				System.out.println(part);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/report/listAllReport.jsp";
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
	if("getOne_forUpdate".equals(action)) {
		
		/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		Integer report_id = Integer.valueOf(req.getParameter("report_id"));
		
		/***************************2.開始查詢資料*****************************************/
		ReportService repSvc = new ReportService();
		ReportVO repVO = repSvc.getOneReport(report_id);
		/***************************3.查詢完成,準備轉交(Send the Success view)************/
		String param = "?report_id="  +repVO.getReport_id()+
	       "&title="  +repVO.getTitle()+
	       "&report_type="  +repVO.getReport_type()+
	       "&status="+repVO.getStatus()+
	       "&starttime="+repVO.getStarttime()+
	       "&updatetime="   +repVO.getUpdatetime()+
	       "&endtime=" +repVO.getEndtime()+
			"&reporter=" +repVO.getReporter()+
			"&handler=" +repVO.getHandler()+
			"&content=" +repVO.getContent()+
			"&report_image=" +repVO.getReport_image();
		
		String url = "/report/updateReport.jsp"+param;
		RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
		successView.forward(req, res);
	}
	  	  
	if ("update".equals(action)) { // 來自updateReport.jsp的請求
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer report_id = Integer.valueOf(req.getParameter("report_id").trim());
				System.out.println(report_id);
				Integer handler = Integer.valueOf(req.getParameter("handler").trim());

				Integer reporter = Integer.valueOf(req.getParameter("reporter").trim());
		
				Integer report_type = Integer.valueOf(req.getParameter("report_type").trim());
				System.out.println(report_type);
				String title = req.getParameter("title");
				System.out.println(title);
				String content = req.getParameter("content");
				System.out.println(content);
				
				ReportService repSvc = new ReportService();
				Part part = req.getPart("report_image");
				byte[]report_image = repSvc.Image(part);
				System.out.println(report_image);
				
				
//				String titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
//				if (title == null || title.trim().length() == 0) {
//					errorMsgs.put("title","標題: 請勿空白");
//				} else if(!title.trim().matches(titleReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.put("title","標題: 只能是中、英文字母、數字");
//	            }
			
//				String contentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
//				if (content == null || content.trim().length() == 0) {
//					errorMsgs.put("content","回報內容: 請勿空白");
//				} else if(!content.trim().matches(contentReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.put("content","回報內容: 只能是中、英文字母、數字");
//	            }
					
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/report/updateReport.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ReportVO reportVO = repSvc.updateReport(title,report_type,reporter,handler,content,report_image,report_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("reportVO", reportVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/report/listAllReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.put("Exception","修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/report/updateReport.jsp");
				failureView.forward(req, res);
			}
		}

}
}
