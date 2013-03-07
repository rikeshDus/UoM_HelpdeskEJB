<%@page import="java.util.*, java.sql.Date"%>
<%@page import="com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page import="businessLogic.*" %>

<%! 
	String user_id,option,outMgs;
	
	int status;

	Date trackingDate;
	TrackingLogManager trackingLogManager = new TrackingLogManager();
	QueryManager queryManager = new QueryManager();
	UserManager userManager = new UserManager();
	
	
	Query query = new Query();
	User sender =new User();
	
	ArrayList<TrackingLog> allTrackingLog =  new ArrayList<TrackingLog>();
%>
<%
	//clear variable
	user_id =  "";
	option = "";
	outMgs ="no tracking  found";
	status = 0;
	
	//get values
	user_id = request.getParameter("userId");
	option = request.getParameter("option");
	
	
	
	
	if(option.equals("getTrackings")){
	 	//get all tracking log
		allTrackingLog = trackingLogManager.getAllTrackingLogManager();
		
	 	//format out put table
	 	outMgs = "<table border=1 width=\"100%\">"+
	 			"<tr ><th colspan = 5 ><center>Unsolve Queries</center></th></tr>"+
	 			"<tr><th>ID</th><th>Queries</th><th>Date</th><th>Reply</th><th>Foward</th></tr>";
	 			
	 	
	 	
		for(int i=0 ; i< allTrackingLog.size(); i++){
		 	if(allTrackingLog.get(i).getInitial_receiver_id().equals(user_id)){
				
		 		
			 	//get date
				trackingDate = allTrackingLog.get(i).getDate_send();
										
				//get status
				status = allTrackingLog.get(i).getStatus();
				
				//get sender
				sender = userManager.findUser(allTrackingLog.get(i).getSender_id());
				
					//question
				query = queryManager.fingQuery(allTrackingLog.get(i).getQuery_id());
				// replyShow('<%=allTrackingLog.get(i).getTracking_id() >','<%=allTrackingLog.get(i).getQuery_id()>','<%=query.getDescrition()>','<%=user_id>')
				if(status<100){
					outMgs += "<tr><td>"+sender.getUser_id() + "</td><td>"+ query.getDescrition()+status+"</td><td>"+trackingDate+
							"</td><td><a onclick=\" replyShow('"+allTrackingLog.get(i).getTracking_id() +"','"+allTrackingLog.get(i).getQuery_id()+"','"+query.getDescrition()+"','"+user_id+"');  \">reply</a></td>"+
							"<td><a onclick=\"alert('features under Construction');\">Foward</a></td></tr>" ;
				//	outMgs += "<tr><td colspan =3><textarea style=\"width:100%\"></textarea><br><div style=\"float:right;\"><input type='button' value='Reply' /></div></td></tr></table></td></tr>";
					//outMgs += sender.getUser_id() + "  "+ query.getDescrition()+" "+trackingDate ;
				}//end of if(status<100){ 
				
			}//end of if(allTrackingLog.get(i).getReciever().equals(user_id)){ 
			
		}//end for(int i=0 ; i< allTrackingLog.size(); i++){
		
		outMgs += "</table>";
		
		if(outMgs.equals(""))	
			outMgs = "No query to solve";
			
		out.print(outMgs);
	}//end of if(option.equals("getTrackings")){
	else{
		
		out.print(outMgs);
	}//end of else{
	
%>