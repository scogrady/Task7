<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="databeans.FavoriteBean" %>

<jsp:include page="template-top.jsp" />
<div class=" col-md-offset-2">
<div class="table-responsive">
		<form role="form" method="post" action="Transition.do">

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
              	  	  	  <td class="col-md-2">
              	  	  	  	<input type="number" name=${fund.fund_id } class="form-control" pattern="\d+(\.\d{1,2})?" 
              	  	  	  	placeholder="Price" data-placement="bottom"  min="0" max="100000000" title="Input should be......" required>
              	  	  	  	
              	  	  	  </td>
              	  	  </tr>	
				  </c:forEach>
              </tbody>
            </table>
            <div class="row">
            	<c:choose>
      				<c:when test="${lastDate == null}">.
      					<div class="col-md-4"><h4>No Transaction Before</h4></div>
      				</c:when>

      				<c:otherwise>
      					<div class="col-md-4"><h4>Last Transition Day: ${lastDate }</h4></div>
      				</c:otherwise>
				</c:choose>
				
            	
            	<div class="col-md-4">
	            	<input type="text" class="form-control col-md-2" name="date" placeholder="Transition Date" pattern="" required>
            	</div>
            	<input type="submit" class="btn btn-primary" name="action" value="Start Transition Day!">
            </div>
            
		</form>

</div>
</div>
<jsp:include page="template-bottom.jsp" />
