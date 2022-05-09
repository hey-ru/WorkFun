package com.report.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

import com.report.model.ReportService;
import com.report.model.ReportVO;
import com.report_comment.model.Report_CommentVO;

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
			System.out.println(report_id);
			/***************************2.開始查詢資料****************************************/
			ReportService repSvc = new ReportService();
			ReportVO repVO = repSvc.getComment(report_id);
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
				System.out.println(reporter);
				Integer handler = Integer.valueOf(req.getParameter("handler").trim());
				System.out.println(handler);
				String content = req.getParameter("content");
				System.out.println(content);
				String contentReg = "^[(\u4e00-\u9fa5)_a-zA-Z0-9_\\n\\s\\(\\)]*$";
				if (content == null || content.trim().length() == 0) {
					errorMsgs.put("content","回報內容: 請勿空白");
				} else if(!content.trim().matches(contentReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("content","回報內容: 只能是中、英文字母、數字! 不要加符號!!");
	            }
				Integer report_type = Integer.valueOf(req.getParameter("report_type").trim());
				
				String title = req.getParameter("title");

				String titleReg = "^[(\u4e00-\u9fa5)_a-zA-Z0-9_)]*$";
				if (title == null || title.trim().length() == 0) {
					errorMsgs.put("title","標題: 請勿空白");
				} else if(!title.trim().matches(titleReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("title","標題: 只能是中、英文字母、數字");
	            }
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/report/addReport.jsp");
					failureView.forward(req, res);
					return;
				}
//				System.out.println(errorMsgs);
				byte[] report_image = null;
				/***************************2.開始新增資料***************************************/
				ReportService repSvc= new ReportService();
				Part part = req.getPart("report_image");
				String filename1 = getFileNameFromPart(part);
				if (filename1!= null && part.getContentType()!=null) {
					report_image = getByteArrayFromPart(part);
				}
//				repSvc.addReport(reporter, handler, content,
//						status, report_image, report_type , title);			
//				for(Part part :parts) {					
//				report_image = repSvc.Image(part);
				System.out.println(report_image);
					ReportVO reportVO = repSvc.addReport(reporter,handler,content,
							report_image,report_type ,title);
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
			
				Integer handler = Integer.valueOf(req.getParameter("handler").trim());

				Integer reporter = Integer.valueOf(req.getParameter("reporter").trim());
		
				Integer report_type = Integer.valueOf(req.getParameter("report_type").trim());
			
				String title = req.getParameter("title");
			
				String content = req.getParameter("content");
			

				ReportVO oldreportVO = new ReportService().getOneReport(report_id);
							
				byte[] report_image = oldreportVO.getReport_image();
				
				Part part = req.getPart("report_image");
				
		
				String filename1 = getFileNameFromPart(part);
					if (filename1!= null && part.getContentType()!=null) {
						report_image = getByteArrayFromPart(part);
					}
				
					ReportService repSvc = new ReportService();
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
	
	if ("listByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			
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
			ReportService repSvc = new ReportService();
			List<ReportVO> list  = repSvc.getAll(map);
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("listByCompositeQuery", list); // 資料庫取出的list物件,存入request
			RequestDispatcher successView = req.getRequestDispatcher("/report/queryList.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/report/listAllReport.jsp");
			failureView.forward(req, res);
		}
	}
	if("listByHandlerQuery".equals(action)){
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			/***************************1.接收請求參數****************************************/
			Integer handler = Integer.valueOf(req.getParameter("handler"));
			/***************************2.開始查詢資料****************************************/
//			ReportService repSvc = new ReportService();
//			List<ReportVO> repVO = repSvc.getHandler(handler);
//			System.out.println(repVO);
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String url = "/report/backListHandler.jsp";
				req.setAttribute("handler", handler);
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listOneReport.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			System.out.println(e.getMessage());
			errorMsgs.put("無法取得要修改的資料",e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/report/backListAll.jsp");
			failureView.forward(req, res);
		}
	}
	
	if("getOne_forComment".equals(action)) {
		/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		Integer report_id = Integer.valueOf(req.getParameter("report_id"));

		/***************************2.開始查詢資料*****************************************/
		ReportService repSvc = new ReportService();
		ReportVO repVO = repSvc.getComment(report_id);

		/***************************3.查詢完成,準備轉交(Send the Success view)************/

		String url = "/report/backListOne.jsp";
		req.setAttribute("repVO", repVO);
		RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
		successView.forward(req, res);
	}


	if("getOne_forModify".equals(action)) {
		/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		Integer report_id = Integer.valueOf(req.getParameter("report_id"));

		/***************************2.開始查詢資料*****************************************/
		ReportService repSvc = new ReportService();
		ReportVO repVO = repSvc.getComment(report_id);

		/***************************3.查詢完成,準備轉交(Send the Success view)************/

		String url = "/report/backForwardReport.jsp";
		req.setAttribute("repVO", repVO);
		RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
		successView.forward(req, res);
	}
}

	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		//System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		//System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	public static byte[] getByteArrayFromPart(Part part) throws IOException {
		InputStream in = part.getInputStream();
		byte[] buffer = new byte[in.available()];
		in.read(buffer);
		in.close();
		return buffer;
	}
}
