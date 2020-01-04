<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
 <title>Страница авторизации</title>
</head>
<body>
	<div align= "center">
	<h1>Страница авторизации</h1>
	</div>
	
	<div align="center" style="padding-top:20px">
	
	<form action="login" method="post">
		<p>
		<b>Логин:</b><br>
		<input type="text" placeholder="Your login" name="login"/>
		 </p>
		<p> <b>Пароль:</b><br>
		<input type="password" placeholder="Your password" name="password"/>
		 </p>
		 
		 <p>
		 <input type="submit" value="Send"/>
		 <input type="reset" value="Clear Form"/>
		 </p>
		 
	</form>
	</div>
	
</body>
</html>