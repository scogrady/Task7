<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="databeans.TransactionBean"%>

<jsp:include page="template-top.jsp" />

<div class="col-xs-12 col-sm-12">
	
	<div class="page-header">
		<h1>Transaction History</h1>
	</div>


	<table class="table">

		<thead>
			<tr>
				<th width="25%">Date of Execution</th>
				<th width="15%">Operation</th>
				<th width="10%">Fund ID</th>
				<th width="15%">Number of shares</th>
				<th width="15%">Share Price</th>
				<th width="20%">Dollar Amount</th>

			</tr>
		</thead>
		<tbody>

			<c:if test="${!empty transactionHistory}">
				<c:forEach items="${transactionHistory}" var="transactionBean">
<c:if test="${transactionBean.getTransaction_type() == 'Buy Fund' }">
<tr class="active">




</tr>
　</c:if>

<c:if test="${transactionBean.getTransaction_type() == 'Sell Fund' }">
<tr class="active">




</tr>
　</c:if>

<c:if test="${transactionBean.getTransaction_type() == 'Request Check' }">
<tr class="active">




</tr>
　</c:if>
<c:if test="${transactionBean.getTransaction_type() == 'Deposit Check' }">
<tr class="active">




</tr>
　</c:if>

					<tr>
						<td>${transactionBean.getExecute_date()}</td>
						<td>${transactionBean.getTransaction_type()}</td>
						<td>${transactionBean.getFund_id()}</td>
						
						<c:set var="getShares" value="${transactionBean.getShares() / 1000 }" />
						<td><fmt:formatNumber type="number" pattern="#,##0.000"	value="${getShares}" /></td>
			
						<c:set var="price" scope="session" value="${transactionBean.getAmount()/transactionBean.getShares()}" />
						<c:set var="priceShares" value="${transactionBean.getAmount()/transactionBean.getShares() * 10}" />
						<td><fmt:formatNumber type="number" pattern="#,##0.00"	value="${priceShares}" /></td>
		
						<c:set var="getAmt" value="${transactionBean.getAmount() / 100}" />
						<td>$<fmt:formatNumber type="number" pattern="#,##0.00"	value="${priceShares}" /></td>
					</tr>


				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>

<jsp:include page="template-bottom.jsp" />
