package businessLogic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coordinator {
	@Id
	private int coordinator_id;
	private String module_code;
	private String course_code;
	private String staff_id;
	private String date;//sql timestamp
	
	public Coordinator()
	{
		
	}

	public int getCoordinator_id() {
		return coordinator_id;
	}

	public void setCoordinator_id(int coordinator_id) {
		this.coordinator_id = coordinator_id;
	}

	public String getModule_code() {
		return module_code;
	}

	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
