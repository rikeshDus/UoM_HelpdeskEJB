<%@page import="com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
<%@page import="java.sql.Date, businessLogic.* , java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!
	String type,title,description,outputMessage = "";
	String[] course;
	String[] faculties;
	int[] faculty;
	Date startDate,endDate;
	ArrayList<Timetable> allFreeSlot = new ArrayList<Timetable>();
%>
<%
 	//capture ajax value from createEventform of event.jsp
  	type = request.getParameter("type");
	title = request.getParameter("title");
	description = request.getParameter("description"); 
	faculties = request.getParameterValues("faculty[]");			
  	course = request.getParameterValues("course[]");
 	startDate = Date.valueOf( request.getParameter("startDate") );
 	endDate = Date.valueOf( request.getParameter("endDate") );
 	
 	//generate schedule
   
  	ScheduleManager scheduleManager = new ScheduleManager();
 	faculty = new int[faculties.length];
 	//convert faculties string array to in array faculty
 	for(int i=0;i<faculties.length;i++){
 		faculty[i] = Integer.parseInt(faculties[i]);
 	}//end for(int i=0;i<faculties.length;i++){
 	
 	
   allFreeSlot = scheduleManager.generateNonSportSchedule(faculty , course, startDate, endDate);
     		
 	 for(int i=0;i<allFreeSlot.size();i++){
 		outputMessage += " <br>" + allFreeSlot.get(i).getDay()+"   "+allFreeSlot.get(i).getDuration();
 	}//end for(int i=0;i<allFreeSlot.size();i++){ */
 	 
 	//messega formating
 	outputMessage += "<br><br>"
 					+"Time <input type=\"time\" name=\"scheduleDaytime\" id=\"scheduleDaytime\">" 
 					+ "<br>"
 					+"Date <input type=\"date\" name=\"scheduleDate\" id=\"scheduleDate\">" 
 					+ "<br>"
 					+ "Duration <input type=\"number\" name=\"scheduleDuration\"  id=\"scheduleDuration\">";
 	
 	out.print(outputMessage);
%>