<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="databeans.FavoriteBean" %>

<jsp:include page="template-top.jsp" />
<div class="container-fluid">
    <div class="panel panel-info">
        <div class="panel-heading">Available Balance</div>
        <div class="panel-body">
             <h4>$5000</h4>

        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <div class="page-header">
                 <h1>Our Fund</h1>

            </div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th width="25%">Fund Name</th>
                        <th width="5%">Ticker</th>
                        <th width="8%">Price</th>
                        <th width="8%">Change1</th>
                        <th width="8%">Change2</th>
                        <th width="26%">Amount</th>
                        <th width="15%">Buy</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <div class="form-group width: 10%">
                            <td>SPARK NO.1</td>
                            <td>SPARK</td>
                            <td>
                                <div class="num">$35.00</div>
                            </td>
                            <td id="change-a">$0.15</td>
                            <td id="change-p">10%</td>
                            <td>
                                <div class="input-group">
                                    <div class="input-group-addon">$</div>
                                    <input type="text" class="form-control" id="exampleInputAmount" placeholder="Amount">
                                </div>
                            </td>
                            <td>
                                <input class="btn btn-default" type="submit" value="Buy">
                            </td>
                        </div>
                    </tr>
                    <tr>
                        <div class="form-group width: 10%">
                            <td>SPARK NO.1</td>
                            <td>SPARK</td>
                            <td>$35.00</td>
                            <td>$0.15</td>
                            <td>10%</td>
                            <td>
                                <div class="input-group">
                                    <div class="input-group-addon">$</div>
                                    <input type="text" class="form-control" id="exampleInputAmount" placeholder="Amount">
                                </div>
                            </td>
                            <td>
                                <input class="btn btn-default" type="submit" value="Buy">
                            </td>
                        </div>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="template-bottom.jsp" />
