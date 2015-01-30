<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="databeans.CustomerBean"%>
<%@ page import="databeans.PositionBean"%>
<%@ page import="databeans.FundBean"%>
<%@ page import="databeans.FundPriceHistoryBean"%>

<jsp:include page="template-top.jsp" />
<style type="text/css">
.scrollspy-customerList {
	height: 600px;
	width: 100%;
	overflow: auto;
	position: relative;
	overflow-y: scroll;
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
	<jsp:include page="success.jsp" />

</div>

<div class="row">
	<div class="col-md-3">
		<h3>Customer list</h3>

		<div style="overflow-y: auto" class="scrollspy-customerlist">
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
        <div class="col-md-7 col-md-offset-1 toppad" >
			<%
            	CustomerBean customer = (CustomerBean) request.getAttribute("customer");
			%>     
          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title">${customer.getFirstname() } ${customer.getLastname() }</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=100" class="img-circle"> </div>
                <div class=" col-md-9 col-lg-9 "> 
                  <table class="table table-user-information">
                    <tbody>
                    <tr>
                    	<td>Username</td>
                    	<td>${customer.getUsername() }</td>
                    </tr>
					<tr>
						<td>Password</td>
						<td id="customer-password">
							<div class="row">
								<p class="col-md-3">******</p>
								<a style="float:right" class="btn btn-sm btn-default" href="ResetCustomerPwd.do">Reset
									Password</a>
							</div>
						</td>
					</tr>
                      <tr>
                        <td>Address</td>
                        <td>${customer.getAddr_line1() }</td>
                      </tr>
                      <% if(customer.getAddr_line2() != null && !customer.getAddr_line2().equals("")){ %>
                      <tr>
						<td></td>
						<td id="customer-address-2"><%=customer.getAddr_line2()%></td>
					  </tr><% }%>
					 
					  <tr>
						 <td>City</td>
						 <td><%=customer.getCity()%></td>
					 </tr>
					 <tr>
					 	<td>State</td>
					 	<td><%=customer.getState()%></td>
					 </tr>
					 <tr>
					 	<td>Zip Code</td>
					 	<td><%=customer.getZip()%></td>
					 </tr>
                      
                     <tr>
						<c:set var="currentBalance"	value="${customer.getCurrent_cash()/100}"  />
						<td scope="row">Account Balance</td>
						<td>$<fmt:formatNumber type="number" pattern="#,##0.00"	value="${currentBalance}" /></td>
					</tr>
					
					<tr>
						<c:set var="availableBalance" value="${customer.getAvailable_cash()/100}"  />
						<td scope="row">Available Balance</td>
						<td>$<fmt:formatNumber type="number" pattern="#,##0.00"	value="${availableBalance}" />
						</td>
					</tr>
					
					<tr>
						<td width="40%">Deposit Money</td>
						<td>
								<form role="form" method="post" action="ViewAccount.do">
									<div class="input-group">
										<span class="input-group-addon" id="basic-addon1">$</span> <input
											type="hidden" name="username"
											value="${customer.getUsername()}"><input
											type="hidden" name="avail_cash"
											value="${customer.getAvailable_cash()}"> 
											<input
											type="text" name="amount" class="form-control input-sm"
											pattern="\d*(\.\d{1,2})?" placeholder="Amount"
											value="${form.getAmount()}"
											title="Only two digits after decimal are allowed." required>
										<span class="input-group-btn"> <input
											class="btn btn-sm btn-default" type="submit" name="action"
											value="Deposit">
										</span>
									</div>
								</form>
						</td>
					</tr>
				
					
					<tr>
						<td>Last Trading Day:</td>
						<c:set var="lastTradingDay" value="<%=customer.getLast_login_time()%>" />
						<td id="last-trading-day"><fmt:formatDate value="${lastTradingDay}" pattern="yyyy-MM-dd" /></td>
					</tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
	<div class="col-md-7 col-md-offset-1" id="customer-account-info">
		<div class="panel panel-primary">
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
							<th>Ticker</th>
							<th class="title_right">Number of Shares</th>
							<th class="title_right">Share Price</th>
							<th class="title_right">Worth</th>
						</tr>
						<%
							double total = 0;
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
							<c:set var="shares" value="<%=pos.getShares() / 1000.00%>" />
							<td class="title_right"><fmt:formatNumber type="number"
									pattern="#,##0.000" value="${shares}" /></td>
							<%
								double price = -1;
									for (FundPriceHistoryBean fundprice : (FundPriceHistoryBean[]) request
											.getAttribute("priceList")) {

										if (fundprice.getFund_id() == pos.getFund_id())
											price = fundprice.getPrice() / 100.00;
									}
							%>
							<c:set var="price" value="<%=price%>" />
							<td class="title_right"><fmt:formatNumber type="number"
									pattern="#,##0.00" value="${price}" /></td>
							<c:set var="worth" value="<%=pos.getShares() * price / 1000.00%>" />
							<td class="title_right"><fmt:formatNumber type="number"
									pattern="#,##0.00" value="${worth}" /></td>
						</tr>
						<%
							total += pos.getShares() * price / 1000.00;
							}
						%>
						<tr>
							<td></td>
							<td></td>
							<td class="title_right">Total Investment:</td>
							<c:set var="total" value="<%=total%>" />
							<td class="title_right"><fmt:formatNumber type="number"
									pattern="#,##0.00" value="${total}" /></td>
						<tr>
					</table>


				</div>
				<div role="tabpanel" class="tab-pane" id="profile">
					<div class="scrollspy-transactionHistory">
						<table class="table">

							<thead>
								<tr>
									<th width="25%">Date of Execution</th>
									<th width="15%">Operation</th>
									<th width="12%" class="title_right">Fund ID</th>
									<th width="15%" class="title_right">Shares</th>
									<th width="15%" class="title_right">Price</th>
									<th width="18%" class="title_right">Amount</th>

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

														<c:set var="priceShares"
															value="${transactionBean.getPrice() / 100 }" />
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
														type="number" pattern="#,##0.00" value="${getAmt}" /></td>

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
														<c:set var="priceShares"
															value="${transactionBean.getPrice() / 100 }" />
														<td class="title_right"><fmt:formatNumber
																type="number" pattern="#,##0.00" value="${priceShares}" /></td>

														<c:set var="getAmt"
															value="${transactionBean.getAmount() / 100}" />
														<td class="title_right">$<fmt:formatNumber
																type="number" pattern="#,##0.00" value="${getAmt}" /></td>
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
														type="number" pattern="#,##0.00" value="${getAmt}" /></td>


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
														type="number" pattern="#,##0.00" value="${getAmt}" /></td>

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