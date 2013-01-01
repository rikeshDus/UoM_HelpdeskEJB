<%@ page import="businessLogic.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="javascript/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
function SubmitcreateEventFormDiv(){
	$("#createEventFormDiv").hide(1000);
}
</script>
<title>UoM Helpdesk</title>
</head>
<body >
	<div id="createEventFormDiv">
		<form action="#">
			<table cellspacing="2" cellpadding="3">
				<tr>
					<td>Event Type</td>
					<td> 
						<select name="">
							<option value="sport">Sport</option>
							<option value="other">Other</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Title</td>
					<td><input type="text" name=""/> </td>
				</tr>
				<tr>
					<td>Description</td>
					<td><textarea name=""></textarea></td>
				</tr>
				<tr>
					<td colspan="2">Faculty Involve </td>
				</tr>
				<tr>
					<td colspan="2">
						Engineering <input type="checkbox" name=""/>
						Agriculture <input type="checkbox" name=""/>
						
					</td>
				</tr>
				<tr>
					<td>Course Involve</td>
					<td>
						<select>
							<option>List of Course</option>
						</select>
						<br>
						<button>Add Course</button>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						Course Added <br>
						<textarea rows="" cols="" readonly="readonly"></textarea>
					</td>
				</tr>
				<tr>
					<td>ID Involve</td>
					<td>
						<select>
							<option>List of Id</option>
						</select>
						<br>
						<button>Add Id</button>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						Id Added <br>
						<textarea rows="" cols="" ></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<button onclick="SubmitcreateEventFormDiv();">Submit</button>
</body>
</html>