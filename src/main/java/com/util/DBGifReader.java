package com.util;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/util/DBGifReader")
public class DBGifReader extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Connection con = null;
		
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CGA101G3");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String id_key= req.getParameter("id_key").trim();
			String id= req.getParameter("id").trim();
			String table= req.getParameter("table").trim();
			String pic= req.getParameter("pic").trim();
			ResultSet rs = stmt.executeQuery(
				"select "+pic+" from "+table+" where "+id_key+" ="+id);
	
			

			if (rs.next()) {
				        //InputStream in = rs.getBinaryStream("pic");
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(pic));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/design/text_mu.png");
				byte[] b =new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();				
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			//System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/design/text_mu.png");
			byte[] b =new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();		
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	
	

}