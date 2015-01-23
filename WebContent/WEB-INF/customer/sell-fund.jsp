<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.FavoriteBean"%>

<jsp:include page="template-top.jsp" />
<jsp:include page="balance.jsp" />
<jsp:include page="error-list.jsp" />

<div class="col-xs-12 col-sm-12">

	<div class="row-fluid">
		<div class="col-md-12">
			<div class="page-header">
				<h1>Your Fund</h1>

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
										<td>
											<div class="num">${fund.getShares() / 1000}</div>
										</td>
										<td id="change-p">$${fund.getPrice() / 100}</td>
										<td>
											<div class="form-inline">
												<input type="text" class="form-control" id="InputAmount1"
													placeholder="100,000,000" size="13px" maxlength="9"
													name="num_1">. <input type="text"
													class="form-control" id="InputAmount2" placeholder="000"
													size="3px" maxlength="3" name="num_2"> shares

											</div>
										</td>
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
