<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="businessLogic.*" %>
    
<%! 
	User user;
	StaffManager staffManager = new StaffManager();
	Staff staff = new Staff();
	boolean isStaff = false;
%>
<%
	//get current user
	user = (User)(session.getAttribute("currentUser"));
	
	//check for staff
	staff = staffManager.findStaffByUserId(user.getUser_id());
	
	if(staff != null){
		
		isStaff = true;
		
	}//end of if(staff == null){

%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="javascript/core.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if(isStaff){
	%>

 	<input type="button" value="View Tracking" id="viewTracking" onclick="getMyTracking('<%= user.getUser_id() %>','getTrackings');"/>
	<div id="replyQuery"></div>
	<div id="divAllTracking">allTracking</div>
	
	
	
	
	
	<%
		}//end of if(isStaff){
		else{
	%>
	
	not a staff	
	
	<%}//end of }//end of if(isStaff){ else{ %>
</body>
</html>