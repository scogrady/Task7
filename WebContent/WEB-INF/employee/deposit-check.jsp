<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.CustomerBean"%>
<%@ page import="databeans.BuyFundBean"%>


<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />

<div class="col-xs-12 col-sm-12">



	<div class="row-fluid">
		<div class="col-md-12">
			<div class="page-header">
				<h1>Deposit Check</h1>
			</div>
			<table class="table table-hover">

				<thead>
					<tr>
						<th width="15%">Username</th>
						<th width="15%">Firstname</th>
						<th width="15%">Lastname</th>
						<th width="15%">Available Cash</th>
						<th width="25%">Amount</th>
						<th width="15%">Deposit</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="customer" items="${customerList}">
						<form   role="form"  method="post" action="DepositCheck.do">
						<tr>
							<td>${customer.getUsername()}</td>
							<td>${customer.getFirstname()}</td>
							<td>${customer.getLastname()}</td>
							<td>${customer.getAvailable_cash()}</td>
							<div class="form-group">
							<td>
								<div class="form-inline">

									<input type="text" id="amount" class="form-control"
										name="amount" placeholder="100,000,000" size="15px">
								</div>
							</td>
							<td>
							<input type="hidden"
										name="username" value="${customer.getUsername()}">
							<input class="btn btn-primary" type="submit"
								name="action" value="Deposit"></td>
							</div>

						</tr>
						</form>
					</c:forEach>
				</tbody>

			</table>

		</div>
	</div>














</div>