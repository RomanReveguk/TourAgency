<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Specific User Page</title>
</head>
<body>
<h1>Specific User Page</h1>
<p>Id: ${user.user_id} </p>
<p>Name: ${user.user_name} </p>
<p>LastName: ${user.user_surname} </p>
<p>mail: ${user.mail} </p>
<p>Birthday: ${user.user_date_of_birth} </p>
<p>RoleId: ${user.roleId} </p>

<% String s = "Some String"; %>
<%=s %>

<form action="UpdateUserServlet2" method="get">
<input type="hidden" value= "${user.user_id}" name="userId" />
	<input type= "submit" value="Update User">
</form>

</body>
</html>