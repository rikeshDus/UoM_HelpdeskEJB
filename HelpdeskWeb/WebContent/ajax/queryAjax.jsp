<%@page import="java.util.ArrayList"%>
<%@page import="businessLogic.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%!String user_id, query_id, query, wildCard, fuzzy, proximity, firstRange,
			secondRange, boosting, excludeWords, booleanQuery, outMgs = "";
	String type;
	int tracking_id;

	TrackingLog trackingLog = null;

	ArrayList<Question> allQuestion = new ArrayList<Question>();
	ArrayList<Answer> allAnswer = new ArrayList<Answer>();

	AnswerManager answerManager = new AnswerManager();
	QueryManager queryManager = new QueryManager();
	TrackingManager trackingManager = new TrackingManager();%>


<%
	type = request.getParameter("type");
	query = request.getParameter("query");
	user_id = request.getParameter("user");

	if (type.equals("normal")) {
		//clear result
		outMgs = "";

		allQuestion = queryManager.getSolution(query);

		if (allQuestion.size() == 0) {
			outMgs = " no result found";
		}//end if(allQuestion.size() == 0){
		else {
			for (int i = 0; i < allQuestion.size(); i++) {

				outMgs += "<br><br><b>"
						+ allQuestion.get(i).getQuestion() + "</b>";

				allAnswer = answerManager
						.findAnswerByQuestion(allQuestion.get(i));

				for (int j = 0; j < allAnswer.size(); j++) {
					outMgs += "<br>" + allAnswer.get(j).getAnswer();
				}//end for(int j=0;j<5;j++){

				if (i == 4)
					break;

			}//end for(int i=0;i<5;i++){

		}//end else

		out.print(outMgs);

	}//end if(type.equals("normal")) 
	else if (type.equals("foward")) {
		//clear variable
		outMgs = "";

	 	//get tracking Log
		trackingLog = queryManager.forwardQuery(user_id, query, type);
		if(trackingLog == null){
			outMgs = "fail";
		}
		else{
			outMgs = trackingLog.getDate_send() + " , "
					 + trackingLog.getTracking_id() + " , "
					+ trackingLog.getStatus() + " , "
					+ trackingLog.getSender_id() + " , "
					+ trackingLog.getQuery_id();  
		}
			
		out.print(outMgs);

	}//end else if(type.equals("foward")){ 
	else if (type.equals("advance")) {
		//clear result
		outMgs = "";

		wildCard = request.getParameter("txt_wildCard");
		fuzzy = request.getParameter("txt_fuzzy");
		proximity = request.getParameter("txt_proximity");
		firstRange = request.getParameter("txt_firstRange");
		secondRange = request.getParameter("txt_secondRange");
		boosting = request.getParameter("txt_boosting");
		excludeWords = request.getParameter("txt_excludeWords");
		booleanQuery = request.getParameter("txt_boolean");

		query = queryManager.formatQuery(wildCard, fuzzy, proximity,
				firstRange, secondRange, boosting, excludeWords,
				booleanQuery);
		if(query == null ){
			outMgs = " no result found null query";
		}
		else
		{
		
			allQuestion = queryManager.getSolution(query);
	
			if (allQuestion.size() == 0) {
				outMgs = " no result found ";
			}//end if(allQuestion.size() == 0){
			else {
				for (int i = 0; i < allQuestion.size(); i++) {
	
					outMgs += "<br><br><b>"
							+ allQuestion.get(i).getQuestion() + "</b>";
	
					allAnswer = answerManager
							.findAnswerByQuestion(allQuestion.get(i));
	
					for (int j = 0; j < allAnswer.size(); j++) {
						outMgs += "<br>" + allAnswer.get(j).getAnswer();
					}//end for(int j=0;j<5;j++){
	
					if (i == 4)
						break;
	
				}//end for(int i=0;i<5;i++){
	
			}//end else
				
		}//end else
		out.print(outMgs);

	}//end else if(type.equals("advance")){
%>