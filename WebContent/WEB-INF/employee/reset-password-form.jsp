<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.CustomerBean"%>

<jsp:include page="template-top.jsp" />

<div class="col-md-3"></div>
<div class="col-md-6">
<%
CustomerBean customerClicked = (CustomerBean) request.getSession(false).getAttribute("customerClicked");
				%>
	<div class="page-header">
		<h2>Reset password for <%=customerClicked.getFirstname()%> <%=customerClicked.getLastname()%></h2>
	</div>
	<jsp:include page="error-list.jsp" />
	<jsp:include page="success.jsp" />
	
	

	<form class="form-horizontal" method="POST"
		action="ResetCustomerPwd.do">
		<input type="hidden" name="id" value="${id}">
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-4 control-label"
				id="new-password">New Password</label>
			<div class="col-sm-7">
				<input type="password" class="form-control" name="newPassword"
					placeholder="Password" required>
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-4 control-label"
				id="confirm-password">Confirm Password</label>
			<div class="col-sm-7">
				<input type="password" class="form-control" name="confirmPassword"
					placeholder="Confirm Password" required>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10"></div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-5 col-sm-5">
				<button type="submit" class="btn btn-primary ">Change</button>

			</div>
		</div>
	</form>


</div>
<div class="col-md-3"></div>



<jsp:include page="template-bottom.jsp" />