<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>

<form action="UpdateUserServlet2" method="post">
	<input type ="hidden" value="${user.user_id }" name= "userId">
	<table align="center">
	<h2>Обновить пользователя</h2>
		<tr>
			<td>Имя:</td>
			<td><input type ="text" placeholder="${user.user_name }" name="firstName"></td>
		</tr>
		<tr>
			<td>Фамилия:</td>
			<td><input type ="text" placeholder="${user.user_surname }" name="surName"></td>
		</tr>
		<tr>
			<td>Дата рождения:</td>
			<td><input type ="text" placeholder="${user.user_date_of_birth }" name="dateBirth"></td>
		</tr>
		<tr>
			<td>Mail/login</td>
			<td><input type ="text" value="${user.mail }" name="mail"></td>
		</tr>
		<tr>
			<td>Введите пароль</td>
			<td><input type ="text" value="${user.password }" name="password"></td>
		</tr>
		<tr>
			<td><input type ="submit" value="Update user"></td>
		</tr>
	</table>
</form>
		<form action="login" method ="get">
		<table>
		<tr>
		
		<td align="left" colspan="2">
		<p>После успешного обновления перейти в личный кабинет</p>
		<br> <input type="submit" value="Перейти " 
              onclick="window.location='WEB-INF/views/login.jsp';" />
              </td>
              </tr>
		</table>
		
		
		</form>
		
</body>
</html>