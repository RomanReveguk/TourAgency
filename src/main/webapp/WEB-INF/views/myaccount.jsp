<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>My account</title>
</head>
<body>
<div align="right">
		<form action="logout" method="get">
			<input type="submit" value="Logout"/>
		</form>
 	</div>
 	<br>
 	<div align="right">
		<form action="AccountSetting" method="get">
			<input type="submit" value="Setting"/>
		</form>
 	</div>
my account
<p>You were successfully signed up with email - ${loggedUser.loggedUserEmail}.</p>
<p>
		Welcome
		<%= request.getAttribute("loggedUserName") %>!
	</p>
</body>
</html>