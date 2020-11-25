<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard - events.co</title>
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
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link nav-active" href="#">Dashboard</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-12">

            <div style="text-align:center; font-size:30px;">
                <div>Welcome to events.co!</div>
                <span style="white-space: pre-line"></span>
                <div>What event do you want to attend?</div>
            </div>

            <div>
                <div class="card-header text-center" style="margin-top:20px; font-size:20px;">Actions</div>
                <div class="card-body text-center d-flex flex-row justify-content-between w-75 mx-auto">
                    <a class="btn btn-success px-4" href="profile">My Profile</a>
                    <a class="btn btn-success px-4" href="tickets">My Tickets</a>
                    <a class="btn btn-success px-4" href="create">Create new event</a>
                </div>
            </div>
            
            <div class="card my-2">
                <div class="card-header" style="text-align:center;  font-size:20px;">All Events</div>
                <div class="card-body">
                    <table class="table table-bordered table-responsive-lg" style="text-align:center;">
                        <thead class="table-info">
                            <tr>
                                <th>Event Name</th>
                                <th>Event Place</th>
                                <th>Event Start</th>
                                <th>Event End</th>
                                <th>Price</th>
                                <th colspan=3>Action</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                       		<c:forEach var="event" items="${listEvent}">
				                <tr>
				                    <td><a href="detail?id=<c:out value="${event.event_id}" />"><c:out value="${event.name}" /></a></td>
				                    <td><c:out value="${event.place}" /></td>
				                    <td><fmt:formatDate value="${event.start_time}" type="both" dateStyle="long" timeStyle="medium"/></td>
				                    <td><fmt:formatDate value="${event.end_time}" type="both" dateStyle="long" timeStyle="medium"/></td>
				                    <td><fmt:formatNumber value="${event.price}" pattern="IDR #,###"/></td>
				                    <td>
				                        <a href="edit?id=<c:out value="${event.event_id}" />">Edit</a>
				                        <a class="pl-3" href="delete?id=<c:out value="${event.event_id}" />">Delete</a>                     
				                    </td>
				                </tr>
				            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="libjs.jspf"%>
</body>
</html>