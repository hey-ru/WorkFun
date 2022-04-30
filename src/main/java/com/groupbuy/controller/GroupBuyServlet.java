package com.groupbuy.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.groupbuy.model.GroupBuyService;
import com.groupbuy.model.GroupBuyVO;
import com.shop.model.*;


@WebServlet("/groupbuy/GroupBuyServlet")
public class GroupBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");
		
		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
////			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("gb_id");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.put("shop_id","請輸入店家編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/shop/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer shop_id = null;
//				try {
//					shop_id = Integer.valueOf(str);
//				} catch (Exception e) {
//					errorMsgs.put("shop_id","店家編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/shop/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				ShopService shopSvc = new ShopService();
//				ShopVO shopVO = shopSvc.getOneShop(shop_id);
//				if (shopVO == null) {
//					errorMsgs.put("shop_id","查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/shop/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("shopVO", shopVO); // 資料庫取出的empVO物件,存入req
//				String url = "/shop/listOneShop.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
////				/***************************其他可能的錯誤處理*************************************/
////			} catch (Exception e) {
////				errorMsgs.put("Exception","無法取得資料:" + e.getMessage());
////				RequestDispatcher failureView = req
////						.getRequestDispatcher("/shop/select_page.jsp");
////				failureView.forward(req, res);
////			}
//		}
		
		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
