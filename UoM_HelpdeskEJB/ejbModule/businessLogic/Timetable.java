package businessLogic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Timetable {
	@Id
	private int timetable_id;
	private int Structure_id;
	private String datetime;//will contain datetime type from mysql
	private int duration;
	private String  lecture_class;
	private String staff_id;
	
	public Timetable(){
		
	}

	public int getTimetable_id() {
		return timetable_id;
	}

	public void setTimetable_id(int timetable_id) {
		this.timetable_id = timetable_id;
	}

	public int getStructure_id() {
		return Structure_id;
	}

	public void setStructure_id(int structure_id) {
		Structure_id = structure_id;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getLecture_class() {
		return lecture_class;
	}

	public void setLecture_class(String lecture_class) {
		this.lecture_class = lecture_class;
	}

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	
	
	
	
	
}
