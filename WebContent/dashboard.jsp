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
	<nav class="navbar navbar-expand-lg navbar-dark" style="background: linear-gradient(45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);">
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
	<c:if test="${deleteSuccess}">
		<div style="right: 0;" class="position-absolute float-right w-25 m-3 alert alert-success alert-dismissible fade show" role="alert">
			Successfully deleted event.
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    		<span aria-hidden="true">&times;</span>
	 		</button>
		</div>
	</c:if>
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
                    <table class="table table-bordered table-responsive-lg text-center">
                        <thead class="table-info text-light" style="background-color: #34495e;">
                            <tr>
                                <th style="border: none;">Event Name</th>
                                <th style="border: none;">Event Place</th>
                                <th style="border: none;">Event Start</th>
                                <th style="border: none;">Event End</th>
                                <th style="border: none;">Price</th>
                                <th style="border: none;" colspan=3>Action</th>
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
										<c:choose>
					                    	<c:when test="${id == event.user_id}">
						                        <a href="edit?id=<c:out value="${event.event_id}" />">Edit</a>
						                        <a class="pl-3" href="delete?id=<c:out value="${event.event_id}" />">Delete</a>
											</c:when>
											<c:otherwise>
						                        <a href="buy?id=<c:out value="${event.event_id}" />">Buy Ticket</a>
						                    </c:otherwise>
										</c:choose>
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