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
				<li class="nav-item">
				<form:form
						action="${pageContext.request.contextPath}/logout" method="POST">
						<button type="submit" class="btn btn-light" value="Logout">
							Logout</button>
					</form:form></li>
			</ul>
		</div>
	</div>
</nav>

<!-- Page Content -->
<div class="container">
	<div class="card border-0 shadow my-5">
		<div class="card-body p-5">
			<h1 class="font-weight-light text-center">Library</h1>
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col" style="text-align: center;">Book Cover</th>
						<th scope="col" style="text-align: center;">Title</th>
						<th scope="col" style="text-align: center;">Author</th>
						<th scope="col" style="text-align: center;">Delete Book</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${bookCollection}" var="book" varStatus="loop">

						<tr>
							<form:form
								action="${pageContext.request.contextPath}/library/delete-confirm"
								modelAttribute="bookToDelete">

								<td class="w-25" style="text-align: center;"><form:input type="hidden" path="imageLink" value="${book.imageLink}" /><img
									src="${book.imageLink}" class="img-fluid img-thumbnail"></td>
								<td style="text-align: center;"><form:input type="hidden" path="title" value="${book.title}"/> ${book.title}</td>
								<td style="text-align: center;"><form:input type="hidden" path="author" value="${book.author}" />${book.author}</td>
								<td style="text-align: center;"><button type="submit"
										value="Submit" class="btn btn-primary btn-danger">Delete</button>
							</form:form>

						</tr>


					</c:forEach>
				</tbody>
			</table>
			<a class="btn btn-primary btn-lg btn-block"
				href="${pageContext.request.contextPath}/library/search-book/"
				role="button">Add a Book</a>
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