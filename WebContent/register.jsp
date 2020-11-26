<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register - events.co</title>
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
				<li class="nav-item"><a class="nav-link text-white" href="login">Login</a></li>
			</ul>
		</div>
	</nav>
	<div class="p-5 w-75 mx-auto">
		<h1 class="display-4 pb-2">Register a new account</h1>
		<hr class="display-4 pb-4">
		<div class="px-5">
			<form class="needs-validation" action="register" method="post">
				<div class="form-group">
					<label for="email">Email address</label>
					<input class="form-control" type="email" name="email" required/>
					<div class="invalid-feedback">
          				Please set an email address.
        			</div>
				</div>
				<div class="form-group">
					<label for="email">Password</label>
					<input class="form-control" type="password" name="password" required/>
				</div>
				<div class="form-group">
					<label for="email">Full Name</label>
					<input class="form-control" type="text" name="name" required/>
	    			<small class="form-text text-muted">Your name will be publicly visible.</small>
				</div>
				<div class="text-right">
					<span>Already have an account? <a href="login">Log in</a>.</span>
					<input class="btn btn-primary px-5 ml-5" type="submit" value="Register" />
				</div>
			</form>
		</div>
	</div>
	<%@ include file="libjs.jspf"%>
</body>
</html>