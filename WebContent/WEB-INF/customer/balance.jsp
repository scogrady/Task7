<div class="row-fluid">
<c:if test="${!empty customer}">
        <div class="col-md-6">
            <div class="panel panel-info">
                <div class="panel-heading"><b>Available Balance</b>

                </div>
                <div class="panel-body">
                     <h4>${customer.getAvailable_cash()}</h4>

                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="panel panel-success">
                <div class="panel-heading"><b>Current Balance</b>

                </div>
                <div class="panel-body">
                     <h4>${customer.getCurrent_cash()}</h4>

                </div>
            </div>
        </div>
        </c:if>
    </div>