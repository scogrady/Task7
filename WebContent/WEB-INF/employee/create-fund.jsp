<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.FavoriteBean"%>

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
							placeholder="Fund Ticker" />
					</p>
					<label>Fund Name</label>
					<p>
						<input type="text" id="fund_name" name="name"
							placeholder="Fund Name" />
					</p>
					<label>Fund Description</label>
					<p>
						<input type="text" id="fund_description" name="description"
							placeholder="Fund Description" />
					</p>
					<p>
						<input class="btn btn-primary" type="submit" name="action"
							value="Create Mutual Fund"></input>
					</p>
				</fieldset>
			</form>
		</div>

		<div class="row">
			<div class="col-sm-6 col-md-4">
				<h3>Fund List.</h3>

				<ol>

					<c:forEach var="fund" items="${fundList}">
						<li>
							<h4>Fund name = ${fund.getName()}</h4>
							<p>Fund Symbol = ${fund.getSymbol()}</p>
							<p>Fund Description = ${fund.getDescription()}</p>
						</li>
					</c:forEach>

				</ol>
			</div>
		</div>


	</div>
</div>
<jsp:include page="template-bottom.jsp" />

