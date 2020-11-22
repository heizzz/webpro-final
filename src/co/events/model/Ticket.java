package co.events.model;
import java.io.Serializable;

public class Ticket implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Boolean used;
	private String purchase_time;
	
	public Boolean getUsed() {
		return used;
	}
	public void setUsed(Boolean used) {
		this.used = used;
	}
	public String getPurchase_time() {
		return purchase_time;
	}
	public void setPurchase_time(String purchase_time) {
		this.purchase_time = purchase_time;
	}
	
}
