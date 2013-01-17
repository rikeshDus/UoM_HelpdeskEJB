<%@page import="java.util.ArrayList"%>
<%@page import="businessLogic.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!
	String user_id;
	String query_id,query, outMgs;
	String type;
	int tracking_id;
	
	ArrayList<Question> allQuestion = new ArrayList<Question>();
	ArrayList<Answer> allAnswer = new ArrayList<Answer>();
	
	AnswerManager answerManager = new AnswerManager();
	QueryManager queryManager = new QueryManager();
	TrackingManager trackingManager = new TrackingManager();
%>


<%
	type = request.getParameter("type");
	query = request.getParameter("query");
	user_id = request.getParameter("user");

	if(type.equals("normal")){
		
		allQuestion = queryManager.getSolution(query);
		
		for(int i=0;i<5;i++){
			outMgs += "<br><br><br>"+allQuestion.get(i).getQuestion();
			
			allAnswer = answerManager.findAnswerByQuestion(allQuestion.get(i));
			
			for(int j=0;j<allAnswer.size();j++){
				outMgs += "<br>" + allAnswer.get(j);
			}//end for(int j=0;j<5;j++){
			
			
		}//end for(int i=0;i<5;i++){
		
		out.print(outMgs);
	}//end if(type.equals("normal"))
%>