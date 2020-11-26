<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>events.co</title>
<%@ include file="lib.jspf"%>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark" style="background: linear-gradient(45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);">
		<a class="navbar-brand" href="#">events.co</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<% if (session.getAttribute("name") == null) { %>
				<li class="nav-item"><a class="nav-link" href="login">Login</a></li>
				<li class="nav-item"><a class="nav-link" href="register">Register</a></li>
				<% } else { %>
				<li class="nav-item"><a class="nav-link" href="dashboard">Dashboard</a></li>
				<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
				<% } %>
			</ul>
		</div>
	</nav>
	<div class="hover py-5">
		<div class="jumbotron w-75 mx-auto">
			<div class="w-50 mx-auto">
				<h1 class="display-2">events.co</h1>
				<p class="lead">Your one stop shop for online events!</p>
				<hr class="my-4">
				<p class="text-right">
					<% if (session.getAttribute("name") == null) { %>
						You are not logged in.
					<% } else { %>
						Welcome, <c:out value="${name}"/>!
					<% } %>
				</p>
			</div>
		</div>
	</div>
	<%@ include file="libjs.jspf"%>
	<script>
		$(".hover").on('mousemove', function(e) {
			let x = e.clientX - $(this).offset().left + $(window).scrollLeft();
			let y = e.clientY - $(this).offset().top + $(window).scrollTop();
			x = x * 20 / $(this).width() - 10;
			y = y * 10 / $(this).height() - 5;
			$(".jumbotron").css("transform", "rotateY(" + x + "deg) rotateX(" + (-y) + "deg)");
		});
		$(".hover").on('mouseenter', function() {
			$(".jumbotron").css("transition", "none");
		});
		$(".hover").on('mouseleave', function() {
			$(".jumbotron").css("transition", "transform 0.2s linear");
			$(".jumbotron").css("transform", "rotateY(0deg) rotateX(0deg)");
		});
	</script>
</body>
</html>