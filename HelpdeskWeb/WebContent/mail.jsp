<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@page import="businessLogic.*" %>
<%!
	String outMgs;
%>
<%
	//clear
	outMgs = "fail";
	
	SendMailSSL sendMail = new SendMailSSL();
	
	outMgs = sendMail.sendMail("fpyengineering@gmail.com", "testingfpy", "hirikesh.dussoye@gmail.com", "integrated testing", "test has been succesfull");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Operation : 

	<%= outMgs %>

</body>
</html>