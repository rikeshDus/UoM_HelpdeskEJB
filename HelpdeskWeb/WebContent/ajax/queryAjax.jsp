<%@page import="java.util.ArrayList"%>
<%@page import="businessLogic.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!
	String user_id;
	String query_id,query, outMgs="";
	String type;
	int tracking_id;
 	
	TrackingLog trackingLog = null;
	
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
		//clear result
		outMgs = "";
		
		allQuestion = queryManager.getSolution(query);
		
		if(allQuestion.size() == 0){
			outMgs =" no result found";
		}//end if(allQuestion.size() == 0){
		else{
			for(int i=0;i<allQuestion.size();i++){
				
				outMgs += "<br><br><b>"+allQuestion.get(i).getQuestion()+"</b>";
				
				allAnswer = answerManager.findAnswerByQuestion(allQuestion.get(i));
				
				for(int j=0;j<allAnswer.size();j++){
					outMgs += "<br>" + allAnswer.get(j).getAnswer();
				}//end for(int j=0;j<5;j++){
				
				if(i==4)
					break;
				
			}//end for(int i=0;i<5;i++){
		
		}//end else
		
		
		
		out.print(outMgs);
	}//end if(type.equals("normal")) 
	else if(type.equals("foward")){
		
		//get tracking Log
		trackingLog = queryManager.forwardQuery(user_id, query, type);
		
		
		
	}//end else if(type.equals("foward")){
		
%>