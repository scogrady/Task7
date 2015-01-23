<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.CustomerBean"%>

<jsp:include page="template-top.jsp" />

<div class="container-fluid">
	<div class="row-fluid">

		<div class="span12">
			<form method="post">
				<fieldset>
					<legend>Deposit Check</legend>

					<label>Customer Username</label>
					<p>
						<input type="text" id="username" name="username"
							placeholder="Customer Username" class="form-control" />
					</p>
					<label>Deposit Amount</label>
					<p>
						<input type="text" id="amount" name="amount"
							placeholder="Deposit Amount" class="form-control" />
					</p>

					<p>
						<input class="btn btn-primary" type="submit" name="action"
							value="Deposit Check"></input>
					</p>
				</fieldset>
			</form>
		</div>

		<div class="row">
			<div class="col-xs-12 col-sm-9">
				<h3>Customer List.</h3>

				<table class="table">
					<thead>
						<tr>
							<th>Username</th>
							<th>Firstname</th>
							<th>Lastname</th>
							<th>Available Cash (long type need/100)</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="customer" items="${customerList}">
							<tr>
								<td>${customer.getUsername()}</td>
								<td>${customer.getFirstname()}</td>
								<td>${customer.getLastname()}</td>
								<td>${customer.getAvailable_cash()}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12 col-sm-9">
				<h3>Transaction List - for programming check.</h3>
				<table class="table">
					<thead>
						<tr>
							<th>Transaction ID</th>
							<th>Transaction Type</th>
							<th>Customer ID</th>
							<th>Transaction Amount</th>
							<th>Transaction Generate Date</th>
							<th>Transaction Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="transaction" items="${transactionList}">
							<tr>
								<td>${transaction.getTransaction_id()}</td>
								<td>${transaction.getTransaction_type()}</td>
								<td>${transaction.getCustomer_id()}</td>
								<td>${transaction.getAmount()}</td>
								<td>${transaction.getGenerate_date()}</td>
								<td>${transaction.getStatus()}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>


	</div>
</div>

<jsp:include page="template-bottom.jsp" />
