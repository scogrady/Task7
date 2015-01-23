<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.FundBean"%>

<jsp:include page="template-top.jsp" />

<div class="row-fluid">
	<div class="form-group">
		<div class="span12">
			<form method="post">
				<fieldset>

					<div class="page-header">
						<h1>Create Mutual Fund</h1>

					</div>
					<div class="form-inline">
						<div class="form-group">

						<label>Fund Ticker</label>
						<p>
							<input type="text" id="fund_symbol" class="form-control"
								name="symbol" placeholder="Fund Ticker">
						</p>
						<label>Fund Name</label>
						<p>
							<input type="text" id="fund_name" class="form-control"
								name="name" placeholder="Fund Name">
					</div>
</div>
	<div class="form-inline">
					<label>Fund Description</label>
							
						<p>
								<input type="text" id="fund_description" class="form-control"
								name="description" placeholder="Fund Description" />
							</p>
							<p>
								<input class="btn btn-primary" type="submit" name="action"
								value="Create"></input>
							</p>
		</div>

		</fieldset>
	</form>
</div>

<div class="row">
	<div class="col-xs-12 col-sm-9">
		<h3>Fund List.</h3>
		<table class="table">
			<thead>
				<tr>
					<th>Fund ID</th>
					<th>Fund name</th>
					<th>Fund Ticker</th>
					<th>Fund Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="fund" items="${fundList}">
					<tr>
						<td>${fund.getFund_id()}</td>
						<td>${fund.getName()}</td>
						<td>${fund.getSymbol()}</td>
						<td>${fund.getDescription()}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>


</div>
</div>
<jsp:include page="template-bottom.jsp" />