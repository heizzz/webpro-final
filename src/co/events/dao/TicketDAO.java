package co.events.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import co.events.model.Ticket;
import co.events.model.User;

public class TicketDAO {
	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public TicketDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public boolean myTickets(User user) throws SQLException {
		boolean out = false;
		String sql = "SELECT * FROM tickets WHERE user_email = ?";
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
	
	public Ticket insertTicket(Ticket ticket) throws SQLException {
    	Ticket o = null;
        String sql = "INSERT INTO tickets (user_id, event_id, ticket_used, ticket_purchase_time) VALUES (?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, ticket.getUser_id());
        statement.setInt(2, ticket.getEvent_id());
        statement.setBoolean(3, ticket.getUsed());
        statement.setTimestamp(4, ticket.getPurchase_time());
         
        statement.executeUpdate();
    	ResultSet res = statement.getGeneratedKeys();
        
        if (res.next()) {
        	o = ticket;
        	o.setTicket_id(res.getInt(1));
        }

        statement.close();
        disconnect();
        
        return o;
    }
}
