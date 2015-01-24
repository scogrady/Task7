<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="message" items="${message}">
	<c:if test="${!(empty message) }">

		<div class="row-fluid">
			<div class="col-md-12">
				<div class="alert alert-success" role="alert">${message }</div>
			</div>
		</div>
	</c:if>
</c:forEach>