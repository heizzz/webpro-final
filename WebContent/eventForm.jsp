<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>
	<c:if test="${event != null}">
		Edit Event - events.co
	</c:if>
	<c:if test="${event == null}">
		Create Event - events.co
	</c:if>
</title>
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
				<li class="nav-item"><a class="nav-link nav-active" href="dashboard">Dashboard</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
			</ul>
		</div>
	</nav>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <c:if test="${event != null}">
            	<h2 class="pb-4">Edit event</h2>
            </c:if>
	        <c:if test="${event == null}">
            	<h2 class="pb-4">Create a new event</h2>
	        </c:if>
            <div class="card my-2">
                <div class="card-body">            
                    <c:if test="${event != null}">
			            <form action="edit" method="post">
			        </c:if>
			        <c:if test="${event == null}">
			            <form action="create" method="post">
			        </c:if>
                        <div class="d-flex flex-column">
                        	<c:if test="${event != null}">
			                    <input type="hidden" name="id" value="<%= request.getParameter("id") %>" />
			                </c:if> 
                            <div>
                                <div class="form-group">
									<label for="event_name">Event Name</label>
                                    <input type="text" name="event_name" class="form-control" placeholder="Event Name" value="<c:if test="${event != null}"><c:out value="${event.name}" /></c:if>" required>
                                </div>
                            </div>
                            <div>
                                <div class="form-group">
									<label for="event_place">Event Place</label>
                                    <input type="text" name="event_place" class="form-control" placeholder="Event Place" value="<c:if test="${event != null}"><c:out value="${event.place}" /></c:if>" required>
                                </div>
                            </div>
                            <div>
                                <div class="form-group">
									<label for="event_price">Price</label>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="event-price">IDR</span>
                                        </div>
                                        <input type="number" name="event_price" class="form-control" placeholder="Event Price"  value="<c:if test="${event != null}"><c:out value="${event.price}" /></c:if>" required>
                                    </div>
                                </div>
                            </div>
                            <div class="d-flex flex-row justify-content-between">
                                <div class="form-group w-50">
									<label for="event_start">Event Start</label>
                                    <input style="background-color: #fff !important;" type="text" id="startdate" name="event_start" class="form-control" placeholder="Event Start" data-input required>
                                </div>
                                <div class="form-group w-50 ml-4">
									<label for="event_end">Event End</label>
                                    <input style="background-color: #fff !important;" type="text" id="enddate" name="event_end" class="form-control" placeholder="Event End" data-input required>
                                </div>
                            </div>
                            <div>
                                <div class="form-group">
									<label for="event_description">Event Description</label>
                                    <textarea class="form-control" name="event_description" rows="5" placeholder="Event Description"><c:if test="${event != null}"><c:out value="${event.description}" /></c:if></textarea>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-12 text-right pr-0">
                                <a class="btn btn-danger px-4" href="dashboard">Back</a>
                                <button type="submit" class="btn btn-primary px-5 ml-3">Submit</button>
                            </div>
                        </div>
                    </form>
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
		allowInput: true,
		altInput: true,
	    altFormat: "F, d Y G:i K",
		mode: "single",
	    dateFormat: "F, d Y G:i K",
	    defaultDate: "${startdate}" == "" ? null : new Date("${startdate}"),
	    onClose: function (selectedDates, dateStr, instance) {
	    	instance.setDate(selectedDates);
	    }
	});
	$("#enddate").flatpickr({
		allowInput: true,
		altInput: true,
	    altFormat: "F, d Y G:i K",
		enableTime: true,
	    dateFormat: "F, d Y G:i K",
	    defaultDate: "${enddate}" == "" ? null : new Date("${enddate}"),
 	    onClose: function (selectedDates, dateStr, instance) {
	     	instance.setDate(selectedDates);
		}
	});
</script>
</body>
</html>