<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="databeans.CustomerBean" %>

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
							placeholder="Customer Username" />
					</p>
					<label>Deposit Amount</label>
					<p>
						<input type="text" id="amount" name="amount"
							placeholder="Deposit Amount" />
					</p>
					
					<p>
						<input class="btn btn-primary" type="submit" name="action"
							value="Deposit Check"></input>
					</p>
				</fieldset>
			</form>
		</div>

		<div class="row">
			<div class="col-sm-6 col-md-4">
				<h3>Customer List.</h3>

				<ol>

					<c:forEach var="customer" items="${customerList}">
						<li>
							<h4>username = ${customer.getUsername()}</h4>
							<p>Firstname = ${customer.getFirstname()}</p>
							<p>Lastname = ${customer.getLastname()}</p>
							<p>Available Cash = ${customer.getAvailable_cash()}</p>

						</li>
					</c:forEach>

				</ol>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-6 col-md-4">
				<h3>Transaction List - for programming check.</h3>

				<ol>

					<c:forEach var="transaction" items="${transactionList}">
						<li>
							<h4>ID = ${transaction.getTransaction_id()}</h4>
							<p>amount = ${transaction.getAmount()}</p>
							<p>customer id = ${transaction.getCustomer_id()}</p>
							<p>generate date = ${transaction.getGenerate_date()}</p>
							<p>transaction type = ${transaction.getTransaction_type()}</p>

						</li>
					</c:forEach>

				</ol>
			</div>
		</div>
		
	</div>
</div>

<jsp:include page="template-bottom.jsp" />
