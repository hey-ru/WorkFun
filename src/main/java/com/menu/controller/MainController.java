package com.menu.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import babroval.testtask.dao.UserDAO;
import babroval.testtask.dao.factory.DAOFactory;
import babroval.testtask.dao.factory.StoradgeTypes;
import babroval.testtask.entities.User;
import babroval.testtask.utils.RequestUtils;
import babroval.testtask.utils.StringUtils;

@WebServlet("/main.htm")
public class MainController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String CSV_FILE = "/WEB-INF/users.csv";

	private static final String IMPORT = "import";

	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		List<User> allUsers = new ArrayList<User>();
		List<User> usersOnPage = new ArrayList<User>();

		Boolean isImport = RequestUtils.isParameterExist(request, IMPORT);

		if (isImport) {
			UserDAO dao = DAOFactory.getFactory(StoradgeTypes.Csv).getUserDAO();

			ServletContext context = getServletContext();

			String path = context.getRealPath(CSV_FILE);
			File csvFile = new File(path);
			allUsers = dao.loadAllUsers(csvFile);

			dao = DAOFactory.getFactory(StoradgeTypes.MySql).getUserDAO();
			dao.storeUsers(allUsers);

			request.setAttribute("info", "CSV file successfully imported into database");

			RequestDispatcher rd = request.getRequestDispatcher(VIEW_INFO);
			rd.forward(request, response);
			return;
		}
	}

		
	
}
