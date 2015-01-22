<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.FavoriteBean"%>

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
			<div class="col-sm-6 col-md-4">
				<h3>Employee List.</h3>

				<ol>

					<c:forEach var="employee" items="${employeeList}">
						<li>
							<h4>username = ${employee.getUsername()}</h4>
							<p>Firstname = ${employee.getFirstname()}</p>
							<p>Lastname = ${employee.getLastname()}</p>
							<p>Password = ${employee.getPassword()}</p>

						</li>
					</c:forEach>

				</ol>
			</div>
		</div>
	</div>
</div>

	<jsp:include page="template-bottom.jsp" />