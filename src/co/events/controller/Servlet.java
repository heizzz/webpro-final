package co.events.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.events.dao.EventDAO;
import co.events.dao.UserDAO;
import co.events.model.Event;
import co.events.model.User;

public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
	private EventDAO eventDAO;
	
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
		try {
		switch (path) {
		case "/":
			welcome(request, response);
			break;
		case "/register":
			userRegisterGet(request, response);
			break;
		case "/login":
			userLoginGet(request, response);
			break;
		case "/home":
			listEvent(request, response);
		case "/create":
            insertEvent(request, response);
            break;
        case "/delete":
            deleteEvent(request, response);
            break;
        case "/edit":
            showEditForm(request, response);
            break;
        case "/update":
            updateEvent(request, response);
            break;
		}
		 } catch (SQLException ex) {
	            throw new ServletException(ex);
	        }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		try {
			switch (path) {
			case "/register":
				userRegisterPost(request, response);
				break;
			case "/login":
				userLoginPost(request, response);
				break;
				
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
	
	private void welcome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("welcome.jsp");
		dispatch.forward(request, response);
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
	
	private void userLoginGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
		dispatch.forward(request, response);
	}
	
	private void userLoginPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User newUser = new User(email, password);
		boolean result = userDAO.login(newUser);
		if (result) {
			response.sendRedirect("home");
		} else {
			response.sendRedirect("login");
		}
	}
    private void listEvent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Event> listEvent = eventDAO.allEvents();
        request.setAttribute("listEvent", listEvent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("eventForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Event existingEvent = eventDAO.getEvent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("eventForm.jsp");
        request.setAttribute("event", existingEvent);
        dispatcher.forward(request, response);
 
    }
    
    // belum handle user_id (pake session)
    private void insertEvent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
    	int user_id = 1;
        String name  = request.getParameter("title");
        int price = Integer.parseInt(request.getParameter("author"));
        String place = request.getParameter("author");
        String desc = request.getParameter("author");
        String start1 = request.getHeader("author");
        String end1 = request.getHeader("author");
        Timestamp start = null;
        Timestamp end = null;
        Date start2;
		try {
			start2 = dateFormat.parse(start1);
			start = new java.sql.Timestamp(start2.getTime());
			Date end2 = dateFormat.parse(end1);
	        end = new java.sql.Timestamp(end2.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Event newEvent = new Event(user_id, name, price, place, desc, start, end);
        eventDAO.insertEvent(newEvent);
        response.sendRedirect("list");
    }
 
    private void updateEvent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));
 
//        Event event = new Event(id, title, author, price);
//        eventDAO.updateEvent(event);
        response.sendRedirect("list");
    }
 
    private void deleteEvent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Event event = new Event(id);
        eventDAO.deleteEvent(event);
        response.sendRedirect("list");
 
    }
}
