<%@page import="java.sql.Timestamp, java.util.*"%>
<%@page import="com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
<%@page import="java.sql.Date, businessLogic.* , java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!
	String type,title,description,calenderStart,calenderEnd,slot="",outputMessage = "";
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
	User user;
	String user_id="1010";
%>
<%
	user = (User)(session.getAttribute("currentUser"));
	session.setAttribute("currentUser", user);
	user_id = user.getUser_id();	

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
	 		slot += "{"+
		            "title: 'My Event',"+
		            "start: '"+allFreeSlot.get(i)+"',allDay: false";    //'2013-01-09 08:00:00.0 '//new Date(y, m, d-3, 16, 0),"+
		     if(i==(allFreeSlot.size()-1))
		     {
		    	 slot += "}";
		     }
		     else{
		    	 slot += "},";
		     }
		        
	 	}//end for(int i=0;i<allFreeSlot.size();i++){ 
	 	  calenderStart = "<script type='text/javascript'>"+ 
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
					    "		var date = (calEvent.start).toString().split(\" \");"+
						
						"var month = [ \"Jan\", \"Feb\", \"Mar\", \"Apr\", \"May\", \"Jun\",\"Jul\", \"Aug\", \"Sep\", \"Oct\", \"Nov\", \"Dec\" ];"+
						 "var mon = (month.indexOf(date[1]) +1);"+
						"var day = date[2];"+
						"var year = date[3];"+
						"var time = date[4];"+
						"var value = \"\";"+
						
						"if(mon<10){"+
						"	value = year+\"-0\"+mon+\"-\"+day;"+
								"$('#scheduleDate').val(value);"+
						"}else"+
							"{"+
							
							"value = year+\"-\"+mon+\"-\"+day;"+
									"$('#scheduleDate').val(value);"+
							"}"+
							 
				    	 "$('#scheduleDaytime').val(time);"+
				         "$(this).css('border-color', 'red');"+
				         "$('#confirmScheduleDiv').css( {"+
				            " 'position': 'absolute',"+
				            " 'left': x,"+
						    " 'top': y"+
				         "});"+
				         
				         "$('#confirmScheduleDiv').show();"+
					    "}"+
					"});</script>";
	 		
	 		
	 		
	 	//messega formating
	 	outputMessage += calenderStart+slot+calenderEnd
					+"-break-<br><div id=\"confirmScheduleDiv\" >"
 					+"<a onclick=\"$('#confirmScheduleDiv').hide();\">Close</a>"
 					+"<br>"
					+"Time <input type=\"time\" name=\"scheduleDaytime\" id=\"scheduleDaytime\">" 
					+ "<br>"
					+"Date <input type=\"date\" name=\"scheduleDate\" id=\"scheduleDate\">" 
					+ "<br>"
					+ "Duration <input type=\"number\" name=\"scheduleDuration\"  id=\"scheduleDuration\">"
					+"<br>"
					+"<a onclick=\"submitSchedule('"+ user_id +"');\">Submit</a>"
					+"</div>";
	 	
	 	  
	 	out.print(outputMessage);
	}//end if(type.equals("other")){
	else{ 
		//
	outputMessage="";
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
 			
 			outputMessage += " round"+(i+1);
			
 			match = sportSchedule.get("round "+(i+1));
			
			for(int j=0;j<match.size();j++){
				outputMessage += " "+match.get(j);
			}
		} 
			
 		outputMessage += " -break-<br>"
				 	+"<button onclick=\"submitSchedule('"+ user_id +"');\">Submit</button>";
	
			 out.print(outputMessage);
	}//end else

	
%>