<%@page import="businessLogic.SendMailSSL"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%!
	String message,name,email,content,respons;
	SendMailSSL mailManager = new SendMailSSL();
%>
<%
	//clear variable
	message = "";
	name = "";
	email = "";
	content = "";
	respons = "default";

	//capture  value
	message = request.getParameter("message");
	name = request.getParameter("name");
	email = request.getParameter("email");
	
	//format message
	content = "\nFrom : "+name+
			  "\nEmail: "+email+
			  "\nQuery: "+
			  message
			  ;
	
	//send
	respons = mailManager.sendMail("fpyengineering@gmail.com", "testingfpy", "fpyengineering@gmail.com", "Query", content);
	
	out.print(respons);
%>