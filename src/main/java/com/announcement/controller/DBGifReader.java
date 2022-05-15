package com.announcement.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/servlet/com.announcement.controller.DBGifReader")
public class DBGifReader extends HttpServlet {

	Connection con;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			
			Statement stmt = con.createStatement();
			String announcement_id= req.getParameter("announcement_id").trim();
			String announcementImg_id= req.getParameter("announcementImg_id").trim();
		String announcementImg="announcementImg";
			ResultSet rs = stmt.executeQuery(
					" select announcementImg "
							+" from  announcement ann join announcement_mapping annmap on ann.announcement_id=annmap.announcement_id "
							+"where ann.announcement_id = "+announcement_id
							+ " and announcementImg_id="+announcementImg_id
							+" order by ann.announcement_id;");
	
			

			if (rs.next()) {
				        //InputStream in = rs.getBinaryStream("pic");
				
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(announcementImg));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/shop/images/no.png");
				byte[] b =new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();				
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			//System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/shop/images/no.png");
			byte[] b =new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();		
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CGA101G3");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}