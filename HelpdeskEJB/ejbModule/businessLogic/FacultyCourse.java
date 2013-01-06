package businessLogic;

public class FacultyCourse {
	private int faculty_course_id;
	private int faculty_id;
	private String course_code;
	
	public FacultyCourse(){
		
	}

	public int getFaculty_course_id() {
		return faculty_course_id;
	}

	public void setFaculty_course_id(int faculty_course_id) {
		this.faculty_course_id = faculty_course_id;
	}

	public int getFaculty_id() {
		return faculty_id;
	}

	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}
	
	
	
}
