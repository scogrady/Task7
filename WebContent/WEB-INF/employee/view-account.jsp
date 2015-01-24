<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
<div class="page-header">
	<h1>View Customer Account</h1>

</div>

<div class="row">
	<div class="col-xs-12 col-sm-12">

		<table class="table">
			<thead>
				<tr>
					<th>Customer ID</th>
					<th>Customer Username</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>View Account </th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="customer" items="${customerList}">
					<tr>
						<td>${customer.getCustomer_id()}</td>
						<td>${customer.getUsername()}</td>
						<td>${customer.getFirstname()}</td>
						<td>${customer.getLastname()}</td>
						<td>
							<form method="post" action="ViewAccount.do">
								<input type="hidden" name="id"
									value="${customer.getCustomer_id()}">

								<button type="submit" class="btn btn-primary">View</button>
							</form>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>
<jsp:include page="template-bottom.jsp" />