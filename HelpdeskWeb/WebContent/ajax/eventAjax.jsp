<%@page import="com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
<%@page import="java.sql.Date, businessLogic.* , java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!
	String type,title,description,facultyEngineering,facultyAgriculture,x,outputMessage = "default";
	String[] course;
	Date startDate,endDate;
	ArrayList<Timetable> allFreeSlot = new ArrayList<Timetable>();
%>
<%
 	//capture ajax value from createEventform of event.jsp
  	type = request.getParameter("type");
	title = request.getParameter("title");
	description = request.getParameter("description");
	facultyEngineering = request.getParameter("facultyEngineering");
	facultyAgriculture = request.getParameter("facultyAgriculture");			
 	course = request.getParameterValues("course[]");
 	startDate = Date.valueOf( request.getParameter("startDate") );
 	endDate = Date.valueOf( request.getParameter("endDate") );
 	
 	 
 	//generate schedule
  
  	ScheduleManager scheduleManager = new ScheduleManager();
 	int[] faculties = {1};
 	
   allFreeSlot = scheduleManager.generateNonSportSchedule(faculties , course, startDate, endDate);
     		
 	 for(int i=0;i<allFreeSlot.size();i++){
 		outputMessage += " <br>" + allFreeSlot.get(i).getDay()+"   "+allFreeSlot.get(i).getDuration();
 	}//end for(int i=0;i<allFreeSlot.size();i++){
 	 
 	 
 	out.print(outputMessage);
%>