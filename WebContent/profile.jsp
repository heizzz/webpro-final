<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile - events.co</title>
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
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link nav-active"
					href="dashboard">Dashboard</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link text-white" href="logout">Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md">
				<div class="mb-5">
					<h1 class="display-4 pb-2">${name}</h1>
					<p class="pb-5"><b>Email address: </b>${email}</p>
				</div>
				<div class="w-100 pb-3 d-flex flex-column align-items-center">
					<div class="btn-group" role="group">
						<button class="btn btn-secondary active px-5" id="btnEvents">My Events</button>
						<button class="btn btn-secondary px-5" id="btnTickets">My Tickets</button>
					</div>
				</div>
                    <table class="table table-bordered table-responsive-lg text-center" id="events">
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
										<td><fmt:formatDate value="${event.start_time}" type="both" dateStyle="long" timeStyle="medium" /></td>
										<td><fmt:formatDate value="${event.end_time}" type="both" dateStyle="long" timeStyle="medium" /></td>
										<td><fmt:formatNumber value="${event.price}" pattern="IDR #,###" /></td>
										<td class="d-flex flex-row">
											<a class="btn btn-primary mr-1" href="edit?id=<c:out value="${event.event_id}" />">Edit</a>
											<a class="btn btn-danger" href="delete?id=<c:out value="${event.event_id}" />">Delete</a>
										</td>
										
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
                    <table class="table table-bordered table-responsive-lg text-center" id="tickets">
                        <thead class="table-info text-light" style="background-color: #34495e;">
                            <tr>
									<th style="border: none;">Code</th>
									<th style="border: none;">Event Name</th>
									<th style="border: none;">Purchase Time</th>
									<th style="border: none;">Event Time</th>
									<th style="border: none;">Used</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="ticket" items="${listTicket}">
									<tr>
										<td><c:out value="${ticket.ticket_id}" /></td>
										<td><c:out value="${ticket.getEvent_name()}" /></td>
										<td><fmt:formatDate value="${ticket.purchase_time}" type="both" dateStyle="long" timeStyle="medium" /></td>
										<td><fmt:formatDate value="${ticket.getEvent_time()}" type="both" dateStyle="long" timeStyle="medium" /></td>
										
										<c:choose>
					                    	<c:when test="${ticket.used==true}">	
					                    		<td valign="middle">
						                            <svg width="20px" height="20px" viewBox="0 0 16 16" class="bi bi-check-circle-fill" fill="green" xmlns="http://www.w3.org/2000/svg">
						                                <path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z" />
						                            </svg>
						                        </td>
											</c:when>
											<c:otherwise>
												<td valign="middle">
						                            <svg width="20px" height="20px" viewBox="0 0 16 16" class="bi bi-x-circle-fill" fill="red" xmlns="http://www.w3.org/2000/svg">
						                                <path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293 5.354 4.646z" />
						                            </svg>
						                        </td>
						                    </c:otherwise>
										</c:choose>
				                    
									</tr>
								</c:forEach>
							</tbody>
						</table>
	</div>
	<%@ include file="libjs.jspf"%>
	<script>
		$(document).ready(function() {
			$("#tickets").hide();
			$("#btnEvents").addClass("active");
		});
		$("#btnEvents").on("click", function() {
			$("#events").show();
			$("#tickets").hide();
			$("#btnEvents").addClass("active");
			$("#btnTickets").removeClass("active");
		});
		$("#btnTickets").on("click", function() {
			$("#tickets").show();
			$("#events").hide();
			$("#btnTickets").addClass("active");
			$("#btnEvents").removeClass("active");
		});
	</script>
</body>
</html>