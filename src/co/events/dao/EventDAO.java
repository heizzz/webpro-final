package co.events.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.events.model.Event;

public class EventDAO {
	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public EventDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null || !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	
    public List<Event> allEvents() throws SQLException {
        List<Event> listEvent = new ArrayList<>();
         
        String sql = "SELECT * FROM events";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int event_id = resultSet.getInt("event_id");
            int user_id = resultSet.getInt("user_id");
            String name = resultSet.getString("event_name");
            int price = resultSet.getInt("event_price");
            String place = resultSet.getString("event_place");
            String desc = resultSet.getString("event_description");
            Date start = new Date(resultSet.getTimestamp("event_start_time").getTime());
            Date end = new Date(resultSet.getTimestamp("event_end_time").getTime());
            
            Event event = new Event(event_id, user_id, name, price, place, desc, start, end);
            listEvent.add(event);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listEvent;
    }
	
    public Event insertEvent(Event event) throws SQLException {
    	Event o = null;
        String sql = "INSERT INTO events (user_id, event_name, event_price, event_place, event_description, event_start_time, event_end_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, event.getUser_id());
        statement.setString(2, event.getName());
        statement.setInt(3, event.getPrice());
        statement.setString(4, event.getPlace());
        statement.setString(5, event.getDescription());
        statement.setTimestamp(6, new Timestamp(event.getStart_time().getTime()));
        statement.setTimestamp(7, new Timestamp(event.getEnd_time().getTime()));
         
        statement.executeUpdate();
    	ResultSet res = statement.getGeneratedKeys();
        
        if (res.next()) {
        	o = event;
        	o.setEvent_id(res.getInt(1));
        }

        statement.close();
        disconnect();
        
        return o;
    }
	
    public boolean deleteEvent(Event event) throws SQLException {
        String sql = "DELETE FROM events where event_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, event.getEvent_id());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
    
    public Event updateEvent(Event event) throws SQLException {
    	Event o = null;
        String sql = "UPDATE events SET user_id = ?, event_name = ?, event_price = ?, event_place = ?, event_description = ?, event_start_time = ?, event_end_time = ? WHERE event_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, event.getUser_id());
        statement.setString(2, event.getName());
        statement.setInt(3, event.getPrice());
        statement.setString(4, event.getPlace());
        statement.setString(5, event.getDescription());
        statement.setTimestamp(6, new Timestamp(event.getStart_time().getTime()));
        statement.setTimestamp(7, new Timestamp(event.getEnd_time().getTime()));
        statement.setInt(8, event.getEvent_id());
         
        statement.executeUpdate();
        
        o = event;
        o.setEvent_id(event.getEvent_id());
        statement.close();
        disconnect();
        
        return o;  
    }
     
    public Event getEvent(int id) throws SQLException {
        Event event = null;
        String sql = "SELECT * FROM events WHERE event_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	int user_id = resultSet.getInt("user_id");
            String name = resultSet.getString("event_name");
            int price = resultSet.getInt("event_price");
            String place = resultSet.getString("event_place");
            String desc = resultSet.getString("event_description");
            Date start = new Date(resultSet.getTimestamp("event_start_time").getTime());
            Date end = new Date(resultSet.getTimestamp("event_end_time").getTime());
             
            event = new Event(user_id, name, price, place, desc, start, end);
        }
         
        resultSet.close();
        statement.close();
         
        return event;
    }
    
}
