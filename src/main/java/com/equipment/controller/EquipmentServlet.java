package com.equipment.controller;

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

import com.eq_image.model.EqImageService;
import com.eq_image.model.EqImageVO;
import com.equipment.model.EquipmentService;
import com.equipment.model.EquipmentVO;
import com.secondHand.model.SecondHandService;
import com.secondHand.model.SecondHandVO;

@WebServlet("/equipment/equipment.do")
@MultipartConfig
public class EquipmentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/equipmentHome.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/equipmentHome.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/equipmentHome.jsp");
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
					+ equipmentVO.getSpec() + "&img1=" + equipmentVO.getImg1() + "&img2=" + equipmentVO.getImg2()
					+ "&img3=" + equipmentVO.getImg3();

			String url = "/equipment/BackUpdateAddEqpt.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
		}

		if ("update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer equipmentId = Integer.valueOf(req.getParameter("equipmentId").trim());

			System.out.println("equipmentId= " + equipmentId);

//			Integer imageId = Integer.valueOf(req.getParameter("imageId").trim());

			String eqName = req.getParameter("eqName");
//			String eqNameReg = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$";
			if (eqName == null || eqName.trim().length() == 0) {
				errorMsgs.put("eqName", "器材名稱: 請勿空白");
			}

			System.out.println("eqName=" + eqName);

//			else if (!eqName.trim().matches(eqNameReg)) {
//				errorMsgs.put("eqName", "器材名稱: 只能是中文，英文字母、数字及_");
//			}

			Integer price = null;
			try {
				price = Integer.valueOf(req.getParameter("price").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("price", "金額請填數字");
			}

//			Integer eqStatus = Integer.valueOf(req.getParameter("eqStatus").trim());

			// 0:上架 1:未歸還器材 2:維修中 3:下架
			Integer eqStatus = null;
			try {
				eqStatus = Integer.valueOf(req.getParameter("eqStatus").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("eqStatus", "請選擇狀態");
			}

//			String introduction = req.getParameter("introduction");
//			if (introduction == null || introduction.trim().length() == 0) {
//				errorMsgs.put("introduction", "器材介紹: 請勿空白");
//			}

			String spec = req.getParameter("spec");
			if (spec == null || spec.trim().length() == 0) {
				errorMsgs.put("spec", "器材規格: 請勿空白");
			}
			System.out.println("spec= " + spec);

			EquipmentVO oldEquipmentVO = new EquipmentService().getByEqId(equipmentId);

			byte[] img1 = oldEquipmentVO.getImg1();
			byte[] img2 = oldEquipmentVO.getImg2();
			byte[] img3 = oldEquipmentVO.getImg3();

			Part pic1 = req.getPart("img1");
			String filename1 = getFileNameFromPart(pic1);
			if (filename1 != null && pic1.getContentType() != null) {
				img1 = getByteArrayFromPart(pic1);
			}

			Part pic2 = req.getPart("img2");
			String filename2 = getFileNameFromPart(pic2);
			if (filename2 != null && pic2.getContentType() != null) {
				img2 = getByteArrayFromPart(pic2);
			}

			Part pic3 = req.getPart("img3");
			String filename3 = getFileNameFromPart(pic3);
			if (filename3 != null && pic3.getContentType() != null) {
				img3 = getByteArrayFromPart(pic3);
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/BackUpdateAddEqpt.jsp");
				failureView.forward(req, res);
				return;
			}

			System.out.println("成功");
			/*************************** 2.開始修改資料 *****************************************/
			EquipmentService equipSvc = new EquipmentService();
			EquipmentVO equipmentVO = equipSvc.updatEquipment(equipmentId, eqName, price, eqStatus, spec, img1, img2,
					img3);

//			EqImageService equipImageSvc = new EqImageService();
//			equipImageSvc.updateEqImage(equipmentId, image1, image2, image3);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("equipmentVO", equipmentVO);

			System.out.println(equipmentVO);

			String url = "/equipment/backOneEqpt.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action))

		{
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String eqName = req.getParameter("eqName");
//			String eqNameReg = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$";
			if (eqName == null || eqName.trim().length() == 0) {
				errorMsgs.put("eqName", "器材名稱: 請勿空白");
			}

//			else if (!eqName.trim().matches(eqNameReg)) {
//				errorMsgs.put("eqName", "器材名稱: 只能是中文，英文字母、数字及_");
//			}

			Integer price = null;
			try {
				price = Integer.valueOf(req.getParameter("price").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("price", "金額請填數字");
			}

//			Integer eqStatus = Integer.valueOf(req.getParameter("eqStatus").trim());

			// 0:上架 3:下架
			Integer eqStatus = null;
			try {
				eqStatus = Integer.valueOf(req.getParameter("eqStatus").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("eqStatus", "請選擇狀態");
			}

//			String introduction = req.getParameter("introduction");
//			if (introduction == null || introduction.trim().length() == 0) {
//				errorMsgs.put("introduction", "器材介紹: 請勿空白");
//			}

			String spec = req.getParameter("spec");
			if (spec == null || spec.trim().length() == 0) {
				errorMsgs.put("spec", "器材規格: 請勿空白");
			}

			System.out.println(action);

			byte[] img1 = null;
			byte[] img2 = null;
			byte[] img3 = null;

			Part pic1 = req.getPart("img1");
			String filename1 = getFileNameFromPart(pic1);
			if (filename1 != null && pic1.getContentType() != null) {
				img1 = getByteArrayFromPart(pic1);
			}

			Part pic2 = req.getPart("img2");
			String filename2 = getFileNameFromPart(pic2);
			if (filename2 != null && pic2.getContentType() != null) {
				img2 = getByteArrayFromPart(pic2);
			}

			Part pic3 = req.getPart("img3");
			String filename3 = getFileNameFromPart(pic3);
			if (filename3 != null && pic3.getContentType() != null) {
				img3 = getByteArrayFromPart(pic3);
			}
//                 
			EquipmentVO equipmentVO = new EquipmentVO();
			equipmentVO.setEqName(eqName);
			equipmentVO.setPrice(price);
			equipmentVO.setEqStatus(eqStatus);
//			equipmentVO.setIntroduction(introduction);
			equipmentVO.setSpec(spec);
			equipmentVO.setImg1(img1);
			equipmentVO.setImg2(img2);
			equipmentVO.setImg3(img3);

//			EqImageVO eqImageVO = new EqImageVO();
//			eqImageVO.setEqImage(image1);
//			eqImageVO.setEqImage(image2);
//			eqImageVO.setEqImage(image3);

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/equipment/backAddEqpt.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			EquipmentService equipSvc = new EquipmentService();
			equipSvc.addEquipment(eqName, price, eqStatus, spec, img1, img2, img3);

//			System.out.println(equipSvc.getLast().getEqId());

//			EqImageService equipImageSvc = new EqImageService();
//			equipImageSvc.addEqImage(equipSvc.getLast().getEqId(), image1, image2, image3);
//			equipSvc.getLast().getEqId()

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/equipment/backEquipmentHome.jsp";
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
			String url = "/equipment/equipmentHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("showEquipment".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			System.out.println("進來了");
			Integer equipmentId = Integer.valueOf(req.getParameter("equipmentId"));
			System.out.println("equipmentId");

			/*************************** 2.開始查詢資料 *****************************************/
			EquipmentService equipmentSvc = new EquipmentService();
			EquipmentVO equipmentVO = equipmentSvc.getByEqId(equipmentId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			HttpSession session = req.getSession();
			session.setAttribute("equipmentVO", equipmentVO);
//			System.out.println(equipmentVO.toString());

			String url = "/booking/bookingHome.jsp";

			// 成功轉交 bookingHome.jsp
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("listByCompositeQuery".equals(action)) { // 來自secondHandHome.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.將輸入資料轉為Map **********************************/
			// 採用Map<String,String[]> getParameterMap()的方法
			// 注意:an immutable java.util.Map
			// Map<String, String[]> map = req.getParameterMap();
			HttpSession session = req.getSession();
			Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");

			// 以下的 if 區塊只對第一次執行時有效
			if (req.getParameter("whichPage") == null) {
				Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
				session.setAttribute("map", map1);
				map = map1;
			}

			/*************************** 2.開始複合查詢 ***************************************/
			EquipmentService equipmentService = new EquipmentService();
			List<EquipmentVO> list = equipmentService.getAllQuery(map);

			System.out.println("list= " + list);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listByCompositeQuery", list); // 資料庫取出的list物件,存入request
//				session.setAttribute("listByCompositeQuery", list);
			RequestDispatcher successView = req.getRequestDispatcher("/equipment/backEquipmentHome.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
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
