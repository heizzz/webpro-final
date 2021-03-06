package co.events.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.events.helper.Crypto;
import co.events.model.User;

public class UserDAO {
	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public boolean register(User user) throws SQLException {
		String sql = "INSERT INTO users (user_name, user_email, user_password) VALUES (?, ?, ?);";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, user.getName());
		statement.setString(2, user.getEmail());
		statement.setString(3, Crypto.hash(user.getPassword()));
		
		boolean result = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return result;
	}
	
	public User login(User user) throws SQLException {
		User out = null;
		String sql = "SELECT user_id, user_name, user_email, user_password FROM users WHERE user_email = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, user.getEmail());
		
		ResultSet res = statement.executeQuery();
		
		if (res.next()) {
			if (Crypto.hash(user.getPassword()).equals(res.getString("user_password"))) out = new User(res.getInt("user_id"), res.getString("user_name"), res.getString("user_email"), res.getString("user_password"));
		}
		
		res.close();
		statement.close();
		disconnect();
		return out;
	}
}
