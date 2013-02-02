<%@page import="businessLogic.*" %>
<%! User user; %>
<%
	user = (User)(session.getAttribute("currentUser"));
	session.setAttribute("currentUser", user);
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	</div>
	
	 </div>



	<div id="footer">

	    Copyright © 2013 <a href="#">University Of Mauritius</a> | 
        Designed by <a href="" target="_parent">Zuhayr & Rikesh</a>  
       
	</div> <!-- end of footer -->
</body>
</html>