<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="databeans.CustomerBean"%>

<%
	CustomerBean customer = (CustomerBean) session
			.getAttribute("customer");
%>

<jsp:include page="template-top.jsp" />

<jsp:include page="balance.jsp" />



<div class="col-xs-12 col-sm-9">

	<div class="page-header">
		<h1>Request Check</h1>
	</div>
	<jsp:include page="error-list.jsp" />
	<jsp:include page="success.jsp" />

	<div class="row-fluid">

		<div class="col-md-12">
			<form class="form-inline" method="post" action="RequestCheck.do">
				<div class="form-group">
					<label class="sr-only" for="InputAmount">Amount (in
						dollars)</label>
					<div class="input-group">
						<div class="input-group-addon">$</div>

						<input type="text" name="num" class="form-control"
							pattern="\d*(\.\d{1,2}0*)?" placeholder="Amount"
							value="${form.getNum()}"
							title="You can request between $0.01 to your available cash. Only two digits after decimal."
							required>
					</div>
					<input type="submit" class="btn btn-primary" name="action"
						value="Request Check">
				</div>
			</form>
			<br>
		</div>
	</div>
	
</div>

<jsp:include page="template-bottom.jsp" />
