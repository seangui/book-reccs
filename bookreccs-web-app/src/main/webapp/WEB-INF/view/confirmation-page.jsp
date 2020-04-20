<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/book-library-style.css">

<!-- Page Content -->
<div class="container">
	<div class="card border-0 shadow my-5">
		<div class="card-body p-5">
			<div class="row">
				<div class="col-12 mb-3" align="center">
					<h2 class="font-weight-light text-center">Congratulations! You have Successfully made an account!</h2>
				</div>
				<div class="col-12 mb-3" align="center">
					<div class="text-center">
						<a class="btn btn-primary btn-large btn-block"
							href="${pageContext.request.contextPath}/login/"> Back To
							Login Page </a>
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