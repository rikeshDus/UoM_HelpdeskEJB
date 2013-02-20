<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Date, businessLogic.* , java.util.ArrayList"%>
<%!
	String title,description = "",type,user_id,time,outMgs="default",round;
	int duration,currentEventId;
	Date date;
	boolean result;
	
	String event[];
	
	ScheduleManager scheduleManager = new ScheduleManager();
	EventManager eventManager = new EventManager();
%>


<%
	//get value
	title = request.getParameter("title");
	type = request.getParameter("type");
	user_id = request.getParameter("user");
	
	if(type.equals("sport")){
		//
		outMgs="";
		event = request.getParameterValues("event[]");
		
 		for(int i =0;i<event.length;i++){
			if(i>0 && event[i].contains(":")){
				
				
				time = event[i];
				outMgs += "\\ loo tim \\:"+i+time+" "+title+" "+type+"\\";
				if(description.equals("") && !round.equals("")){
					description = round;
				}
				
				currentEventId = eventManager.createEvent( title, description, type, user_id);
				result = scheduleManager.createSchedule( 0, date, time, currentEventId);
				if(result && currentEventId>0){
					outMgs = "success"; 	
				}//end if(result && currentEventId>0){
				else{
					outMgs += "fail :"+i+event[i];
				}//end else
				description="";
			}
			else if(event[i].contains("break")){
					//do nothing
					outMgs += "  \\:"+i+event[i]+"\\";
				}
			else{
				
				
				if(event[i].contains("-")){
					
				
					date = Date.valueOf(event[i]);
					outMgs += "  \\loo dat \\:"+i+date+"\\";	
				}else
				{
								
					description += " "+event[i];
					if(description.contains("round")){
						round =description;
					}
					outMgs += "  \\loo des\\:"+i+description+"\\";
				}
			}
			
		} 
			
		out.print(outMgs);
	}
	else
	{
		time = request.getParameter("time");
		duration =Integer.parseInt(request.getParameter("duration"));
		date = Date.valueOf( request.getParameter("date") );
		description = request.getParameter("description");
		
		 
		currentEventId = eventManager.createEvent( title, description, type, user_id);
		result = scheduleManager.createSchedule( duration, date, time, currentEventId);
		if(result && currentEventId>0){
			out.print("success"); 	
		}//end if(result && currentEventId>0){
		else{
			out.print("fail"+time+":"+date+":"+duration+":"+currentEventId);
		}//end else

	}
/* 	
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
 */%>