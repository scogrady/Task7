<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="databeans.FavoriteBean"%>

<jsp:include page="template-top.jsp" />


<div class=" col-md-offset-2">
	<div class="page-header">
		<h1>Transition Day</h1>
		<p>Fund Price should be between $5.00 and $10,000.00</p>
	</div>
	<jsp:include page="error-list.jsp" />
	<jsp:include page="success.jsp" />
	<div class="table-responsive">
		<form role="form" method="post" action="Transition.do">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="5%">#</th>
						<th width="20%">Fund Name</th>
						<th width="10%">Fund Ticker</th>
						<th width="10%" class="title_right">Last Price</th>
						<th width="25%" class="title_center">Price</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="fund" items="${buyFundList}" varStatus="inner">
						<tr>
							<td>${fund.fund_id }</td>
							<td>${fund.name }</td>
							<td>${fund.symbol }</td>
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
							<td class="title_center"><input type="hidden" name="id"
								value="${fund.fund_id}">
								<div class="form-inline">
									<c:choose>
										<c:when test="${form.price[inner.index] > 0 }">
											<c:set var="price" value="${form.getFormatPrice()[inner.index] }">
											</c:set>
										</c:when>
										<c:otherwise>
											<c:set var="price" value="">
											</c:set>
										</c:otherwise>
									</c:choose>
									<input type="text" name="price" class="form-control"
										pattern="\d+(\.\d{1,2})?" placeholder="Price"
										title="Fund Price should be between $5.00 and $10,000.00"
										required value="${price }">
								</div></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="row">
				<c:choose>
					<c:when test="${lastDate == null}">.
      					<div class="col-md-4">
							<h4>No Transaction Before</h4>
						</div>
					</c:when>
					<c:otherwise>
						<div class="col-md-4">
							<h4>Last Transition Day: ${lastDate }</h4>
						</div>
					</c:otherwise>
				</c:choose>


				<div class="col-md-4">
					<input type="text" class="form-control col-md-2" name="date"
						pattern="\d{4}-\d{2}-\d{2}"
						title="Please input the right date as yyyy-MM-dd and make sure it is later than last transition day"
						placeholder="Transition Date (yyyy-MM-dd)" required value=${form.date }>
				</div>
				<input type="submit" class="btn btn-primary" name="action"
					value="Start Transition Day!">
			</div>


		</form>

	</div>
</div>
<jsp:include page="template-bottom.jsp" />
