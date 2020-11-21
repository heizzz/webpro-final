package co.events.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.events.dao.UserDAO;
import co.events.model.User;

public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
	
	@Override
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		
		userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
//		try {
			switch (path) {
			case "/register":
				userRegisterGet(request, response);
				break;
			}
//		} catch (SQLException e) {
//			throw new ServletException(e);
//		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		try {
			switch (path) {
			case "/register":
				userRegisterPost(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
	
	private void userRegisterGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("register.jsp");
		dispatch.forward(request, response);
	}
	
	private void userRegisterPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		User newUser = new User(name, email, password);
		userDAO.register(newUser);
		response.sendRedirect("login");
	}
}
