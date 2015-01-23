
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-top.jsp" />
<div class="col-md-3"></div>
<div class="col-md-6">
<!-- EMPLOYEE FORM -->
    <div class="">
     <div class="page-header">
				<h1>Change Password</h1>

			</div>
        <form class="form-horizontal" method="POST" action="ChangeEmployeePwd.do">
        <div class="form-group">
                <label for="inputPassword3" class="col-sm-4 control-label" id="old-password">Old Password</label>
                <div class="col-sm-7">
                    <input type="password" class="form-control" name="oldPassword" placeholder="Password">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-4 control-label" id="new-password">New Password</label>
                <div class="col-sm-7">
                    <input type="password" class="form-control" name="newPassword" placeholder="Password">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-4 control-label" id="confirm-password">Confirm Password</label>
                <div class="col-sm-7">
                    <input type="password" class="form-control" name="confirmPassword" placeholder="Confirm Password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10"></div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-5">
                    <button type="submit" class="btn btn-primary ">Change</button>
                  
                </div>
            </div>
        </form>
    </div>
                      <jsp:include page="error-list.jsp" />
    
</div>
<div class="col-md-3"></div><jsp:include page="template-bottom.jsp" />
