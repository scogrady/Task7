<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="databeans.CustomerBean"%>
<%@ page import="databeans.BuyFundBean"%>


<jsp:include page="template-top.jsp" />
<jsp:include page="balance.jsp" />


<div class="col-xs-12 col-sm-12">

	<!-- 新建股票的价格显示 -->

	<div class="row-fluid">
		<div class="col-md-12">
			<div class="page-header">
				<h1>BUY FUND</h1>
			</div>
			<jsp:include page="error-list.jsp" />
			<jsp:include page="success.jsp" />
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="20%">Fund Name</th>
						<th width="10%">Ticker</th>
						<th width="10%" class="title_right">Price</th>
						<th width="30%" class="title_center">Amount</th>
						<th width="11%" class="title_center">Buy</th>
					</tr>
				</thead>

				<tbody>
					<c:if test="${!empty buyFundList}">

						<c:forEach items="${buyFundList}" var="fund">
							<form role="form" method="post" action="BuyFund.do">
								<tr>

									<td>${fund.getName()}</td>
									<td>${fund.getSymbol()}</td>
									<c:choose>

										<c:when test="${fund.getPrice() == -1}">
											<td></td>
										

										</c:when>
										<c:otherwise>
											<td align="right"><c:set var="getPrice"
													value="${fund.getPrice()/100}" />
												<div class="num">
													$
													<fmt:formatNumber type="number" pattern="#,##0.00"
														value="${getPrice}" />
												</div></td>
											
										</c:otherwise>
									</c:choose>
									<td align="center"><c:choose>
											<c:when test="${fund.getFund_id() == form.getFund_id()}">
												<div class="form-inline">
													$ <input type="text" name="num" class="form-control"
														pattern="\d*(\.\d{1,2})?" placeholder="Amount"
														data-placement="bottom" min="0" max="100000000"
														value="${form.getNum()}"
														title="You can buy between $10 to your available cash. Only two digits after decimal."
														required>
												</div>

											</c:when>
											<c:otherwise>
												<div class="form-inline">
													$ <input type="text" name="num" class="form-control"
														pattern="\d*(\.\d{1,2})?" placeholder="Amount"
														data-placement="bottom" min="0" max="100000000"
														title="You can buy between $10 to your available cash. Only two digits after decimal."
														required>
												</div>
											</c:otherwise>
										</c:choose></td>
									<td class="title_center"><input type="hidden" name="fund_id"
										value="${fund.getFund_id()}"> <input type="hidden"
										name="avail_cash" value="${customer.getAvailable_cash()}"><input
										class="btn btn-primary" type="submit" name="action"
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
