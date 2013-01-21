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
<title>UoM Helpdesk</title>
<script type="text/javascript" src="javascript/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	  // Handler for .ready() called.
	$('#loading')
	.hide(10)  // hide it initially
	.ajaxStart(function() {
	    $(this).show(100);
	})
	.ajaxStop(function() {
	    $(this).hide(100);
	})
	;
	  
	  
	$("#advanceForm").hide();
	});
	
	
	function displayTechnicalForm(){
		//display material
	}
	
	function forwardQuery(){
		$.post("ajax/queryAjax.jsp",
		{
			'query':$("#query").val(),
			'user':<%= user.getUser_id()%> ,
			'type':'foward'
		},
		function(data,status){
								
			$("#queryResult").html(data);
			
		});

	}//end of function forwardQuery(){
	
	function advanceQuery(){
		$("#advanceForm").show(500);
	}//end function advanceQuery()
	
	function getSolution(){
		document.getElementById("queryResult").innerHTML = "searching ..";
			$.post("ajax/queryAjax.jsp",
				{
					'query':$("#query").val(),
					'user':<%= user.getUser_id()%> ,
					'type':'normal'
				},
				function(data,status){
										
					$("#queryResult").html(data);
					
				});
	}
	function getAdvanceSolution(){
		document.getElementById("queryResult").innerHTML = "searching ..";
			$.post("ajax/queryAjax.jsp",
				{
					'query':$("#query").val(),
					'user':<%= user.getUser_id()%> ,
					
					'txt_wildCard':$("#txt_wildCard").val(),
					'txt_fuzzy':$("#txt_fuzzy").val(),
					'txt_proximity':$("#txt_proximity").val(),
					'txt_firstRange':$("#txt_firstRange").val(),
					'txt_secondRange':$("#txt_secondRange").val(),
					'txt_boosting':$("#txt_boosting").val(),
					'txt_excludeWords':$("#txt_excludeWords").val(),
					'txt_boolean':$("#txt_boolean").val(),
					
					'type':'advance'
				},
				function(data,status){
										
					$("#queryResult").html(data);
					
				});
	}
</script>

</head>
<body>
	<div id="loading">Loading...</div>
	<input type="button" value="Forward Query" onclick="forwardQuery();"/>
	<input type="button" value="Advance Query" onclick="advanceQuery();"/>
	<%
	if(isStaff){
	%>
	<%= combo %>
	<%
	}
	%>
	<br>
	Query
	<textarea id="query"></textarea>
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
		
		<input type="button" value="Advance Search" onClick="getAdvanceSolution();"/>
	</div>
	<br>
	<input type="button" value="Submit" onClick="getSolution();"/>
	<br>
	<br>
	<div id="queryResult"></div>
	<div id="tracking"></div>
	
</body>
</html>