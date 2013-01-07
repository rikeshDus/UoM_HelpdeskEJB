<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Date, businessLogic.* , java.util.ArrayList"%>
<%!
	String title,description,type,user_id,time;
	int duration,currentEventId;
	Date date;
	boolean result;
%>


<%
	//get value
	title = request.getParameter("title");
	description = request.getParameter("description");
	type = request.getParameter("type");
	user_id = request.getParameter("user");
	 
	time = request.getParameter("time");
	duration =Integer.parseInt(request.getParameter("duration"));
	date = Date.valueOf( request.getParameter("date") );

	
	
	ScheduleManager scheduleManager = new ScheduleManager();
	EventManager eventManager = new EventManager();
	
	
	 
	currentEventId = eventManager.createEvent( title, description, type, user_id);
	result = scheduleManager.createSchedule( duration, date, time, currentEventId);
	if(result && currentEventId>0){
		out.print("success"); 	
	}//end if(result && currentEventId>0){
	else{
		out.print("fail");
	}//end else
%>