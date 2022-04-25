package com.shop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.shop.model.ShopService;
import com.shop.model.ShopVO;

@WebServlet("/shop/ShopServlet.do")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("shop_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入店家編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer shop_id = null;
				try {
					shop_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("店家編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ShopService shopSvc = new ShopService();
				ShopVO shopVO = shopSvc.getOneShop(shop_id);
				if (shopVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shopVO", shopVO); // 資料庫取出的empVO物件,存入req
				String url = "/shop/listOneShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/shop/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer shop_id = new Integer(req.getParameter("shop_id"));
				
				/***************************2.開始查詢資料****************************************/
				ShopService shopSvc = new ShopService();
				ShopVO shopVO = shopSvc.getOneShop(shop_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("shopVO", shopVO);         // 資料庫取出的empVO物件,存入req
				String url = "/shop/update_shop_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/shop/listAllShop.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer shop_id = new Integer(req.getParameter("shop_id").trim());
//				
//				String shop_name = req.getParameter("shop_name");
//				String shop_nameReg = "^(\u4e00-\u9fa5)(/u0800-/u4e00)(a-zA-Z0-9_)$";
//				if (shop_name == null || shop_name.trim().length() == 0) {
//					errorMsgs.add("店家名稱: 請勿空白");
//				} else if(!shop_name.trim().matches(shop_nameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("店家: 只能是中、日、英文字母、數字和_");
//	            }
//				
//				Integer shop_type = new Integer(req.getParameter("shop_type").trim());
//				//(0: 飲料, 1: 中式 2: 異國, 3: 小吃, 4: 素食 5:其他 )
//				
//				String address = req.getParameter("address");
//				String addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_-)]$";
//				if (address.trim().length() != 0 && !address.trim().matches(addressReg)) { 
//					errorMsgs.add("店家: 只能是中、英文字母、數字和_-");
//	            }
//				
//				String tel = req.getParameter("tel");
//				String telReg = "^([0-9]{3}-?[0-9]{8}|[0-9]{4}-?[0-9]{7})$";
//				if (tel == null || tel.trim().length() == 0) {
//					errorMsgs.add("電話:請至少留一個電話或手機號碼");
//				} else if(!tel.trim().matches(telReg)) { 
//					errorMsgs.add("電話:請再確認一下號碼");
//	            }
//				
//				String website = req.getParameter("website");
//				
//				Integer min_amt = null;
//				try {
//					min_amt = new Integer(req.getParameter("min_amt").trim());
//				} catch (NumberFormatException e) {
//					min_amt = 0;
//					errorMsgs.add("外送低消金額請填數字.");
//				}
//				
//				byte[] shop_img1 = req.getParameter(shop_img1);
//				
//				
//				
//				
//				try {
//					comm = new Double(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}
//
//				Integer deptno = new Integer(req.getParameter("deptno").trim());
//
//				EmpVO empVO = new EmpVO();
//				empVO.setEmpno(empno);
//				empVO.setEname(ename);
//				empVO.setJob(job);
//				empVO.setHiredate(hiredate);
//				empVO.setSal(sal);
//				empVO.setComm(comm);
//				empVO.setDeptno(deptno);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				EmpService empSvc = new EmpService();
//				empVO = empSvc.updateEmp(empno, ename, job, hiredate, sal,comm, deptno);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/update_emp_input.jsp");
//				failureView.forward(req, res);
//			}
//		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
	
				String shop_name = req.getParameter("shop_name");
				String shop_nameReg = "^(\u4e00-\u9fa5)(/u0800-/u4e00)(a-zA-Z0-9_)$";
				if (shop_name == null || shop_name.trim().length() == 0) {
					errorMsgs.add("店家名稱: 請勿空白");
				} else if(!shop_name.trim().matches(shop_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("店家: 只能是中、日、英文字母、數字和_");
	            }
				
				Integer shop_type = new Integer(req.getParameter("shop_type").trim());
				//(0: 飲料, 1: 中式 2: 異國, 3: 小吃, 4: 素食 5:其他 )
				
				String address = req.getParameter("address");
				String addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_-)]$";
				if (address.trim().length() != 0 && !address.trim().matches(addressReg)) { 
					errorMsgs.add("店家: 只能是中、英文字母、數字和_-");
	            }
				
				String tel = req.getParameter("tel");
				String telReg = "^([0-9]{3}-?[0-9]{8}|[0-9]{4}-?[0-9]{7})$";
				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.add("電話:請至少留一個電話或手機號碼");
				} else if(!tel.trim().matches(telReg)) { 
					errorMsgs.add("電話:請再確認一下號碼");
	            }
				
				String website = req.getParameter("website");
				
				Integer min_amt = null;
				try {
					min_amt = new Integer(req.getParameter("min_amt").trim());
				} catch (NumberFormatException e) {
					min_amt = 0;
					errorMsgs.add("外送低消金額請填數字.");
				}
				
				byte[] shop_img1 = getPictureByteArray(req.getPart("shop_img1"));
				byte[] shop_img2 = getPictureByteArray(req.getPart("shop_img2"));
				byte[] shop_img3 = getPictureByteArray(req.getPart("shop_img3"));
				
//				Collection<Part> parts = req.getParts();
//				for (Part part : parts) {
//					String filename = getFileNameFromPart(part);
//					if (filename!= null && part.getContentType()!=null) {
//						InputStream in = part.getInputStream();
//						byte[] buf = new byte[in.available()];
//						in.read(buf);
//						in.close();
//					}
//				}
				//byte[] shop_img1 = req.getParameter(shop_img1);
				
				

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
					req.setAttribute("shopVO", shopVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/addShop.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ShopService shopSvc = new ShopService();
				shopVO = shopSvc.addShop(shop_name, shop_type, address, tel, website,
						min_amt, shop_img1, shop_img2, shop_img3);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/shop/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/shopp/addShop.jsp");
				failureView.forward(req, res);
			}
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
	public static byte[] getPictureByteArray(Part part) throws IOException {
		InputStream fis = part.getInputStream();
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

}
