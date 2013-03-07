package businessLogic;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Foward {
	@Id
	private int foward_id;
	private int tracking_id;
	private int query_id;
	private String receiver_id;
	private String next_receiver_id;
	private Date date_foward;
	
	public Foward() {}
	
	public int getFoward_id() {
		return foward_id;
	}
	public void setFoward_id(int foward_id) {
		this.foward_id = foward_id;
	}
	public int getTracking_id() {
		return tracking_id;
	}
	public void setTracking_id(int tracking_id) {
		this.tracking_id = tracking_id;
	}
	public int getQuery_id() {
		return query_id;
	}
	public void setQuery_id(int query_id) {
		this.query_id = query_id;
	}
	public String getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}
	public String getNext_receiver_id() {
		return next_receiver_id;
	}
	public void setNext_receiver_id(String next_receiver_id) {
		this.next_receiver_id = next_receiver_id;
	}
	public Date getDate_foward() {
		return date_foward;
	}
	public void setDate_foward(Date date_foward) {
		this.date_foward = date_foward;
	}
	
	
	
	
}