////			try {
//				/***************************1.接收請求參數****************************************/
//				Integer gb_id = Integer.valueOf(req.getParameter("gb_id"));
//				
//				/***************************2.開始查詢資料****************************************/
//				GroupBuyService groupBuySvc = new GroupBuyService();
//				GroupBuyVO groupBuyVO = groupBuySvc.getOneGB(gb_id);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				String param = "?gb_id="  +groupBuyVO.getGb_id()+
//								"&shop_id=" +groupBuyVO.getShop_id()+
//						       "&shop_name="  +groupBuyVO.getShop_name()+
//						       "&gb_owner=" +groupBuyVO.getGb_owner()+
//						       "&start_time="+groupBuyVO.getStart_time()+
//						       "&end_time="    +groupBuyVO.getEnd_time()+
//						       "&arr_time="   +groupBuyVO.getArr_time()+
//						       "&gb_status=" +groupBuyVO.getGb_status()+
//						       "&min_amt=" +groupBuyVO.getMin_amt();						       
//										    
//				String url = "/groupbuy/update_groupbuy_input.jsp"+param;
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
////				/***************************其他可能的錯誤處理**********************************/
////			} catch (Exception e) {
////				errorMsgs.put("Exception","無法取得要修改的資料:" + e.getMessage());
////				RequestDispatcher failureView = req
////						.getRequestDispatcher("/shop/listAllShop.jsp");
////				failureView.forward(req, res);
////			}
//		}
		
		
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//		
////			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer shop_id = Integer.valueOf(req.getParameter("shop_id").trim());
//				
//				String shop_name = req.getParameter("shop_name");
//				String shop_nameReg ="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_\\(\\-\\)]*$";
//				if (shop_name == null || shop_name.trim().length() == 0) {
//					errorMsgs.put("shop_name","店家名稱: 請勿空白");
//				} else if(!shop_name.trim().matches(shop_nameReg)) {
//					errorMsgs.put("shop_name","店家: 只能是中、日、英文字母、數字、_、-和()");
//	            }
//				
//				Integer shop_type = Integer.valueOf(req.getParameter("shop_type").trim());
//				//(0: 飲料, 1: 中式 2: 異國, 3: 小吃, 4: 素食 5:其他 )
//				
//				String address = req.getParameter("address").trim();
//				String addressReg ="^[(\u4e00-\u9fa5)a-zA-Z0-9_\\-]*$";
//				if ((address.trim().length() != 0) && !(address.trim().matches(addressReg))) { 
//					errorMsgs.put("address","地址: 只能是中、英文字母、數字、-和_");
//	            }
//				
//				String tel = req.getParameter("tel");
//				String telReg = "(\\d{2,3}-?|\\(\\d{2,3}\\)-?)\\d{3,4}-?\\d{4}|09\\d{2}-?(\\d{6}|-\\d{3}-\\d{3})";
//				if (tel == null || tel.trim().length() == 0) {
//					errorMsgs.put("tel","電話:請至少留一個電話或手機號碼");
//				} else if(!tel.trim().matches(telReg)) { 
//					errorMsgs.put("tel","電話:請再確認一下號碼");
//	            }
//				
//				String website = req.getParameter("website");
//				
//				Integer min_amt = 0;
//				String min_amtstr = req.getParameter("min_amt").trim();
//				if(min_amtstr.trim().length() != 0) {
//					try {
//						min_amt = Integer.valueOf(min_amtstr);
//					} catch (NumberFormatException e) {
//						errorMsgs.put("min_amt","外送低消請填數字");
//					}
//				}
//				
//				
//				ShopVO oldshopVO = new ShopService().getOneShop(shop_id);
//							
//				byte[] shop_img1 = oldshopVO.getShop_img1();
//				byte[] shop_img2 = oldshopVO.getShop_img2();
//				byte[] shop_img3 = oldshopVO.getShop_img3();
//				
//				Part pic1 = req.getPart("shop_img1");
//				String filename1 = getFileNameFromPart(pic1);
//					if (filename1!= null && pic1.getContentType()!=null) {
//						shop_img1 = getByteArrayFromPart(pic1);
//					}
//				
//		        Part pic2 = req.getPart("shop_img2");
//				String filename2 = getFileNameFromPart(pic2);
//					if (filename2!= null && pic2.getContentType()!=null) {
//						shop_img2 = getByteArrayFromPart(pic2);
//					}
//				
//				Part pic3 = req.getPart("shop_img3");
//				String filename3 = getFileNameFromPart(pic3);
//					if (filename3!= null && pic3.getContentType()!=null) {
//						shop_img3 = getByteArrayFromPart(pic3);
//					}
//							
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/shop/update_shop_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				ShopService shopSvc = new ShopService();
//				ShopVO shopVO = shopSvc.updateShop(shop_id, shop_name, shop_type, address, tel, website,
//						min_amt, shop_img1, shop_img2, shop_img3);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("shopVO", shopVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/shop/listOneShop.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
////				/***************************其他可能的錯誤處理*************************************/
////			} catch (Exception e) {
////				errorMsgs.put("Exception","修改資料失敗:" + e.getMessage());
////				RequestDispatcher failureView = req
////						.getRequestDispatcher("/emp/update_emp_input.jsp");
////				failureView.forward(req, res);
////			}
//		}

        if ("insert".equals(action)) { // 來自listALLShop.jsp listOneShop.jsp的請求  
			
        	Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
	
				String shop_name = req.getParameter("shop_name");
				String shop_nameReg ="^[(\u4e00-\u9fa5)(\u0800-\u4e00)a-zA-Z0-9_\\(\\-\\)]*$";
				if (shop_name == null || shop_name.trim().length() == 0) {
					errorMsgs.put("shop_name","店家名稱: 請勿空白");
				} else if(!shop_name.trim().matches(shop_nameReg)) {
					errorMsgs.put("shop_name","店家: 只能是中、英文字母、數字和_");
	            }
				
				Integer shop_type = Integer.valueOf(req.getParameter("shop_type").trim());
				//(0: 飲料, 1: 中式 2: 異國, 3: 小吃, 4: 素食 5:其他 )
				
				String placecode = req.getParameter("placecode").trim();
				String city = req.getParameter("city").trim();
				String dist = req.getParameter("dist").trim();
				String addressend = req.getParameter("address").trim();
				String addressReg ="^[(\u4e00-\u9fa5)a-zA-Z0-9_\\-]*$";
				if ((addressend.trim().length() != 0) && !(addressend.trim().matches(addressReg))) { 
					errorMsgs.put("address","地址: 只能是中、英文字母、數字和_");
	            }
				String address = placecode+city+dist+addressend;
				
				String tel = req.getParameter("tel");
				String telReg = "(\\d{2,3}-?|\\(\\d{2,3}\\)-?)\\d{3,4}-?\\d{4}|09\\d{2}-?(\\d{6}|-\\d{3}-\\d{3})";
				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.put("tel","電話:請至少留一個電話或手機號碼");
				} else if(!tel.trim().matches(telReg)) { 
					errorMsgs.put("tel","電話:請再確認一下號碼");
	            }
				
				String website = req.getParameter("website");
				
				Integer min_amt = 0;
				String min_amtstr = req.getParameter("min_amt").trim();
				if(min_amtstr.trim().length() != 0) {
					try {
						min_amt = Integer.valueOf(min_amtstr);
					} catch (NumberFormatException e) {
						errorMsgs.put("min_amt","外送低消請填數字");
					}
				}
				
				byte[] shop_img1 = null;
				byte[] shop_img2 = null;
				byte[] shop_img3 = null;
				
				Part pic1 = req.getPart("shop_img1");
				String filename1 = getFileNameFromPart(pic1);
					if (filename1!= null && pic1.getContentType()!=null) {
						shop_img1 = getByteArrayFromPart(pic1);
					}
				
		        Part pic2 = req.getPart("shop_img2");
				String filename2 = getFileNameFromPart(pic2);
					if (filename2!= null && pic2.getContentType()!=null) {
						shop_img2 = getByteArrayFromPart(pic2);
					}
				
				Part pic3 = req.getPart("shop_img3");
				String filename3 = getFileNameFromPart(pic3);
					if (filename3!= null && pic3.getContentType()!=null) {
						shop_img3 = getByteArrayFromPart(pic3);
					}
							

				ShopVO shopVO = new ShopVO();
				shopVO.setShop_name(shop_name);
				shopVO.setShop_type(shop_type);
				shopVO.setAddress(address);
				shopVO.setTel(tel);
				shopVO.setWebsite(website);
				shopVO.setMin_amt(min_amt);
				shopVO.setShop_img1(shop_img1);
				shopVO.setShop_img2(shop_img2);
				shopVO.setShop_img3(shop_img3);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/addShop.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ShopService shopSvc = new ShopService();
				shopSvc.addShop(shop_name, shop_type, address, tel, website,
						min_amt, shop_img1, shop_img2, shop_img3);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/shop/listAllShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.put("Exception","新資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/shop/addShop.jsp");
//				failureView.forward(req, res);
//			}
}
		
		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
//				
//				/***************************2.開始刪除資料***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
	
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
