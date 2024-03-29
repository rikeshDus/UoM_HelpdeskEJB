<%@page import="java.util.ArrayList"%>
<%@ page import="businessLogic.*" %>

<%! 
	String comboCourse;
	String comboUser;
	String chechBoxFaculty;
	ArrayList<Course> allCourse = new ArrayList<Course>();
	ArrayList<User> allUser = new ArrayList<User>();
	ArrayList<Faculty> allFaculty = new ArrayList<Faculty>();
	User user;
	String user_id;
%>
<%
	//clear varirable
	chechBoxFaculty="";
	comboCourse= "";
	chechBoxFaculty = "";
	comboUser="";
	
	user = (User)(session.getAttribute("currentUser"));
	session.setAttribute("currentUser", user);
	user_id = user.getUser_id();	
	 
	 
 	CourseManager courseManager = new CourseManager();
	allCourse = courseManager.getAllCourse();
	
	//generating <option> for each course </option>
	for(int i=0;i<allCourse.size();i++){
		comboCourse += "<option value=\' "+allCourse.get(i).getCourse_code()+" \'>"+allCourse.get(i).getName()+"</option>";
	}//end for(int i=0;i<allCourse.size();i++){
		
		 
		
		
	/* 
	generating a list of student Id from database
	format it to fit in a combo box :-
	<option value=id> Id </option>
	 */
	 UserManager userManager = new UserManager();
	 allUser = userManager.getAllUser();
	 
	 for(int i=0; i<allUser.size();i++){
		 comboUser += "<option value =\\\' "+allUser.get(i).getUser_id()+"\\\' >"+allUser.get(i).getUser_id()+"</option>";
	 }
	 
	 
	 /* 
		generating a list of faculty from database
		format it to fit in a combo box :-
		<option value=id> faculty name </option>
	 */
	  FacultyManager facultyManager = new FacultyManager();	 
	 allFaculty = facultyManager.getAllFaculty();
	 
	 for(int i=0;i<allFaculty.size();i++){
		 chechBoxFaculty += " "+allFaculty.get(i).getName()+" <input type=\"checkbox\" name=\"faculty\" value=\""+allFaculty.get(i).getFaculty_id()+"\" id=\""+allFaculty.get(i).getName()+"\"/>";
	 }//end of for(int i=0;i<allFaculty.size();i++){
	  
	  
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- <link href="style.css" rel="stylesheet" type="text/css" /> --%>
	<link rel='stylesheet' type='text/css' href='fullcalendar/fullcalendar.css' />
	<link rel='stylesheet' type='text/css' href='fullcalendar/fullcalendar.print.css' media='print' />
	<style type='text/css'>

	
	#calendar {
		width: 900px;
		margin: 0 auto;
		margin-top: 40px;
		text-align: center;
		font-size: 14px;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		}
		#confirmScheduleDiv{
	z-index: 20;
	background-color: grey;
	}
	#divDisplay{
	z-index: 20;
	background-color: grey;
	}

</style>
<%--	<script type='text/javascript' src='jquery/jquery-1.8.1.min.js'></script> --%>
	<script type='text/javascript' src='jquery/jquery-ui-1.8.23.custom.min.js'></script>
	<script type='text/javascript' src='fullcalendar/fullcalendar.min.js'></script>
	<!-- <script type="text/javascript" src="javascript/jquery-1.8.3.min.js"></script>-->
	<script type="text/javascript" src="javascript/core.js"></script>
	<title>UoM Helpdesk</title>
</head>
<body >

 <div id="content">
 
 <div id = "innercontent">
	<!-- <div id="loading" align="right"><b>Loading...</b></div> -->
	<div id="deleteEvent">
		
	
	
	</div>
	

	
	<div id="createEventFormDiv">
	
			<table cellspacing="2" cellpadding="3">
				<tr>
					<td width="150px">Event Type</td>
					<td> 
						<select name="" id="eventType" style="width: 210px" onchange="sportHideShow();">
							<option value="other">Other</option>
							<option value="sport">Sport</option>							
						</select>
					</td>
				</tr>
				<tr>
					<td>Title</td>
					<td><input type="text" name="" id="title" size="30"/> </td>
				</tr>
					
				<tr>
					<td>Description</td>
					<td><textarea name="" style="width: 210px" id="description"></textarea></td>
				</tr>
			</table>
			<div id="innerSportOtherEventForm">
				<table>
					<tr>
						<td width="150px">Faculty Involve </td>
					
						<td >
							<%= chechBoxFaculty %>
						</td>
					</tr>
					<tr>
						<td>Course Involve</td>
						<td>
							<select id="comboCourse">
								<%= comboCourse %>
							</select>
							<br>
							<input type="button" onclick="addCourse()" value="add">
						</td>
					</tr>
					<tr>
						<td>
							Course Added
						</td>
						<td> 
							<div id="courseAddedConfirmMessge"></div><br>
							<textarea rows="" cols="" style="width: 210px" readonly="readonly" id="courseAddedTextArea"></textarea>
						</td>
					</tr>
				</table>
			</div>
			<div id="innerSportCreateEventForm">
				<table>			
					<tr>
						<td width="150px">Number of teams</td>
						<td><input type="number" id="txt_numOfTeam" min="0" onblur="sportTeamsDisplay('<%= comboUser %>');"/> </td>
					</tr>	
				</table>
				<div id="innerSportTeamsDisplay"></div>
			</div>
			<table>	
				<tr>
					<td width="150px">Starting Date</td>
					<td><input type="date" name="startDate" id="startDate" style="width: 210px"></td>
				</tr>
				<tr>
					<td>Ending Date</td>
					<td><input type="date" name="endDate" id="endDate" style="width: 210px"></td>
				</tr>
			</table>
		
		<button onclick="submitcreateEventFormDiv();">Submit</button>
		
	</div>
	
	
	
	
	<div id="displaySchedule">
		
		<button onclick="submitSchedule('<%= user_id %>');alert('<%= user_id %>')">Submit</button>
	</div>
	<div id='calenderMgs'></div>
	<div id='calendar'></div>
	<div id = 'divDisplay'>
		<a onclick="$('#divDisplay').hide();" >Close</a>		
		<center><a onclick="loadEvent('update');">update</a> 
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a onclick="loadEvent('delete')">delete</a></center>
		<br>
		Title : <input type="text" id="txt_tem_title"/>
		<br>
		Time : <input type="text" id="txt_tem_time"/>
		<br>
		Date : <input type="text" id="txt_tem_date"/>
		<br>
		<input type="hidden" id="txt_hid_event_id"/> 
	</div>
</div>
 </div>


</body>
</html>