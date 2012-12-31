package businessLogic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tracking {
	
	@Id
	private int tracking_id;
	private String date;
	private int status;
	private int query_id;
	private String user_id;
	
	
	public Tracking(){
		
	}


	public int getTracking_id() {
		return tracking_id;
	}


	public void setTracking_id(int tracking_id) {
		this.tracking_id = tracking_id;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getQuery_id() {
		return query_id;
	}


	public void setQuery_id(int query_id) {
		this.query_id = query_id;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}
