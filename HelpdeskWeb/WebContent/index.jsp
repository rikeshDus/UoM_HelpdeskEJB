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
    	<title>UoM Helpdesk</title>
  </head>
  <body>
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
  </body>
</html> 
