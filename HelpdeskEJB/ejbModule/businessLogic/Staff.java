package businessLogic;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Staff {
	@Id
	private String staff_id;
	private String user_id;
	private String working_description;
	private String position;
	private int salary;
	
	
	
	
	public Staff() {
	}
	
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getWorking_description() {
		return working_description;
	}
	public void setWorking_description(String working_description) {
		this.working_description = working_description;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
	
}

