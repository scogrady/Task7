<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="databeans.FavoriteBean" %>

<jsp:include page="template-top.jsp" />
<h1>Transaction History</h1>
<div class="col-md-7 col-md-offset-2"><br><br>
<h3>Welcome John Doe</h3><br>
<h5>Your Transaction History</h5>


<table class="table">
  <tr >
<td><h4><strong>Transaction Date</strong></h4></td>
<td><h4><strong>Operation</strong></h4></td>
<td><h4><strong>Fund Name</strong></h4></td>
<td><h4><strong>Number of shares</strong></h4></td>
<td><h4><strong>Share Price</strong></h4></td>
<td><h4><strong>Dollar Amount</strong></h4></td>
</tr>
<tr class="warning">
<td>12/1/2014</td>
<td>Deposit</td>
<td></td><td></td>
<td></td>
<td>200</td>


</tr>
<tr class="danger">
<td>12/25/2014</td>
<td>Bought</td>
<td>Mutual Fund 4</td>
<td>60</td>
<td>14</td>
<td>500</td>

</tr>
<tr class="warning">
<td>12/30/2014</td>
<td>Deposit</td>
<td></td>
<td></td>
<td></td>
<td>150</td>

</tr>

<tr>
<td>1/7/2014</td>
<td>Request Check</td>
<td></td>
<td></td>
<td></td>
<td>360</td></tr>
<tr class="success">
<td>1/8/2015</td>
<td>Sold</td>
<td>Mutual Fund 2</td>
<td>70</td>
<td>11</td>
<td>780</td>
</tr>

<tr class="success">
<td>1/15/2014</td>
<td>Sold</td>
<td>Mutual Fund 1</td>
<td>40</td>
<td>18</td>
<td>110</td>

</tr>
<tr class="danger">
<td>1/17/2014</td>
<td>Bought</td>
<td>Mutual Fund 3</td>
<td>40</td>
<td>16</td>
<td>600</td>


</tr>
<tr>
<td>1/20/2014</td>
<td>Request check</td>
<td></td>
<td></td>
<td></td>
<td>675</td>


</tr>
<tr class="warning">
<td>1/22/2014</td>
<td>Deposit</td>
<td></td>
<td></td>
<td></td>
<td>445</td>

</tr>
<tr class="success">
<td>1/23/2014</td>
<td>Sold</td>
<td>Mutual Fund 1</td>
<td>40</td>
<td>16</td>
<td>600</td>

</tr>
<tr>
<td>1/20/2014</td>
<td>Request check</td>
<td></td>
<td></td>
<td></td>
<td>675</td>

</tr>

<tr class="warning">
<td>1/22/2014</td>
<td>Deposit</td>
<td></td>
<td></td>
<td></td>
<td>445</td>

</tr>

</table>
</div>





<jsp:include page="template-bottom.jsp" />
