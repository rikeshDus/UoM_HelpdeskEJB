<%@page import="java.sql.Timestamp, java.util.*"%>
<%@page import="com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
<%@page import="java.sql.Date, businessLogic.* , java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!
	String type,title,description,outputMessage = "";
	String[] course;
	String[] faculties;
	String[] teamParticipant;
	String[] participant;
	int[] faculty;
	int team;
	Date startDate,endDate;
	ArrayList<Timestamp> allFreeSlot = new ArrayList<Timestamp>();
	List<String> match = new ArrayList<String>();
	HashMap<String,List<String>> sportSchedule = new HashMap<String,List<String>>();
	HashMap<String, String[]> teamsHashMap = new HashMap<String, String[]>();
%>
<%
 	//capture ajax value from createEventform of event.jsp
   	type = request.getParameter("type");
	title = request.getParameter("title");
	startDate = Date.valueOf( request.getParameter("startDate") );
 	endDate = Date.valueOf( request.getParameter("endDate") );
	
	if(type.equals("other")){
		course = request.getParameterValues("course[]");
		description = request.getParameter("description"); 
		faculties = request.getParameterValues("faculty[]");			
	  	course = request.getParameterValues("course[]");
	 	
	 	
	 	//generate schedule
	   
	  	ScheduleManager scheduleManager = new ScheduleManager();
	 	faculty = new int[faculties.length];
	 	//convert faculties string array to in array faculty
	 	for(int i=0;i<faculties.length;i++){
	 		faculty[i] = Integer.parseInt(faculties[i]);
	 	}//end for(int i=0;i<faculties.length;i++){
	 	
	 	
	   allFreeSlot = scheduleManager.generateNonSportSchedule(faculty , course, startDate, endDate);
	     		
	 	 for(int i=0;i<allFreeSlot.size();i++){
	 		outputMessage += " <br>" + allFreeSlot.get(i)+"   ";
	 	}//end for(int i=0;i<allFreeSlot.size();i++){ 
	 	 
	 	//messega formating
	 	outputMessage += "<br><br>"
	 					+"Time <input type=\"time\" name=\"scheduleDaytime\" id=\"scheduleDaytime\">" 
	 					+ "<br>"
	 					+"Date <input type=\"date\" name=\"scheduleDate\" id=\"scheduleDate\">" 
	 					+ "<br>"
	 					+ "Duration <input type=\"number\" name=\"scheduleDuration\"  id=\"scheduleDuration\">";
	 	 
	 	out.print(outputMessage);
	}//end if(type.equals("sport")){
	else{ 
		
	
		//getvalues
		team = Integer.parseInt(request.getParameter("teams")); 
		teamParticipant = request.getParameterValues("teamsParticipant[]");
		ScheduleManager scheduleManager = new ScheduleManager();
		
	 	for(int i=0;i<teamParticipant.length;i++){
			teamsHashMap.put("team"+(i+1), teamParticipant[i].split(","));
		}
		 
	/* 	String[] id = {"1010790"};
		teamsHashMap.put("team1", id);
		teamsHashMap.put("team2", id);
		teamsHashMap.put("team3", id);
		teamsHashMap.put("team4", id);
		teamsHashMap.put("team5", id);
		teamsHashMap.put("team6", id);
		teamsHashMap.put("team7", id);
		teamsHashMap.put("team8", id); */
		
		sportSchedule = scheduleManager.generateSportSchedule(teamsHashMap, startDate, endDate);
		
		
 		for(int i=0;i<sportSchedule.size();i++){
 			
 			if(i==0)
				outputMessage += "round"+(i+1);
 			else
 				outputMessage += " round"+(i+1);
			match = sportSchedule.get("round "+(i+1));
			
			for(int j=0;j<match.size();j++){
				outputMessage += " "+match.get(j);
			}
		} 
			
 		outputMessage += "-break-<br>"
					+"Time <input type=\"time\" name=\"scheduleDaytime\" id=\"scheduleDaytime\">" 
					+ "<br>"
					+"Date <input type=\"date\" name=\"scheduleDate\" id=\"scheduleDate\">" 
					+ "<br>"
					+ "Duration <input type=\"number\" name=\"scheduleDuration\"  id=\"scheduleDuration\">";
	
			 out.print(outputMessage);
	}//end else

	
%>