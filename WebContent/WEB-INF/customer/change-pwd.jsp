<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="databeans.FavoriteBean" %>

<jsp:include page="template-top.jsp" />
<div class="col-md-3"></div>
<div class="col-md-6">
    <div class="jumbotron">
        <form class="form-horizontal">
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-4 control-label">Old Password</label>
                <div class="col-sm-7">
                    <input type="email" class="form-control" id="old-password" placeholder="Old password">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-4 control-label" id="new-password">New Password</label>
                <div class="col-sm-7">
                    <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-4 control-label" id="confirm-password">Confirm Password</label>
                <div class="col-sm-7">
                    <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10"></div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-5">
                    <button type="submit" class="btn btn-primary btn-lg btn-block">Change</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="col-md-3"></div><jsp:include page="template-bottom.jsp" />
