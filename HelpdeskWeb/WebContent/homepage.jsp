<%@page import="businessLogic.*" %>
<%! User user; %>
<%
	user = (User)(session.getAttribute("currentUser"));
	session.setAttribute("currentUser", user);
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
					<li><a href="#" onclick="$('#div_content').load('event.jsp', function() { loadEvent('loadEvent');});" >Update Event</a></li>
					<li><a href="#" onclick="$('#div_content').load('event.jsp', function() { loadEvent('loadEvent');});" >Delete Event</a></li>
				</ul>
			</li>
            <li><a href="#" onclick="alert('plz sign in first');" >Query</a></li>
            <li><a href="#" onclick="alert('site is under construction');" >Advance Search</a></li>
            <li><a href="#" onclick="alert('site is under construction');" >Contact Us</a></li>
          </ul>
		  
        </div>
      </nav>
    </header>
	<div><div style="float:right"><div style="position:relative;top:-10px;">Search : <input type="text" /></div></div>
    <div id="site_content">
    <div id="sidebar_container">
        <div class="sidebar">
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
        </div>--%>
      </div>
      <div class="content" id="div_content">
      			 welcome to uom helpdesk
				userid : <%=user.getUser_id() %>
			
				<br>
				<br>
				<a href="queryResponce.jsp">queryResponce</a>
      
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



<%-- 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="javascript/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="javascript/core.js"></script>
<title>UoM Helpdesk</title>
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
<li><a href="homepage.jsp"class='selected'  title="Home">Home</a></li>
<li><a href="event.jsp" title="Click here to ">Event </a></li>
<li><a href="query.jsp"  title="Click here">Query</a></li>
<li><a href="" title="Click here to ">Research</a></li><li></td>
</ul>    
 <!-- /.nav -->
	<div id="search" style=position:absolute;left:800px;top:250px>
	
  <font color="white"><b>Search:</b></font> <input type="text" name="txtsearch" id="txtsearch">
  <input type="submit" value="Search" onClick="getSolution('txtsearch','<%= user.getUser_id()%>');">
<div id="queryResult" style="color: #317FFC;"></div>
	</div>	  
	<div id="welcomeNote"></div>
	
	<div id="Features"></div>
         
 </div>

 
</div>

 <div id="content">
  <div id = "innercontent">
welcome to uom helpdesk
userid : <%=user.getUser_id() %>

	<br>
	<br>
	<a href="queryResponce.jsp">queryResponce</a>

	</div>
	
	 </div>



	<div id="footer">

	    Copyright © 2013 <a href="#">University Of Mauritius</a> | 
        Designed by <a href="" target="_parent">Zuhayr & Rikesh</a>  
       
	</div> <!-- end of footer -->
</body>
</html> --%>