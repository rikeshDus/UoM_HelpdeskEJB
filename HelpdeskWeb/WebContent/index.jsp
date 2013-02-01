<%@page import="businessLogic.UserManager, businessLogic.User" %>
<%!
	String username,password;
	boolean loginValidate = false,formSubmit;
	UserManager userManager;
%>
<%
	formSubmit = Boolean.parseBoolean( request.getParameter("formSubmit") );
	if(formSubmit)
	{
		username = request.getParameter("txt_username");
		password = request.getParameter("txt_password");
		
		userManager = new UserManager();
		
		loginValidate = userManager.Login(username, password);
		
		if(loginValidate)
		{
			//set session time
			session.setMaxInactiveInterval(3600);
			
			session.setAttribute("currentUser", userManager.getUser());
			response.sendRedirect("homepage.jsp");
		}//end if(loginValidate)
	}//end if(formSubmit)
	
	
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<link href="style.css" rel="stylesheet" type="text/css" />
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
<li><a href=""class='selected'  title="Home">Home</a></li>
<li><a href="" title="Click here to ">Event </a></li>
<li><a href=""  title="Click here">Query</a></li>
<li><a href="" title="Click here to ">Research</a></li><li></td>
</ul>    
 <!-- /.nav -->
	<div id="search" style=position:absolute;left:800px;top:250px>
	
	<form action="">
  <font color="white"><b>Search:</b></font> <input type="text" name="txtsearch">
  <input type="submit" value="Search">
</form>
	
	</div>	  
	<div id="welcomeNote"></div>
	
	<div id="Features"></div>
         
 </div>

 
</div>

 <div id="content">
  	<form action="index.jsp" method="post">
	    <table>
	    	<tr>
	    		<td>Username :</td>
	    		<td>
	    			<input type="text" name="txt_username" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>Password :</td>
	    		<td><input type="password" name="txt_password" /></td>
	    	</tr>
	    	<%if(formSubmit && !loginValidate){%>
	    		<tr>
	    			<td colspan="2">Username and Password is not valid</td>
	    		</tr>
	    	<%}%>
	    	<tr>
	    		<td colspan="2"><input type="submit" value="Sign In"/></td>
	    	</tr>
	    </table>
	    <input type="hidden" name="formSubmit" value="true"/>
	 </form>
	 </div>



	<div id="footer">

	    Copyright © 2013 <a href="#">University Of Mauritius</a> | 
        Designed by <a href="" target="_parent">Zuhayr & Rikesh</a>  
       
	</div> <!-- end of footer -->
  </body>
</html> 
