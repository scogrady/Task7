<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="error" items="${errors}">
	<c:if test="${!(empty errors) }">

		<div class="row-fluid">
			<div class="col-md-12">
				<div class="alert alert-danger" role="alert">${error }</div>
			</div>
		</div>
	</c:if>
</c:forEach>