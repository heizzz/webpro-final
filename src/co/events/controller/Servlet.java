package co.events.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		eventDAO = new EventDAO(jdbcURL, jdbcUsername, jdbcPassword);
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
			case "/logout":
				userLogout(request, response);
				break;
			case "/dashboard":
				dashboard(request, response);
				break;
			case "/create":
	            eventCreateGet(request, response);
	            break;
	        case "/edit":
	            eventEditGet(request, response);
	            break;
	        case "/delete":
	            eventDelete(request, response);
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
			case "/create":
	            eventCreatePost(request, response);
	            break;
	        case "/edit":
	            eventEditPost(request, response);
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
		HttpSession session = request.getSession();
		if (session.getAttribute("name") != null) {
			response.sendRedirect("dashboard");
			return;
		}
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
		HttpSession session = request.getSession();
		if (session.getAttribute("name") != null) {
			response.sendRedirect("dashboard");
			return;
		}
		RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
		dispatch.forward(request, response);
	}
	
	private void userLoginPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User formUser = new User(email, password);
		User user = userDAO.login(formUser);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("name", user.getName());
			session.setAttribute("id", user.getUser_id());
			response.sendRedirect("dashboard");
		} else {
			response.sendRedirect("login");
		}
	}
	
	private void userLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(".");
	}
	
    private void dashboard(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Event> listEvent = eventDAO.allEvents();
        request.setAttribute("listEvent", listEvent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
 
    private void eventCreateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("eventForm.jsp");
        dispatcher.forward(request, response);
    }
    
    private void eventCreatePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		HttpSession session = request.getSession();
		int user_id = (int) session.getAttribute("id");
        String name  = request.getParameter("event_name");
        int price = Integer.parseInt(request.getParameter("event_price"));
        String place = request.getParameter("event_place");
        String desc = request.getParameter("event_description");
        SimpleDateFormat startD = new SimpleDateFormat("MMMM, dd yyyy hh:mm a");
        SimpleDateFormat endD = new SimpleDateFormat("MMMM, dd yyyy hh:mm a");
        Timestamp start;
        Timestamp end;
		try {
			start = new Timestamp(startD.parse(request.getParameter("event_start")).getTime());
			end = new Timestamp(endD.parse(request.getParameter("event_end")).getTime());
	        
	        Event newEvent = new Event(user_id, name, price, place, desc, start, end);
	        Event complete = eventDAO.insertEvent(newEvent);
	        response.sendRedirect("detail?id=" + complete.getEvent_id());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
 
    private void eventEditGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Event existingEvent = eventDAO.getEvent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("eventForm.jsp");
        request.setAttribute("event", existingEvent);
        dispatcher.forward(request, response);
    }
    
    private void eventEditPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		HttpSession session = request.getSession();
		int event_id = Integer.parseInt(request.getParameter("id"));
		int user_id = (int) session.getAttribute("id");
        String name  = request.getParameter("event_name");
        int price = Integer.parseInt(request.getParameter("event_price"));
        String place = request.getParameter("event_place");
        String desc = request.getParameter("event_description");
        SimpleDateFormat startD = new SimpleDateFormat("MMMM, dd yyyy hh:mm a");
        SimpleDateFormat endD = new SimpleDateFormat("MMMM, dd yyyy hh:mm a");
        Timestamp start;
        Timestamp end;
		try {
			start = new Timestamp(startD.parse(request.getParameter("event_start")).getTime());
			end = new Timestamp(endD.parse(request.getParameter("event_end")).getTime());
	        
	        Event newEvent = new Event(event_id, user_id, name, price, place, desc, start, end);
	        eventDAO.updateEvent(newEvent);
	        response.sendRedirect("detail?id=" + newEvent.getEvent_id());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
 
    private void eventDelete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Event event = new Event(id);
        eventDAO.deleteEvent(event);
        response.sendRedirect("profile");
    }
}
