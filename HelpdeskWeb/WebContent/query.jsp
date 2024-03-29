<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="businessLogic.*"%>

<%! 
	User user;
	Staff staff;
	
	boolean isStaff = false;

	String combo ="<select id=\"comboQueryType\" onchange=\"displayTechnicalFrom();\"><option>normal</option><option>technical</option></select>"; 

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
<%-- <link href="style.css" rel="stylesheet" type="text/css" />--%>
<script type="text/javascript" src="javascript/core.js"></script>

</head>
<body>


 <div id="content">
	 <div id = "innercontent">
	 
	<input type="button" value="Forward Query" onclick="forwardQuery('<%= user.getUser_id()%>');"/>
<!-- 	<input type="button" value="Advance Query" onclick="advanceQuery();"/>  -->
	<%
	if(isStaff){
	%>
	<%= combo %>
	<%
	}
	%>
	<br></br>
	Query<br>
	<textarea id="query"></textarea>
<br><br>
<input type="button" value="Submit" onClick="getQuestion('questions','<%= user.getUser_id() %>','none');"/>
</div></div>
	<br>
	<br>
	<div id="queryResult"></div>
	<div id="tracking"></div>

</body>
</html>