<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="databeans.CustomerBean"%>

<%
	CustomerBean customer = (CustomerBean) session
			.getAttribute("customer");
%>

<jsp:include page="template-top.jsp" />

<jsp:include page="balance.jsp" />


<div class="col-xs-12 col-sm-9">

<h1>Request Check</h1>

	
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
							placeholder="Amount" name="num">
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
