<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="databeans.FavoriteBean" %>

<jsp:include page="template-top.jsp" />


<div class=" col-md-offset-2">
<div class="page-header">
		<h1>Transition Day</h1>
	</div>
	<jsp:include page="error-list.jsp" />
<jsp:include page="success.jsp" />
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
                  <c:forEach var = "fund" items = "${fundList}" varStatus="inner">
              	  	  <tr>
              	  	  	  <td>${fund.fund_id }</td>
              	  	  	  <td>${fund.name }</td>
              	  	  	  <td>${fund.symbol }</td>
              	  	  	  <td>${fund.description }</td>
              	  	  	  <td class="col-md-2">
              	  	  	  	<input type="hidden" name="id" value="${fund.fund_id}">
              	  	  	  	<input type="text" name="price" class="form-control" pattern="\d+(\.\d{1,2})?" 
              	  	  	  	placeholder="Price" title="Input should be......" required value="${form.price[inner.index] }">
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
	            	<input type="text" class="form-control col-md-2" name="date" pattern="\\d{4}-\\d{2}-\\d{2}" title="Please input the right date as yyyy-MM-dd and make sure it is later than last transition day" 
	            	placeholder="Transition Date"  required value=${form.date }>
            	</div>
            	<input type="submit" class="btn btn-primary" name="action" value="Start Transition Day!">
            </div>
            
		</form>

</div>
</div>
<jsp:include page="template-bottom.jsp" />
