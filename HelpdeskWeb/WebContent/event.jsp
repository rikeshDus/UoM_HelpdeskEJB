<%@page import="java.util.ArrayList"%>
<%@ page import="businessLogic.*" %>

<%! 
	String comboCourse = "";
	String comboUser = "";
	ArrayList<Course> allCourse = new ArrayList<Course>();
	ArrayList<User> allUser = new ArrayList<User>();
%>
<%
	CourseManager courseManager = new CourseManager();
	allCourse = courseManager.getAllCourse();
	
	//generating <option> for each course </option>
	for(int i=0;i<allCourse.size();i++){
		comboCourse += "<option value=\" "+allCourse.get(i).getCourse_code()+" \">"+allCourse.get(i).getName()+"</option>";
	}//end for(int i=0;i<allCourse.size();i++){
		
		
		
		
	/* 
	generating a list of student Id from database
	format it to fit in a combo box :-
	<option value=id> Id </option>
	 */
	 UserManager userManager = new UserManager();
	 allUser = userManager.getAllUser();
	 
	 for(int i=0; i<allUser.size();i++){
		 comboUser += "<option value = \" "+allUser.get(i).getUser_id()+" \" >"+allUser.get(i).getUser_id()+"</option>";
	 }
	 
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="javascript/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
var courseArray = [];

var courseNumberAdded = 0;


function submitcreateEventFormDiv(){
	$("#createEventFormDiv").hide(1000);
	$.post("ajax/eventAjax.jsp",
	{
		 'type':$("#eventType").val(),
		'title':$("#title").val(),
		'description':$("#description").val(),
		'facultyEngineering':  $("#checkFacultyEngineering").val(),
		'facultyAgriculture':  $("#checkFacultyAgriculture").val(),
		'course[]': courseArray
	},
	function(data,status){
		alert("Data:" + data);
	});
	
}//end function SubmitcreateEventFormDiv(){

function addCourse(){	
	courseArray[courseNumberAdded] = $("#comboCourse").val();
	alert(courseArray[courseNumberAdded]);
	courseNumberAdded++;
	$("#courseAddedTextArea").append($("#comboCourse").text());
	$("#courseAddedConfirmMessge").html("<span style='color: #123456;'> Course Successfully Added </span>").show().fadeIn(500).fadeOut(2000);
	
}//end function addCourse(){
</script>
<title>UoM Helpdesk</title>
</head>
<body >
	<div id="createEventFormDiv">
		<form action="#" name="createEventForm">
			<table cellspacing="2" cellpadding="3">
				<tr>
					<td>Event Type</td>
					<td> 
						<select name="" id="eventType">
							<option value="sport">Sport</option>
							<option value="other">Other</option>
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
				<tr>
					<td colspan="2">Faculty Involve </td>
				</tr>
				<tr>
					<td colspan="2">
						Engineering <input type="checkbox" name="faculty" id="checkFacultyEngineering"/>
						Agriculture <input type="checkbox" name="faculty" id="checkFacultyAgriculture"/>
						
					</td>
				</tr>
				<tr>
					<td>Course Involve</td>
					<td>
						<select id="comboCourse">
							<%= comboCourse %>
						</select>
						<br>
						<button onclick="addCourse();">Add Course</button>
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
							<%= comboUser %>
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
		</form>
	</div>
	<button onclick="submitcreateEventFormDiv();">Submit</button>
</body>
</html>