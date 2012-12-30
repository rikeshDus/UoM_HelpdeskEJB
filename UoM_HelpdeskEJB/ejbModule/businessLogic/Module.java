package businessLogic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Module {
	@Id
	private String module_code;
	private String name;
	private String syllabus;
	
	public Module(){
		
	}

	public String getModule_code() {
		return module_code;
	}

	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}
	
	
}
