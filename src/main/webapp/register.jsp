<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.4.19/sweetalert2.css" integrity="sha512-p06JAs/zQhPp/dk821RoSDTtxZ71yaznVju7IHe85CPn9gKpQVzvOXwTkfqCyWRdwo+e6DOkEKOWPmn8VE9Ekg==" crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<body>
	<section class="vh-100">
		<div class="container-fluid">
		
			<input id="status" type="hidden" value="<%= request.getAttribute("status") %>"/>
			
			<div class="row">
				<div class="col-sm-8 px-0 d-none d-sm-block">
					<img
						src="https://images.unsplash.com/photo-1631173716529-fd1696a807b0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1112&q=80"
						alt="Login image" class="w-100 vh-100"
						style="object-fit: cover; object-position: left;">
				</div>
				<div class="col-sm-4 text-black mt-5">

					<div class="px-5 ms-xl-4">
							<span class="h1 fw-bold mb-0">Learner's Academy</span>
					</div>

					<div
						class="d-flex align-items-center h-custom-2 px-5 ms-xl-4 mt-5 pt-5 pt-xl-0 mt-xl-n5">

						<form method="post" action="register" style="width: 23rem;">

							<h3 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Register</h3>

						<div class="form-outline mb-4">
								<input type="text" id="form2Example18" name="name"
									class="form-control" /> <label
									class="form-label" for="form2Example18">First Name</label>
							</div>
							
							<div class="form-outline mb-4">
								<input type="text" id="form2Example18" name="surname"
									class="form-control" /> <label
									class="form-label" for="form2Example18">Last Name</label>
							</div>

							<div class="form-outline mb-4">
								<input type="email" id="form2Example18" name="email"
									class="form-control" /> <label
									class="form-label" for="form2Example18">Email address</label>
							</div>

							<div class="form-outline mb-4">
								<input type="password" id="form2Example28" name="password"
									class="form-control" /> <label
									class="form-label" for="form2Example28">Password</label>
							</div>
							
							<div class="form-outline mb-4">
								<input type="password" id="form2Example28" name="confirm-password"
									class="form-control" /> <label
									class="form-label" for="form2Example28">Confirm Password</label>
							</div>

							<div class="pt-1 mb-4">
								<button class="btn btn-info btn-block" type="submit">Register</button>
							</div>
							<p>
								have an account? <a href="login.jsp" class="link-info">Login
									here</a>
							</p>

						</form>

					</div>

				</div>
			</div>
		</div>
	</section>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.4.19/sweetalert2.min.js" integrity="sha512-8EbzTdONoihxrKJqQUk1W6Z++PXPHexYlmSfizYg7eUqz8NgScujWLqqSdni6SRxx8wS4Z9CQu0eakmPLtq0HA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
		
		<script type="text/javascript">
			var status = document.getElementById("status").value;
			if(status === "success"){
				Swal.fire(
						  'Successful?',
						  'Registration Successfull',
						  'success'
						);	
			}
			else if(status === "failed"){
				Swal.fire(
						  'Error?',
						  'An error has occured. Please try again.',
						  'error'
						);
			}
			
		</script>

</body>
</html>