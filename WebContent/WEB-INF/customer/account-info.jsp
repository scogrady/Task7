<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="databeans.CustomerBean" %>
<%@ page import="databeans.PositionBean" %>
<%@ page import="databeans.FundBean" %>
<%@ page import="databeans.FundPriceHistoryBean" %>



<jsp:include page="template-top.jsp" />
	<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                	<h1>Customer Account </h1>

            </div>
            <div class="panel-body">
            <%
            
            CustomerBean customer = (CustomerBean) session.getAttribute("customer");
        	
			%>
			   
				
            
                <table>
                    <tr>
                        <td>Name :</td>
                        <td id="customer-name"><%=customer.getFirstname()%> <%=customer.getLastname()%></td>
                    </tr>
                    <tr>
                        <td>Address :</td>
                        <td id="customer-address-1"><%=customer.getAddr_line1()%></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td id="customer-address-2"><%=customer.getAddr_line2()%></td>
                    </tr>
                    <tr>
                        <td>City,State,Zip:</td>
                        <td id="customer-city"><%=customer.getCity()%>,<%=customer.getState()%>,<%=customer.getZip()%></td>
                    </tr>
                    <tr>
                        <td>Account balance: </td>
                        <td id="customer-balance">$<%=customer.getCurrent_cash()/100%></td>
                    </tr>
                    <tr>
                        <td>Available balance: </td>
                        <td id="customer-balance">$<%=customer.getAvailable_cash()/100%></td>
                    </tr>
                    <tr>
                        <td>Last Trading Day</td>
                        <td id="last-trading-day"><%=customer.getLast_login_time()%></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="col-md-2"></div>
</div>
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
    
   			
            
           
        	<h3> Portfolio</h3>

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
                <td><%=pos.getShares() / 1000 %></td>
                <%
            	long price=-1;
            	for (FundPriceHistoryBean fundprice : (FundPriceHistoryBean[])request.getAttribute("priceList")){
            		
            		if(fundprice.getFund_id()==pos.getFund_id())
            			price=fundprice.getPrice() / 100;
            	}
            		%>
                <td><%=price %></td>
                <td><%=pos.getShares()*price / 1000 %></td>
            </tr>
            <%
            total+=pos.getShares()*price  / 1000;
            }
             %>
            
            
            <tr>
                <td></td>
                <td></td>
                <td>Total Investment:</td>
                <td><%=total %></td>
        </table>
        <div class="col-md-2"></div>
    </div>
</div>
<jsp:include page="template-bottom.jsp" />
