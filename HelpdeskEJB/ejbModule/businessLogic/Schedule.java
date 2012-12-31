package businessLogic;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Schedule {
	@Id
	private int schedule_id;
	private int duration;
	private String time;//will contain time type of mysql
	private Date date;
	
	
	public Schedule(){
		
	}


	public int getSchedule_id() {
		return schedule_id;
	}


	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
