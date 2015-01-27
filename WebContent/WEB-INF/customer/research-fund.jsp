<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="databeans.FavoriteBean"%>
<%@ page import="databeans.FundPriceHistoryBean"%>

<jsp:include page="template-top.jsp" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<style type="text/css">
.scrollspy-fundlist {
	height: 460px;
	width: 100%;
	overflow: auto;
	position: relative;
}

.scrollspy-chart {
	height: 388px;
	width: 100%;
	overflow: auto;
	position: relative;
}
</style>
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
            					  title: {
						                    text: 'Time'
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
							series : [
							    {
									name : "${fundPriceHistoryName.getSymbol()}",
									data : [
								        <c:forEach var="fundPriceHistory" items="${fundPriceHistoryList}">
											${fundPriceHistory.getPrice()/100},
										</c:forEach>]
							    },
							    
							    <c:if test="${!empty comparePriceHistoryList}">
							    {
							    	name : "${comparePriceHistoryName.getSymbol()}",
									data : [
								        <c:forEach var="comparePriceHistory" items="${comparePriceHistoryList}">
											${comparePriceHistory.getPrice()/100},
										</c:forEach>]
							    }</c:if>
							]
					});
	});
	
	
	$('#myTab a').click(function (e) {
		  e.preventDefault()
		  $(this).tab('show')
		})
</script>


<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="page-header">
				<h3>The most popular choices.</h3>
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
										href="ResearchFund.do?fund_id=${now_id}&compare_id=${recommandFund.getFund_id()}" class="btn btn-default" role="button">Compare</a>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>


			<div class="row">
				<div class="col-sm-6 col-md-3">
					<h3>Mutual Funds.</h3>

					<div data-spy="scroll" data-offset="50" class="scrollspy-fundlist">
						<ul class="list-group">
							<c:forEach var="fund" items="${fundList}">
								<li class="list-group-item"><a href="ResearchFund.do?fund_id=${fund.getFund_id()}">${fund.getName()}-${fund.getSymbol()}</a>
									<p>${fund.getDescription()}</p></li>
							</c:forEach>

						</ul>
					</div>
				</div>

				<div class="col-sm-6 col-md-9" id="detail-section">
				   <c:if test="${empty comparePriceHistoryList}">
					<h3>Detail Information of ${fundPriceHistoryName.getName()} -
						${fundPriceHistoryName.getSymbol()}</h3>
					<dl>
						<dt>${fundPriceHistoryName.getDescription()}</dt>
					</dl>
					</c:if>
					
					<c:if test="${!empty comparePriceHistoryList}">
					<h3>Compare ${fundPriceHistoryName.getSymbol()} v.s. ${comparePriceHistoryName.getSymbol()}</h3>
					<dl>
						<dt>${fundPriceHistoryName.getSymbol()}: ${fundPriceHistoryName.getDescription()}</dt>
						<dt>${comparePriceHistoryName.getSymbol()}: ${comparePriceHistoryName.getDescription()}</dt>
					</dl>
					</c:if>
					
					
					<div role="tabpanel">

						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#home"
								aria-controls="home" role="tab" data-toggle="tab">Graphic</a></li>
							<li role="presentation"><a href="#profile"
								aria-controls="profile" role="tab" data-toggle="tab">Chart</a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="home">
								<div id="containerChart"
									style="min-width: 310px; height: 400px; margin: 0 auto"></div>
							</div>
							<div role="tabpanel" class="tab-pane" id="profile">

								<div data-spy="scroll" data-offset="50" class="scrollspy-chart">

									
									<table class="table">
										<thead>
											<tr>
												<th>Date</th>
												<th>Price</th>
												<th>Status</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="fundPriceHistory"
												items="${fundPriceHistoryList}">
												<tr>
													<td>${fundPriceHistory.getPrice_date()}</td>
													<td class="title_right">$<fmt:formatNumber type="number" pattern="#,##0.00" value="${fundPriceHistory.getPrice()/100}" /></td>
													
													<td>Up</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>

								</div>
							</div>
						</div>

					</div>

				</div>
			</div>

		</div>

	</div>
	<jsp:include page="template-bottom.jsp" />