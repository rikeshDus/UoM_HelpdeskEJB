<%@page import="java.util.*, java.sql.Date"%>
<%@page import="com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page import="businessLogic.*" %>

<%! 
	String user_id,option,outMgs;
	
	int status;

	Date trackingDate;
	
	TrackingManager trackingManager = new TrackingManager();
	TrackingLogManager trackingLogManager = new TrackingLogManager();
	QuestionManager questionManager = new QuestionManager();
	UserManager userManager = new UserManager();
	
	
	Question question = new Question();
	Tracking tracking = new Tracking();
	User sender =new User();
	
	ArrayList<TrackingLog> allTrackingLog =  new ArrayList<TrackingLog>();
%>
<%
	//clear variable
	user_id =  "";
	option = "";
	outMgs ="no tracking  ffound";
	status = 0;
	
	//get values
	user_id = request.getParameter("userId");
	option = request.getParameter("option");
	
	
	
	
	if(option.equals("getTrackings")){
		outMgs="";
		//get all tracking log
		allTrackingLog = trackingLogManager.getAllTrackingLogManager();
		
		for(int i=0 ; i< allTrackingLog.size(); i++){
			
			if(allTrackingLog.get(i).getReciever().equals(user_id)){
				
				
				//get date
				trackingDate = allTrackingLog.get(i).getDate();
				
				//get tracking
				tracking = trackingManager.findTracking(allTrackingLog.get(i).getTracking_id());
				
				//get status
				status = tracking.getStatus();
				
				//get sender
				sender = userManager.findUser(tracking.getUser_id());
				
				//question
				question = questionManager.findQuestion(allTrackingLog.get(i).getQuestion());
				
				if(status<100){
					outMgs += sender.getUser_id() + " - "+ question.getQuestion();
				}//end of if(status<100){
				
				
				
				
			}//end of if(allTrackingLog.get(i).getReciever().equals(user_id)){
			
		}//end for(int i=0 ; i< allTrackingLog.size(); i++){
		
		out.print(outMgs);
	}//end of if(option.equals("getTrackings")){
	else{
		
		out.print(outMgs);
	}//end of else{
	
%>