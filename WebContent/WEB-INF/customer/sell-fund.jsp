<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.FavoriteBean"%>

<jsp:include page="template-top.jsp" />


<div class="col-xs-12 col-sm-9">
	<jsp:include page="balance.jsp" />

	<div class="row-fluid">
		<div class="col-md-12">
			<div class="page-header">
				<h1>Your Fund</h1>

			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="30%">Fund Name</th>
						<th width="15%">You own</th>
						<th width="10%">Price</th>
						<th width="30%">Share</th>
						<th width="15%">Sell</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sellFundList}" var="fund">
				<form role="form" method="post" action="SellFund.do">
					<tr>
						<div class="form-group">
							<td>${fund.getName()}</td>
							<td>
								<div class="num">${fund.getShares()} shares</div>
							</td>
							<td id="change-p">$${fund.getPrice()}</td>
							<td>
								<div class="form-inline">
									<input type="text" class="form-control" id="InputAmount1"
										placeholder="100000000000" size="10" maxlength="12" name="num_1">.
									<input type="text" class="form-control" id="InputAmount2"
										placeholder="000" size="1" maxlength="3"  name="num_2"> shares

								</div>
							</td>
							<td>
							<input type="hidden" name="fund_id" value="${fund.getFund_id()}">
							<input type="hidden" name="shares" value="${fund.getShares()}">
							<input class="btn btn-default" type="submit"
								value="Sell" name="action"></td>
						</div>
					</tr>
					</form>
					</c:forEach>
					
				</tbody>
			</table>
		</div>
	</div>
</div>


<jsp:include page="template-bottom.jsp" />
