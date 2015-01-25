<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="databeans.CustomerBean"%>
<%@ page import="databeans.PositionBean"%>
<%@ page import="databeans.FundBean"%>
<%@ page import="databeans.FundPriceHistoryBean"%>

<jsp:include page="template-top.jsp" />
<style type="text/css">
.scrollspy-customerList {
	height: 800px;
	width: 100%;
	overflow: auto;
	position: relative;
}

.scrollspy-transactionHistory {
	height: 250px;
	width: 100%;
	overflow: auto;
	position: relative;
}
</style>

<div class="page-header">
	<h3>View Customer Account</h3>

	<jsp:include page="error-list.jsp" />
</div>

<div class="row">
	<div class="col-md-3">
		<h3>Customer list</h3>
			
		<div data-spy="scroll" data-offset="50" class="scrollspy-customerlist">
			<ul class="list-group">
				<c:forEach var="customer" items="${customerList}">
					<li class="list-group-item"><a
						href="ViewAccount.do?customer_id=${customer.getCustomer_id()}">${customer.getFirstname()}
								${customer.getLastname()}</a>
						<p>User Name:${customer.getUsername()}</p></li>
				</c:forEach>
			</ul>
		</div>
	</div>

	<div class="col-md-9" id="customer-account-info">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Customer Account</h4>
			</div>
			<div class="panel-body">
				<%
					CustomerBean customer = (CustomerBean) request
							.getAttribute("customer");
				%>
				<table class="table">
					<tr>
						<th scope="row" width="25%">Name :</th>
						<td id="customer-name"><%=customer.getFirstname()%> <%=customer.getLastname()%></td>
					</tr>
					<tr>
						<th scope="row">Address :</th>
						<td id="customer-address-1"><%=customer.getAddr_line1()%></td>
					</tr>
					<%
						if (customer.getAddr_line2() != null) {
					%>
					<tr>
						<td></td>
						<td id="customer-address-2"><%=customer.getAddr_line2()%></td>
					</tr>
					<%
						}
					%>
					<tr>
						<th scope="row">City,State,Zip:</th>
						<td id="customer-city"><%=customer.getCity()%>,<%=customer.getState()%>,<%=customer.getZip()%></td>
					</tr>
					<tr>
						<c:set var="currentBalance"
							value="<%=customer.getCurrent_cash() / 100%>" />
						<th scope="row">Current balance:</th>
						<td>$<fmt:formatNumber type="number" pattern="#,##0.00"
								value="${currentBalance}" /></td>
					</tr>
					<tr>
						<c:set var="availableBalance"
							value="<%=customer.getAvailable_cash() / 100%>" />
						<th scope="row">Available balance:</th>
						<td>
							<div class="row">
							<p class="col-md-3">$<fmt:formatNumber type="number" pattern="#,##0.00"	value="${availableBalance}" /></p>
							<form role="form"  method="post" action="DepositCheck.do">
								<div class="input-group col-md-5">
									<span class="input-group-addon" id="basic-addon1">$</span>
									<input type="hidden" name="username" value="${customer.getUsername()}">
									<input type="text" class="form-control" id="amount" name="amount" >
									<span class="input-group-btn">					
										<input class="btn btn-default" type="submit" name="action" value="Deposit">
									</span>
								</div>
							</form>
							</div>
						</td>
						<td>
					</tr>
					<tr>
						<th></th>
						<td>$
							<form role="form" method="post" action="DepositCheck.do">
								<input type="text" id="amount" name="amount"> <input
									type="hidden" name="username" value="${customer.getUsername()}">
								<input class="btn btn-primary" type="submit" name="action"
									value="Deposit">
						</td>
						</form>
						</td>
					</tr>
					<tr>
						<th scope="row">Last Trading Day:</th>
						<c:set var="lastTradingDay"
							value="<%=customer.getLast_login_time()%>" />
						<td id="last-trading-day"><fmt:formatDate
								value="${lastTradingDay}" pattern="MMM dd yyyy " /></td>
					</tr>
				</table>
			</div>
			<div class="panel-heading">
				<h4>Financial Information</h4>
			</div>

			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#home"
					aria-controls="home" role="tab" data-toggle="tab">Portfolio</a></li>
				<li role="presentation"><a href="#profile"
					aria-controls="profile" role="tab" data-toggle="tab">Transaction
						History</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">

				<div role="tabpanel" class="tab-pane active" id="home">
					<table class="table table-striped">
						<tr>
							<th>Fund Code</th>
							<th>Number of Shares</th>
							<th>Share Price</th>
							<th>Worth</th>
						</tr>
						<%
							long total = 0;
							for (PositionBean pos : (PositionBean[]) request
									.getAttribute("position")) {
						%>
						<tr>
							<%
								String symbol = "";
									for (FundBean fun : (FundBean[]) request
											.getAttribute("fundTicker")) {
										if (fun.getFund_id() == pos.getFund_id())
											symbol = fun.getSymbol();
									}
							%>
							<td><%=symbol%></td>
							<c:set var="shares" value="<%=pos.getShares() / 1000%>" />
							<td><fmt:formatNumber type="number" pattern="#,##0.000"
									value="${shares}" /></td>
							<%
								long price = -1;
									for (FundPriceHistoryBean fundprice : (FundPriceHistoryBean[]) request
											.getAttribute("priceList")) {

										if (fundprice.getFund_id() == pos.getFund_id())
											price = fundprice.getPrice() / 100;
									}
							%>
							<c:set var="price" value="<%=price%>" />
							<td><fmt:formatNumber type="number" pattern="#,##0.00"
									value="${price}" /></td>
							<c:set var="worth" value="<%=pos.getShares() * price / 1000%>" />
							<td><fmt:formatNumber type="number" pattern="#,##0.00"
									value="${worth}" /></td>
						</tr>
						<%
							total += pos.getShares() * price / 1000;
							}
						%>
						<tr>
							<td></td>
							<td></td>
							<td>Total Investment:</td>
							<c:set var="total" value="<%=total%>" />
							<td><fmt:formatNumber type="number" pattern="#,##0.00"
									value="${total}" /></td>
						<tr>
					</table>


				</div>
				<div role="tabpanel" class="tab-pane" id="profile">
					<div data-spy="scroll" data-offset="50"
						class="scrollspy-transactionHistory">

						<table class="table">
							<thead>
								<tr>
									<th width="25%">Date of Execution</th>
									<th width="15%">Operation</th>
									<th width="10%" class="title_right">FundID</th>
									<th width="15%" class="title_right">Shares</th>
									<th width="15%" class="title_right">Price</th>
									<th width="20%" class="title_right">Dollar Amount</th>

								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty transactionHistory}">
									<c:forEach items="${transactionHistory}" var="transactionBean">
										<c:if
											test="${transactionBean.getTransaction_type() == 'Buy Fund' }">
											<tr class="active">
												<td><fmt:formatDate type="both" pattern="yyyy-MM-dd"
														value="${transactionBean.getExecute_date()}" /></td>
												<td>${transactionBean.getTransaction_type()}</td>
												<td class="title_right">${transactionBean.getFund_id()}</td>
												<c:choose>
													<c:when test="${!empty transactionBean.getExecute_date()}">

														<c:set var="getShares"
															value="${transactionBean.getShares() / 1000 }" />
														<td class="title_right"><fmt:formatNumber
																type="number" pattern="#,##0.000" value="${getShares}" /></td>

														<c:set var="price" scope="session"
															value="${transactionBean.getAmount()/transactionBean.getShares()}" />
														<c:set var="priceShares"
															value="${transactionBean.getAmount()/transactionBean.getShares() * 10}" />
														<td class="title_right"><fmt:formatNumber
																type="number" pattern="#,##0.00" value="${priceShares}" /></td>
													</c:when>

													<c:otherwise>

														<td></td>
														<td></td>
													</c:otherwise>
												</c:choose>

												<c:set var="getAmt"
													value="${transactionBean.getAmount() / 100}" />
												<td class="title_right">$<fmt:formatNumber
														type="number" pattern="#,##0.00"
														value="${transactionBean.getAmount()}" /></td>
											</tr>
										</c:if>

										<c:if
											test="${transactionBean.getTransaction_type() == 'Sell Fund' }">
											<tr class="success">
												<td><fmt:formatDate type="both" pattern="yyyy-MM-dd"
														value="${transactionBean.getExecute_date()}" /></td>
												<td>${transactionBean.getTransaction_type()}</td>
												<td class="title_right">${transactionBean.getFund_id()}</td>
												<c:set var="getShares"
													value="${transactionBean.getShares() / 1000 }" />
												<td class="title_right"><fmt:formatNumber type="number"
														pattern="#,##0.000" value="${getShares}" /></td>
												<c:choose>
													<c:when test="${!empty transactionBean.getExecute_date()}">
														<c:set var="price" scope="session"
															value="${transactionBean.getAmount()/transactionBean.getShares()}" />
														<c:set var="priceShares"
															value="${transactionBean.getAmount()/transactionBean.getShares() * 10}" />
														<td class="title_right"><fmt:formatNumber
																type="number" pattern="#,##0.00" value="${priceShares}" /></td>
														<c:set var="getAmt"
															value="${transactionBean.getAmount() / 100}" />
														<td class="title_right">$<fmt:formatNumber
																type="number" pattern="#,##0.00"
																value="${transactionBean.getAmount()}" /></td>
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

												<td><fmt:formatDate type="both" pattern="yyyy-MM-dd"
														value="${transactionBean.getExecute_date()}" /></td>
												<td>${transactionBean.getTransaction_type()}</td>
												<td></td>
												<td></td>
												<td></td>
												<c:set var="getAmt"
													value="${transactionBean.getAmount() / 100}" />
												<td class="title_right">$<fmt:formatNumber
														type="number" pattern="#,##0.00"
														value="${transactionBean.getAmount()}" /></td>
											</tr>
										</c:if>
										<c:if
											test="${transactionBean.getTransaction_type() == 'Deposit Check' }">
											<tr class="warning">
												<td><fmt:formatDate type="both" pattern="yyyy-MM-dd"
														value="${transactionBean.getExecute_date()}" /></td>

												<td>${transactionBean.getTransaction_type()}</td>
												<td></td>
												<td></td>
												<td></td>
												<c:set var="getAmt"
													value="${transactionBean.getAmount() / 100}" />
												<td class="title_right">$<fmt:formatNumber
														type="number" pattern="#,##0.00"
														value="${transactionBean.getAmount()}" /></td>
											</tr>
										</c:if>

									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>


		</div>
	</div>


</div>
<jsp:include page="template-bottom.jsp" />