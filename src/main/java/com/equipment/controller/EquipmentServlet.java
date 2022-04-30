package com.equipment.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.eq_image.model.EqImageService;
import com.eq_image.model.EqImageVO;
import com.equipment.model.EquipmentService;
import com.equipment.model.EquipmentVO;

@WebServlet("/equipment/equipment.do")
@MultipartConfig
public class EquipmentServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");
		
		if ("getOne_For_Display".equals(action)) { // 來自eq_select_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("equipmentId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("equipmentId", "請輸入器材編號");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/eq_select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer equipmentId = null;
			try {
				equipmentId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("equipmentId", "器材編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/eq_select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			EquipmentService equipSvc = new EquipmentService();
			EquipmentVO equipmentVO = equipSvc.getByEqId(equipmentId);
			if (equipmentVO == null) {
				errorMsgs.put("equipmentId", "查無此資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/eq_select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("equipmentVO", equipmentVO);
			String url = "/equipment/listOneEquipment.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_eqStatus".equals(action)) { // 來自eq_select_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("eqStatus");

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/eq_select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer eqStatus = null;
			try {
				eqStatus = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("eqStatus", "器材狀態格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/eq_select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			EquipmentService equipSvc = new EquipmentService();
			EquipmentVO equipmentVO = equipSvc.getByEqstatus(eqStatus);
			if (equipmentVO == null) {
				errorMsgs.put("eqStatus", "查無此資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/eq_select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("equipmentVO", equipmentVO);
			String url = "/equipment/getAllByEqStatus.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_eqName".equals(action)) { // 來自eq_select_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String eqName1 = req.getParameter("eqName");
			if (eqName1 == null || eqName1.trim().length() == 0) {
				errorMsgs.put("eqName", "器材名稱: 請勿空白");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/eq_select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			EquipmentService equipSvc = new EquipmentService();
			List<EquipmentVO> equipmentVO = equipSvc.getAllByEqName(eqName1);

//			System.out.println(equipmentVO);
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/eq_select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("list", equipmentVO);
			String url = "/equipment/getAllByName.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer equipmentId = Integer.valueOf(req.getParameter("equipmentId"));

			/*************************** 2.開始查詢資料 ****************************************/
			EquipmentService equipSvc = new EquipmentService();
			EquipmentVO equipmentVO = equipSvc.getByEqId(equipmentId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?eqName=" + equipmentVO.getEqName() + "&price=" + equipmentVO.getPrice() + "&eqStatus="
					+ equipmentVO.getEqStatus() + "&introduction=" + equipmentVO.getIntroduction() + "&spec="
					+ equipmentVO.getSpec();
			String url = "/equipment/update_equipment_input.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer equipmentId = Integer.valueOf(req.getParameter("equipmentId").trim());

			String eqName = req.getParameter("eqName");
			if (eqName == null || eqName.trim().length() == 0) {
				errorMsgs.put("eqName", "器材名稱: 請勿空白");
			}

			Integer price = null;
			try {
				price = Integer.valueOf(req.getParameter("price").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("price", "金額請填數字");
			}

			Integer eqStatus = Integer.valueOf(req.getParameter("eqStatus").trim());

			String introduction = req.getParameter("introduction");

			String spec = req.getParameter("spec");

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/update_equipment_input.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			EquipmentService equipSvc = new EquipmentService();
			EquipmentVO equipmentVO = equipSvc.updatEquipment(equipmentId, eqName, price, eqStatus, introduction, spec);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("equipmentVO", equipmentVO);
			String url = "/equipment/listOneEquipment.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String eqName = req.getParameter("eqName");

			if (eqName == null || eqName.trim().length() == 0) {
				errorMsgs.put("eqName", "器材名稱: 請勿空白");
			}

			Integer price = null;
			try {
				price = Integer.valueOf(req.getParameter("price").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("price", "金額請填數字");
			}

			Integer eqStatus = Integer.valueOf(req.getParameter("eqStatus").trim());

			String introduction = req.getParameter("introduction");

			String spec = req.getParameter("spec");

System.out.println(action);
			
			byte[] image = null;
			Part pics = req.getPart("image");
			String filename = getFileNameFromPart(pics);
			if (filename != null && pics.getContentType() != null) {
				image = getByteArrayFromPart(pics);
			}
//                 
			EquipmentVO equipmentVO = new EquipmentVO();
			equipmentVO.setEqName(eqName);
			equipmentVO.setPrice(price);
			equipmentVO.setEqStatus(eqStatus);
			equipmentVO.setIntroduction(introduction);
			equipmentVO.setSpec(spec);
			
			EqImageVO eqImageVO = new EqImageVO();
			eqImageVO.setEqImage(image);

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/addEquipment.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			EquipmentService equipSvc = new EquipmentService();
			equipSvc.addEquipment(eqName, price, eqStatus, introduction, spec);
			
			System.out.println(equipSvc.getLast().getEqId());
			
			EqImageService equipImageSvc = new EqImageService();
			equipImageSvc.addEqImage(equipSvc.getLast().getEqId(), image);
//			equipSvc.getLast().getEqId()

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/equipment/listAllEquipment.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("delete".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer equipmentId = Integer.valueOf(req.getParameter("equipmentId"));

			/*************************** 2.開始刪除資料 ***************************************/
			EquipmentService equipSvc = new EquipmentService();
			equipSvc.deleteByEqId(equipmentId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/equipment/listAllEquipment.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

	// 取出上傳檔案名稱
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename);// 測試用
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
