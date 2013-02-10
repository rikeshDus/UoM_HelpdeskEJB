package businessLogic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
    
    public Schedule findSchedule(int schedule_id) {
    	Connection con;
    	String query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	Schedule temporarySchedule = null;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	query = "SELECT * FROM schedule WHERE schedule_id = ?";
    	
    	try {
    		pstmt = con.prepareStatement(query);
    		
			pstmt.setInt(1, schedule_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				temporarySchedule = new Schedule();
				
				temporarySchedule.setDate(rs.getDate("date"));
				temporarySchedule.setDuration(rs.getInt("duration"));
				temporarySchedule.setEvent_id(rs.getInt("event_id"));
				temporarySchedule.setSchedule_id(rs.getInt("schedule_id"));
				temporarySchedule.setTime(rs.getString("starttime"));
			}//end while
			
			
			rs.close();
    		pstmt.close();
    		con.close();
    		return temporarySchedule;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return null;
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
    
    public ArrayList<Schedule> getAllSchedule(){
		Connection con;
		Statement stmt;
		ResultSet rs;
		Schedule temporarySchedule;
		ArrayList<Schedule> allSchedule = new ArrayList<Schedule>();
		String query = "SELECT * FROM schedule";
		
		DatabaseConnection dbconnect = new DatabaseConnection();
		con = dbconnect.getConnection();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				
				temporarySchedule = new Schedule();
				
				temporarySchedule.setSchedule_id(rs.getInt("schedule_id"));
				temporarySchedule.setDuration(rs.getInt("duration"));
				temporarySchedule.setTime(rs.getString("starttime"));
				temporarySchedule.setDate(rs.getDate("date"));
				temporarySchedule.setEvent_id(rs.getInt("event_id"));
				
				allSchedule.add(temporarySchedule);
				
			}//end while(rs.next())
			
			
			rs.close();
			stmt.close();
			con.close();
			
			return allSchedule;
			
		} catch (SQLException e) {
			
			//load errorpage
		}	
		
		return null;		
}//end  public ArrayList<Tracking> getAllTracking(){
       
    /*Generate Schedule if sport
     * Use round robin algorithm (first come , first serve)
     * generate round of the sport
     * store round in hashmap with key=round1,2,.. and value = arraylist of teams.
     * ArrayList will store teams which vs each other consecutively, ArrayList.get(0) vs ArrayList.get(1)
     * return hashMap
     */
    public HashMap<String,List<String>> generateSportSchedule(HashMap<String,String[]> teamsHashMap ,Date startDate, Date endDate){
    	//variable
    	HashMap<String, List<String>> result = new HashMap<String, List<String>>();
    	ArrayList <String> allTeams = new ArrayList<String>();
    	ArrayList <String> allParticipant = new ArrayList<String>();
    	ArrayList<String> allCourseCode = new ArrayList<String>();
    	ArrayList<String> module = new ArrayList<String>();
    	ArrayList<Timestamp> allTime = new ArrayList<Timestamp>();
    	ArrayList<String> temSchedule = new ArrayList<String>();
    	ArrayList<CourseStructure > allCourseStructure = new ArrayList<CourseStructure>();
    	String[] partipant;
    	int tem = 0,tem2 = 0;
    	
    	
    	allTeams.addAll(teamsHashMap.keySet());
    	//get all teams and participant
    	for(int i=0;i<allTeams.size();i++){
    		//get list of participant in team
    		partipant = teamsHashMap.get(allTeams.get(i));
    		
    		//add paticipant to allPaticipant ArrayList
    		for(int j=0;j<partipant.length;j++){
    			allParticipant.add(partipant[j]);
    		}//end for(int j=0;j<partipant.length;j++){
    		
    		
    	}//end for for(int i=0;i<teamsHashMap.size();i++){
    	
    	
    	//get Time
    	//manager
		StudentManager studentManager = new StudentManager();
		TeachManager teachManager = new TeachManager();
		CourseStructureManager coursestructure = new CourseStructureManager();
		
		//loop in team A
		for(int j = 0; j<allParticipant.size();j++){
			//get courses by student ids;
			
			if(studentManager.findStudent(allParticipant.get(j)) != null){
				allCourseCode.add(studentManager.findStudent(allParticipant.get(j)).getCourse_code());
			}//end if(studentManager.findStudent(participantTeamAIds[j]) != null){
			
			//get module in which staff works
			
			module = teachManager.getModuleFromStaffId(allParticipant.get(j));
			if(module.size()>0){
				//get course_structure by courses(module_code)
    			allCourseStructure.addAll( coursestructure.getAllCourseStructureByModule(module) );
			}//end if(module.size()>0){
			
			
		}//end for(int j = 0; j<participantTeamAIds.length;j++){
    	
		
		//get List of date
		String[] course = new String[allCourseStructure.size()];
		for(int j = 0; j<allCourseStructure.size();j++){
			course[j] = allCourseStructure.get(j).getCourse_code();
		}//end for(int j = 0; j<allCourseStructure.size();j++){
		
		int[] faculties = {};
		
		allTime = generateNonSportSchedule(faculties, course, startDate, endDate);
		
		while(tem<allTeams.size()){
			//add match
			temSchedule.add(allTeams.get(tem));
    		temSchedule.add(allTeams.get(tem+1));
    		temSchedule.add(allTime.get(tem2).toString());
			
			tem++;
			tem++;
			tem2++;
		}
		
		int numOfTeams = allTeams.size();
		int round =1;
		

		numOfTeams = numOfTeams/2;
		result.put("round "+round, temSchedule);
		round++;
		
		while(numOfTeams>=2){
			
			temSchedule = new ArrayList<String>();
			temSchedule.add(allTime.get(tem2).toString());
			numOfTeams = numOfTeams/2;
			result.put("round "+round, temSchedule);
			round++;
			tem2++;
			
		}
		
		
    	
    	/*//variable
    	int numOfTeam = 0;
    	String[] participantTeamAIds;
    	String[] participantTeamBIds;
    	
    	ArrayList<String> module = new ArrayList<String>();
    	String actualTeamA,actualTeamB;
    	ArrayList<String> allCourseCode = new ArrayList<String>();
    	ArrayList<String> temSchedule = new ArrayList<String>();
    	ArrayList<CourseStructure > allCourseStructure = new ArrayList<CourseStructure>();
    	Timestamp time;
    	
    	HashMap<String, List<String>> result = new HashMap<String, List<String>>();
    	
    	//get number of team
    	numOfTeam = teamsHashMap.size();
    	
    	
    	 * number of match in round 1 = numOfTeam/2
    	 
    	int round = 1, rounds = 1;
    	while(round<numOfTeam)
    	{
    		
    		round = round*2;
    		
	    	for (int i=0; i < (numOfTeam/2); i++) {
	    		i++;
	    		actualTeamA = "team"+ ((i*2)-1);
	    		actualTeamB = "team"+ (i*2);
	    		
	    		//get particpants
	    		participantTeamAIds = teamsHashMap.get(actualTeamA);
	    		participantTeamBIds = teamsHashMap.get(actualTeamB);
	    		
	    		//manager
	    		StudentManager studentManager = new StudentManager();
	    		TeachManager teachManager = new TeachManager();
	    		CourseStructureManager coursestructure = new CourseStructureManager();
	    		
	    		//loop in team A
	    		for(int j = 0; j<participantTeamAIds.length;j++){
	    			//get courses by student ids;
	    			
	    			if(studentManager.findStudent(participantTeamAIds[j]) != null){
	    				allCourseCode.add(studentManager.findStudent(participantTeamAIds[j]).getCourse_code());
	    			}//end if(studentManager.findStudent(participantTeamAIds[j]) != null){
	    			
	    			//get module in which staff works
	    			
	    			module = teachManager.getModuleFromStaffId(participantTeamAIds[j]);
	    			if(module.size()>0){
	    				//get course_structure by courses(module_code)
	        			allCourseStructure.addAll( coursestructure.getAllCourseStructureByModule(module) );
	    			}//end if(module.size()>0){
	    			
	    			
	    		}//end for(int j = 0; j<participantTeamAIds.length;j++){
	    		
	    		//loop in team B
	    		for(int j = 0; j<participantTeamBIds.length;j++){
	    			//get courses by student ids;
	    			if(studentManager.findStudent(participantTeamAIds[j]) != null){
	    				allCourseCode.add(studentManager.findStudent(participantTeamAIds[j]).getCourse_code());
	    			}//end if(studentManager.findStudent(participantTeamAIds[j]) != null){
	    			
	    			//get module in which staff works
	    			module = teachManager.getModuleFromStaffId(participantTeamAIds[j]);
	    			
	    			if(module.size()>0){
	    				//get course_structure by courses(module_code)
	        			CourseStructureManager coursestructure = new CourseStructureManager();
	        			allCourseStructure = coursestructure.getAllCourseStructureByModule(module);
	    			}//end if(module.size()>0){
	    			
	    		}//end for(int j = 0; j<participantTeamAIds.length;j++){
	    		
	    		String[] course = new String[allCourseStructure.size()];
	    		for(int j = 0; j<allCourseStructure.size();j++){
	    			course[i] = allCourseStructure.get(j).getCourse_code();
	    		}//end for(int j = 0; j<allCourseStructure.size();j++){
	    		
	    		int[] faculties = {};
	    		
	    		time = generateNonSportSchedule(faculties, course, startDate, endDate).get(0);
	    		
	    		temSchedule.add(actualTeamA);
	    		temSchedule.add(actualTeamB);
	    		temSchedule.add(time.toString());
	    		
	    		
	    		result.put("round "+rounds, temSchedule);
	        	rounds++;
			}//end for (int i = 0; i < numOfTeam; i++) {
    	
    	
    	//other round of projects
    	
    	
    	
    	}//end while
*/    	return result;
    }
    
    
    
    
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
   
   	public Schedule getScheduleByEvent(int event_id){
   		Connection con;
   		String query;
   		Schedule schedule = null;
   		ResultSet rs = null;
   		PreparedStatement pstmt;
	   
   		query = "SELECT * FROM schedule WHERE event_id=?";
   		
   		DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
   		
   		
   		try{
   			
   			pstmt = con.prepareStatement(query);
   			
   			pstmt.setInt(1, event_id);
   			
   			rs = pstmt.executeQuery();
   			
   			while (rs.next()) {
   				schedule = new Schedule();
   				
   				schedule.setDate(rs.getDate("date"));System.out.println("enter");
   				schedule.setDuration(rs.getInt("duration"));
   				schedule.setEvent_id(rs.getInt("event_id"));
   				schedule.setSchedule_id(rs.getInt("schedule_id"));
   				schedule.setTime(rs.getString("starttime"));
   				
				return schedule;
			}//end while (rs.next()) {
   		
   		}catch(SQLException se){
   			
   		}
	   
	   
	   return null;
   }//end of  public Schedule getScheduleByEvent(){
    
   public boolean updateSchedule(Schedule schedule){
	   Connection con;
	   String query;
	   int update;
	   ResultSet rs = null;
	   PreparedStatement pstmt;
	   
	   query = "UPDATE schedule SET duration = ?, starttime = ?, date = ? WHERE schedule_id = ? ";
	   
	   DatabaseConnection dbconnect = new DatabaseConnection();
	   con = dbconnect.getConnection();
	   
	   try{
		   pstmt = con.prepareStatement(query);
		   pstmt.setInt(1, schedule.getDuration());
		   pstmt.setString(2, schedule.getTime());
		   pstmt.setDate(3, schedule.getDate());
		   pstmt.setInt(4, schedule.getSchedule_id());
		   
		   update = pstmt.executeUpdate();
		   
		   if(update == 1)
			   return true;
		   else
			   return false;
		   
	   }catch(SQLException se){
		   
	   }
  		
	   return false;
   }//end public boolean updateSchedule(int schedule_id,String field, String value){
   	
   	
}
