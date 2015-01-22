<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="databeans.FavoriteBean" %>

<jsp:include page="template-top.jsp" />
<div class="col-md-10 col-md-offset-2">
<div class="table-responsive">
		<form role="form" method="post" action="login.do">

            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Fund Name</th>
                  <th>Fund Ticker</th>
                  <th>Fund Description</th>
                  <th>Price</th>
                </tr>
              </thead>
              <tbody>
                  <c:forEach var = "fund" items = "${fundList}">
              	  	  <tr>
              	  	  	  <td>${fund.fund_id }</td>
              	  	  	  <td>${fund.name }</td>
              	  	  	  <td>${fund.symbol }</td>
              	  	  	  <td>${fund.description }</td>
              	  	  	  <td class="col-md-2"><input type="text" name=${fund.fund_id } class="form-control" placeholder="Price"></td>
              	  	  </tr>	
				  </c:forEach>
              </tbody>
            </table>
            <input type="text" class="form-control" name="date" placeholder="Transition Date">
            <input type="submit" class="btn btn-primary" name="action" value="Start Transition Day!">
		</form>

</div>
</div>
<jsp:include page="template-bottom.jsp" />
