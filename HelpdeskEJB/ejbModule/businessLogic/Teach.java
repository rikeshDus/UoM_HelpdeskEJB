package businessLogic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Teach {
	@Id
	private int teach_id;
	private String staff_id;
	private String module_code;
	private String date;//will contain timstamp type of mysql
	
	public Teach(){
		
	}

	public int getTeach_id() {
		return teach_id;
	}

	public void setTeach_id(int teach_id) {
		this.teach_id = teach_id;
	}

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	public String getModule_code() {
		return module_code;
	}

	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
