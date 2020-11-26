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
import co.events.model.Ticket;

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
	
	public Ticket insertTicket(Ticket ticket) throws SQLException {
    	Ticket o = null;
        String sql = "INSERT INTO tickets (user_id, event_id, ticket_used, ticket_purchase_time) VALUES (?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, ticket.getUser_id());
        statement.setInt(2, ticket.getEvent_id());
        statement.setBoolean(3, ticket.getUsed());
//        statement.setTimestamp(4, ticket.getPurchase_time());
        statement.setTimestamp(4, new Timestamp (ticket.getPurchase_time().getTime()));
         
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
	
    public List<Ticket> myTickets(int id) throws SQLException {
        List<Ticket> listTicket = new ArrayList<>();
         
        String sql = "SELECT * FROM events, tickets WHERE tickets.event_id = events.event_id AND tickets.user_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
        
        ResultSet resultSet = statement.executeQuery();
         
        while (resultSet.next()) {
            String name = resultSet.getString("event_name");
            Date start = new Date(resultSet.getTimestamp("event_start_time").getTime());
            Date purchase = new Date(resultSet.getTimestamp("ticket_purchase_time").getTime());
            int ticket_id = resultSet.getInt("ticket_id");
            
            
            Ticket ticket = new Ticket(ticket_id, name, purchase, start);
//            System.out.println(name + purchase.toString());
            listTicket.add(ticket);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listTicket;
    }
    
    public void updateTicket(Ticket ticket) throws SQLException {
        String sql = "UPDATE tickets SET ticket_used = 1 WHERE ticket_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, ticket.getTicket_id());
         
        statement.executeUpdate();
        
        statement.close();
        disconnect();
    }
    
    public int check(Ticket ticket) throws SQLException {
        String sql = "SELECT * from tickets WHERE tickets.ticket_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, ticket.getTicket_id());
         
    	ResultSet res = statement.executeQuery();
        
        if (res.next()) {
        	int event_id = res.getInt("event_id");
        	boolean used = res.getBoolean("ticket_used");
        	

            statement.close();
            disconnect();
        	
        	if(used) return 3;
        	if(event_id != ticket.getEvent_id()) return 2;
        	updateTicket(ticket);
        	return 1;
        }
        return 0;
    }
}
