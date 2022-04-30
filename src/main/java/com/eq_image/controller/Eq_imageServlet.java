package com.eq_image.controller;
//package com.eq_image.controller;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//
//import com.eq_image.model.EqImageService;
//import com.eq_image.model.EqImageVO;
//import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;
//import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;
//
//@MultipartConfig
//public class Eq_imageServlet extends HttpServlet {
//
//	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
//	}
//
//	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//		res.setContentType("text/html; charser=UTF-8");
//System.out.println(action);
//		if ("Insert".equals(action)) {
//
//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//
//			byte[] image = null;
//			Part pics = req.getPart("image");
//			String filename = getFileNameFromPart(pics);
//			if (filename != null && pics.getContentType() != null) {
//				image = getByteArrayFromPart(pics);
//			}
//
//			EqImageVO eqImageVO = new EqImageVO();
//			eqImageVO.setEqImage(image);
//
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/addEquipment.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/*************************** 2.開始新增資料 ***************************************/
//			EqImageService eqImageSvc = new EqImageService();
//			eqImageSvc.addEqImage(image);
//
//			/***************************3.新增完成,準備轉交(Send the Success view)***********/
//			
//			String url = "equipment/listAllEquipment.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//		}
//	}
//
//	// 取出上傳檔案名稱
//	public String getFileNameFromPart(Part part) {
//		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 測試用
//		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename);// 測試用
//		if (filename.length() == 0) {
//			return null;
//		}
//		return filename;
//	}
//
//	public static byte[] getByteArrayFromPart(Part part) throws IOException {
//		InputStream in = part.getInputStream();
//		byte[] buffer = new byte[in.available()];
//		in.read(buffer);
//		in.close();
//		return buffer;
//	}
//}