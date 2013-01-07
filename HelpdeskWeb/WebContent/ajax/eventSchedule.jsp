<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Date, businessLogic.* , java.util.ArrayList"%>
<%!
	String title,description,type,user_id,time;
	int duration;
	Date date;
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

	out.print(title+" " + description+" " +type+" " +user_id+" " +time+" " +duration+" " +date); 
	/* 
	ScheduleManager scheduleManager = new ScheduleManager();
	EventManager eventManager = new EventManager();
	
	eventManager.createEvent(null, title, description, type, user_id);
	scheduleManager.createSchedule(null, duration, date, time, "LAST_INSERT_ID()");
	 */
	
	/* 
	INSERT INTO foo (auto,text)
    VALUES(NULL,'text');         # generate ID by inserting NULL
INSERT INTO foo2 (id,text)
    VALUES(LAST_INSERT_ID(),'text');  */
%>