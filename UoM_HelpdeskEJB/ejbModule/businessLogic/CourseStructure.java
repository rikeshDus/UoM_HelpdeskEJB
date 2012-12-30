package businessLogic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CourseStructure {
	@Id
	private int course_structure_id;
	private String course_code;
	private String module_code;
	
	public CourseStructure(){
		
	}

	public int getCourse_structure_id() {
		return course_structure_id;
	}

	public void setCourse_structure_id(int course_structure_id) {
		this.course_structure_id = course_structure_id;
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}

	public String getModule_code() {
		return module_code;
	}

	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}
	
	
}
