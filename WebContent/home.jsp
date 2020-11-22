<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ include file="lib.jspf"%>
</head>
<body>
	<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-10">

            <div style="text-align:center; font-size:30px;">
                <div>Welcome to events.co!</div>
                <span style="white-space: pre-line"></span>
                <div>What event do you want to attend?</div>
            </div>

            <div>
                <div class="card-header text-center" style="margin-top:20px; font-size:20px;">Actions</div>
                <div class="card-body text-center d-flex flex-row justify-content-between w-75 mx-auto">
                    <a class="btn btn-success px-4" href="profile">My Profile</a>
                    <a class="btn btn-success px-4" href="myTickets">My Tickets</a>
                    <a class="btn btn-success px-4" href="myEvents">My Events</a>
                    <a class="btn btn-success px-4" href="createEvent">Create new event</a>
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
				                    <td><c:out value="${event.event_name}" /></td>
				                    <td><c:out value="${event.event_place}" /></td>
				                    <td><c:out value="${event.event_start}" /></td>
				                    <td><c:out value="${event.event_end}" /></td>
				                    <td><c:out value="${event.event_price}" /></td>
				                    <td>
				                        <a href="/edit?id=<c:out value='${event.id}' />">Edit</a>
				                        &nbsp;&nbsp;&nbsp;&nbsp;
				                        <a href="/delete?id=<c:out value='${event.id}' />">Delete</a>                     
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