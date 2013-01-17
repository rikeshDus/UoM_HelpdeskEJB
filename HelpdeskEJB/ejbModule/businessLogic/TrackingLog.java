package businessLogic;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;


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
