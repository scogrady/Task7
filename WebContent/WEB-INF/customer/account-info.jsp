<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="databeans.CustomerBean"%>
<%@ page import="databeans.PositionBean"%>
<%@ page import="databeans.FundBean"%>
<%@ page import="databeans.FundPriceHistoryBean"%>


<jsp:include page="template-top.jsp" />
     <div class="row">
      <div class="col-md-5  toppad  pull-right col-md-offset-3 ">
      </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >
			<%
            	CustomerBean customer = (CustomerBean) session.getAttribute("customer");
			%>     
          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title">${customer.getFirstname() } ${customer.getLastname() }</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=100" class="img-circle"> </div>
                <div class=" col-md-9 col-lg-9 "> 
                  <table class="table table-user-information">
                    <tbody>
                      <tr>
                        <td>Address:</td>
                        <td>${customer.getAddr_line1() }</td>
                      </tr>
                      
                      <% if(customer.getAddr_line2() != null && !customer.getAddr_line2().equals("")){ %>
                      <tr>
						<td></td>
						<td id="customer-address-2"><%=customer.getAddr_line2()%></td>
					  </tr><% }%>
					 
					  <tr>
						 <td>City</td>
						 <td><%=customer.getCity()%></td>
					 </tr>
					 <tr>
					 	<td>State</td>
					 	<td><%=customer.getState()%></td>
					 </tr>
					 <tr>
					 	<td>Zip Code</td>
					 	<td><%=customer.getZip()%></td>
					 </tr>
                      
                     <tr>
						<c:set var="currentBalance"	value="${customer.getCurrent_cash()/100}"  />
						<td scope="row">Account balance:</td>
						<td>$<fmt:formatNumber type="number" pattern="#,##0.00"	value="${currentBalance}" /></td>
					</tr>
					
					<tr>
						<c:set var="availableBalance" value="${customer.getAvailable_cash()/100}"  />
						<td scope="row">Available balance:</td>
						<td>$<fmt:formatNumber type="number" pattern="#,##0.00"	value="${availableBalance}" />
						</td>
					</tr>
					
					<tr>
						<td>Last Trading Day:</td>
						<c:set var="lastTradingDay" value="<%=customer.getLast_login_time()%>" />
						<td id="last-trading-day"><fmt:formatDate value="${lastTradingDay}" pattern="yyyy-MM-dd" /></td>
					</tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-8">
		<h3>Portfolio</h3>

		<table class="table table-striped">
			<tr>
				<th>Fund Code</th>
				<th class="title_right">Shares</th>
				<th class="title_right">Price</th>
				<th class="title_right">Amount</th>
			</tr>
			<% 
             double total=0;
            for (PositionBean pos : (PositionBean[])request.getAttribute("position")){
            	%>
			<tr>
				<%
            	String symbol="";
            	for (FundBean fun : (FundBean[])request.getAttribute("fundTicker")){
            		if(fun.getFund_id()==pos.getFund_id())
            			symbol=fun.getSymbol();
            	}
            		%>
				<td><%=symbol%></td>
				<c:set var="shares" value="<%=pos.getShares() / 1000.0 %>" />
				<td  class="title_right"><fmt:formatNumber type="number" pattern="#,##0.000"	value="${shares}" /></td>
				<%
            	double price=-1;
            	for (FundPriceHistoryBean fundprice : (FundPriceHistoryBean[])request.getAttribute("priceList")){
            		
            		if(fundprice.getFund_id()==pos.getFund_id())
            			price=fundprice.getPrice() / 100.00;
            	}
            		%>
            		<c:set var="price" value="<%=price %>" />
				<td  class="title_right">$<fmt:formatNumber type="number" pattern="#,##0.00" value="${price}" /></td>
				<c:set var="worth" value="<%=pos.getShares()*price / 1000 %>" />				
				<td  class="title_right">$<fmt:formatNumber type="number" pattern="#,##0.00" value="${worth}" /></td>
			</tr>
			<%
            total+=pos.getShares()*price  / 1000.00;
            }
             %>
			<tr>
				<td></td>
				<td></td>
				<td  class="title_right">Total Investment:</td>
				<c:set var="total" value="<%=total %>" />
				<td  class="title_right">$<fmt:formatNumber type="number" pattern="#,##0.00" value="${total}" /></td>
			<tr>
		</table>
		</div>
		<div class="col-md-2"></div>
	</div>

<jsp:include page="template-bottom.jsp" />
