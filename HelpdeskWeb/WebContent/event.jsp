<%@page import="java.util.ArrayList"%>
<%@ page import="businessLogic.*" %>

<%! 
	String comboCourse = "";
	String comboUser = "";
	String chechBoxFaculty = "";
	ArrayList<Course> allCourse = new ArrayList<Course>();
	ArrayList<User> allUser = new ArrayList<User>();
	ArrayList<Faculty> allFaculty = new ArrayList<Faculty>();
	User user;
	String user_id;
%>
<%
	user = (User)(session.getAttribute("currentUser"));
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
		 comboUser += "<option value = \' "+allUser.get(i).getUser_id()+" \' >"+allUser.get(i).getUser_id()+"</option>";
	 }
	 
	 
	 /* 
		generating a list of faculty from database
		format it to fit in a combo box :-
		<option value=id> faculty name </option>
	 */
	  FacultyManager facultyManager = new FacultyManager();	 
	 allFaculty = facultyManager.getAllFaculty();
	 
	 for(int i=0;i<allFaculty.size();i++){
		 chechBoxFaculty += allFaculty.get(i).getName()+" <input type=\"checkbox\" name=\"faculty\" value=\""+allFaculty.get(i).getFaculty_id()+"\" id=\""+allFaculty.get(i).getName()+"\"/>";
	 }//end of for(int i=0;i<allFaculty.size();i++){
	  
	  
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="javascript/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
		  // Handler for .ready() called.
			$("#displaySchedule").hide();
			$("#innerSportCreateEventForm").hide();
		});
		
		function sportHideShow(){
			var event = $("#eventType").val();
			if(event == "sport")
			{
				$("#innerSportCreateEventForm").show().fadeIn(500);
				$("#innerSportOtherEventForm").show().fadeOut(500);
			}
			else
			{
				$("#innerSportCreateEventForm").show().fadeOut(500);
				$("#innerSportOtherEventForm").show().fadeIn(500);
			}
		
						
		}
		
		var courseArray = [];
		var courseNumberAdded = 0;
		var teamArray ="";
		//var teamParticipant = "";
		var teamParticipantCount = 0;
		
		function sportTeamsDisplay(){
			//get number of teams
			var numTeams = $("#txt_numOfTeam").val();
			var round = numTeams;
			while(round>2){
				round = round/2;
					if((round%2) == 1){
						alert("Number of team should be 2,4,8,16,32: table of 2");
						break;
					}
				
								
			}
			//initialize array
			teamArray = new Array(numTeams);
			fillTeamArray(numTeams);
			
			
			var display = "<table>";
			
			for(var i=0; i<numTeams;i++){
				display += "<tr><td> Team "+(i+1)+"</td><td><select id= 'selectTeam" +(i+1)+"'><%= comboUser %></select></td>";
				display += "<td><button onclick=\"addParticipant('Team" +(i+1)+"')\">add</button><td></tr><tr><td colspan='3'><textarea id= 'textareaTeam" +(i+1)+"'></textarea></td></tr>";
			}//end for(var i=0; i<numTeams;i++){
			
			display += "</table>";
			
			//add disply to div
			$("#innerSportTeamsDisplay").html(display);
			
		}//end function sportTeamsDisplay(){
			
 			
		function addParticipant(team){
			var combo = "#select"+team;
			var textarea = "#textarea"+team;
			
			var participant = $(combo).val();
			var texareaValue = $(textarea).val();
			var ParticipantCount = team.charAt( team.length-1 );
			teamParticipantCount = parseInt(ParticipantCount)-1;
			$(textarea).val(texareaValue+participant );
			var teamParticipant = teamArray[teamParticipantCount];
			teamParticipant.push(participant);
			teamArray[teamParticipantCount] = teamParticipant;
			teamParticipantCount++;
		}//end function addParticipant(team){
			
		
		
		function fillTeamArray(numTeams){
			for(var i = 0;i<numTeams;i++){
				teamArray[i] = new Array();
				/* var textarea = "#textareaTeam"+(i+1);
				var text = " "+$(textarea).val();
				teamParticipant = text.split("   ");
				teamParticipant.splice(0, 1);
				alert(teamParticipant.length +" : "+teamParticipant);
				teamArray[i] = new Array(teamParticipant.length);
				for(var j=0;j<teamParticipant.length;j++){
					teamArray[i][j] = teamParticipant[j];
					alert(i + " "+ j);
				}
				teamArray[i] = teamParticipant; */
				
			}
			
			
		}
		var roundArr = [];
		
		function submitcreateEventFormDiv(){			
			$("#createEventFormDiv").hide(1000);
			//get checkbox value
			var faculties = [];
			$("input[name='faculty']:checked").each(function(){faculties.push($(this).val());});
			$.post("ajax/eventAjax.jsp",
			{
				'type':$("#eventType").val(),
				'title':$("#title").val(), 
				'description':$("#description").val(),
				'faculty[]':  faculties,
				'course[]': courseArray,
				'startDate':$("#startDate").val(),
				'endDate':$("#endDate").val(),
				'teams':$("#txt_numOfTeam").val(),
				'teamsParticipant[]':teamArray
			},
			function(data,status){
				
				 //extract data
				var allData = data.split("-break-");
				var form = allData[1];
				var round = allData[0]; 
				
				roundArr = round.split(" ");
				
				
				var temp =$("#displaySchedule").html();
				$("#displaySchedule").html( round+"<br>confirm Schedule</br>"+temp);
				$("#displaySchedule").show().fadeIn(1000);
			});
			
		}//end function SubmitcreateEventFormDiv(){
		
			
			
		function addCourse(){	
			courseArray[courseNumberAdded] = $("#comboCourse").val();
			alert(courseArray[courseNumberAdded]);
			courseNumberAdded++;
			//$("#courseAddedTextArea").append($("#comboCourse").text());
			$("#courseAddedConfirmMessge").html("<span style='color: #123456;'> Course Successfully Added </span>").show().fadeIn(500).fadeOut(2000);
			
		}//end function addCourse(){
			
			
		function submitSchedule(){
			$.post("ajax/eventSchedule.jsp",
			{
				'type':$("#eventType").val(),
				'title':$("#title").val(), 
				'description':$("#description").val(),
			 	'date':$("#scheduleDate").val(),
				'time':$("#scheduleDaytime").val(),
				'duration':$("#scheduleDuration").val(),
				'event[]':roundArr,
				'user':<%= user_id %>
			},
			function(data,status){
				$("#displaySchedule").html( data+"in saving operation <a href='homepage.jsp'>back</a>");
				
			});
			
		}//end function submitSchedule()
		
		
	</script>
	<title>UoM Helpdesk</title>
