<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="databeans.FavoriteBean"%>

<jsp:include page="template-top.jsp" />


<div class="col-xs-12 col-sm-12">

	<div class="row-fluid">
		<div class="col-md-12">
			<div class="page-header">
				<h1>Search on Twitter</h1>
			</div>

			<div class="row">
				<div class="col-lg-6">
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Search for..."> <span
							class="input-group-btn">
							<button class="btn btn-default" type="button">Go!</button>
						</span>
					</div>
					<!-- /input-group -->
				</div>
				<!-- /.col-lg-6 -->
			</div>

			<jsp:include page="error-list.jsp" />
			<jsp:include page="success.jsp" />
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Twitter User ID</th>
						<th>Twitter Comment</th>
					</tr>
				</thead>
				<tbody>
					<!--<c:if test="${!empty sellFundList}">-->
					<form role="form" method="post" action="SellFund.do">
						<tr>
							<div class="form-group">
								<td>User ID</td>
								<td>Comments</td>
							</div>
						</tr>
					</form>
					<!--</c:if>-->
				</tbody>
			</table>
		</div>
	</div>
</div>


<jsp:include page="template-bottom.jsp" />
