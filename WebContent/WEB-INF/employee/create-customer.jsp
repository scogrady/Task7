<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.CustomerBean"%>

<jsp:include page="template-top.jsp" />


<div class="container-fluid">

 <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="page-header">
                 <h1>Create Customer</h1>

            </div>
            <form method="post" action="CreateCustomer.do">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" id="username-new"  name="username" placeholder="Username" maxlength="20" required>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input type="password" class="form-control" id="password-new-1" name="password" placeholder="Password" maxlength="20" required>
                    <br/>
                    <input type="password" class="form-control" id="password-new-2"  name="confirm" placeholder="Confirm Password" maxlength="20" required>
                </div>
                <div class="form-group">
                    <label for="firstname">First Name</label>
                    <input type="text" class="form-control" id="firstname-new" name="firstname" placeholder="First Name" maxlength="20" pattern="[a-zA-Z ]{2,30}" title="Please enter correct First Name" required>
                </div>
                <div class="form-group">
                    <label for="lastname">Last Name</label>
                    <input type="text" class="form-control" id="lastname-new" name="lastname" placeholder="Last Name" maxlength="20" pattern="[a-zA-Z ]{2,30}" title="Please enter correct Last Name" required>
                </div>
                <div class="form-group">
                    <label for="addressline-1">Address Line</label>
                    <input type="text" class="form-control" id="address-new-1" name="addr_line1" placeholder="Address Line 1"maxlength="50" required>
                    <br/>
                    <input type="text" class="form-control" id="address-new-2" name="addr_line2" placeholder="Address Line 2" maxlength="50" required>
                </div>
                <div class="form-group">
                    <label for="city">City</label>
                    <input type="text" class="form-control" id="city-new" name="city" placeholder="City" maxlength="20" pattern="[a-zA-Z ]{2,30}" title="Please enter correct City" required>
                </div>
                <div class="form-group">
                    <label for="State">State</label>
                    <select class="form-control"  name="state">
                        <option value="AL">Alabama</option>
                        <option value="AK">Alaska</option>
                        <option value="AZ">Arizona</option>
                        <option value="AR">Arkansas</option>
                        <option value="CA">California</option>
                        <option value="CO">Colorado</option>
                        <option value="CT">Connecticut</option>
                        <option value="DE">Delaware</option>
                        <option value="DC">District Of Columbia</option>
                        <option value="FL">Florida</option>
                        <option value="GA">Georgia</option>
                        <option value="HI">Hawaii</option>
                        <option value="ID">Idaho</option>
                        <option value="IL">Illinois</option>
                        <option value="IN">Indiana</option>
                        <option value="IA">Iowa</option>
                        <option value="KS">Kansas</option>
                        <option value="KY">Kentucky</option>
                        <option value="LA">Louisiana</option>
                        <option value="ME">Maine</option>
                        <option value="MD">Maryland</option>
                        <option value="MA">Massachusetts</option>
                        <option value="MI">Michigan</option>
                        <option value="MN">Minnesota</option>
                        <option value="MS">Mississippi</option>
                        <option value="MO">Missouri</option>
                        <option value="MT">Montana</option>
                        <option value="NE">Nebraska</option>
                        <option value="NV">Nevada</option>
                        <option value="NH">New Hampshire</option>
                        <option value="NJ">New Jersey</option>
                        <option value="NM">New Mexico</option>
                        <option value="NY">New York</option>
                        <option value="NC">North Carolina</option>
                        <option value="ND">North Dakota</option>
                        <option value="OH">Ohio</option>
                        <option value="OK">Oklahoma</option>
                        <option value="OR">Oregon</option>
                        <option value="PA">Pennsylvania</option>
                        <option value="RI">Rhode Island</option>
                        <option value="SC">South Carolina</option>
                        <option value="SD">South Dakota</option>
                        <option value="TN">Tennessee</option>
                        <option value="TX">Texas</option>
                        <option value="UT">Utah</option>
                        <option value="VT">Vermont</option>
                        <option value="VA">Virginia</option>
                        <option value="WA">Washington</option>
                        <option value="WV">West Virginia</option>
                        <option value="WI">Wisconsin</option>
                        <option value="WY">Wyoming</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="zip">Zip Code</label>
                    <input type="text" class="form-control" id="zip-new" name="zip" placeholder="Zip Code" pattern="[0-9]{5}" title="Please enter 5 digit Zip code" required>
                </div>
               
                <input type="submit" class="btn btn-default" id="submit-new-customer" name="action">
            </form>
        </div>
    </div>
    <div class="col-md-3"></div>
</div>



	



<div class="row">
	<div class="col-xs-12 col-sm-9">
		<h3>Customer List.</h3>
		<table class="table">
			<thead>
				<tr>
					<th>Customer ID</th>
					<th>Customer Username</th>
					<th>First Name</th>
					<th>Last Name</th>
					
					<th>City</th>
					<th>State</th>
					<th>Zip Code</th>
					<th>Password</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="customer" items="${customerList}">
					<tr>
					<td>${customer.getCustomer_id()}</td>
						<td>${customer.getUsername()}</td>
						<td>${customer.getFirstname()}</td>
						<td>${customer.getLastname()}</td>
						<td>${customer.getCity()}</td>
						<td>${customer.getState()}</td>
						<td>${customer.getZip()}</td>
						<td>${customer.getPassword()}-delete</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>




<jsp:include page="template-bottom.jsp" />
