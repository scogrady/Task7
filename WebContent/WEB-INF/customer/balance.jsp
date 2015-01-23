<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row-fluid">
<c:if test="${!empty customer}">
        <div class="col-md-6">
            <div class="panel panel-info">
                <div class="panel-heading"><b>Available Balance</b>

                </div>
                <div class="panel-body">
                <c:set var="AvailBalance"	value="${customer.getAvailable_cash()/100}" />
                     <h4>$<fmt:formatNumber type="number" pattern="#,##0.00"	value="${AvailBalance}" /></h4>

                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="panel panel-success">
                <div class="panel-heading"><b>Current Balance</b>

                </div>
                <div class="panel-body">
                	<c:set var="CurrBalance"	value="${customer.getCurrent_cash()/100}" />
                     <h4>$<fmt:formatNumber type="number" pattern="#,##0.00"	value="${CurrBalance}" /></h4>

                </div>
            </div>
        </div>
        </c:if>
    </div>