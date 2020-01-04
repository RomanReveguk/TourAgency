<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Страница регистрации</title>
</head>
<body>
<h2>Страница регистрации</h2>
 <form action="registeruserpage" method ="post"  >
	<table>
		<tr>
			<td> Имя: </td>
			<td><input type= "text" name="firstName"></td>
		</tr>
		 <tr>
			<td> Фамилия: </td>
			<td><input type= "text" name="surName"></td>
		</tr>
		 <tr>
			<td> Дата рождения: </td>
			<td><input type= "text" name="dateBirth"></td>
		</tr>
		 <tr>
			<td> mail/login: </td>
			<td><input type= "text" name="mail"></td>
		</tr>
		 <tr>
			<td> Введите пароль: </td>
			<td><input type= "text" name="password"></td>
		</tr>
		<tr>
			<td> Повторите пароль: </td>
			<td><input type= "text" name="passwordCheck"></td>
		</tr>
			
		<tr>
			<td align="right" colspan="2"><input type= "submit" value="send"></td>
		</tr>
		</table>
		</form>
		
		
		<form action="login" method ="get">
		<table>
		<tr>
		
		<td align="left" colspan="2">
		<p>После успешной регистрации перейти в личный кабинет</p>
		<br> <input type="submit" value="Перейти " 
              onclick="window.location='WEB-INF/views/login.jsp';" />
              </td>
              </tr>
		</table>
		
		
		</form>
</body>
</html>