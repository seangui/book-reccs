<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- HTML + CSS Taken from https://startbootstrap.com/snippets/sign-in-split/ -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login-page-style.css">

<div class="container-fluid">
	<div class="row no-gutter">
		<div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
		<div class="col-md-8 col-lg-6">
			<div class="login d-flex align-items-center py-5">
				<div class="container">
					<div class="row">
						<div class="col-md-9 col-lg-8 mx-auto">
							<h3 class="login-heading mb-4">Welcome To Book Reccs!</h3>

							<!-- Make sure the /authenticate-login matches with .loginprocessingurl in bookreccssecruityconfig -->
							<form:form
								action="${pageContext.request.contextPath}/authenticate-login"
								method="POST">

								<!-- Adding Login Error Message -->
								<c:if test="${param.error != null }">
									<i class="failed">Username or password invalid</i>
								</c:if>
								
								<!-- These 'names' (username, password etc.) are defaults used by spring security -->
								<div class="form-label-group">
									<input type="text" name="username" id="inputUsername"
										class="form-control" placeholder="Username" required autofocus>
									<label for="inputUsername">Username</label>
								</div>
								
								<div class="form-label-group">
									<input type="password" name="password" id="inputPassword" class="form-control"
										placeholder="Password" required> <label
										for="inputPassword">Password</label>
								</div>
								
								<!-- we'll use this for another time when I can build the functionality 
								<div class="custom-control custom-checkbox mb-3">
									<input type="checkbox" class="custom-control-input"
										id="customCheck1"> <label class="custom-control-label"
										for="customCheck1">Remember password</label>
								</div>
								-->
								
								<button
									class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"
									type="submit">Sign in</button>
								<div class="text-center">
									 <a class="small" href="${pageContext.request.contextPath}/register/create-acc">Create
										an account</a>
								</div>

							</form:form>



						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="${pageContext.request.contextPath}/webjars/jquery/3.4.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">


