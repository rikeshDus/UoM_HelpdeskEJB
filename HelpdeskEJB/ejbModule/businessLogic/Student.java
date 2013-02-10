package businessLogic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
	private String student_id;
	private String user_id;
	private int cpa;
	private int lpa;
	private int gpa;
	private String course_code;
	private int credit;
	private int year;
	
	public Student() {
		
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getCpa() {
		return cpa;
	}

	public void setCpa(int cpa) {
		this.cpa = cpa;
	}

	public int getLpa() {
		return lpa;
	}

	public void setLpa(int lpa) {
		this.lpa = lpa;
	}

	public int getGpa() {
		return gpa;
	}

	public void setGpa(int gpa) {
		this.gpa = gpa;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
}
