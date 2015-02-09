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
		<h3>Tweets History</h3>
		<table class="table table-striped">
			<tr>
				<th>Time</th>
				<th>Location</th>
				<th>Comment</th>
				<th>Number</th>
			</tr>

			<tr>
				
				<td>Time</td>
				<td>Location</td>
				<td>Comment</td>
				<td>Number</td>
			</tr>


		</table>
		</div>
		<div class="col-md-2"></div>
	</div>

<jsp:include page="template-bottom.jsp" />
