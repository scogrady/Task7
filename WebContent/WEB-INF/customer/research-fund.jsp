<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="databeans.FavoriteBean"%>
<%@ page import="databeans.FundPriceHistoryBean"%>

<jsp:include page="template-top.jsp" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?libraries=geometry&sensor=false"></script>
<style type="text/css">
.scrollspy-fundlist {
	height: 460px;
	width: 100%;
	overflow: auto;
	position: relative;
	overflow-y: scroll;
}

.scrollspy-chart {
	height: 388px;
	width: 100%;
	overflow: auto;
	position: relative;
}

.texthead {
	height: 30px;
	overflow: hidden;
	text-overflow: ellipsis;
}

.texthidden {
	height: 80px;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="page-header">
				<h3>Show on the Map.</h3>
			</div>

			<div class="row">
				<div class="col-lg-6">
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Search for..."> <span
							class="input-group-btn">
							<button class="btn btn-default" type="button">Go!</button>
						</span>
					</div>
					<!-- /input-group -->
				</div>
				<!-- /.col-lg-6 -->
			</div>

			<div class="col-sm-6 col-md-9" id="detail-section">
				<div role="tabpanel">

					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#home"
							aria-controls="home" role="tab" data-toggle="tab">Map</a></li>
						<li role="presentation"><a href="#profile"
							aria-controls="profile" role="tab" data-toggle="tab">Info</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="home">
							<div id="mymap" style="width: 500px; height: 500px;"></div>
						</div>
						<div role="tabpanel" class="tab-pane" id="profile">
							<div data-spy="scroll" data-offset="50" class="scrollspy-chart">
								<table class="table">
									<thead>
										<tr>
											<th width="20%">User ID</th>
											<th width="40%">Location</th>
										</tr>
									</thead>
									<tbody>

										<tr>
											<td>userid</td>
											<td>Location</td>
										</tr>

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