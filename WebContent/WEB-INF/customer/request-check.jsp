<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="databeans.CustomerBean"%>

<%
	CustomerBean customer = (CustomerBean) session
			.getAttribute("customer");
%>

<jsp:include page="template-top.jsp" />

<jsp:include page="balance.jsp" />
<jsp:include page="error-list.jsp" />


<div class="col-xs-12 col-sm-9">

<div class="page-header">
				<h1>Request Check</h1>
			</div>

	<div class="row-fluid">
	
		<div class="col-md-12">
			<form class="form-inline"  method="post" action="RequestCheck.do">
				<div class="form-group">
					<label class="sr-only" for="InputAmount">Amount (in
						dollars)</label>
					<div class="input-group">
						<div class="input-group-addon">$</div>
						<input type="text" class="form-control" id="exampleInputAmount"
							placeholder="Amount" name="num">
					</div>
					 <input type="hidden" name="avail_cash" value="${customer.getAvailable_cash()}">
					<input type="submit" class="btn btn-primary" value="Request Check">
				</div>
			</form>
			<br>
		</div>
	</div>
	<br />
	<div class="row-fluid">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Mail to:</h3>

				</div>
				<div class="panel-body"><%=customer.getAddr_line1()%></div>
				<div class="panel-body"><%=customer.getAddr_line2()%></div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="template-bottom.jsp" />
