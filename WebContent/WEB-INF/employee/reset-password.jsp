<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.FavoriteBean"%>

<jsp:include page="template-top.jsp" />
<div class="page-header">
	<h1>Reset Password</h1>

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

					<th>City</th>
					<th>State</th>
					<th>Zip Code</th>
					<th>Password</th>
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
						<td>${customer.getCity()}</td>
						<td>${customer.getState()}</td>
						<td>${customer.getZip()}</td>
						<td>${customer.getPassword()}</td>
						<td>
						<a
							href="ResetPassword.do?id=${customer.getCustomer_id()}" class="">
							<button type="submit" class="btn btn-primary">Change</button></a>
					
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>
<jsp:include page="template-bottom.jsp" />
