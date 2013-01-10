<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="businessLogic.*"%>

<%! 
	User user;
	Staff staff;
	
	boolean isStaff = false;

	String combo ="<select><option>normal</option><option>technical</option></select>"; 

	StudentManager studentManager = new StudentManager();
	StaffManager staffManager = new StaffManager();
%>
<%
	//get current user
	user = (User)(session.getAttribute("currentUser"));

	/*
	*check if staff or student
	*if student give form for a normal query
	* if staff give form for technical qeury also
	*/
	staff = staffManager.findStaffByUserId(user.getUser_id());
	if(staff != null){
		isStaff = true;
	}//end if(staff == null){
	
		
	/*
	*send normal query by ajax and retrieve solutions
	*give "forward" option which can be use if user has not agree to current solution
	*/
	

	/*
	*when user is fowarding query, use ajax to submit value
	*load tracking
	*/

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UoM Helpdesk</title>
</head>
<body>
	Query
	<textarea></textarea>
	<br>
	<br>
	<%
	if(isStaff){
	%>
	<%= combo %>
	<%
	}
	%>
	
	<input type="button" value="Query" />
</body>
</html>