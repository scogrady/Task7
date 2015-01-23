<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="databeans.FavoriteBean"%>
<%@ page import="databeans.FundPriceHistoryBean"%>

<script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>

<jsp:include page="template-top.jsp" />
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="page-header">
				<h3>The most popular chooses.</h3>
			</div>
			<div class="row">

				<c:forEach var="recommandFund" items="${recommandFundList}">
					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<img data-src="holder.js/300x300" alt="...">
							<div class="caption">
								<h3>${recommandFund.getName()}-
									${recommandFund.getSymbol()}</h3>
								<p>${recommandFund.getDescription()}</p>
								<p>
									<a href="ResearchFund.do?fund_id=${recommandFund.getFund_id()}"
										class="btn btn-primary" role="button">View More</a> <a
										href="#" class="btn btn-default" role="button">Compare</a>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>


			<div class="row">
				<div class="col-sm-6 col-md-4">
					<h3>Mutual Funds.</h3>

					<ol>

						<c:forEach var="fund" items="${fundList}">
							<li>
								<h4>${fund.getName()}-${fund.getSymbol()}</h4>
								<p>${fund.getDescription()}</p>
								<p>
									<a href="ResearchFund.do?fund_id=${fund.getFund_id()}"
										class="btn">View More »</a>
								</p>
							</li>
						</c:forEach>

					</ol>
				</div>


				<div class="col-sm-6 col-md-8" id="detail-section">


					<h3>Detail Information of ${fundPriceHistoryName.getName()} -
						${fundPriceHistoryName.getSymbol()}</h3>
					<dl>
						<dt>${fundPriceHistoryName.getDescription()}</dt>
					</dl>
					<table class="table">
						<thead>
							<tr>
								<th width="25%">Date</th>
								<th width="20%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Price</th>
								<th width="50%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="fundPriceHistory" items="${fundPriceHistoryList}">
								<tr>
								<c:set var="getPrice_date" value="${fundPriceHistory.getPrice_date()}" />
									<td><fmt:formatDate value="${getPrice_date}" pattern="MMM dd yyyy " /></td>
									<c:set var="getPrice" value="${fundPriceHistory.getPrice()}" />
									<td align="right"><fmt:formatNumber type="number" pattern="#,##0.00"	value="${getPrice}" /></td>
									<td align="center">Up</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>



			<nav>
				<ul class="pagination">
					<li class="disabled"><a href="#" aria-label="Previous"><span
							aria-hidden="true">&laquo;</span></a></li>
					<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>

		</div>
	</div>
</div><jsp:include page="template-bottom.jsp" />