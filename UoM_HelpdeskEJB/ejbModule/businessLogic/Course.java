package businessLogic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {
	@Id
	private String course_code;
	private String descrition;
	private String type;
	
	public Course(){
		
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}

	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
