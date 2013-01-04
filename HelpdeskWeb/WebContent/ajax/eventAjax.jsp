<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!
	String type,title,description,facultyEngineering,facultyAgriculture;
	String course[];
%>
<%
 	//capture ajax value from createEventform of event.jsp
 	type = request.getParameter("type");
	title = request.getParameter("title");
	description = request.getParameter("description");
	facultyEngineering = request.getParameter("facultyEngineering");
	facultyAgriculture = request.getParameter("facultyAgriculture");			
 	course = request.getParameterValues("course[]");
 	
 	//generate schedule
 					
 					
 	//out.print(test);
%>