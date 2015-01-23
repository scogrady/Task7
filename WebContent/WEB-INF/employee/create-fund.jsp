
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.FundBean"%>

<jsp:include page="template-top.jsp" />

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<form method="post">
				<fieldset>
					<legend>Create Mutual Fund</legend>


					<label>Fund Ticker</label>
					<p>
						<input type="text" id="fund_symbol" name="symbol"
							placeholder="Fund Ticker" class="form-control" />
					</p>
					<label>Fund Name</label>
					<p>
						<input type="text" id="fund_name" name="name"
							placeholder="Fund Name" class="form-control" />
					</p>
					<label>Fund Description</label>
					<p>
						<input type="text" id="fund_description" name="description"
							placeholder="Fund Description" class="form-control" />
					</p>
					<p>
						<input class="btn btn-primary" type="submit" name="action"
							value="Create Mutual Fund"></input>
					</p>
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