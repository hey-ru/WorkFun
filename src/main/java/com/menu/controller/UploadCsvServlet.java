package com.menu.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.menu.model.MenuService;
import com.menu.model.MenuVO;
import com.opencsv.CSVReader;

@WebServlet("/menu/uploadcsvservlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadCsvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); // 處理中文檔名
		System.out.println("ContentType=" + req.getContentType()); // 測試用

//之後動態抓取店家，並新增其菜單		
//		String action = request.getParameter("action");
//		Integer shop_id = Integer.valueOf(request.getParameter("shop_id"));

		// 取得上傳的CSV檔案
		Part filePart = req.getPart("csvfile");
		InputStream fileContent = filePart.getInputStream();

		Reader in = new InputStreamReader(fileContent);

		CSVReader csvreader;
		Iterator<String[]> iterator;
		Connection conn = null;
		String message = "";// 上傳成功印出表格
		String message2 = null; // 上傳結果訊息

		// 連線資料庫
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://database-1.cqm5mb4z5ril.ap-northeast-1.rds.amazonaws.com:3306/CGA101-03?serverTimezone=Asia/Taipei";
		String userid = "cga101-03";
		String passwd = "cga101-03";

		String shop_id = req.getParameter("shop_id");
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);

			// 使用opencsv讀取
			csvreader = new CSVReader(new InputStreamReader(fileContent, "UTF-8"));
			String[] strArr;

			while ((strArr = csvreader.readNext()) != null) {

				System.out.println("Item 品項 : " + strArr[0]);
				System.out.println("Price 價格 : " + strArr[1]);
				System.out.println("==========================");

				// 新增資料庫測試
				// 讀取後新增至資料庫
				String sql = "INSERT INTO menu (item, price, shop_id) VALUES (?, ?, ?)";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, strArr[0]);
				statement.setString(2, strArr[1]);
				statement.setInt(3, Integer.valueOf(shop_id));
				int row = statement.executeUpdate();

				// 在jsp中印出上傳結果
				if (row > 0) {
					if (message == "")
						message = "<tr><th>Item</th><th>Price</th></tr>";
					message += "<tr>" + "<td>" + strArr[0] + "</td>" + "<td>" + strArr[1] + "</td>" + "</tr>";
					message2 = "Menu upload Successfully!!";
				}

			}
		} catch (Exception ex) {
			message2 = "ERROR: " + ex.getMessage();
			ex.printStackTrace();

		} finally {
			if (conn != null) {
				// closes the database connection
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			// sets the message in request scope
			req.setAttribute("Message", message);
			req.setAttribute("Message2", message2);

			// 再取得一次店家菜單物件集合回應,以顯示於店家菜單畫面
			MenuService menuService = new MenuService();
			List<MenuVO> menuList = menuService.getByShopId(Integer.valueOf(shop_id));
			req.setAttribute("menuList", menuList);

			// forwards to the message page
			getServletContext().getRequestDispatcher("/menu/listMenuByShop.jsp").forward(req, res);
		}
		in.close();

	}

}
