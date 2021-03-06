<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login - events.co</title>
<%@ include file="lib.jspf"%>
</head>
<body style="background-color:#f8fafc;">
	<nav class="navbar navbar-expand-lg navbar-dark" style="background: linear-gradient(45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);">
		<a class="navbar-brand" href=".">events.co</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link text-white" href="register">Register</a></li>
			</ul>
		</div>
	</nav>
	<c:if test="${success}">
		<div style="right: 0;" class="position-absolute float-right w-25 m-3 alert alert-success alert-dismissible fade show" role="alert">
			Register successful! Please log in.
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    		<span aria-hidden="true">&times;</span>
	 		</button>
		</div>
	</c:if>
	<div class="p-5 w-75 mx-auto">
		<h1 class="display-4 pb-2">Log into your account</h1>
		<hr class="display-4 pb-4">
		<div class="px-5">
			<form action="login" method="post">
				<div class="form-group">
					<label for="email">Email address</label>
					<input class="form-control" type="email" name="email" required/>
				</div>
				<div class="form-group">
					<label for="email">Password</label>
					<input class="form-control" type="password" name="password" required/>
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