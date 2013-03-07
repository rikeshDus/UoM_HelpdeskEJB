<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="businessLogic.*" %>

<%!
	String outmgs,queryId,userId,trackingId,answer;
	int replyUpdateId,statusUpdateId;
	
	TrackingLog trackingLog = new TrackingLog();
	
	ReplyManager replyManager = new ReplyManager();
	TrackingLogManager trackingLogManager = new TrackingLogManager();
%>
<%
	//clear variable
	outmgs = "errror";
	
	//capture request
	queryId = request.getParameter("queryId");
	userId = request.getParameter("userId");
	trackingId = request.getParameter("trackingId");
	answer = request.getParameter("answer");
	
	//create Reply
	replyUpdateId = replyManager.createReply( Integer.parseInt(queryId), userId, Integer.parseInt(trackingId), answer);
	outmgs = "Fail Reply.";
	if(replyUpdateId > 0){
		
		//UPDATE STATUS
		trackingLog = trackingLogManager.findTrackingLog(Integer.parseInt(trackingId));
		trackingLog.setStatus(100);
		
		statusUpdateId = trackingLogManager.updateTrackingLog(trackingLog);
		if(statusUpdateId > 0)
			outmgs = "Succesful Reply";
		
	}else{
		outmgs = "Fail Reply. Please try to re perform the operation";
	}
	
	
	out.print(outmgs);
%>