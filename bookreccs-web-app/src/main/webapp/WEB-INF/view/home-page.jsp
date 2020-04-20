<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- HTML + CSS Taken from https://startbootstrap.com/snippets/full-image-header/ -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/home-page-style.css">

<!-- Navigation -->
<nav
	class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
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
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/library/">Book Library
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/recommendations/">Book
						Recommendations</a></li>
				<li class="nav-item">
				<form:form action="${pageContext.request.contextPath}/logout" method="POST">
						<button type="submit" class="btn btn-light" value="Logout"> Logout</button>
				</form:form> 
				</li>
			</ul>
		</div>
	</div>
</nav>

<!-- Full Page Image Header with Vertically Centered Content -->
<header class="masthead"> </header>

<!-- Page Content -->
<section class="py-5">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-6">
				<div class="card">
					<img class="card-img-top"
						src="https://source.unsplash.com/k2Kcwkandwg/1920x1080"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title">Book Library</h5>
						<p class="card-text">Add your favorite books to the book
							library to get more recommendations.</p>
						<a href="${pageContext.request.contextPath}/library/"
							class="btn btn-primary">See my Book Library</a>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="card">
					<img class="card-img-top"
						src="https://source.unsplash.com/hgFY1mZY-Y0/1920x1080"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title">Book Reccs</h5>
						<p class="card-text">See the book recommendations we give you
							based on your book list.</p>
						<a href="${pageContext.request.contextPath}/recommendations/"
							class="btn btn-primary">See my Book Reccs</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<script
	src="${pageContext.request.contextPath}/webjars/jquery/3.4.1/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link
	href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">