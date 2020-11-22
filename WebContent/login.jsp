<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login - events.co</title>
</head>
<body>
	<h1>Login Account</h1>
	<form action="login" method="post">
		<table>
			<tr>
				<td>Email Address</td>
				<td>
					<input type="email" name="email"/>
				</td>
			</tr>
			<tr>
				<td>Password</td>
				<td>
					<input type="password" name="password"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Login"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>