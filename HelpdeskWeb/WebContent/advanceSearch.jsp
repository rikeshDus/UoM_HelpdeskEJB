<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="businessLogic.*"%>

<%! 
	User user;
	Staff staff;
	
	boolean isStaff = false;

	String combo ="<select id=\"comboQueryType\" onchange=\"displayTechnicalFrom();\"><option>normal</option><option>technical</option></select>"; 

	StudentManager studentManager = new StudentManager();
	StaffManager staffManager = new StaffManager();
%>
<%
	//get current user
	user = (User)(session.getAttribute("currentUser"));

	/*
	*check if staff or student
	*if student give form for a normal query
	* if staff give form for technical qeury also
	*/
	staff = staffManager.findStaffByUserId(user.getUser_id());
	if(staff != null){
		isStaff = true;
	}//end if(staff == null){
	
		
	/*
	*send normal query by ajax and retrieve solutions
	*give "forward" option which can be use if user has not agree to current solution
	*/
	

	/*
	*when user is fowarding query, use ajax to submit value
	*load tracking
	*/

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="advanceForm"> 
		Wilddcard Searches 
		<input type="text" name="txt_wildCard" id="txt_wildCard"/>
		use ? or * search
		<br>
		Fuzzy Searches
		<input type="text" name="txt_fuzzy" id="txt_fuzzy"/> 
		add ~ follow by (0.1 t0 1) for similarity
		<br>
		Proximity Searches
		<input type="text" name="txt_proximity" id="txt_proximity"/>
		add ~ follow by a number
		<br>
		Range Searches
		<input type="text" name="txt_firstRange" id="txt_firstRange"/>
		to
		<input type="text" name="txt_secondRange" id="txt_secondRange"/>
		<br>
		Boosting Term
		<input type="text" name="txt_boosting" id="txt_boosting"/>
		add ^ follow by a number
		<br>
		Exclude Words
		<input type="text" name="txt_excludeWords" id="txt_excludeWords"/>
		add - follow by a number
		<br>
		Boolean Operators
		<input type="text" name="txt_boolean" id="txt_boolean"/>
		add OR between words
		
		</br></br>
		<input type="button" value="Advance Search" onClick="getAdvanceSolution('<%= user.getUser_id()%>');"/>
	</div>
	<br>
	<br>
	<br>
	<div id="queryResult"></div>
	<div id="tracking"></div>
</body>
</html>