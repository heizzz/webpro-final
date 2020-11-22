<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login - events.co</title>
<%@ include file="lib.jspf"%>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark"
		style="background-color: #2980b9">
		<a class="navbar-brand" href=".">events.co</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="register">Register</a></li>
			</ul>
		</div>
	</nav>
	<div class="p-5 w-75 mx-auto">
		<h1 class="display-4 pb-2">Log into your account</h1>
		<hr class="display-4 pb-4">
		<div class="px-5">
			<form action="login" method="post">
				<div class="form-group">
					<label for="email">Email address</label>
					<input class="form-control" type="email" name="email" />
				</div>
				<div class="form-group">
					<label for="email">Password</label>
					<input class="form-control" type="password" name="password" />
				</div>
				<div class="text-right">
					<span>Don't have an account? <a href="register">Register</a>.</span>
					<input class="btn btn-primary px-5 ml-5" type="submit" value="Login" />
				</div>
			</form>
		</div>
	</div>
	<%@ include file="libjs.jspf"%>
</body>
</html>