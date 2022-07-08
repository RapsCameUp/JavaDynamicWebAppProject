
<%
if (session.getAttribute("name") == null) {
	response.sendRedirect("login.jsp");
}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<title>Raps</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.4.19/sweetalert2.min.js"
	integrity="sha512-8EbzTdONoihxrKJqQUk1W6Z++PXPHexYlmSfizYg7eUqz8NgScujWLqqSdni6SRxx8wS4Z9CQu0eakmPLtq0HA=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.4.19/sweetalert2.css"
	integrity="sha512-p06JAs/zQhPp/dk821RoSDTtxZ71yaznVju7IHe85CPn9gKpQVzvOXwTkfqCyWRdwo+e6DOkEKOWPmn8VE9Ekg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<body id="page-top">

	<nav class="navbar navbar-dark bg-primary navbar-expand-lg">
		<div class="container-fluid">
			<input id="status" type="hidden"
				value="<%=request.getAttribute("status")%>" />

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01"
				aria-controls="navbarTogglerDemo01" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
				<a class="navbar-brand" href="#"></a>
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="index.jsp"><i class="fas fa-home"></i>
							Home</a></li>
					<li class="nav-item"><a class="nav-link text-white"
						href="teachers"> <i class="fas fa-chalkboard-teacher"></i>
							Subjects
					</a></li>
					<li class="nav-item"><a class="nav-link text-white"
						href="students"><i class="fas fa-users"></i> Students</a></li>
					<li class="nav-item"><a class="nav-link text-white"
						href="subjects"><i class="fas fa-book"></i> Subjects</a></li>
					<li class="nav-item"><a class="nav-link text-white"
						href="classes"><i class="fas fa-school"></i> Classes</a></li>
				</ul>
				<form class="d-flex">
					<input class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search"> <a href="logout"
						class="btn btn-light" type="submit">Logout</a>
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid mt-5">
		<div class="row">
			<div class="offset-sm-1 col-sm-10">
				<div class="card">
					<div class="card-header">
						<p class="float-start">Subjects</p>
						<a type="button" class="btn btn-sm btn-primary float-end"
							data-bs-toggle="modal" data-bs-target="#addnewsubjectmodal">Add
							New Subject</a>
					</div>
					<div class="card-body">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Name</th>
									<th scope="col">Description</th>
									<th scope="col">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="temp" items="${ ListOfSubjects }">
									<tr>
										<th scope="row">${temp.id}</th>
										<td>${temp.name}</td>
										<td>${temp.description}</td>
										<td><a class="btn btn-sm btn-danger"
											href="subjects?action=delete&id=<c:out value='${temp.id}' />">Remove</a>
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

	<div class="modal fade" id="addnewsubjectmodal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Enter Subject Details Below</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
					<form action="subjects" method="post">
				<div class="modal-body">
						
					  <div class="mb-3">
					    <label class="form-label">Name</label>
					    <input type="text" class="form-control" name="name">
					  </div>
					    <div class="mb-3">
					    <label class="form-label">Description</label>
					    <input type="text" class="form-control" name="description">
					  </div>
					  <div class="mb-3">
					    <select class="form-select" name="classnum">
						  <option selected>Select class</option>
						  <c:forEach var="temp" items="${ ListOfClasses }">
						  		<option value="${temp.num}">${temp.num}</option>
						</c:forEach>
						</select>
					  </div>
					   <input type="hidden" class="form-control" name="action" value="doAdd">		
				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
					</form>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.4.19/sweetalert2.min.js"
		integrity="sha512-8EbzTdONoihxrKJqQUk1W6Z++PXPHexYlmSfizYg7eUqz8NgScujWLqqSdni6SRxx8wS4Z9CQu0eakmPLtq0HA=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>

	<script type="text/javascript">
		var status = document.getElementById("status").value;
		if (status === "success") {
			Swal.fire('Successful?', 'Successfull', 'success');
		} else if (status === "failed") {
			Swal.fire('Error?', 'An error has occured. Please try again.',
					'error');
		}
	</script>

</body>
</html>