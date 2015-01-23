<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.EmployeeBean"%>

<jsp:include page="template-top.jsp" />


<div class="container-fluid">
	<div class="row-fluid">

		<div class="span12">
			<form method="post">
				<fieldset>
					<legend>Create Employee Account</legend>

					<label>Username</label>
					<p>
						<input type="text" id="username" name="username"
							placeholder="Username" />
					</p>
					<label>First Name</label>
					<p>
						<input type="text" id="firstname" name="firstname"
							placeholder="First Name" />
					</p>
					<label>Last Name</label>
					<p>
						<input type="text" id="lastname" name="lastname"
							placeholder="Last Name" />
					</p>
					<label>Password</label>
					<p>
						<input type="text" id="password" name="password"
							placeholder="Password" /> <br /> <input type="text"
							id="confirm" name="confirm" placeholder="Confirm Password" />
					</p>
					<p>
						<input class="btn btn-primary" type="submit" name="action"
							value="Create Employee"></input>
					</p>
				</fieldset>
			</form>
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



	</div>
</div>

<jsp:include page="template-bottom.jsp" />