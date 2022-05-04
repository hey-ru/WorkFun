package com.groupbuy.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.groupbuy.model.*;
import com.menu.model.*;
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

		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		//參團者加入揪團並新增修改刪除品項(加入菜單搜尋結果)
		if ("showGB".equals(action)) { 

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer gb_id = Integer.valueOf(req.getParameter("gb_id"));
				Integer shop_id = Integer.valueOf(req.getParameter("shop_id")); //以店家搜尋出菜單
				
				/***************************2.開始查詢資料*****************************************/
				GroupBuyService gbSvc = new GroupBuyService();
				GroupBuyVO groupBuyVO = gbSvc.getOneGB(gb_id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("groupBuyVO", groupBuyVO);
				System.out.println(groupBuyVO.toString());
				
				//再取得一次店家菜單物件集合,以顯示於填寫揪團單畫面
				MenuService menuService = new MenuService();
				List<MenuVO> menuList = menuService.getByShopId(shop_id);
				req.setAttribute("menuList", menuList);
				System.out.println(menuList.toString());
				
				String url = "/groupbuylist/buyer_joinGB.jsp";
				
				// 成功轉交 buyer_joinGB.jsp
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
		}
		
		
		
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
	
				Integer shop_id = Integer.valueOf(req.getParameter("shop_id").trim());
				String shop_name = req.getParameter("shop_name");
				Integer gb_owner = Integer.valueOf(req.getParameter("gb_owner").trim());
				
				Timestamp start_time = null;
				try {
					start_time = Timestamp.valueOf(req.getParameter("start_time"));
				} catch (IllegalArgumentException e) {
					start_time = new java.sql.Timestamp((long)(System.currentTimeMillis()));
				}
				
				Timestamp end_time = null;
				try {
					end_time = Timestamp.valueOf(req.getParameter("end_time"));
					if(end_time.compareTo(start_time)<=0) {
						end_time = null;
						errorMsgs.put("end_time","請再確認截止時間必須晚於開始時間!");
					}
				} catch (IllegalArgumentException e) {
					end_time = null;
					errorMsgs.put("end_time","請輸入截止時間!");
				}
				
				Timestamp arr_time = null;
				try {
					arr_time = Timestamp.valueOf(req.getParameter("arr_time"));
					if(arr_time.compareTo(end_time)<=0) {
						arr_time = null;
						errorMsgs.put("arr_time","請再確認到貨時間必須晚於截止時間!");
					}
				} catch (IllegalArgumentException e) {
					arr_time = null;
				}
				
								
				
				Integer min_amt = 0;
				String min_amtstr = req.getParameter("min_amt").trim();
				if(min_amtstr.trim().length() != 0) {
					try {
						min_amt = Integer.valueOf(min_amtstr);
					} catch (NumberFormatException e) {
						errorMsgs.put("min_amt","外送低消請填數字");
					}
				}
				
				GroupBuyVO groupBuyVO = new GroupBuyVO();
				groupBuyVO.setShop_id(shop_id);
				groupBuyVO.setShop_name(shop_name);
				groupBuyVO.setGb_owner(gb_owner);
				groupBuyVO.setStart_time(start_time);
				groupBuyVO.setEnd_time(end_time);
				groupBuyVO.setArr_time(arr_time);
				groupBuyVO.setMin_amt(min_amt);
				
				groupBuyVO.getShopVO();


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/groupbuy/owner_addGB.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				GroupBuyService groupBuySvc = new GroupBuyService();
				groupBuySvc.addGroupBuy(shop_id, shop_name, gb_owner, start_time, end_time, arr_time, gb_owner, min_amt);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/groupbuy/gbHome.jsp";
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
