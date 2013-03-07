<%@page import="businessLogic.*" %>
<%! User user; 
	Staff staff; 
	boolean isStaff;
	StaffManager staffManager = new StaffManager();
%>
<%
	//clear varialble
	staff = new Staff();
	isStaff = false;

	user = (User)(session.getAttribute("currentUser"));
	session.setAttribute("currentUser", user);
	
	staff = staffManager.findStaffByUserId(user.getUser_id());
	if(staff != null){
		isStaff = true;
	}//end if(staff == null){
	
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
  <title>UoM Help Desk</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="css/style.css" />
  <link rel="stylesheet" href="css/table.css" type="text/css"/>
  <!-- modernizr enables HTML5 elements and feature detects -->
  <script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
  <script type='text/javascript' src='js/jquery.js'></script>
  <script type="text/javascript" src="javascript/core.js"></script>
</head>

<body>
  <div id="main">
  <div id="loading" align="right"><b>Loading...</b></div>
  <div align="right"><a href="index.jsp">Logout</a> </div>
    <header>
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="index.html"><span class="logo_colour"><img src="images/_logo.gif" width="" height=""/></span></a></h1>
          
        </div>
      </div>
      <nav >
        <div id="menu_container">
          <ul class="sf-menu" id="nav">
            <li><a href="homepage.jsp">Home</a></li>
            <li><a href="#" onclick="$('#div_content').load('event.jsp');">Event</a>
				<ul>
					<li><a href="#" onclick="$('#div_content').load('event.jsp');" >Create Event</a></li>
					<li><a href="#" onclick="$('#div_content').load('event.jsp', function() { loadEvent('loadEvent'); $('#sidebar_help').html('<a href=#>help</a>') });" >Update Event</a></li>
					<li><a href="#" onclick="$('#div_content').load('event.jsp', function() { loadEvent('loadEvent'); $('#sidebar_help').html('<a href=#>help</a>') });" >Delete Event</a></li>
				</ul>
			</li>
            <li><a href="#" onclick="$('#div_content').load('query.jsp');" >Query</a>
            	<%if(isStaff){ %>
            		<ul>
            			<li><a href="#" onclick="$('#div_content').load('query.jsp');" >Make Queries</a>
            			<li><a href="#" onclick="$('#div_content').load('queryResponce.jsp', function() {$('#viewTracking').click();$('#viewTracking').hide();});">Reply Queries</a></li>
            		</ul>            	
            	<%}//end of if(isStaff){%>
            </li>
            <li><a href="#" onclick="$('#div_content').load('advanceSearch.jsp');" >Advance Search</a></li>
            <li><a href="#" onclick="$('#div_content').load('contactUs.jsp');" >Contact Us</a></li>
          </ul>
		  
        </div>
      </nav>
    </header>
	<div>
		<div style="float:right">
			<div style="background-color: rgba(37, 29, 29, 0.5);width: 230px;text-align: center;padding-top: 8px;border-radius: 40px;alignment-baseline: central;height: 30px;position:relative;top:-10px;"> 
				<input type=search results=5 name=s id="search_input">
				<input type="button" value="Search"  onclick="getSolution('search_input','<%= user.getUser_id()%>')"/>		
			</div>
		</div>
    <div id="site_content">
    <div id="sidebar_container">
        <div class="sidebar" id="sidebar_help">
          <h4>Quick Info</h4> 
       
          <p>Welcome page allow u to edit you profile</p>
    
        </div>
   <%--     <div class="sidebar">
          <h3>Services Offeres</h3>
          <ul>
            <li><a href="#">24 Hour services</a></li>
            <li><a href="#">Faq</a></li>
            <li><a href="#">Personalise Query</a></li>
            <li><a href="#">Request Fowarding</a></li>
            <li><a href="#">Tracking Request</a></li>
          </ul>
        </div> --%>
      </div>
      <div class="content" id="div_content">
      			 welcome to uom helpdesk
				userid : <%=user.getUser_id() %>
			
				<br>
				<br>
				
      
      </div>
    </div>
    <footer>
		<p>  	
			Copyright © 2013 <a href="#">University Of Mauritius</a> | 
			Designed by <a href="" target="_parent">Zuhayr & Rikesh</a>  
		</p>
    </footer>
  </div>
  <p>&nbsp;</p>
  <!-- javascript at the bottom for fast page loading -->
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript" src="js/jquery.easing-sooper.js"></script>
  <script type="text/javascript" src="js/jquery.sooperfish.js"></script>
  <script type="text/javascript">
    $(document).ready(function() {
      $('ul.sf-menu').sooperfish();
    });
  </script>
</body>
</html>