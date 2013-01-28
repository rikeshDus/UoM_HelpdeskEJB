<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="businessLogic.*" %>
<%!
	String option="loadEvent",outMgs="error",calenderStart,calenderEnd,calenderMiddle="",alertDisplay,eventId;
	String user_id="1010790";
	ArrayList<Event> allEvent = new ArrayList<Event>();

	EventManager eventManager = new EventManager();
%>
<%
	//load value
	option = request.getParameter("option");

	//calender format
	calenderStart = "<script type='text/javascript'>"+
			"$('.fc-content').remove();"+
			"$('.fc-header').remove();"+
			
				   "$('#calendar').fullCalendar({"+
	 	  		"aspectRatio: 2,editable: true,"+
			    "events: [";//+
			       /*  "{"+
			            "title: 'My Event',"+
			            "start: '2013-01-09 08:00:00.0 '//new Date(y, m, d-3, 16, 0),"+
			            
			        "}"+ */
			        // other events here"+
	calenderEnd	 =   "],"+
			    "eventClick: function(calEvent, jsEvent, view) {"+
				//"var initial = $('#divDisplay').html();"+
			   	//"$('#divDisplay').html(\"<input type='text' value ='\"+calEvent.id+\"' /> \");"+ 
				"var initial = calEvent.id;"+
				"$('#txt_hid_event_id').val(calEvent.id);"+
		        "$('#divDisplay').show();"+ 
			   	"$(this).css('border-color', 'red');"+
		         "$('#divDisplay').css( {"+
		         " 'position': 'absolute',"+
		            " 'left': x,"+
		            " 'top': y"+
		         "});"+ 
		         
			    "}"+
			"});</script>";
	//divDisplay = "<div id = 'divDisplay'>add <br> delete</div>";
	
	if (option.equals("loadEvent")){
		calenderMiddle  = "";
		allEvent = eventManager.getAllEvent();
		for(int i=0;i<allEvent.size();i++){
			
			if(allEvent.get(i).getUser_id().equals(user_id)){
				calenderMiddle += 	"{"+
									"id: "+allEvent.get(i).getEvent_id()+","+
									"title: '"+allEvent.get(i).getTitle()+"',"+
									"start: '2013-01-09 08:00:00.0 '"+
									"},";				
				
			}//end if(allEvent.get(i).getUser_id().equals(user_id)){			
			
		}//end of for(int i=0;i<allEvent.size();i++){		
			
			
		//format out put
		calenderMiddle = calenderMiddle.substring(0, calenderMiddle.length() - 1);
		outMgs = calenderStart+calenderMiddle+calenderEnd;
		
		out.print(outMgs);
			
	}//end if (option.equals("loadEvent")){
	else if(option.equals("delete")){
		
		//load value
		eventId = request.getParameter("eventId");
		
		/* delete event */
		
		
		/* reload calender */
		calenderMiddle  = "";
		allEvent = eventManager.getAllEvent();
		for(int i=0;i<allEvent.size();i++){
			
			if(allEvent.get(i).getUser_id().equals(user_id)){
				calenderMiddle += 	"{"+
									"id: "+allEvent.get(i).getEvent_id()+","+
									"title: '"+allEvent.get(i).getTitle()+"',"+
									"start: '2013-01-09 08:00:00.0 '"+
									"},";				
				
			}//end if(allEvent.get(i).getUser_id().equals(user_id)){			
			
		}//end of for(int i=0;i<allEvent.size();i++){		
		
		/* prepare alert message */		
		alertDisplay = "<h3>Delete Success </h3> "+ eventId;
		
		//format out put
		calenderMiddle = calenderMiddle.substring(0, calenderMiddle.length() - 1);
		outMgs = alertDisplay+calenderStart+calenderMiddle+calenderEnd;
		
		out.print(outMgs);
	}//end else if(option.equals("delete")){
	else if (option.equals("update")){
		//load value
		eventId = request.getParameter("eventId");
		
		
	
		/* update event */
		
		
		/* reload calender */
		calenderMiddle  = "";
		allEvent = eventManager.getAllEvent();
		for(int i=0;i<allEvent.size();i++){
			
			if(allEvent.get(i).getUser_id().equals(user_id)){
				calenderMiddle += 	"{"+
									"id: "+allEvent.get(i).getEvent_id()+","+
									"title: '"+allEvent.get(i).getTitle()+"',"+
									"start: '2013-01-09 08:00:00.0 '"+
									"},";				
				
			}//end if(allEvent.get(i).getUser_id().equals(user_id)){			
			
		}//end of for(int i=0;i<allEvent.size();i++){		
		
		/* prepare alert message */		
		alertDisplay = "<h3>Update Success</h3> "+eventId;
		
		//format out put
		calenderMiddle = calenderMiddle.substring(0, calenderMiddle.length() - 1);
		outMgs = alertDisplay+calenderStart+calenderMiddle+calenderEnd;
		
		out.print(outMgs);
	}//end else if (option.equals("update")){
	else{
		
	}//end else
	
%>