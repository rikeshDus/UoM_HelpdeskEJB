package businessLogic;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class TrackingLog{
	@Id
	private int tracking_id;
	private String sender_id;
	private int query_id;
	private String initial_receiver_id;
	private int status;
	private Date date_send;
		
	public TrackingLog() {
	 
	}
	
	
	public int getTracking_id() {
		return tracking_id;
	}
	public void setTracking_id(int tracking_id) {
		this.tracking_id = tracking_id;
	}
	public String getSender_id() {
		return sender_id;
	}
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}
	public int getQuery_id() {
		return query_id;
	}
	public void setQuery_id(int query_id) {
		this.query_id = query_id;
	}
	public String getInitial_receiver_id() {
		return initial_receiver_id;
	}
	public void setInitial_receiver_id(String initial_receiver_id) {
		this.initial_receiver_id = initial_receiver_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getDate_send() {
		return date_send;
	}
	public void setDate_send(Date date_send) {
		this.date_send = date_send;
	}
	
	
	
}//end class


/*
@Entity
public class TrackingLog {
	@Id
	private int tracking_log_id;
	private int tracking_id;
	private int question;
	private Date date;
	private String reciever;
	
	public TrackingLog(){}
	
	public int getTracking_log_id() {
		return tracking_log_id;
	}
	public void setTracking_log_id(int tracking_log_id) {
		this.tracking_log_id = tracking_log_id;
	}
	public int getTracking_id() {
		return tracking_id;
	}
	public void setTracking_id(int tracking_id) {
		this.tracking_id = tracking_id;
	}
	public int getQuestion() {
		return question;
	}
	public void setQuestion(int question) {
		this.question = question;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	
	
	
}
*/