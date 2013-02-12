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
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="javascript/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="javascript/core.js">
<%-- $(document).ready(function() {
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
			alert(data);					
			$("#queryResult").html(data);
			
		});

	}//end of function forwardQuery(){
	
	function advanceQuery(){
		$("#advanceForm").show(500);
	}//end function advanceQuery()
	
	function getSolution(divName){
		var div = "#"+divName;
		document.getElementById("queryResult").innerHTML = "searching ..";
			$.post("ajax/queryAjax.jsp",
				{
					'query':$(div).val(),
					'user':<%= user.getUser_id()%> ,
					'type':'normal'
				},
				function(data,status){
					if(div.indexOf("search") == -1){
						$("#queryResult").html(data);					
					}else{
						
						$("#innercontent").hide();	
						$("#content").html($("#content").html()+"<div id=\"newcontent\" style=\"position: absolute;top:320px;\"><a onclick=\"searchShow();\">close</a>"+data+"</div>");	
					}
					
					
				});
	}
	
	function searchShow(){
		$('#newcontent').hide();	
		$('#innercontent').show();	
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
	} --%>
</script>

</head>
<body>

	<div id="site_title_bar">
    
    	<div id="site_title">
            <h1><a href="" target="_parent">
               <!-- <img src="images/" alt="" />-->

					<div id="DivBannerRotatorFX"></div>
	
                
            </a></h1>
        </div>
		
		<script type="text/javascript" src="swfobject.js"></script>
	<script type="text/javascript">
		var flashvars = {};
		var params = {};
		params.base = "";
		params.scale = "noscale";
		params.salign = "tl";
		params.wmode = "transparent";
		params.allowFullScreen = "true";
		params.allowScriptAccess = "always";
		swfobject.embedSWF("BannerRotatorFX.swf", "DivBannerRotatorFX", "800", "150", "9.0.0", false, flashvars, params);
	</script>
      
	</div> <!-- end of site_title_bar -->

<div id ="header" >
<ul class="navigation">
<!--nav-->

<div style=position:absolute;left:340px>
<li><a href="homepage.jsp"  title="Home">Home</a></li>
<li><a href="event.jsp" title="Click here to ">Event </a></li>
<li><a href="query.jsp" class='selected' title="Click here">Query</a></li>
<li><a href="" title="Click here to ">Research</a></li><li></td>
</ul>      
 <!-- /.nav -->
	<div id="search" style=position:absolute;left:800px;top:250px>
	
	
  <font color="white"><b>Search:</b></font> <input type="text" name="txtsearch" id="txtsearch">
  <input type="submit" value="Search" onClick="getSolution('txtsearch','<%= user.getUser_id()%>');">

	
	</div>	  
	<div id="welcomeNote"></div>
	
	<div id="Features"></div>
         
 </div>

 
</div>

 <div id="content">
	 <div id = "innercontent">
	 <div id="loading" align="right"><b>Loading...</b></div>
	 
	<input type="button" value="Forward Query" onclick="forwardQuery('<%= user.getUser_id()%>');"/>
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
		
		<input type="button" value="Advance Search" onClick="getAdvanceSolution('<%= user.getUser_id()%>');"/>
	</div>
	<br>
<!-- 	<input type="button" value="Submit" onClick="getSolution('query','<%-- user.getUser_id() --%>');"/> -->
<input type="button" value="Submit" onClick="getQuestion('questions','<%= user.getUser_id() %>');"/>

	<br>
	<br>
	<div id="queryResult"></div>
	<div id="tracking"></div>
	
	
	
	</div>

</div>

	<div id="footer">

	    Copyright © 2013 <a href="#">University Of Mauritius</a> | 
        Designed by <a href="" target="_parent">Zuhayr & Rikesh</a>  
       
	</div> <!-- end of footer -->
</body>
</html>