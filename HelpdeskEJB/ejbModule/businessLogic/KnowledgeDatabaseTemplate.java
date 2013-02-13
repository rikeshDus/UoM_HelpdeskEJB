package businessLogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import jess.*;

public class KnowledgeDatabaseTemplate {
	static FileOutputStream outFile;
	public FileOutputStream saveKnowledgeBase(){
		try {
			outFile = new FileOutputStream(new File("knowledgebase.clp"));
			
			new PrintStream(outFile).println ("(deftemplate answer " +
					" \"Answer table from database\" " +
					"(slot answer_id (type INTEGER)(default 0))" +
					"(slot question_id (type INTEGER)(default 0))" +
					"(slot answer (type String))" +
					")");
			
			new PrintStream(outFile).println ("(deftemplate coordinator " +
					"\"coordinator table from databse\"" +
					"(slot coordinator_id)" +
					"(slot module_code)" +
					"(slot course_code)" +
					"(slot staff_id)" +
					"(slot date)" +
					")");
			
			new PrintStream(outFile).println ("(deftemplate course" +
					" \"course tbale from database\" " +
					"(slot course_code)" +
					"(slot name)" +
					"(slot description)" +
					"(slot type)" +
					"(slot diploma_exit (type INTEGER))" +
					"(slot degree_exit (type INTEGER))" +
					")");
			
			new PrintStream(outFile).println ("(deftemplate course_structure" +
					" \"\" " +
					"(slot course_structure_id)" +
					"(slot course_code)" +
					"(slot module_code)" +
					")");
			
			
			new PrintStream(outFile).println ("(deftemplate event" +
					" \"table event\" " +
					"(slot event_id)" +
					"(slot title)" +
					"(slot description)" +
					"(slot type)" +
					"(slot user_id)" +
					")");
			
			new PrintStream(outFile).println ("(deftemplate faculty" +
					" \"\" " +
					"(slot faculty_id)" +
					"(slot name)" +
					")");
			
			new PrintStream(outFile).println ("(deftemplate faculty_course" +
					" \"\" " +
					"(slot faculty_course_id)" +
					"(slot faculty_id)" +
					"(slot course_code)" +
					")");
			
			new PrintStream(outFile).println ("(deftemplate module" +
					" \"\" " +
					"(slot module_code)" +
					"(slot name)" +
					"(slot syllabus)" +
					")");
			
			new PrintStream(outFile).println ("(deftemplate module_registration" +
					" \"\" " +
					"(slot module_registration_id)" +
					"(slot student_id)" +
					"(slot module_code)" +
					"(slot date)" +
					")");
			
			
			new PrintStream(outFile).println ("(deftemplate query" +
					" \"\" " +
					"(slot query_id)" +
					"(slot description)" +
					"(slot date)" +
					"(slot user_id)" +
					"(slot type)" +
					")");
			
			
			new PrintStream(outFile).println ("(deftemplate question" +
					" \"\" " +
					"(slot question_id)" +
					"(slot question)" +
					")");
			
			
			new PrintStream(outFile).println ("(deftemplate schedule" +
					" \"\" " +
					"(slot schedule_id)" +
					"(slot duration)" +
					"(slot starttime)" +
					"(slot date)" +
					"(slot event_id)" +
					")");
			
			new PrintStream(outFile).println ("(deftemplate staff" +
					" \"\" " +
					"(slot staff_id)" +
					"(slot user_id)" +
					"(slot working_description)" +
					"(slot position)" +
					"(slot salary (type INTEGER))" +
					")");
			
			
			new PrintStream(outFile).println ("(deftemplate student" +
					" \"\" " +
					"(slot student_id)" +
					"(slot user_id)" +
					"(slot cpa (type INTEGER))" +
					"(slot lpa (type INTEGER))" +
					"(slot gpa (type INTEGER))" +
					"(slot course_code)" +
					"(slot credit (type INTEGER))" +
					"(slot year (type INTEGER))" +
					"(slot end_academic_year (DEFAULT \"false\"))" +
					")");
			
			new PrintStream(outFile).println("(deftemplate student_evidence" +
					" \"\" " +
					"(slot student_id)" +
					"(slot evidence)" +
					"(slot url)" +
					"(slot date)" +
					"(slot status)" +
					"(slot exam_period (DEFAULT \"false\"))" +
					"(slot current_exam_period (DEFAULT \"false\"))" +
					")");
			
			
			
			new PrintStream(outFile).println("(deftemplate teach" +
					" \"\" " +
					"(slot teach_id)" +
					"(slot staff_id)" +
					"(slot module_code)" +
					"(slot date)" +
					")");
			
			new PrintStream(outFile).println("(deftemplate timetable" +
					" \"\" " +
					"(slot timetable_id)" +
					"(slot structure_id)" +
					"(slot starttime)" +
					"(slot day)" +
					"(slot duration)" +
					"(slot class)" +
					"(slot staff_id)" +
					")");
			
			new PrintStream(outFile).println("(deftemplate tracking" +
					" \"\" " +
					"(slot tracking_id)" +
					"(slot date)" +
					"(slot status)" +
					"(slot query_id)" +
					"(slot user_id)" +
					")");
			
			new PrintStream(outFile).println("(deftemplate tracking_log" +
					" \"\" " +
					"(slot tracking_log_id)" +
					"(slot tracking_id)" +
					"(slot question_id)" +
					"(slot date)" +
					"(slot receiver)" +
					")");
			
			
			new PrintStream(outFile).println("(deftemplate user" +
					" \"\" " +
					"(slot user_id)" +
					"(slot password)" +
					"(slot name)" +
					"(slot surname)" +
					"(slot house_number)" +
					"(slot locality)" +
					"(slot country)" +
					"(slot email)" +
					"(slot dob)" +
					")");
					
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return outFile;
	}
	
	
	public Rete getDataBaseTemplates(){
		Rete engine = new Rete();
		
		try {
			engine.eval("(deftemplate answer " +
					" \"Answer table from database\" " +
					"(slot answer_id (type INTEGER)(default 0))" +
					"(slot question_id (type INTEGER)(default 0))" +
					"(slot answer (type String))" +
					")");
			
			
			engine.eval("(deftemplate coordinator " +
					"\"coordinator table from databse\"" +
					"(slot coordinator_id)" +
					"(slot module_code)" +
					"(slot course_code)" +
					"(slot staff_id)" +
					"(slot date)" +
					")");
			
			engine.eval("(deftemplate course" +
					" \"course tbale from database\" " +
					"(slot course_code)" +
					"(slot name)" +
					"(slot description)" +
					"(slot type)" +
					"(slot diploma_exit (type INTEGER))" +
					"(slot degree_exit (type INTEGER))" +
					")");
			
			engine.eval("(deftemplate course_structure" +
					" \"\" " +
					"(slot course_structure_id)" +
					"(slot course_code)" +
					"(slot module_code)" +
					")");
			
			
			engine.eval("(deftemplate event" +
					" \"table event\" " +
					"(slot event_id)" +
					"(slot title)" +
					"(slot description)" +
					"(slot type)" +
					"(slot user_id)" +
					")");
			
			engine.eval("(deftemplate faculty" +
					" \"\" " +
					"(slot faculty_id)" +
					"(slot name)" +
					")");
			
			engine.eval("(deftemplate faculty_course" +
					" \"\" " +
					"(slot faculty_course_id)" +
					"(slot faculty_id)" +
					"(slot course_code)" +
					")");
			
			engine.eval("(deftemplate module" +
					" \"\" " +
					"(slot module_code)" +
					"(slot name)" +
					"(slot syllabus)" +
					")");
			
			engine.eval("(deftemplate module_registration" +
					" \"\" " +
					"(slot module_registration_id)" +
					"(slot student_id)" +
					"(slot module_code)" +
					"(slot date)" +
					")");
			
			
			engine.eval("(deftemplate query" +
					" \"\" " +
					"(slot query_id)" +
					"(slot description)" +
					"(slot date)" +
					"(slot user_id)" +
					"(slot type)" +
					")");
			
			
			engine.eval("(deftemplate question" +
					" \"\" " +
					"(slot question_id)" +
					"(slot question)" +
					")");
			
			
			engine.eval("(deftemplate schedule" +
					" \"\" " +
					"(slot schedule_id)" +
					"(slot duration)" +
					"(slot starttime)" +
					"(slot date)" +
					"(slot event_id)" +
					")");
			
			engine.eval("(deftemplate staff" +
					" \"\" " +
					"(slot staff_id)" +
					"(slot user_id)" +
					"(slot working_description)" +
					"(slot position)" +
					"(slot salary (type INTEGER))" +
					")");
			
			
			engine.eval("(deftemplate student" +
					" \"\" " +
					"(slot student_id)" +
					"(slot user_id)" +
					"(slot cpa (type INTEGER))" +
					"(slot lpa (type INTEGER))" +
					"(slot gpa (type INTEGER))" +
					"(slot course_code)" +
					"(slot credit (type INTEGER))" +
					"(slot year (type INTEGER))" +
					"(slot end_academic_year (DEFAULT \"false\"))" +
					")");
			
			engine.eval("(deftemplate student_evidence" +
					" \"\" " +
					"(slot student_id)" +
					"(slot evidence)" +
					"(slot url)" +
					"(slot date)" +
					"(slot status)" +
					"(slot exam_period (DEFAULT \"false\"))" +
					"(slot current_exam_period (DEFAULT \"false\"))" +
					")");
			
			
			
			engine.eval("(deftemplate teach" +
					" \"\" " +
					"(slot teach_id)" +
					"(slot staff_id)" +
					"(slot module_code)" +
					"(slot date)" +
					")");
			
			engine.eval("(deftemplate timetable" +
					" \"\" " +
					"(slot timetable_id)" +
					"(slot structure_id)" +
					"(slot starttime)" +
					"(slot day)" +
					"(slot duration)" +
					"(slot class)" +
					"(slot staff_id)" +
					")");
			
			engine.eval("(deftemplate tracking" +
					" \"\" " +
					"(slot tracking_id)" +
					"(slot date)" +
					"(slot status)" +
					"(slot query_id)" +
					"(slot user_id)" +
					")");
			
			engine.eval("(deftemplate tracking_log" +
					" \"\" " +
					"(slot tracking_log_id)" +
					"(slot tracking_id)" +
					"(slot question_id)" +
					"(slot date)" +
					"(slot receiver)" +
					")");
			
			
			engine.eval("(deftemplate user" +
					" \"\" " +
					"(slot user_id)" +
					"(slot password)" +
					"(slot name)" +
					"(slot surname)" +
					"(slot house_number)" +
					"(slot locality)" +
					"(slot country)" +
					"(slot email)" +
					"(slot dob)" +
					")");
			
			
			
			
			
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return engine;
	}
	
	public Rete populateDataBaseTemplates(Rete engine){
		try {
		
		//Managers
		//answer
		AnswerManager answerManager = new AnswerManager();
		ArrayList<Answer> allAnswer = answerManager.getAllAnswer();
		for (int i = 0; i < allAnswer.size(); i++) {
			Fact fact = new Fact("answer", engine);	
			
			fact.setSlotValue("answer", new Value(allAnswer.get(i).getAnswer(),RU.STRING));
			fact.setSlotValue("answer_id", new Value(allAnswer.get(i).getAnswer_id(),RU.INTEGER));
			fact.setSlotValue("question_id", new Value(allAnswer.get(i).getQuestion_id(),RU.INTEGER));
			
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
			
			engine.assertFact(fact);
			
		}//end for (int i = 0; i < allAnswer.size(); i++) {
		
		
		//course
		CourseManager courseManager = new CourseManager();
		for (int i = 0; i < courseManager.getAllCourse().size(); i++) {
			Fact fact = new Fact("course", engine);	
			
			fact.setSlotValue("course_code", new Value(courseManager.getAllCourse().get(i).getCourse_code(),RU.STRING));
			fact.setSlotValue("name", new Value(courseManager.getAllCourse().get(i).getName(),RU.STRING));
			fact.setSlotValue("description", new Value(courseManager.getAllCourse().get(i).getDescrition(),RU.STRING));
			fact.setSlotValue("type", new Value(courseManager.getAllCourse().get(i).getType(),RU.STRING));
			fact.setSlotValue("diploma_exit", new Value(courseManager.getAllCourse().get(i).getDiploma_exit(),RU.INTEGER));
			fact.setSlotValue("degree_exit", new Value(courseManager.getAllCourse().get(i).getDegree_exit(),RU.INTEGER));
			
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
			engine.assertFact(fact);
		}//end of for (int i = 0; i < courseManager.getAllCourse().size(); i++) {
		
		//course_structure
		CourseStructureManager courseStructureManager = new CourseStructureManager();
		ArrayList< CourseStructure> allCourseStructures = courseStructureManager.getAllCourseStructure();
		for (int i = 0; i < allCourseStructures.size(); i++) {
			Fact fact = new Fact("course_structure", engine);	
			
			fact.setSlotValue("course_structure_id", new Value(allCourseStructures.get(i).getCourse_structure_id()+"",RU.STRING));
			fact.setSlotValue("course_code", new Value(allCourseStructures.get(i).getCourse_code(),RU.STRING));
			fact.setSlotValue("module_code", new Value(allCourseStructures.get(i).getModule_code(),RU.STRING));
			
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
			engine.assertFact(fact);
			
			
		}//end for (int i = 0; i < allCourseStructures.size(); i++) {
	
		
		
		//event
		EventManager eventManager = new EventManager();
		ArrayList <Event> allevent = eventManager.getAllEvent();
		for (int i = 0; i < allevent.size(); i++) {
			Fact fact = new Fact("event", engine);	
			
			fact.setSlotValue("event_id", new Value(allevent.get(i).getUser_id(),RU.STRING));
			fact.setSlotValue("title", new Value(allevent.get(i).getTitle(),RU.STRING));
			fact.setSlotValue("description", new Value(allevent.get(i).getDescroiption(),RU.STRING));
			fact.setSlotValue("type", new Value(allevent.get(i).getType(),RU.STRING));
			fact.setSlotValue("user_id", new Value(allevent.get(i).getUser_id(),RU.STRING));
			
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
			
			engine.assertFact(fact);
		}//end for (int i = 0; i < allevent.size(); i++) {
	
		//faculty
		FacultyManager facultyManager = new FacultyManager();
		ArrayList<Faculty> allFaculty = facultyManager.getAllFaculty();
		for (int i = 0; i < allFaculty.size(); i++) {
			Fact fact = new Fact("faculty", engine);	
			
			fact.setSlotValue("name", new Value(allFaculty.get(i).getName(),RU.STRING));
			fact.setSlotValue("faculty_id", new Value(allFaculty.get(i).getFaculty_id()+"",RU.STRING));
			
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
			engine.assertFact(fact);
		}//end for (int i = 0; i < allFaculty.size(); i++) {
		
		
		//module
		//module_registration
		//query
		QueryManager queryManager = new QueryManager();
		ArrayList<businessLogic.Query> allQuery = queryManager.getAllQuery();		
		for (int i = 0; i < allQuery.size(); i++) {
			Fact fact = new Fact("query", engine);	
			
			fact.setSlotValue("query_id", new Value(allQuery.get(i).getQuery_id()+"",RU.STRING));
			fact.setSlotValue("description", new Value(allQuery.get(i).getDescrition(),RU.STRING));
			fact.setSlotValue("date", new Value(allQuery.get(i).getDate()+"",RU.STRING));
			fact.setSlotValue("user_id", new Value(allQuery.get(i).getUser_id()+"",RU.STRING));
			fact.setSlotValue("type", new Value(allQuery.get(i).getType()+"",RU.STRING));
			
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
			engine.assertFact(fact);
			
		}//end for (int i = 0; i < allQuery.size(); i++) {
		
		
		//question
		QuestionManager questionManager = new QuestionManager();
		ArrayList<Question> allQuestion =questionManager.getAllQuestion();
		for (int i = 0; i < allQuestion.size(); i++) {
			Fact fact = new Fact("question", engine);	
			
			fact.setSlotValue("question", new Value(allQuestion.get(i).getQuestion(),RU.STRING));
			fact.setSlotValue("question_id", new Value(allQuestion.get(i).getQuestion_id()+"",RU.STRING));
			
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
			engine.assertFact(fact);
		}//end for (int i = 0; i < allQuestion.size(); i++) {
		
		
		
		//schedule
		ScheduleManager scheduleManager = new ScheduleManager();
		ArrayList<Schedule> allSchedules = scheduleManager.getAllSchedule();
		for (int i = 0; i < allSchedules.size(); i++) {
			Fact fact = new Fact("schedule", engine);	
			
			fact.setSlotValue("schedule_id", new Value(allSchedules.get(i).getSchedule_id()+"",RU.STRING));
			fact.setSlotValue("duration", new Value(allSchedules.get(i).getDuration()+"",RU.STRING));
			fact.setSlotValue("starttime", new Value(allSchedules.get(i).getTime(),RU.STRING));
			fact.setSlotValue("date", new Value(allSchedules.get(i).getDate().toString(),RU.STRING));
			fact.setSlotValue("event_id", new Value(allSchedules.get(i).getEvent_id()+"",RU.STRING));
			
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
			engine.assertFact(fact);

		}//end for (int i = 0; i < allSchedules.size(); i++) {
		
		//staff
		StaffManager staffManager = new StaffManager();
		ArrayList<Staff> allStaff = staffManager.getAllStaff();
		for (int i = 0; i < allStaff.size(); i++) {
			Fact fact = new Fact("staff", engine);	
			
			fact.setSlotValue("staff_id", new Value(allStaff.get(i).getStaff_id(),RU.STRING));
			fact.setSlotValue("user_id", new Value(allStaff.get(i).getUser_id(),RU.STRING));
			fact.setSlotValue("working_description", new Value(allStaff.get(i).getWorking_description(),RU.STRING));
			fact.setSlotValue("position", new Value(allStaff.get(i).getPosition()+"",RU.STRING));
			fact.setSlotValue("salary", new Value(allStaff.get(i).getSalary(),RU.INTEGER));
			
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
			
			engine.assertFact(fact);
			
		}//for (int i = 0; i < allStaff.size(); i++) {
		
		
		
		//student
		StudentManager studentManager = new StudentManager();
		ArrayList <Student> allstudent = studentManager.getAllStudent();		
		for (int i = 0; i < allstudent.size(); i++) {
			Fact fact = new Fact("student", engine);	
			
			fact.setSlotValue("student_id", new Value(allstudent.get(i).getStudent_id(),RU.STRING));
			fact.setSlotValue("user_id", new Value(allstudent.get(i).getUser_id(),RU.STRING));
			fact.setSlotValue("cpa", new Value(allstudent.get(i).getCpa(),RU.INTEGER));
			fact.setSlotValue("gpa", new Value(allstudent.get(i).getGpa(),RU.INTEGER));
			fact.setSlotValue("lpa", new Value(allstudent.get(i).getLpa(),RU.INTEGER));
			fact.setSlotValue("course_code", new Value(allstudent.get(i).getCourse_code(),RU.STRING));
			fact.setSlotValue("credit", new Value(allstudent.get(i).getCredit(),RU.INTEGER));
			fact.setSlotValue("year", new Value(allstudent.get(i).getYear(),RU.INTEGER));
			
			Date date = allstudent.get(i).getLast_result_date();
			if(date.getMonth()>4 && date.getMonth()<8)
				fact.setSlotValue("end_academic_year", new Value("true",RU.STRING));
			else
				fact.setSlotValue("end_academic_year", new Value("false",RU.STRING));
			
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
			
			engine.assertFact(fact);
		}//end for (int i = 0; i < allstudent.size(); i++) {
		
		
		//Stuend envidence
		StudentEvidenceManager studentEvidenceManager = new StudentEvidenceManager();
		ArrayList<StudentEvidence> allStudentEvidences = studentEvidenceManager.getAllStudentEvidence();
		
		for (int i = 0; i < allStudentEvidences.size(); i++) {
			Fact fact = new Fact("student_evidence", engine);	
			
			
			
			fact.setSlotValue("student_id", new Value(allStudentEvidences.get(i).getStudent_id(),RU.STRING));
			fact.setSlotValue("evidence", new Value(allStudentEvidences.get(i).getEvidence() ,RU.STRING));
			fact.setSlotValue("url", new Value(allStudentEvidences.get(i).getUrl(),RU.STRING));
			fact.setSlotValue("date", new Value(allStudentEvidences.get(i).getDate()+"",RU.STRING));
			fact.setSlotValue("status", new Value(allStudentEvidences.get(i).getStatus(),RU.STRING));
			fact.setSlotValue("exam_period", new Value(allStudentEvidences.get(i).getExam_period(),RU.STRING));
			
			if(allStudentEvidences.get(i).getExam_period().equals("true") && (allStudentEvidences.get(i).getDate().getYear()+1900) ==  Calendar.getInstance().get(Calendar.YEAR)){
				fact.setSlotValue("current_exam_period", new Value("true",RU.STRING));
			}
			
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
			engine.assertFact(fact);
			
		}//for (int i = 0; i < allStaff.size(); i++) {


		
		//teach
		TeachManager teachManager = new TeachManager();
		ArrayList<Teach> allTeachs = teachManager.getAllTeach();
		for (int i = 0; i < allTeachs.size(); i++) {
			Fact fact = new Fact("teach", engine);	
			
			fact.setSlotValue("teach_id", new Value(allTeachs.get(i).getTeach_id()+"",RU.STRING));
			fact.setSlotValue("staff_id", new Value(allTeachs.get(i).getStaff_id(),RU.STRING));
			fact.setSlotValue("module_code", new Value(allTeachs.get(i).getModule_code(),RU.STRING));
			fact.setSlotValue("date", new Value(allTeachs.get(i).getDate(),RU.STRING));
			
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
			
			
			engine.assertFact(fact);
		}//end for (int i = 0; i < allTeachs.size(); i++) {
		
		//timetable
		TimetableManager timetableManager = new TimetableManager();
		ArrayList<Timetable> allTimetables = timetableManager.getAllTimetable();
		for (int i = 0; i < allTimetables.size(); i++) {
			Fact fact = new Fact("timetable", engine);	
			
			fact.setSlotValue("timetable_id", new Value(allTimetables.get(i).getTimetable_id()+"",RU.STRING));
			fact.setSlotValue("structure_id", new Value(allTimetables.get(i).getStructure_id()+"",RU.STRING));
			fact.setSlotValue("starttime", new Value(allTimetables.get(i).getTime()+"",RU.STRING));
			fact.setSlotValue("day", new Value(allTimetables.get(i).getDay()+"",RU.STRING));
			fact.setSlotValue("duration", new Value(allTimetables.get(i).getDuration()+"",RU.STRING));
			fact.setSlotValue("class", new Value(allTimetables.get(i).getClass()+"",RU.STRING));
			fact.setSlotValue("staff_id", new Value(allTimetables.get(i).getStaff_id()+"",RU.STRING));
			
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
					
			engine.assertFact(fact);
		}// end for (int i = 0; i < allTimetables.size(); i++) {
		
		
		
		//tracking
		TrackingManager trackingManager = new TrackingManager();
		ArrayList<Tracking> allTracking = trackingManager.getAllTracking();
		for (int i = 0; i < allTracking.size(); i++) {
			Fact fact = new Fact("tracking", engine);	
			
			fact.setSlotValue("tracking_id", new Value(allTracking.get(i).getTracking_id()+"",RU.STRING));
			fact.setSlotValue("date", new Value(allTracking.get(i).getDate()+"",RU.STRING));
			fact.setSlotValue("status", new Value(allTracking.get(i).getStatus()+"",RU.STRING));
			fact.setSlotValue("query_id", new Value(allTracking.get(i).getQuery_id()+"",RU.STRING));
			fact.setSlotValue("user_id", new Value(allTracking.get(i).getUser_id()+"",RU.STRING));
			
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
			
			
			engine.assertFact(fact);
		}//end for (int i = 0; i < allTracking.size(); i++) {
		
		
		
		
		
		//tracking_log
		TrackingLogManager trackingLogManager = new TrackingLogManager();
		ArrayList<TrackingLog> allTrackingLogManagers = trackingLogManager.getAllTrackingLogManager();
		for (int i = 0; i < allTrackingLogManagers.size(); i++) {
			Fact fact = new Fact("tracking_log", engine);	
			
			fact.setSlotValue("tracking_log_id", new Value(allTrackingLogManagers.get(i).getTracking_log_id()+"",RU.STRING));
			fact.setSlotValue("tracking_id", new Value(allTrackingLogManagers.get(i).getTracking_id()+"",RU.STRING));
			fact.setSlotValue("question_id", new Value(allTrackingLogManagers.get(i).getQuestion()+"",RU.STRING));
			fact.setSlotValue("date", new Value(allTrackingLogManagers.get(i).getDate()+"",RU.STRING));
			fact.setSlotValue("receiver", new Value(allTrackingLogManagers.get(i).getReciever()+"",RU.STRING));
			
			
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
			
			engine.assertFact(fact);
		}//end for (int i = 0; i < allTrackingLogManagers.size(); i++) {
		
		
		
		//user
		UserManager userManager = new UserManager();
		ArrayList<User> allUser = userManager.getAllUser();
		for (int i = 0; i < allUser.size(); i++) {
			Fact fact = new Fact("user", engine);	
			
			fact.setSlotValue("user_id", new Value(allUser.get(i).getUser_id(),RU.STRING));
			fact.setSlotValue("password", new Value(allUser.get(i).getPassword(),RU.STRING));
			fact.setSlotValue("name", new Value(allUser.get(i).getName(),RU.STRING));
			fact.setSlotValue("surname", new Value(allUser.get(i).getSurname(),RU.STRING));
			fact.setSlotValue("house_number", new Value(allUser.get(i).getHouse_number()+"",RU.STRING));
			fact.setSlotValue("locality", new Value(allUser.get(i).getLocality(),RU.STRING));
			fact.setSlotValue("country", new Value(allUser.get(i).getCountry(),RU.STRING));
			fact.setSlotValue("email", new Value(allUser.get(i).getEmail(),RU.STRING));
			fact.setSlotValue("dob", new Value(allUser.get(i).getDate_of_birth().toString(),RU.STRING));
			if(outFile != null){
				String temp = (fact.toStringWithParens());
				new PrintStream(outFile).println ("(assert("+temp.substring(7, temp.length())+")");
			}
			
			
			
			engine.assertFact(fact);
		}//endfor (int i = 0; i < allUser.size(); i++) {
		
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return engine;
	}
	
	public Rete populateRule(Rete engine){
		try {
		
			
			//student can exit with a degree
			engine.eval("(defrule student-degree" +
					"  \"student can exit with diploma\" " +
					"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
					"(course (course_code ?course_code)(name ?name)(description ?description)(type ?type)(diploma_exit ?diploma_exit)(degree_exit ?degree_exit))" +
					"(test (>= ?credit ?degree_exit))" +
					"(student-pass ?id)" +
					"=>" +
					"(assert (can-exit-with-degree ?id))" +
					//"(printout t ?id \" =<id : course => \" ?course_code \" :=> \" ?credit \" :=> \" ?degree_exit crlf)" +
					")");
			if(outFile != null){
				new PrintStream(outFile).println ("" +
						"" +
						"" +
						"(defrule student-degree" +
						"  \"student can exit with diploma\" " +
						"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
						"(course (course_code ?course_code)(name ?name)(description ?description)(type ?type)(diploma_exit ?diploma_exit)(degree_exit ?degree_exit))" +
						"(test (>= ?credit ?degree_exit))" +
						"(student-pass ?id)" +
						"=>" +
						"(assert (can-exit-with-degree ?id))" +
						//"(printout t ?id \" =<id : course => \" ?course_code \" :=> \" ?credit \" :=> \" ?degree_exit crlf)" +
						")"
						
						);
			}
			
			//student can exit with a diploma
			engine.eval("(defrule student-diploma" +
					"  \"student can exit with diploma\" " +
					"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
					"(course (course_code ?course_code)(name ?name)(description ?description)(type ?type)(diploma_exit ?diploma_exit)(degree_exit ?degree_exit))" +
					"(test (>= ?credit ?diploma_exit))" +
					"(student-pass ?id)" +
					"=>" +
					"(assert (can-exit-with-diploma ?id))" +
					//"(printout t ?id \" =<id : course => \" ?course_code \" :=> \" ?credit \" :=> \" ?degree_exit crlf)" +
					")");
			
			if(outFile != null){
				new PrintStream(outFile).println ("(defrule student-diploma" +
						"  \"student can exit with diploma\" " +
						"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
						"(course (course_code ?course_code)(name ?name)(description ?description)(type ?type)(diploma_exit ?diploma_exit)(degree_exit ?degree_exit))" +
						"(test (>= ?credit ?diploma_exit))" +
						"(student-pass ?id)" +
						"=>" +
						"(assert (can-exit-with-diploma ?id))" +
						//"(printout t ?id \" =<id : course => \" ?course_code \" :=> \" ?credit \" :=> \" ?degree_exit crlf)" +
						")"
						
						);
			}
			
			
			//pass student
			engine.eval("(defrule student-pass" +
					"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
					"(test (>= ?cpa 40))" +
					"=>" +
					"(assert (student-pass ?id))" +
					")");
			
			
			if(outFile != null){
				new PrintStream(outFile).println ("" +
						"(defrule student-pass" +
						"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
						"(test (>= ?cpa 40))" +
						"=>" +
						"(assert (student-pass ?id))" +
						")"
						);
			}
			
			//fail student
			engine.eval("(defrule student-fail" +
					"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
					"(test (< ?cpa 40))" +
					"=>" +
					"(assert (student-fail ?id))" +
					")");
			
			
			
			if(outFile != null){
				new PrintStream(outFile).println ("(defrule student-fail" +
						"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
						"(test (< ?cpa 40))" +
						"=>" +
						"(assert (student-fail ?id))" +
						")"
						);
			}
			
			//student first-class-first-division
			engine.eval("(defrule student-first-class-first-division" +
					"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
					"(test (and (>= ?cpa 70) (<= ?cpa 100)))" +
					"(can-exit-with-degree ?id)" +
					"=>" +
					"(assert (student-has-first-class-first-division ?id))" +
					")");
			if(outFile != null){
				new PrintStream(outFile).println ("(defrule student-first-class-first-division" +
						"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
						"(test (and (>= ?cpa 70) (<= ?cpa 100)))" +
						"(can-exit-with-degree ?id)" +
						"=>" +
						"(assert (student-has-first-class-first-division ?id))" +
						")"
						
						);
			}
			
			//student second-class-first-division
			engine.eval("(defrule student-second-class-first-division" +
					"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
					"(test (and (>= ?cpa 60) (<= ?cpa 69)))" +
					"(can-exit-with-degree ?id)" +
					"=>" +
					"(assert (student-has-second-class-first-division ?id))" +
					")");
			
			if(outFile != null){
				new PrintStream(outFile).println ("(defrule student-second-class-first-division" +
						"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
						"(test (and (>= ?cpa 60) (<= ?cpa 69)))" +
						"(can-exit-with-degree ?id)" +
						"=>" +
						"(assert (student-has-second-class-first-division ?id))" +
						")"
						
						);
			}
			
			//student second-class-second-division
			engine.eval("(defrule student-second-class-second-division" +
					"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
					"(test (and (>= ?cpa 50) (<= ?cpa 59)))" +
					"(can-exit-with-degree ?id)" +
					"=>" +
					"(assert (student-has-second-class-second-division ?id))" +
					")");
			if(outFile != null){
				new PrintStream(outFile).println ("(defrule student-second-class-second-division" +
						"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
						"(test (and (>= ?cpa 50) (<= ?cpa 59)))" +
						"(can-exit-with-degree ?id)" +
						"=>" +
						"(assert (student-has-second-class-second-division ?id))" +
						")"
						);
				
			}
			
			//student third-class
			engine.eval("(defrule student-third-class" +
					"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
					"(test (and (>= ?cpa 40) (<= ?cpa 49)))" +
					"(can-exit-with-degree ?id)" +
					"=>" +
					"(assert (student-has-third-class ?id))" +
					")");
			if(outFile != null){
				new PrintStream(outFile).println ("(defrule student-third-class" +
						"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))" +
						"(test (and (>= ?cpa 40) (<= ?cpa 49)))" +
						"(can-exit-with-degree ?id)" +
						"=>" +
						"(assert (student-has-third-class ?id))" +
						")"
						);			
				
			}
			
			
			
			String examinerBoardDs1 = "(defrule examiner_board_ds_1"+
			"	    (student (student_id ?student_id)(user_id ?user_id)( cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)( credit  ?credit)( year ?year)( end_academic_year ?end_academic_year))"+
			"	   	(student_evidence (student_id ?student_id)(evidence ?evidence)(url ?url)(date ?date)(status ?status)(exam_period ?exam_period )(current_exam_period ?current_exam_period ))"+
			"	    (test (= ?status \"valid\")) "+
			"	    (test (= ?exam_period \"true\"))"+ 
			"	    (test (= ?current_exam_period \"true\"))"+ 
			"	    =>"+
			"	    (assert (valid_evidence ?student_id))"+
			"	    )";

			String terminationCpaLess25 =	"(defrule termination_cpa_less_25"+
				"    (student (student_id ?student_id)(user_id ?user_id)( cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)( credit  ?credit)( year ?year)( end_academic_year ?end_academic_year))"+
				"   (test (= ?year 1))"+
				"    (test (< ?cpa 25))"+
				"   (test (= ?end_academic_year \"true\"))"+
				"    =>"+
				"   (assert (terminate ?student_id))"+
				"     ) ";

			String terminationCpaLess25_2 =	"(defrule termination_cpa_less_25_2"+
				"    (student (student_id ?student_id)(user_id ?user_id)( cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)( credit  ?credit)( year ?year)( end_academic_year ?end_academic_year))"+
				"   (terminate ?student_id)"+
				"    (valid_evidence ?student_id)"+
				"    =>"+
				"    (assert (repeat_year ?student_id))"+
				"     )"; 
			
			
			engine.eval(examinerBoardDs1);
			engine.eval(terminationCpaLess25);
			engine.eval(terminationCpaLess25_2);
			if(outFile != null){
				new PrintStream(outFile).println (examinerBoardDs1);
				new PrintStream(outFile).println (terminationCpaLess25);
				new PrintStream(outFile).println (terminationCpaLess25_2);
			}
			
			
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return engine;		
	}//end of public Rete populateRule(Rete engine){
	
	public Rete populateQuery(Rete engine){
		String searchByDegree ="", searchByName="",searchByDiploma="";
		
		searchByDegree =" (defquery search-degree-by-id "+
			   " \"Finds people with a given last name\" "+
			   " (declare (variables ?id)) "+
			   " (student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year)) "+
			   " (user (user_id ?user_id) (password ?password) (name ?name) (surname ?surname) (house_number ?houseno) (locality ?locality) (country ?country) (email ?email) (dob ?dob)) "+
			   " (can-exit-with-degree ?id) "+
			   " ) ";
		
		searchByDiploma =" (defquery search-diploma-by-id "+
				   " \"Finds people with a given last name\" "+
				   " (declare (variables ?id)) "+
				   " (student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year)) "+
				   " (user (user_id ?user_id) (password ?password) (name ?name) (surname ?surname) (house_number ?houseno) (locality ?locality) (country ?country) (email ?email) (dob ?dob)) "+
				   " (can-exit-with-diploma ?id) "+
				   " ) ";
		
		searchByName="(defquery search-by-name"+
		  "\"Finds people with a given last name\""+
		  "(declare (variables ?user_id))"+
		  " (user (user_id ?user_id) (password ?password) (name ?name) (surname ?surname) (house_number ?houseno) (locality ?locality) (country ?country) (email ?email) (dob ?dob)) "+
		   ")";
		
		
		String all = "(defquery student_terminate" +
				"\"Finds people with a given last name\"" +
				"(declare (variables ?id))" +
				"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))  (user (user_id ?user_id) (password ?password) (name ?name) (surname ?surname) (house_number ?houseno) (locality ?locality) (country ?country) (email ?email) (dob ?dob))" +
				"(terminate ?id)  " +
				") " +
				"" +
				"(defquery student_repeat" +
				"\"Finds people with a given last name\"" +
				"(declare (variables ?id))" +
				"(student (student_id ?id)(user_id ?user_id)(cpa ?cpa)(lpa ?lpa)(gpa ?gpa)(course_code ?course_code)(credit ?credit)(year ?year))  (user (user_id ?user_id) (password ?password) (name ?name) (surname ?surname) (house_number ?houseno) (locality ?locality) (country ?country) (email ?email) (dob ?dob))" +
				"(repeat_year ?id)" +
				")";
		

		try {
			engine.eval(searchByDegree);
			engine.eval(searchByDiploma);
			engine.eval(searchByName);
			engine.eval(all);
			if(outFile != null){
				new PrintStream(outFile).println (searchByDegree );
				new PrintStream(outFile).println (searchByDiploma );
				new PrintStream(outFile).println (searchByName);
				new PrintStream(outFile).println (all);
			}
			
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		
		return engine;
	}//end of public Rete populateQuery(Rete engine){
	
	
	
	public static void main(String[] args) {
		outFile = new KnowledgeDatabaseTemplate().saveKnowledgeBase();
		Rete engine = new KnowledgeDatabaseTemplate().getDataBaseTemplates();
		
		try {
			engine =  new KnowledgeDatabaseTemplate().populateDataBaseTemplates(engine);
			engine.eval("(printout t  \" working\"  crlf)");
						
			engine = new KnowledgeDatabaseTemplate().populateRule(engine);
			engine = new KnowledgeDatabaseTemplate().populateQuery(engine);
	      /*  QueryResult result =  engine.runQueryStar("search-by-name", new ValueVector().add(new Value("P 0001", RU.STRING)));	        
	        while (result.next()) {
	        	 System.out.println(result.getString("user_id") + " , " + result.getString("name")+" can exit with degree ");
	        }*/
			engine.run();
	        QueryResult result =  engine.runQueryStar("search-diploma-by-id", new ValueVector().add(new Value("1010790", RU.STRING)));	        
	        while (result.next()) {
	        	 System.out.println(result.getString("user_id") + " , " + result.getString("name")+" can exit with diploma ");
	        	 
	        }
	        engine.eval("(facts)");
	        
			outFile.close();
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}