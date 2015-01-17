<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="databeans.FavoriteBean" %>

<jsp:include page="template-top.jsp" />
<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading">
             <h3 class="panel-title">Balance</h3>

        </div>
        <div class="panel-body">Available Balance:</div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <div class="page-header">
                 <h1>Your Fund</h1>

            </div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th width="30%">Fund Name</th>
                        <th width="15%">You own</th>
                        <th width="10%">Price</th>
                        <th width="30%">Share</th>
                        <th width="15%">Sell</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <div class="form-group width: 10%">
                            <td>SPARK NO.1</td>
                            <td>
                                <div class="num">500 shares</div>
                            </td>
                            <td id="change-p">$35.00</td>
                            <td>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="exampleInputAmount" placeholder="Amount">
                                    <div class="input-group-addon">shares</div>
                                </div>
                            </td>
                            <td>
                                <input class="btn btn-default" type="submit" value="Sell">
                            </td>
                        </div>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="template-bottom.jsp" />
