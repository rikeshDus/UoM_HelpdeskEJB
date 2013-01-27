<%@page import="businessLogic.*" %>
<%! User user; %>
<%
	user = (User)(session.getAttribute("currentUser"));
	session.setAttribute("currentUser", user);
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
	<a href="event.jsp">Event</a>
	<br>
	<a href="myEvent.jsp">My Event</a>
	<br>
	<a href="query.jsp">Query</a>
	
</body>
</html>