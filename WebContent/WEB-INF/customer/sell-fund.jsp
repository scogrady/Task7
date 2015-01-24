<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="databeans.FavoriteBean"%>

<jsp:include page="template-top.jsp" />
<jsp:include page="balance.jsp" />
<jsp:include page="error-list.jsp" />

<div class="col-xs-12 col-sm-12">

	<div class="row-fluid">
		<div class="col-md-12">
			<div class="page-header">
				<h1>SELL FUND</h1>

			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="30%">Fund Name</th>
						<th width="15%">Share</th>
						<th width="10%">Price</th>
						<th width="30%">Sell Share</th>
						<th width="15%">Sell</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty sellFundList}">
						<c:forEach items="${sellFundList}" var="fund">
							<form role="form" method="post" action="SellFund.do">
								<tr>
									<div class="form-group">
										<td>${fund.getName()}</td>
										<td><c:set var="getShare"
												value="${fund.getShares() / 1000}" />
											<div class="num">
												<fmt:formatNumber type="number" pattern="#,##0.000"
													value="${getShare}" />
											</div></td>
										<c:set var="getPrice" value="${fund.getPrice() / 100}" />
										<td id="change-p">$<fmt:formatNumber type="number"
												pattern="#,##0.00" value="${getPrice}" /></td>
										<td>
										<c:choose>
												<c:when test="${fund.getFund_id() == form.getFund_id()}">
													<div class="form-inline">
														<input type="text" name="num" class="form-control"
															pattern="\d+(\.\d{1,3})?" placeholder="Share"
															data-placement="bottom" min="0" max="100000000"
															value="${form.getNum()}"
															title="You can sell between 0.001 share to what you have now. Only three digits after decimal."
															required>

													</div>
												</c:when>
												<c:otherwise>
													<div class="form-inline">
														<input type="text" name="num" class="form-control"
															pattern="\d+(\.\d{1,3})?" placeholder="Share"
															data-placement="bottom" min="0" max="100000000"
															title="You can sell between 0.001 share to what you have now. Only three digits after decimal."
															required>

													</div>
												</c:otherwise>
											</c:choose></td>
									<td><input type="hidden" name="fund_id"
										value="${fund.getFund_id()}"> <input type="hidden"
										name="shares" value="${fund.getShares()}"> <input
										class="btn btn-default" type="submit" value="Sell"
										name="action"></td>
									</div>
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
