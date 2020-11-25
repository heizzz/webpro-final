package co.events.model;
import java.io.Serializable;
import java.sql.Timestamp;

public class Ticket implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int ticket_id;
	private int event_id;
	private int user_id;
	private Boolean used;
	private Timestamp purchase_time;
	
	public Ticket(int user_id, int event_id, Boolean used, Timestamp purchase_time) {
		super();
		this.event_id = event_id;
		this.user_id = user_id;
		this.used = used;
		this.purchase_time = purchase_time;
	}
	
	public int getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public int getEvent_id() {
		return event_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public Boolean getUsed() {
		return used;
	}
	public void setUsed(Boolean used) {
		this.used = used;
	}
	public Timestamp getPurchase_time() {
		return purchase_time;
	}
	public void setPurchase_time(Timestamp purchase_time) {
		this.purchase_time = purchase_time;
	}
	
}
