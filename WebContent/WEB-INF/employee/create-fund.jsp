<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.FundBean"%>

<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<jsp:include page="success.jsp" />



<div class="row-fluid">
	<div class="col-md-3"></div>
	<div class="col-md-6">
		<div class="form-group">
			<form method="post">
				<fieldset>

					<div class="page-header">
						<h1>Create Mutual Fund</h1>
					</div>

					<div class="form-group">

						<label>Fund Ticker</label>
						<input type="text" id="fund_symbol" class="form-control" name="symbol" placeholder="Fund Ticker" value="${form.getSymbol()}" maxlength="30" required> 
						<label>Fund Name</label>
						<input type="text" id="fund_name" class="form-control" name="name" placeholder="Fund Name" value="${form.getName()}" maxlength="30" required>
					</div>

					<label>Fund Description</label>

					<p>
						<input type="text" id="fund_description" class="form-control"
							name="description" placeholder="Fund Description"  value="${form.getDescription()}">
					</p>
					<p>
						<input class="btn btn-primary" type="submit" name="action" value="Create">
					</p>
					<div class="col-md-3"></div>

				</fieldset>
			</form>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12 col-sm-9">

			<div class="page-header">
				<h3>Fund List</h3>
			</div>

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
<jsp:include page="template-bottom.jsp" />