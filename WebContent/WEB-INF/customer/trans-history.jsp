<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.TransactionBean"%>

<jsp:include page="template-top.jsp" />

<div class="col-xs-12 col-sm-12">
	<h3>Welcome ${customer.getFirstname()} ${customer.getLastname()}</h3>

	<div class="page-header">
		<h1>Transaction History</h1>
	</div>


	<table class="table">


		<thead>
			<tr>
				<th width="15%">Date of Execution</th>
				<th width="15%">Operation</th>
				<th width="15%">Fund ID</th>
				<th width="20%">Number of shares</th>
				<th width="15%">Share Price</th>
				<th width="20%">Dollar Amount</th>

			</tr>
		</thead>
		<tbody>

			<c:if test="${!empty transactionHistory}">
				<c:forEach items="${transactionHistory}" var="transactionBean">

					<tr>
						<td>${transactionBean.getExecute_date()}</td>


						<td>${transactionBean.getTransaction_type()}</td>

						<td>${transactionBean.getFund_id()}</td>


						<td>${transactionBean.getShares()}</td>

						<c:set var="price" scope="session"
							value="${transactionBean.getAmount()/transactionBean.getShares()}" />
						<td>${transactionBean.getAmount()/transactionBean.getShares()}</td>

						<td>${transactionBean.getAmount()}</td>
					</tr>


				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>

<jsp:include page="template-bottom.jsp" />
