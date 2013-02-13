<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="businessLogic.* , jess.*;" %>
<%!
//varaibles
String question,type,outMgs,user_id;
ArrayList<KnowledgeBaseMapping> allKnowledgeBaseMappings = new ArrayList<KnowledgeBaseMapping>();

KnowledgeBaseMappingManager knowledgeBaseMappingManager = new KnowledgeBaseMappingManager();

%>
<%
//clearing variable
	question = "";
	type ="";
	outMgs="";
	user_id="";
%>
<%
//implementation
	question = request.getParameter("question");
	type = request.getParameter("type");
	user_id = request.getParameter("user");
	
	if(type.equals("questions")){
		
		allKnowledgeBaseMappings = knowledgeBaseMappingManager.findQuestion(question);
		if(allKnowledgeBaseMappings.size()>0){
			for(int i=0;i<allKnowledgeBaseMappings.size();i++){
				outMgs +="<a onclick=\" alert('"+allKnowledgeBaseMappings.get(i).getQueryFormat()+"');getQuestion('"+allKnowledgeBaseMappings.get(i).getQueryFormat()+"','" +user_id+"')\">"+ allKnowledgeBaseMappings.get(i).getQuestion()+"</a></br>";
				
				
			}//end of for(int i=0;i<allKnowledgeBaseMappings.size();i++){
		}else{
			outMgs =" no result";
		}
		
		
		
		out.print(outMgs);
	}//end if(type.equals("")){
	else{
		outMgs = " no result";
		
		
		Rete engine = new KnowledgeDatabaseTemplate().getDataBaseTemplates();
		
		try {
			engine =  new KnowledgeDatabaseTemplate().populateDataBaseTemplates(engine);
			//engine.eval("(printout t  \" working\"  crlf)");
						
			engine = new KnowledgeDatabaseTemplate().populateRule(engine);
			engine = new KnowledgeDatabaseTemplate().populateQuery(engine);
			/* QueryResult result =  engine.runQueryStar("search-by-name", new ValueVector().add(new Value("P 0001", RU.STRING)));	        
	         while (result.next()) {
	        	 System.out.println(result.getString("user_id") + " , " + result.getString("name")+" can exit with degree ");
	        } */
			engine.run();
	     //   user_id = user_id.replaceAll(" ", "");
	        QueryResult result =  engine.runQueryStar(type, new ValueVector().add(new Value(user_id, RU.STRING)));	        
	        while (result.next()) {
	        	
	        	outMgs = result.getString("user_id") + " , " + result.getString("name")+" can exit with diploma ";
	        }
	        
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		out.print(outMgs);
	}//end else if(type.equals("knowledge")){

%>