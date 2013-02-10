package businessLogic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {
	@Id
	private String course_code;
	private String name;
	private String descrition;
	private String type;
	private int diploma_exit;
	private int degree_exit;
	
	
	public Course(){
		
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getDegree_exit() {
		return degree_exit;
	}

	public void setDegree_exit(int degree_exit) {
		this.degree_exit = degree_exit;
	}

	public int getDiploma_exit() {
		return diploma_exit;
	}

	public void setDiploma_exit(int diploma_exit) {
		this.diploma_exit = diploma_exit;
	}
	
	
}
