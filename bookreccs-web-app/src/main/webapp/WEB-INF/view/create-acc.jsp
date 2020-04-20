<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- HTML & CSS Taken from Colorlib at: https://colorlib.com/wp/template/creative-login-form/ -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login-page-style.css">



<!-- New Page -->

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
							<h3 class="login-heading mb-4">Create An Account</h3>
							<form:form class="register-form"
								action="${pageContext.request.contextPath}/register/process-regristration-form"
								modelAttribute="userDto">

								<!-- Check for registration error - This is for the model attribute we're using in process-registration-form controller -->
								<c:if test="${registrationError != null}">
									<div class="alert alert-danger col-xs-offset-1 col-xs-10">
										${registrationError}</div>
								</c:if>
								<div class="form-label-group">
									<form:errors path="username" />
									<form:input class="form-control" id="inputUsername" type="text"
										name="username" path="username" placeholder="username" />
									<label for="inputUsername">Username</label>
								</div>

								<div class="form-label-group">
									<form:errors path="password" />
									<form:input class="form-control" id="inputPassword"
										type="password" name="password" path="password"
										placeholder="password" />
									<label for="inputPassword">Password</label>

								</div>

								<div class="form-label-group">
									<form:errors path="email" />
									<form:input class="form-control" type="text" path="email"
										name="email" placeholder="email address" id="inputEmail" />
									<label for="inputEmail">Email</label>
								</div>

								<button
									class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"
									type="submit">Create Account</button>

								<div class="text-center">
									<a class="small"
										href="${pageContext.request.contextPath}/login/">Already
										have an account? Sign In</a>
								</div>
							</form:form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script
	src="${pageContext.request.contextPath}/webjars/jquery/3.4.1/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link
	href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">