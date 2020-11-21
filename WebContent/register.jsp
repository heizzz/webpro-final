<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register - events.co</title>
</head>
<body>
	<h1>Register Account</h1>
	<form action="register" method="post">
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
				<td>Full Name</td>
				<td>
					<input type="text" name="name"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Register"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>