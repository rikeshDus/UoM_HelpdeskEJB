package businessLogic;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class ModuleRegistration {
	@Id
	private int module_registration_id;
	private String student_id;
	private String module_code;
	private Date date;
	
	
	public ModuleRegistration(){
		
	}


	public int getModule_registration_id() {
		return module_registration_id;
	}


	public void setModule_registration_id(int module_registration_id) {
		this.module_registration_id = module_registration_id;
	}


	public String getStudent_id() {
		return student_id;
	}


	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}


	public String getModule_code() {
		return module_code;
	}


	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}

