<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="databeans.CustomerBean"%>

<%
	CustomerBean customer = (CustomerBean) session
			.getAttribute("customer");
%>

<jsp:include page="template-top.jsp" />
<h1>Request Check</h1>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="col-md-6">
			<div class="panel panel-info">
				<div class="panel-heading">
					<b>Available Balance</b>

				</div>
				<div class="panel-body">
					<h4>$5000</h4>

				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="panel panel-success">
				<div class="panel-heading">
					<b>Current Balance</b>

				</div>
				<div class="panel-body">
					<h4>$5000</h4>

				</div>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<form class="form-inline">
				<div class="form-group">
					<label class="sr-only" for="InputAmount">Amount (in
						dollars)</label>
					<div class="input-group">
						<div class="input-group-addon">$</div>
						<input type="text" class="form-control" id="exampleInputAmount"
							placeholder="Amount">
					</div>
					<input type="submit" class="btn btn-primary" value="Request Check">
				</div>
			</form>
			<br>
		</div>
		<div class="col-md-3"></div>
	</div>
	<br />
	<div class="row-fluid">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Address</h3>

				</div>
				<div class="panel-body"><%=customer.getAddr_line1()%></div>
				<div class="panel-body"><%=customer.getAddr_line2()%></div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="template-bottom.jsp" />
