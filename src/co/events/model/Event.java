package co.events.model;
import java.io.Serializable;
import java.sql.Timestamp;

public class Event implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int event_id;
	
	private int user_id;
	private String name;
	private int price;
	private String place;
	private String description;
	private Timestamp start_time;
	private Timestamp end_time;
	
	public Event(int user_id, String name, int price, String place, String description, Timestamp start_time,Timestamp end_time) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.price = price;
		this.place = place;
		this.description = description;
		this.start_time = start_time;
		this.end_time = end_time;
	}
	public Event(int event_id) {
		super();
		this.event_id = event_id;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	public Timestamp getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	
	
}
