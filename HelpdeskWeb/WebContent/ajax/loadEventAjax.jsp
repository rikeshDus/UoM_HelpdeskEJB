<%@page import="java.sql.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="businessLogic.*" %>
<%!
	String option="loadEvent",outMgs="error",calenderStart,calenderEnd,calenderMiddle="",alertDisplay,eventId;
	String user_id="1010790",title,time;
	Date date;
	int event_id;
	Schedule schedule;
	Event event;
	boolean trsactionConfirmation;
	
	ArrayList<Event> allEvent = new ArrayList<Event>();

	EventManager eventManager = new EventManager();
	ScheduleManager scheduleManager = new ScheduleManager();
%>
<%
	//load value
	option = request.getParameter("option");

	//calender format
	calenderStart = "<script type='text/javascript'>"+
			"$('.fc-content').remove();"+
			"$('.fc-header').remove();"+
			
				   "$('#calendar').fullCalendar({"+
	 	  		"aspectRatio: 2,editable: false,"+
			    "events: [";//+
			       /*  "{"+
			            "title: 'My Event',"+
			            "start: '2013-01-09 08:00:00.0 '//new Date(y, m, d-3, 16, 0),"+
			            
			        "}"+ */
			        // other events here"+
	calenderEnd	 =   "],"+
			    "eventClick: function(calEvent, jsEvent, view) {"+
				//"var initial = $('#divDisplay').html();"+
			   	//"$('#divDisplay').html($('#divDisplay').html()+\"<input type='text' value ='\"+calEvent.title+\"' /> \");"+ 
				"var date = (calEvent.start).toString().split(\" \");"+
						
				"var month = [ \"Jan\", \"Feb\", \"Mar\", \"Apr\", \"May\", \"Jun\",\"Jul\", \"Aug\", \"Sep\", \"Oct\", \"Nov\", \"Dec\" ];"+
				"var mon = (month.indexOf(date[1]) +1);"+
				"var day = date[2];"+
				"var year = date[3];"+
				"var time = date[4];"+
				"var value = \"\";"+
				
				"if(mon<10){"+
				"	value = year+\"-0\"+mon+\"-\"+day;"+
						"$('#txt_tem_date').val(value);"+
				"}else"+
					"{"+
					
					"value = year+\"-\"+mon+\"-\"+day;"+
							"$('#txt_tem_date').val(value);"+
					"}"+
					 
		    	 "$('#txt_tem_time').val(time);"+
				"$('#txt_tem_title').val(calEvent.title);"+
				//"$('#txt_tem_time').val(calEvent.start);"+
				"var initial = calEvent.id;"+
				"$('#txt_hid_event_id').val(calEvent.id);"+
		        "$('#divDisplay').show();"+ 
			   	"$(this).css('border-color', 'red');"+
		         "$('#divDisplay').css( {"+
		         " 'position': 'absolute',"+
		            " 'left': x-125,"+
		            " 'top': y-315"+
		         "});"+ 
		         
			    "}"+
			"});</script>";
	//divDisplay = "<div id = 'divDisplay'>add <br> delete</div>";
	
	if (option.equals("loadEvent")){
		calenderMiddle  = "";
	 	allEvent = eventManager.getAllEvent();
		for(int i=0;i<allEvent.size();i++){
			
			if(allEvent.get(i).getUser_id().equals(user_id)){
				event_id = allEvent.get(i).getEvent_id();
				schedule = scheduleManager.getScheduleByEvent(event_id);
			
				calenderMiddle += 	"{"+
									"id: "+allEvent.get(i).getEvent_id()+","+
									"title: '"+allEvent.get(i).getTitle()+"',"+
									//should do schedule
									
									"start: '"+schedule.getDate()+" "+schedule.getTime()+"',"+   //2013-01-09 08:00:00.0
									"allDay: false"+
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
		trsactionConfirmation = eventManager.deleteEvent(Integer.parseInt(eventId));
		
		/* prepare alert message */		
		if(trsactionConfirmation)
			alertDisplay = "<h3>Delete Success </h3> "+ eventId;
		else
			alertDisplay = "<h3>Delete Fail </h3> "+ eventId;
		
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
		
		
		
		//format out put
		calenderMiddle = calenderMiddle.substring(0, calenderMiddle.length() - 1);
		outMgs = alertDisplay+calenderStart+calenderMiddle+calenderEnd;
		
		out.print(outMgs);
	}//end else if(option.equals("delete")){
	else if (option.equals("update")){
		//load value
		eventId = request.getParameter("eventId");
		title = request.getParameter("title");
		time = request.getParameter("time");
		date = Date.valueOf( request.getParameter("date"));
		
	
		/* update event */
		event = eventManager.findEvent(event_id);
		event.setTitle(title);
		trsactionConfirmation = eventManager.updateEvent(event);
		if(trsactionConfirmation){
			schedule = scheduleManager.getScheduleByEvent(event_id);
			schedule.setDate(date);
			schedule.setTime(time);		
			trsactionConfirmation = scheduleManager.updateSchedule(schedule);
			
			if(trsactionConfirmation){
				/* prepare alert message */		
				alertDisplay = "<h3>Update Success</h3> "+eventId;
			}
			else{
				/* prepare alert message */		
				alertDisplay = "<h3>Update Fail</h3> "+eventId;
			}
		}
		else{
			/* prepare alert message */		
			alertDisplay = "<h3>Update Success</h3> "+eventId;
		}
		
		
		
		
		/* reload calender */
		calenderMiddle  = "";
		allEvent = eventManager.getAllEvent();
		for(int i=0;i<allEvent.size();i++){
			event_id = allEvent.get(i).getEvent_id();
			schedule = scheduleManager.getScheduleByEvent(event_id);
			
			if(allEvent.get(i).getUser_id().equals(user_id)){
				calenderMiddle += 	"{"+
									"id: "+allEvent.get(i).getEvent_id()+","+
									"title: '"+allEvent.get(i).getTitle()+"',"+
									"start: '"+schedule.getDate()+" "+schedule.getTime()+"',"+   //2013-01-09 08:00:00.0
									"allDay: false"+
									"},";				
				
			}//end if(allEvent.get(i).getUser_id().equals(user_id)){			
			
		}//end of for(int i=0;i<allEvent.size();i++){		
		
		
		
		//format out put
		calenderMiddle = calenderMiddle.substring(0, calenderMiddle.length() - 1);
		outMgs = alertDisplay+calenderStart+calenderMiddle+calenderEnd;
		
		out.print(outMgs);
	}//end else if (option.equals("update")){
	else{
		
	}//end else
	
%>