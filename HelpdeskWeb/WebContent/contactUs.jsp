<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <script type="text/javascript" src="javascript/core.js"></script>
</head>
<body>
		<center><h2>Contact Us</h2>
        <p>Say hello, using this contact form.</p></center>
        <div id="mailConfirm"></div>
          <div class="form_settings">
            <p><span>Name</span><input class="contact" type="text" name="your_name" id="your_name" placeholder="Enter your name" onblur="validate(this);" required/></p>
            <p><span>Email Address</span><input class="contact" type="email" name="your_email" id="your_email" placeholder="Enter your email address" onblur="validate(this);" required/></p>
            
            <p><span>Message</span><textarea class="contact textarea" placeholder="Enter your message" rows="5" cols="50" name="your_message"  id="your_message" onblur="validate(this);" required></textarea></p>
            <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="button" name="contact_submitted" value="send" onclick="sendEmail();"/></p>
           </div>
      
</body>
</html>