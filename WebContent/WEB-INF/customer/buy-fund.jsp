<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="databeans.CustomerBean"%>
<%@ page import="databeans.BuyFundBean"%>


<jsp:include page="template-top.jsp" />
<jsp:include page="balance.jsp" />
<jsp:include page="error-list.jsp" />


<div class="col-xs-12 col-sm-12">



	<div class="row-fluid">
		<div class="col-md-12">
			<div class="page-header">
				<h1>BUY FUND</h1>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="20%">Fund Name</th>
						<th width="10%">Ticker</th>
						<th width="8%">Price</th>
						<th width="8%">Change</th>
						<th width="8%">Chg%</th>
						<th width="30%">Amount</th>
						<th width="11%">Buy</th>
					</tr>
				</thead>

				<tbody>
					<c:if test="${!empty buyFundList}">

						<c:forEach items="${buyFundList}" var="fund">
							<form role="form" method="post" action="BuyFund.do">
								<tr>

									<td>${fund.getName()}</td>
									<td>${fund.getSymbol()}</td>
									<td><c:set var="getPrice" value="${fund.getPrice()/100}" />
										<div class="num">
											$
											<fmt:formatNumber type="number" pattern="#,##0.00"
												value="${getPrice}" />
										</div></td>
									<td><c:set var="getChange" value="${fund.getChange()/100}" />
										<p id="change-a">
											$
											<fmt:formatNumber type="number" pattern="#,##0.00"
												value="${getPrice}" />
										</p></td>
									<c:set var="getChgPer" value="${fund.getChgPer()}" />
									<td id="change-b"><fmt:formatNumber type="number"
											pattern="#,##0.00" value="${getChgPer}" />%</td>

									<td><c:choose>
											<c:when test="${fund.getFund_id() == form.getFund_id()}">
												<div class="form-inline">
													$ <input type="text" name="num" class="form-control"
														pattern="\d+(\.\d{1,2})?" placeholder="Amount"
														data-placement="bottom" min="0" max="100000000"
														value="${form.getNum()}"
														title="You can buy between $10 to your available cash. Only two digits after decimal."
														required>
												</div>

											</c:when>
											<c:otherwise>
												<div class="form-inline">
													$ <input type="text" name="num" class="form-control"
														pattern="\d+(\.\d{1,2})?" placeholder="Amount"
														data-placement="bottom" min="0" max="100000000"
														title="You can buy between $10 to your available cash. Only two digits after decimal."
														required>
												</div>
											</c:otherwise>
										</c:choose></td>
									<td><input type="hidden" name="fund_id"
										value="${fund.getFund_id()}"> <input type="hidden"
										name="avail_cash" value="${customer.getAvailable_cash()}"><input
										class="btn btn-default" type="submit" name="action"
										value="Buy"></td>


								</tr>
							</form>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>





<jsp:include page="template-bottom.jsp" />
