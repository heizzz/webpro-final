package co.events.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public boolean allEvents() throws SQLException {
		boolean out = false;
		String sql = "SELECT * FROM events";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		
		ResultSet res = statement.executeQuery();
		
		if (res.next()) {
//			if (Crypto.hash(user.getPassword()).equals(res.getString("user_password"))) out = true;
		}
		
		res.close();
		statement.close();
		disconnect();
		return out;
	}
	
	public boolean myEvents(User user) throws SQLException {
		boolean out = false;
		String sql = "SELECT * FROM events WHERE user_email = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, user.getEmail());
		
		ResultSet res = statement.executeQuery();
		
		if (res.next()) {
//			if (Crypto.hash(user.getPassword()).equals(res.getString("user_password"))) out = true;
		}
		
		res.close();
		statement.close();
		disconnect();
		return out;
	}
	
}
