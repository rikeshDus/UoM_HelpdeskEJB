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
<%--
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
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		 <script type="text/javascript" src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>
<style type="text/css">
* { font-family: Verdana; font-size: 96%; }
label { width: 10em; float: left; }
label.error { float: none; color: red; padding-left: .5em; vertical-align: top; }
p { clear: both; }
.submit { margin-left: 12em; }
em { font-weight: bold; padding-right: 1em; vertical-align: top; }
</style>
  <script>
  $(document).ready(function(){
	  $('#txt_username').validate();
  });
  </script>
  
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
	    			<input type="text" name="txt_username" class="required"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>Password :</td>
	    		<td><input type="password" name="txt_password" id="txt_password" /></td>
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
<html>
--%>
<head>
  <title>UoM Help Desk</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="css/style.css" />
  <link rel="stylesheet" href="css/table.css" type="text/css"/>
  <!-- modernizr enables HTML5 elements and feature detects -->
  <script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
</head>

<body>
  <div id="main">
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
            <li><a href="index.jsp">Home</a></li>
            <li><a href="event.jsp">Event</a>
				<ul>
					<li><a href="examples.html">Create Event</a></li>
					<li><a href="examples.html">Update Event</a></li>
					<li><a href="examples.html">Delete Event</a></li>
				</ul>
			</li>
            <li><a href="page.html">Query</a></li>
            <li><a href="another_page.html">Advance Search</a></li>
            <li><a href="contact.php">Contact Us</a></li>
          </ul>
		  
        </div>
      </nav>
    </header>
	<div><div style="float:right"><div style="position:relative;top:-10px;">Search : <input type="text" /></div></div>
    <div id="site_content">
      <div id="sidebar_container">
        <div class="sidebar">
         <%--  <h3>Latest News</h3>
          <h4>New Website Launched</h4> 
          <h5>January 1st, 2012</h5>--%>
           <h4>New Website Launched</h4>
          <p>2013 sees the redesign of our website. We assure to find solutions to your queries.</p>
    
        </div>
        <div class="sidebar">
          <h3>Services Offeres</h3>
          <ul>
            <li><a href="#">24 Hour services</a></li>
            <li><a href="#">Faq</a></li>
            <li><a href="#">Personalise Query</a></li>
            <li><a href="#">Request Fowarding</a></li>
            <li><a href="#">Tracking Request</a></li>
          </ul>
        </div>
      </div>
      <div class="content">
 
      <section class="loginform cf">
		<form naction="index.jsp" method="post">
			<ul>
				<li>
					<label for="usermail">Username :</label>
					<input type="text" name="txt_username" required/>
				</li>
				<li>
					<label for="password">Password</label>
					<input type="password" name="txt_password" id="txt_password" placeholder="password" required/>
				</li>
				<li>
					<%if(formSubmit && !loginValidate){%>
	    			Username and Password is not valid
	    			<%}%>
				</li>
				<li>
				
					<input type="submit" value="Login">
				</li>
			</ul>
			 <input type="hidden" name="formSubmit" value="true"/>
		</form>
	</section>
      
      
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
