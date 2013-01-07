package businessLogic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;

/**
 * Session Bean implementation class ScheduleManager
 */
@Stateless
@LocalBean
public class ScheduleManager {

    /**
     * Default constructor. 
     */
    public ScheduleManager() {
        
    }
    
    
    
    public boolean createSchedule(int duration, Date date, String time,int event_id){
    	Connection con;
    	String query;
    	PreparedStatement pstmt;
    	int insert=0;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	query = "INSERT INTO schedule VALUES(null,?,?,?,?)";
    	
    	try{
    		pstmt = con.prepareStatement(query);
    		
    		
    		pstmt.setInt(1, duration);
    		pstmt.setString(2, time);
    		pstmt.setDate(3, date);
    		pstmt.setInt(4, event_id);
    		
    		insert = pstmt.executeUpdate();
    	}
    	catch(SQLException sqle){
    		//load error page
    	}    	
    	
    	if(insert>0){
    		return true;
		}    	
		return false;    	
    }//end of public boolean createSchedule(){
    
    /*Generate schedule if not sport.
     * retrieve all free slots of event participant
     *  return to the user
     */
   public ArrayList<Timestamp> generateNonSportSchedule(int faculties[], String course[] ,Date startDate, Date endDate){
    	
    	
    	ArrayList<CourseStructure > allCourseStructure = new ArrayList<CourseStructure>();
    	ArrayList<CourseStructure > tempCourseStructure = new ArrayList<CourseStructure>();
    	ArrayList<Timestamp> allFreeSlot = new ArrayList<Timestamp>();
    	
    	
    	// get all structureCourse based on faculty
    	if(faculties.length>0){
    		FacultyCourseManager facultyCourseManager = new FacultyCourseManager();
    		allCourseStructure = facultyCourseManager.getCourseByFaculty(faculties);
    	}//end if(faculties.length>0)
    	
    	//get all structureCourse based on course
    	if(course.length>0){
    		CourseStructureManager coursestructure = new CourseStructureManager();
    		tempCourseStructure = coursestructure.getAllCourseStructureByCourse(course);
    	}//end if(course.length>0)
    	
    	//merge both CourseStructure Arraylist
    	for(int i =0 ; i<tempCourseStructure.size();i++){
    		if(!allCourseStructure.contains(tempCourseStructure.get(i))){
    			allCourseStructure.add(tempCourseStructure.get(i));
    		}//end if(!allCourseStructure.contains(tempCourseStructure.get(i))){
    	}//for(int i =0 ; i<tempCourseStructure.size();i++)
    	
    	
    	
    	 /* implement a genetic algorithm
    	 * to find the best fit date
    	 */
    	//variables
    	ArrayList<Timestamp> allSolutions = new ArrayList<Timestamp>();
    	ArrayList<Integer> solutionFitness = new ArrayList<Integer>();
    	int totalTime,fitnessValue=0;
    	Timestamp time = new Timestamp(startDate.getYear(), startDate.getMonth(), startDate.getDate(), 8, 0, 0, 0);
    	boolean result;
    	//initialize population
    	/*university start at 8a.m to 8p.m
    	 * This makes 12 hr
    	 * Total time = (endDate -StartDate) * 12
    	 */
    	 
    	
    	long daysBetween =  endDate.getTime()- startDate.getTime();
    	
        totalTime =  (int)( (daysBetween * 12)/ (1000 * 60 * 60 * 24) );
        allSolutions.add(time);
        Calendar calender = Calendar.getInstance();
    	for(int i=1;i<totalTime;i++){
    		if(time.getHours()>0 && time.getHours()<20){
    			calender.setTime(time);
    			
    			calender.add(Calendar.HOUR, 1);
    			long tim = calender.getTime().getTime();
    			time = new Timestamp(tim);
    			allSolutions.add(time);
    		}//end if(time.getHours()>0 && time.getHours()<8){
    		else{
    			calender.setTime(time);
    			
    			calender.add(Calendar.HOUR, 12);
    			long tim = calender.getTime().getTime();
    			time = new Timestamp(tim);
    			allSolutions.add(time);    			
    		}//end else   		    		
    	}//end for(int i=1;i<totalTime;i++){
   	
    	
    	//fitness
    	/*
    	 * if current timetable slot is free = increase by 1
    	 * if module is not in current timetable slot = increase by 1
    	 * if module is not a degree = increase by 1
    	 * if module is not year 3 = increase by 1 
    	 */
    	TimetableManager timetableManager = new TimetableManager();
    	//travel through population
    	for(int i=0; i<allSolutions.size();i++){
    		//travel through course Structure
    
    		for(int j=0;j<allCourseStructure.size();j++){
    			
    			//check for current timetable slot
        		int day = allSolutions.get(i).getDay();
        		Time temTime = new Time(allSolutions.get(i).getTime());
        		
        		//check for course structure timetable slot
        		result = timetableManager.findTimetableByDayTime(temTime, getDays(day), allCourseStructure.get(j).getCourse_structure_id());
        		if(!result){
        			fitnessValue++;
        		}//end if(!result)   
    			
    		}//end for(int j=0;j<allCourseStructure.size();j++){
    		
    		solutionFitness.add(fitnessValue);
    		fitnessValue = 0;
    	}//end for (int i=0; i<allSolutions.size();i++){
    	
    	int maxFitness = Collections.max(solutionFitness);
    	for(int i =0 ; i<allSolutions.size();i++){
    		if(solutionFitness.get(i) == maxFitness){
    			allFreeSlot.add(allSolutions.get(i));
    		}	
    	}
    	
    	
    	
    	return allFreeSlot;    	
    }//end of 
   	
   
   public String getDays(int day){
	   switch (day) {
		case 0:
			return "Sunday";
		case 1:
			return "Monday";
		case 2:
			return "Tuesday";
		case 3:
			return "Wednesday";
		case 4:
			return "Thursday";
		case 5:
			return "Friday";
		case 6:
			return "Saturday";
		default:
			return "Sunday";
			
	}//end switch
   }//end of public String getDays(int day){
   
    
    
}
