package com.report_comment.controller;

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

import com.report.model.ReportService;
import com.report.model.ReportVO;
import com.report_comment.model.Report_CommentService;
import com.report_comment.model.Report_CommentVO;

@WebServlet("/reportCommentServlet")
@MultipartConfig
(
	 fileSizeThreshold   = 1024 * 1024 * 10,
		        maxFileSize         = 1024 * 1024 * 50,
		        maxRequestSize      = 1024 * 1024 * 100
)
public class ReportCommentServlet extends HttpServlet{
	ReportService repSvc = new ReportService();
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

//-----------------------------------------------------------------------	
		 if ("insert".equals(action)) { // 來自addReportComment.jsp的請求  

				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				Integer report_id = Integer.valueOf(req.getParameter("report_id").trim());
				ReportVO repVO = repSvc.getComment(report_id);
				req.setAttribute("repVO", repVO);
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					System.out.println(report_id);
					String comment = req.getParameter("comment");
					System.out.println(comment);
					String commentReg = "^[(\\u4e00-\\u9fa5)_a-zA-Z0-9_'\\\\n\\\\s\\\\(\\\\)\\'\\\"{}\\\\[\\\\]\\\\*&.。?？!！,，：:；;＄$]*$";
					if (comment == null || comment.trim().length() == 0) {
						errorMsgs.put("comment","處理訊息: 請勿空白");
					} else if(!comment.trim().matches(commentReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.put("comment","處理訊息: 只能是中、英文字母、數字、標點符號");
		            }
					
					System.out.println(errorMsgs);
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/report/backListOne.jsp");
						failureView.forward(req, res);
						return;
					}
					/***************************2.開始新增資料***************************************/
					Report_CommentService repcomSvc= new Report_CommentService();
					byte[] report_comment_image = null;	
					Part part = req.getPart("report_comment_image");
					
					String filename1 = getFileNameFromPart(part);
					if (filename1!= null && part.getContentType()!=null) {
						report_comment_image = getByteArrayFromPart(part);
					}
					Report_CommentVO report_commentVO = repcomSvc.addReport_Comment(report_id,comment,report_comment_image);
					ReportService repSvc = new ReportService();
					ReportVO repVO2 = repSvc.reject(report_id);
					
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/report/backListAll.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.put("Exception",e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/report/backListOne.jsp");
					failureView.forward(req, res);
				}
			}
		//-----------------------------------------------------------------------			 
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
				
				String url = "/report/backListOne.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
			}
		//-----------------------------------------------------------------------	
			  	  
			if ("update".equals(action)) { // 來自updateReport.jsp的請求
					Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
					req.setAttribute("errorMsgs", errorMsgs);
					Integer report_id = Integer.valueOf(req.getParameter("report_id").trim());
					ReportVO repVO = repSvc.getComment(report_id);
					req.setAttribute("repVO", repVO);
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						System.out.println(report_id);
						Integer handler = Integer.valueOf(req.getParameter("handler").trim());
						System.out.println(handler);
						Integer report_type = Integer.valueOf(req.getParameter("report_type").trim());
						System.out.println(report_type);
						Integer status = Integer.valueOf(req.getParameter("status"));
						System.out.println(status);
						String comment = req.getParameter("comment");
						String commentReg = "^[(\\u4e00-\\u9fa5)_a-zA-Z0-9_'\\\\n\\\\s\\\\(\\\\)\\'\\\"{}\\\\[\\\\]\\\\*&.。?？!！,，：:；;＄$]*$";
						if (comment == null || comment.trim().length() == 0) {
							errorMsgs.put("comment","處理訊息: 請勿空白");
						} else if(!comment.trim().matches(commentReg)) { //以下練習正則(規)表示式(regular-expression)
							errorMsgs.put("comment","處理訊息: 只能是中、英文字母、數字、標點符號");
			            }
						Report_CommentService recSvc = new Report_CommentService();
						System.out.println(errorMsgs);
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/report/backForwardReport.jsp");
							failureView.forward(req, res);
							return; //程式中斷
						}
						
						/***************************2.開始修改資料*****************************************/
						
						ReportVO reportVO = recSvc.changeType(handler,report_type,status,report_id);
						Report_CommentVO report_CommentVO = recSvc.forward(report_id, comment);
						
						/***************************3.修改完成,準備轉交(Send the Success view)*************/
						req.setAttribute("reportVO", reportVO); // 資料庫update成功後,正確的的empVO物件,存入req
						String url = "/report/backListAll.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.put("Exception","修改資料失敗:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/report/backForwardReport.jsp");
						failureView.forward(req, res);
					}
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