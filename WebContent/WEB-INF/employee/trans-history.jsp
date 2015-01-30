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
				<th width="10%" class="title_right">Fund ID</th>
				<th width="15%" class="title_right">Number of shares</th>
				<th width="15%" class="title_right">Share Price</th>
				<th width="20%" class="title_right">Dollar Amount</th>

			</tr>
		</thead>
		<tbody>

			<c:if test="${!empty transactionHistory}">
				<c:forEach items="${transactionHistory}" var="transactionBean">
					<c:if
						test="${transactionBean.getTransaction_type() == 'Buy Fund' }">
						<tr class="active">

							<td><fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${transactionBean.getExecute_date()}" /></td>
							<td>${transactionBean.getTransaction_type()}</td>
							<td class="title_right">${transactionBean.getFund_id()}</td>
							<c:choose>
								<c:when test="${!empty transactionBean.getExecute_date()}">

									<c:set var="getShares"
										value="${transactionBean.getShares() / 1000 }" />
									<td class="title_right"><fmt:formatNumber type="number" pattern="#,##0.000"
											value="${getShares}" /></td>

									<c:set var="price" scope="session"
										value="${transactionBean.getAmount()/transactionBean.getShares()}" />
									<c:set var="priceShares"
										value="${transactionBean.getAmount()/transactionBean.getShares() * 10}" />
									<td class="title_right">$<fmt:formatNumber type="number" pattern="#,##0.00"
											value="${priceShares}" /></td>
								</c:when>

								<c:otherwise>

									<td></td>
									<td></td>
								</c:otherwise>
							</c:choose>

							<c:set var="getAmt" value="${transactionBean.getAmount() / 100}" />
							<td class="title_right">$<fmt:formatNumber type="number" pattern="#,##0.00"
									value="${transactionBean.getAmount()}" /></td>

						</tr>
					</c:if>

					<c:if
						test="${transactionBean.getTransaction_type() == 'Sell Fund' }">
						<tr class="success">
							<td><fmt:formatDate type="both"  pattern="yyyy-MM-dd"  value="${transactionBean.getExecute_date()}" /></td>

							<td>${transactionBean.getTransaction_type()}</td>
							<td class="title_right">${transactionBean.getFund_id()}</td>
							<c:set var="getShares"
								value="${transactionBean.getShares() / 1000 }" />
							<td class="title_right"><fmt:formatNumber type="number" pattern="#,##0.000"
									value="${getShares}" /></td>

							<c:choose>
								<c:when test="${!empty transactionBean.getExecute_date()}">
									<c:set var="price" scope="session"
										value="${transactionBean.getAmount()/transactionBean.getShares()}" />
									<c:set var="priceShares"
										value="${transactionBean.getAmount()/transactionBean.getShares() * 10}" />
									<td class="title_right">$<fmt:formatNumber type="number" pattern="#,##0.00"
											value="${priceShares}" /></td>

									<c:set var="getAmt"
										value="${transactionBean.getAmount() / 100}" />
									<td class="title_right">$<fmt:formatNumber type="number" pattern="#,##0.00"
											value="${priceShares}" /></td>
								</c:when>
								<c:otherwise>
									<td></td>
									<td></td>
								</c:otherwise>
							</c:choose>

						</tr>
					</c:if>

					<c:if
						test="${transactionBean.getTransaction_type() == 'Request Check' }">
						<tr class="info">

							<td><fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${transactionBean.getExecute_date()}" /></td>
							<td>${transactionBean.getTransaction_type()}</td>
							<td></td>
							<td></td>
							<td></td>
							<c:set var="getAmt" value="${transactionBean.getAmount() / 100}" />
							<td class="title_right">$<fmt:formatNumber type="number" pattern="#,##0.00"
									value="${transactionBean.getAmount()}" /></td>
						</tr>
					</c:if>
					<c:if
						test="${transactionBean.getTransaction_type() == 'Deposit Check' }">
						<tr class="warning">
							<td><fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${transactionBean.getExecute_date()}" /></td>

							<td>${transactionBean.getTransaction_type()}</td>
							<td></td>
							<td></td>
							<td></td>
							<c:set var="getAmt" value="${transactionBean.getAmount() / 100}" />
							<td class="title_right">$<fmt:formatNumber type="number" pattern="#,##0.00"
									value="${transactionBean.getAmount()}" /></td>

						</tr>
					</c:if>



				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>

<jsp:include page="template-bottom.jsp" />
