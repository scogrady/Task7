<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.FavoriteBean"%>

<jsp:include page="template-top.jsp" />
<h1>Transaction History</h1>
<div class="col-md-7 col-md-offset-2">
	<br> <br>
	<h3>Welcome ${customer.getFirstName()} ${customer.getLastName()}</h3>
	<br>
	<h5>Your Transaction History</h5>

	<table class="table">
		<tr>
			<td><h4>
					<strong>Date of Execution</strong>
				</h4></td>
			<td><h4>
					<strong>Operation</strong>
				</h4></td>
			<td><h4>
					<strong>Fund ID</strong>
				</h4></td>
			<td><h4>
					<strong>Number of shares</strong>
				</h4></td>
			<td><h4>
					<strong>Share Price</strong>
				</h4></td>
			<td><h4>
					<strong>Dollar Amount</strong>
				</h4></td>
		</tr>

		<c:if test="${!empty transactionHistory}">
			<c:forEach items="${transactionHistory}" var="transactionBean">

				<tr>
					<td>${transactionBean.getExecute_date()}</td>
				</tr>
				<tr>
					<td>${transactionBean.getTransaction_type()}</td>
				</tr>
				<tr>
					<td>${transactionBean.getFund_id()}</td>
				</tr>
				<tr>

					<td>${transactionBean.getShares()}</td>
				</tr>
				<tr>
					<c:set var="price" scope="session"
						value="${transactionBean.getAmount()/transactionBean.getShares()}" />
					<td>${transactionBean.getAmount()/transactionBean.getShares()}</td>
				</tr>
				<tr>
					<td>${transactionBean.getAmount()}</td>
				</tr>
				<tr>
					<td></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</div>

<jsp:include page="template-bottom.jsp" />
