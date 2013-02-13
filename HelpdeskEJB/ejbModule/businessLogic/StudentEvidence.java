package businessLogic;

import java.sql.Date;

public class StudentEvidence {
	private String student_id;
	private String evidence;
	private String url;
	private Date date;
	private String status;
	private String exam_period;
	
	
	public StudentEvidence() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getEvidence() {
		return evidence;
	}
	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getExam_period() {
		return exam_period;
	}
	public void setExam_period(String exam_period) {
		this.exam_period = exam_period;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
}
