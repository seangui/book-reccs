<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/book-library-style.css">

<!-- Navigation -->
<nav
	class="navbar navbar-expand-lg navbar-light bg-light static-top mb-5 shadow">
	<div class="container">
		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/home/">Book Reccs</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.request.contextPath}/library/">Book Library
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/recommendations/">Book
						Recommendations</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Logout</a></li>
			</ul>
		</div>
	</div>
</nav>

<!-- Page Content -->
<div class="container">
	<div class="card border-0 shadow my-5">
		<div class="card-body p-5">
			<div class="row">
				<div class="col-12 mb-3" align="center">
					<h2 class="font-weight-light text-center">This book has already been added!</h2>
				</div>
				<div class="col-12 mb-3" align="center">
					<div class="text-center">
						<a class="btn btn-primary btn-large btn-block"
							href="${pageContext.request.contextPath}/library/search-book/"> Back To
							Library </a>
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