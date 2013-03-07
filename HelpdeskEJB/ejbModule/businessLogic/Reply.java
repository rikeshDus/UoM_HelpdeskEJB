package businessLogic;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reply {
	@Id
	private int tracking_id;
	private String recieve_id;
	private int query_id;
	private Date date_reply;
	private String answer;
	
	public Reply() {}

	public int getTracking_id() {
		return tracking_id;
	}

	public void setTracking_id(int tracking_id) {
		this.tracking_id = tracking_id;
	}

	public String getRecieve_id() {
		return recieve_id;
	}

	public void setRecieve_id(String recieve_id) {
		this.recieve_id = recieve_id;
	}

	public int getQuery_id() {
		return query_id;
	}

	public void setQuery_id(int query_id) {
		this.query_id = query_id;
	}

	public Date getDate_reply() {
		return date_reply;
	}

	public void setDate_reply(Date date_reply) {
		this.date_reply = date_reply;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
		
	
}