</head>
<body >
	
	<div id="createEventFormDiv">
	
			<table cellspacing="2" cellpadding="3">
				<tr>
					<td>Event Type</td>
					<td> 
						<select name="" id="eventType" onchange="sportHideShow();">
							<option value="other">Other</option>
							<option value="sport">Sport</option>							
						</select>
					</td>
				</tr>
				<tr>
					<td>Title</td>
					<td><input type="text" name="" id="title"/> </td>
				</tr>
					
				<tr>
					<td>Description</td>
					<td><textarea name="" id="description"></textarea></td>
				</tr>
			</table>
			<div id="innerSportOtherEventForm">
				<table>
					<tr>
						<td colspan="2">Faculty Involve </td>
					</tr>
					<tr>
						<td colspan="2">
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
						<td colspan="2">
							Course Added <br>
							<div id="courseAddedConfirmMessge"></div><br>
							<textarea rows="" cols="" readonly="readonly" id="courseAddedTextArea"></textarea>
						</td>
					</tr>
					<tr>
						<td>ID Involve</td>
						<td>
							<select id="comboUser">
								<%=comboUser %>
							</select>
							<br>
							<button>Add Id</button>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							Id Added <br>
							<textarea rows="" cols="" id="courseAddedTextArea"></textarea>
						</td>
					</tr>
				</table>
			</div>
			<div id="innerSportCreateEventForm">
				<table>			
					<tr>
						<td>Number of teams</td>
						<td><input type="number" id="txt_numOfTeam" min="0" onblur="sportTeamsDisplay();"/> </td>
					</tr>	
				</table>
				<div id="innerSportTeamsDisplay"></div>
			</div>
			<table>	
				<tr>
					<td>Starting Date</td>
					<td><input type="date" name="startDate" id="startDate"></td>
				</tr>
				<tr>
					<td>Ending Date</td>
					<td><input type="date" name="endDate" id="endDate"></td>
				</tr>
			</table>
		
		<button onclick="submitcreateEventFormDiv();">Submit</button>
		
	</div>
	
	
	
	
	<div id="displaySchedule">
		
		<button onclick="submitSchedule();">Submit</button>
	</div>
</body>
</html>