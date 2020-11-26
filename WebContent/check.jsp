<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Check Attendance - events.co</title>
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
				<li class="nav-item"><a class="nav-link nav-active" href="dashboard">Dashboard</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link text-white" href="logout">Logout</a></li>
			</ul>
		</div>
	</nav>
	<c:if test="${checkSuccess}">
		<div style="right: 0;" class="position-absolute float-right w-25 m-3 alert alert-success alert-dismissible fade show" role="alert">
			Check attendance successful!
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    		<span aria-hidden="true">&times;</span>
	 		</button>
		</div>
	</c:if>
	<c:if test="${ticketUsed}">
		<div style="right: 0;" class="position-absolute float-right w-25 m-3 alert alert-danger alert-dismissible fade show" role="alert">
			Ticket has been used!
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    		<span aria-hidden="true">&times;</span>
	 		</button>
		</div>
	</c:if>
	<c:if test="${checkFail}">
		<div style="right: 0;" class="position-absolute float-right w-25 m-3 alert alert-danger alert-dismissible fade show" role="alert">
			Wrong Ticket ID!
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    		<span aria-hidden="true">&times;</span>
	 		</button>
		</div>
	</c:if>
	<c:if test="${ticketNotAvailable}">
		<div style="right: 0;" class="position-absolute float-right w-25 m-3 alert alert-danger alert-dismissible fade show" role="alert">
			Ticket ID is not available!
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    		<span aria-hidden="true">&times;</span>
	 		</button>
		</div>
	</c:if>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <h2 class="pb-4 display-4"><c:out value="${event.name}" /></h2>
            <h3 class="lead ml-3">Check Attendance</h3>
            <div class="card my-2">
                <div class="card-body">
                	<form action="check" method="post">
	                	<div class="d-flex flex-column">
	                         <div>
								 <input type="hidden" name="event_id" class="form-control" value="${id}">
	                             <div class="form-group">
									 <label for="ticket_id">Ticket ID</label>
	                                 <input type="text" name="ticket_id" class="form-control" placeholder="Ticket ID" required>
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
<%@ include file="libjs.jspf"%>
</body>
</html>