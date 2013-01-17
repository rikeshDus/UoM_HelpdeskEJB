<%@page import="java.util.ArrayList"%>
<%@page import="businessLogic.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!
	String user_id;
	String query_id,query;
	String type;
	int tracking_id;
	ArrayList<Question> allQuestion = new ArrayList<Question>();
	
	
	QueryManager queryManager = new QueryManager();
	TrackingManager trackingManager = new TrackingManager();
%>


<%
	type = request.getParameter("type");
	query = request.getParameter("query");
	user_id = request.getParameter("user");

	if(type.equals("normal")){
		
		allQuestion = queryManager.getSolution(query);
		
	/* 	if(query_id!=0){
			
			//insert tracking
			tracking_id = trackingManager.createTracking(user_id, query_id);
			
			
			
		}//end if(query_id!=0){ */
		
	}//end if(type.equals("normal"))
%>