<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Event Details - events.co</title>
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
				<li class="nav-item"><a class="nav-link nav-active" href="dashboard">Dashboard</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link text-white" href="logout">Logout</a></li>
			</ul>
		</div>
	</nav>
	<c:if test="${createSuccess}">
		<div style="right: 0;" class="position-absolute float-right w-25 m-3 alert alert-success alert-dismissible fade show" role="alert">
			Create event successful!
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    		<span aria-hidden="true">&times;</span>
	 		</button>
		</div>
	</c:if>
	<c:if test="${editSuccess}">
		<div style="right: 0;" class="position-absolute float-right w-25 m-3 alert alert-success alert-dismissible fade show" role="alert">
			Edit event successful!
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    		<span aria-hidden="true">&times;</span>
	 		</button>
		</div>
	</c:if>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <h2 class="pb-4 display-4"><c:out value="${event.name}" /></h2>
            <h3 class="lead ml-3">Event details</h3>
            <div class="card my-2">
                <div class="card-body">
                	<table class="table">
                		<tr>
                			<td style="width: 30%; border: none;">Event Location</td>
                			<td style="border: none;"><c:out value="${event.place}" /></td>
                		</tr>
                		<tr>
                			<td>Event Price</td>
                			<td>IDR <c:out value="${event.price}" /></td>
                		</tr>
                		<tr>
                			<td>Event Time</td>
                			<td>
                				<fmt:formatDate dateStyle="long" type="both" value="${event.start_time}"/><span class="px-3">&mdash;</span><fmt:formatDate dateStyle="long" type="both" value="${event.end_time}"/>
                			</td>
                		</tr>
                	</table>
                	<div class="py-3">
                		<h3 class="lead">Event Description</h3>
                		<hr class="display-4 pb-3">
						<c:if test="${event.description.length() == 0}">
							<span class="pl-3 text-muted">No description provided.</span>
						</c:if>
						<c:if test="${event.description.length() > 0}">
							<span class="pl-3"><c:out value="${event.description}" /></span>
						</c:if>
                	</div>
                	<div class="col-xs-12 col-sm-12 col-md-12 text-right pr-0">
						<c:choose>
	                    	<c:when test="${id == event.user_id}">
					    		<a class="btn btn-primary px-4" href="check?id=<c:out value="${event.event_id}" />">Check attendance</a>
							</c:when>
						</c:choose>
					    <a class="btn btn-danger px-4" href="dashboard">Back</a>
					</div>
                </div>
            </div>
        </div>
    </div>
</div>
<fmt:formatDate type="both" value="${event.start_time}" var="startdate"/>
<fmt:formatDate type="both" value="${event.end_time}" var="enddate"/>
<%@ include file="libjs.jspf"%>
<script type="text/javascript">
	$("#startdate").flatpickr({
		mode: "single",
	    dateFormat: "F, d Y G:i K",
	    defaultDate: "${startdate}" == "" ? null : new Date("${startdate}")
	});
	$("#enddate").flatpickr({
		enableTime: true,
	    dateFormat: "F, d Y G:i K",
	    defaultDate: "${enddate}" == "" ? null : new Date("${enddate}")
	});
</script>
</body>
</html>