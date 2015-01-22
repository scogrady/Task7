<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>CFS</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="employee.css" rel="stylesheet">
</head>

<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="wrap">
<nav class="navbar navbar-inverse" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Carnegie Financial Services</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul id="group-function" class="nav navbar-nav">
          <li class="active dropdown" id = "account-op">
  				<a class="dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
    				Account Management
    				<span class="caret"></span>
  				</a>
  				<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
    				<li role="presentation"><a role="menuitem" tabindex="-1" href="CreateEmployee.do">Employee</a></li>
    				<li role="presentation"><a role="menuitem" tabindex="-1" href="CreateCustomer.do">Customer</a></li>
  				</ul>
          
          </li>
          <li id = "fund-op"><a href="CreateFund.do" >Fund Management</a></li>
          <li id = "transaction-day"><a href="Transition.do">Transition Day</a></li>
      </ul>

      <ul class="nav navbar-nav navbar-right">
      	<li><a style="color: white">Welcome! 				Hanze Xu</a></li>
		<li><a href="logout.do">Logout</a></li>
		<li>&nbsp;</li><br/>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid" style="margin-bottom: 80px">

<div class="row">
  <div id="eaccount-operation" class="col-sm-2">
    <div class="sidebar-nav">
      <div class="navbar navbar-default" role="navigation">
        <div class="navbar-collapse collapse sidebar-navbar-collapse">
          <ul id="sub-operation" class="nav navbar-nav">
            <li class="active" name = "CreateEmployee"><a href="CreateEmployee.do">Create Employee Account</a></li>
            <li name = "ChangeEmployeePwd"><a href="ChangeEmployeePwd.do">Change Password</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
  </div>
  
  <div id="caccount-operation" class="col-sm-2" style="display:none">
    <div class="sidebar-nav">
      <div class="navbar navbar-default" role="navigation">
        <div class="navbar-collapse collapse sidebar-navbar-collapse">
          <ul id="sub-operation" class="nav navbar-nav">
            <li class="active" name = "CreateCustomer"><a href="CreateCustomer.do">Create Customer Account</a></li>
            <li name = "ResetPassword"><a href="ResetPassword.do">Rest Password</a></li>
            <li name = "ViewAccount"><a href="ViewAccount.do">View Customer Account</a></li>
            <li name = "ViewTransaction"><a href="ViewTransaction.do">Customer Transaction History</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
  </div>
  
  <div id="fund-operation" class="col-sm-2" style="display:none">
    <div class="sidebar-nav">
      <div class="navbar navbar-default" role="navigation">
        <div class="navbar-collapse collapse sidebar-navbar-collapse">
          <ul id="sub-operation" class="nav navbar-nav">
            <li class="active" name = "CreateFund"><a href="CreateFund.do">Create Fund</a></li>
            <li name = "DepositCheck"><a href="DepositCheck.do">Deposit Check</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
  </div>
  <div class="col-sm-10">
