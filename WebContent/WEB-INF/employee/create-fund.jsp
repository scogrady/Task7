<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.FavoriteBean"%>

<jsp:include page="template-top.jsp" />

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<form  method="post">
				<fieldset>
					<legend>Create Mutual Fund</legend>
					
					
					<label>Fund Ticker</label>
					<p>
						<input type="text" id="fund_symbol" name="symbol"/>
					</p>
					<label>Fund Name</label>
					<p>
						<input type="text" id="fund_name" name="name"/>
					</p>
					<label>Fund Description</label>
					<p>
						<input type="text" id="fund_description" name="description"/>
					</p>
					<p>
						<input class="btn btn-primary" type="submit" name="action" value="Create Mutual Fund"></input>
					</p>
				</fieldset>
			</form>
		</div>
	</div>
</div>
<jsp:include page="template-bottom.jsp" />

