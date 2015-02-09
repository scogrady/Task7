<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="databeans.CustomerBean"%>

<jsp:include page="template-top.jsp" />


<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="page-header">
				<h3>Analysis.</h3>
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
			<div class="row">
				<div class="col-sm-6 col-md-9" id="detail-section">
					<div role="tabpanel">

						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#home"
								aria-controls="home" role="tab" data-toggle="tab">Graphic</a></li>
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
												<th width="40%">Times</th>
											</tr>
										</thead>
										<tbody>

											<tr>
												<td>Userid</td>
												<td>Times</td>
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
</div>

<jsp:include page="template-bottom.jsp" />
