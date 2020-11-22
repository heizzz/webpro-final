package co.events.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Timestamp;

import co.events.helper.Crypto;
import co.events.model.Event;
import co.events.model.User;

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
            int user_id = resultSet.getInt("user_id");
            String name = resultSet.getString("event_name");
            int price = resultSet.getInt("event_price");
            String place = resultSet.getString("event_place");
            String desc = resultSet.getString("event_description");
            Timestamp start = resultSet.getTimestamp("event_start_time");
            Timestamp end = resultSet.getTimestamp("event_end_time");
            
            Event event = new Event(user_id, name, price, place, desc, start, end);
            listEvent.add(event);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listEvent;
    }
	
    public boolean insertEvent(Event event) throws SQLException {
        String sql = "INSERT INTO events (user_id, event_name, event_price, event_place, event_description, event_start_time, event_end_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, event.getUser_id());
        statement.setString(2, event.getName());
        statement.setInt(3, event.getPrice());
        statement.setString(4, event.getPlace());
        statement.setString(5, event.getDescription());
        statement.setTimestamp(6, event.getStart_time());
        statement.setTimestamp(7, event.getEnd_time());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
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
    
    public boolean updateEvent(Event event) throws SQLException {
        String sql = "UPDATE events SET user_id = ?, event_name = ?, event_price = ?, event_place = ?, event_description = ?, event_start_time = ?, event_end_time = ?";
        sql += " WHERE event_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, event.getUser_id());
        statement.setString(2, event.getName());
        statement.setInt(3, event.getPrice());
        statement.setString(4, event.getPlace());
        statement.setString(5, event.getDescription());
        statement.setTimestamp(6, event.getStart_time());
        statement.setTimestamp(7, event.getEnd_time());
        statement.setInt(8, event.getEvent_id());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
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
            Timestamp start = resultSet.getTimestamp("event_start_time");
            Timestamp end = resultSet.getTimestamp("event_end_time");
             
            event = new Event(user_id, name, price, place, desc, start, end);
        }
         
        resultSet.close();
        statement.close();
         
        return event;
    }
    
}
