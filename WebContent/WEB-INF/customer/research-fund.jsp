<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.FundBean"%>
<%@ page import="databeans.FundPriceHistoryBean"%>

<jsp:include page="template-top.jsp" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript">
	$(function() {
		$('#containerChart')
				.highcharts(
						{
							chart : {
								type : 'areaspline'
							},
							title : {
								text : 'Fund Price History'
							},
							legend : {
								layout : 'vertical',
								align : 'left',
								verticalAlign : 'top',
								x : 150,
								y : 100,
								floating : true,
								borderWidth : 1,
								backgroundColor : (Highcharts.theme && Highcharts.theme.legendBackgroundColor)
										|| '#FFFFFF'
							},
							xAxis : {
								//TODO find the right type not categories but ???
								
								  type: 'datetime',
								  //
            					  title: {
						                    text: 'Date'
						                }
							},
							yAxis : {
								title : {
									text : 'Fund Price'
								}
							},
							tooltip : {
								shared : true,
								valueSuffix : ' $'
							},
							credits : {
								enabled : false
							},
							plotOptions : {
								areaspline : {
									fillOpacity : 0.5
								}
							},
							series : [ {
								name : 'Fund Price',
								data : [
								        <c:forEach var="fundPriceHistory" items="${fundPriceHistoryList}">
											${fundPriceHistory.getPrice()},
										</c:forEach>]
							} ]
					});
	});
</script>


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
								<th>Date</th>
								<th>Price</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="fundPriceHistory" items="${fundPriceHistoryList}">
								<tr>
									<td>${fundPriceHistory.getPrice_date()}</td>
									<td>${fundPriceHistory.getPrice()}</td>
									<td>Up</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>

		            <div id="containerChart" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
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
	
</div>
<jsp:include page="template-bottom.jsp" />