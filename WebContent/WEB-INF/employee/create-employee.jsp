<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.EmployeeBean"%>

<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />


<div class="col-xs-12 col-sm-12">
  <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="page-header">
                 <h1>Create Employee</h1>

            </div>
            <form  method="post" action="CreateEmployee.do">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" id="username-new" name="username" placeholder="Username" value="${form.getUsername()}" maxlength="30" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input type="password" class="form-control" id="password-new-1"  name="password" placeholder="Password" maxlength="30" required>
                    <br/>
                    <input type="password" class="form-control" id="password-new-2"  name="confirm" placeholder="Confirm Password" maxlength="30" required >
                </div>
                        
                <div class="form-group">
                    <label for="firstname">First Name</label>
                    <input type="text" class="form-control" id="firstname-new" name="firstname" placeholder="First Name" value="${form.getFirstname()}" maxlength="20" pattern="[a-zA-Z ]{2,30}" title="Please enter correct First Name" required>
                </div>
                <div class="form-group">
                    <label for="lastname">Last Name</label>
                    <input type="text" class="form-control" id="lastname-new" name="lastname" placeholder="Last Name" value="${form.getLastname()}" maxlength="20" pattern="[a-zA-Z ]{2,30}" title="Please enter correct Last Name" required>
                </div>
                <input type="submit" class="btn btn-primary" id="submit-new-emlpoyee" name="action" value="Create">
            </form>
        </div>
    </div>
    <div class="col-md-3"></div>
</div>
	

		<div class="row">
			<div class="col-xs-12 col-sm-9">
				<h3>Employee List.</h3>
				<table class="table">
					<thead>
						<tr>
							<th>Employee Username</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Password</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="employee" items="${employeeList}">
							<tr>
								<td>${employee.getUsername()}</td>
								<td>${employee.getFirstname()}</td>
								<td>${employee.getLastname()}</td>
								<td>${employee.getPassword()}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
<jsp:include page="template-bottom.jsp" />