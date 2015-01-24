<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="databeans.CustomerBean"%>
<%@ page import="databeans.PositionBean"%>
<%@ page import="databeans.FundBean"%>
<%@ page import="databeans.FundPriceHistoryBean"%>


<jsp:include page="template-top.jsp" />
<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Customer Account</h4>

			</div>
			<div class="panel-body">
				<%
            CustomerBean customer = (CustomerBean) session.getAttribute("customer");
			%>
				<table class="table">
					<tr >
						<th scope="row" width="25%">Name :</th>
						<td id="customer-name"><%=customer.getFirstname()%> <%=customer.getLastname()%></td>
					</tr>
					<tr>
						<th scope="row">Address :</th>
						<td id="customer-address-1"><%=customer.getAddr_line1()%></td>
					</tr>
					<tr><% if(customer.getAddr_line2()!=null){ %>
						<td></td>
						<td id="customer-address-2"><%=customer.getAddr_line2()%></td>
					</tr><%} %>
					<tr>
						<th scope="row">City,State,Zip:</th>
						<td id="customer-city"><%=customer.getCity()%>,<%=customer.getState()%>,<%=customer.getZip()%></td>
					</tr>
					<tr>
						<c:set var="currentBalance"	value="<%=customer.getCurrent_cash()/100%>" />
						<th scope="row">Account balance:</th>
						<td>$<fmt:formatNumber type="number" pattern="#,##0.00"	value="${currentBalance}" /></td>
					</tr>
					<tr>
						<c:set var="availableBalance" value="<%=customer.getAvailable_cash()/100%>" />
						<th scope="row">Available balance:</th>
						<td>$<fmt:formatNumber type="number" pattern="#,##0.00"	value="${availableBalance}" />
						</td>
						<td></td>
					</tr>
					<tr>
						<th scope="row">Last Trading Day:</th>
						<c:set var="lastTradingDay" value="<%=customer.getLast_login_time()%>" />
						<td id="last-trading-day"><fmt:formatDate value="${lastTradingDay}" pattern="MMM dd yyyy " /></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="col-md-3"></div>
</div>
<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-8">
		<h3>Portfolio</h3>

		<table class="table table-striped">
			<tr>
				<th>Fund Code</th>
				<th>Number of Shares</th>
				<th>Share Price</th>
				<th>Worth</th>
			</tr>
			<% 
             long total=0;
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
				<c:set var="shares" value="<%=pos.getShares() / 1000 %>" />
				<td><fmt:formatNumber type="number" pattern="#,##0.000"	value="${shares}" /></td>
				<%
            	long price=-1;
            	for (FundPriceHistoryBean fundprice : (FundPriceHistoryBean[])request.getAttribute("priceList")){
            		
            		if(fundprice.getFund_id()==pos.getFund_id())
            			price=fundprice.getPrice() / 100;
            	}
            		%>
            		<c:set var="price" value="<%=price %>" />
				<td><fmt:formatNumber type="number" pattern="#,##0.00" value="${price}" /></td>
				<c:set var="worth" value="<%=pos.getShares()*price / 1000 %>" />				
				<td><fmt:formatNumber type="number" pattern="#,##0.00" value="${worth}" /></td>
			</tr>
			<%
            total+=pos.getShares()*price  / 1000;
            }
             %>
			<tr>
				<td></td>
				<td></td>
				<td>Total Investment:</td>
				<c:set var="total" value="<%=total %>" />
				<td><fmt:formatNumber type="number" pattern="#,##0.00" value="${total}" /></td>
			<tr>
		</table>
		</div>
		<div class="col-md-2"></div>
	</div>

<jsp:include page="template-bottom.jsp" />
