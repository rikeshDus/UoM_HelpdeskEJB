<%@page import="businessLogic.UserManager" %>
<%!
	String username,password;
	boolean loginValidate = false;
%>
<%
	username = request.getParameter("txt_username");
	password = request.getParameter("txt_password");
	
	UserManager userManager = new UserManager();
	
	loginValidate = userManager.Login(username, password);
	
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UoM Helpdesk</title>
</head>
<body>
	<% if(loginValidate){ %>
	
		successful
	
	<%
		}//end of if(loginValidate)
		else {
	%>
		fail
	<% }//end of else %>
</body>
</html>