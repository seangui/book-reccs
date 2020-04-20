<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/book-library-style.css">

<!-- Taken From https://startbootstrap.com/snippets/full-image-background/ -->

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
			<h1 class="font-weight-light text-center">Search a Book To Add
				To Your Library</h1>

			<form:form
				action="${pageContext.request.contextPath}/library/search-results"
				modelAttribute="book">

				<div class="form-group">
					<label>Title of Book</label> 
					
					<form:errors path="title"/>
					<form:input type="text" path="title" class="form-control" placeholder="Title"/>
				</div>

				<div class="form-group">
					<label>Author of Book</label> 
					
					<!-- When Form is first loaded Spring MVC will call .getAuthor on the DTO hence the path field -->
					<!-- When Form is submitted Spring will call the setter methods -->
					<form:errors path="author"/>
					<form:input type="text" path="author" class="form-control" placeholder="Author"/>
				</div>

				<button type="submit" value="Submit" class="btn btn-primary btn-lg btn-block"
					role="button">Search</button>

			</form:form>

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