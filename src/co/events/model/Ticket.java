package co.events.model;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Ticket implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int ticket_id;
	private int event_id;
	private int user_id;
	private Boolean used;
	private Date purchase_time;
	private Detail detail;
	

	class Detail{
		private String event_name;
		private Date event_time;
		
		private Detail(String event_name, Date event_time) {
			this.event_name = event_name;
			this.event_time = event_time;
		}
	}
	
	public Ticket(int ticket_id, String event_name, Date purchase, Date event_time, Boolean used) {
		this.ticket_id = ticket_id;
		this.purchase_time = purchase;
		this.used = used;
		this.detail = new Detail(event_name, event_time);
	}
	public Ticket(int user_id, int event_id, Boolean used, Timestamp purchase_time) {
		super();
		this.event_id = event_id;
		this.user_id = user_id;
		this.used = used;
		this.purchase_time = purchase_time;
	}
	public Ticket(int ticket_id, int event_id) {
		this.ticket_id = ticket_id;
		this.event_id = event_id;
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
	public Date getPurchase_time() {
		return purchase_time;
	}
	public void setPurchase_time(Timestamp purchase_time) {
		this.purchase_time = purchase_time;
	}
	public Detail getDetail() {
		return detail;
	}
	public void setDetail(Detail detail) {
		this.detail = detail;
	}
	public Boolean isUsed() {
		if (this.used) return true;
		else return false;
	}
	
	public String getEvent_name() {
		return detail.event_name;
	}

	public void setEvent_name(String event_name) {
		this.detail.event_name = event_name;
	}

	public Date getEvent_time() {
		return detail.event_time;
	}

	public void setEvent_time(Date event_time) {
		this.detail.event_time = event_time;
	}
	
}

//class Detail{
//	private int ticket_id;
//	private String event_name;
//	private Date purchase;
//	private Date event_time;
//	private boolean used;
//	
//	public Detail(int ticket_id, String event_name, Date purchase, Date event_time) {
//		this.ticket_id = ticket_id;
//		this.event_name = event_name;
//		this.purchase = purchase;
//		this.event_time = event_time;
//		this.used = false;
//	}
//	
//	public int getTicket_id() {
//		return ticket_id;
//	}
//
//	public void setTicket_id(int ticket_id) {
//		this.ticket_id = ticket_id;
//	}
//
//	public String getEvent_name() {
//		return event_name;
//	}
//
//	public void setEvent_name(String event_name) {
//		this.event_name = event_name;
//	}
//
//	public Date getPurchase() {
//		return purchase;
//	}
//
//	public void setPurchase(Date purchase) {
//		this.purchase = purchase;
//	}
//
//	public Date getEvent_time() {
//		return event_time;
//	}
//
//	public void setEvent_time(Date event_time) {
//		this.event_time = event_time;
//	}
//
//	public boolean isUsed() {
//		return used;
//	}
//	public void setUsed(boolean used) {
//		this.used = used;
//	}
//	
//}
