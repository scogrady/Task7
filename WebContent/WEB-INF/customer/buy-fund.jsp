<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.FavoriteBean"%>

<jsp:include page="template-top.jsp" />
<jsp:include page="balance.jsp" />


<div class="container-fluid">


	<div class="row-fluid">
		<div class="col-md-12">
			<div class="page-header">
				<h1>Our Fund</h1>

			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="25%">Fund Name</th>
						<th width="5%">Ticker</th>
						<th width="8%">Price</th>
						<th width="8%">Change</th>
						<th width="8%">Chg%</th>
						<th width="30%">Amount</th>
						<th width="11%">Buy</th>
					</tr>
				</thead>
				
				<tbody>
				
				<c:forEach items="${buyFundList}" var="fund">
					<tr>
						<div class="form-group">
							<td>${fund.getName()}</td>
							<td>${fund.getSymbol()}</td>
							<td>
								<div class="num">$${fund.getPrice()}</div>
							</td>
							<td>
								<p id="change-a">$${fund.getChange()}</p>
							</td>
							<td id="change-b">${fund.getChgPer()}%</td>
							<div class="input-group">
								<td>
									<div class="form-inline">
										$ <input type="text" class="form-control" id="InputAmount1"
											placeholder="1000000000000" size="11" maxlength="13" name="num_1">.
										<input type="text" class="form-control" id="InputAmount2"
											placeholder="00" size="1" maxlength="2  name="num_2"">
									</div>
								</td>
								<td><input type="hidden" name="fund_id" value="${fund.getFund_id()}"> <input
									class="btn btn-default" type="submit" value="Buy"></td>
							</div>
						</div>
					</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
</div>





<jsp:include page="template-bottom.jsp" />
