<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="databeans.CustomerBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CWS</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet">
<link href="customer.css" rel="stylesheet">
</head>

<body class="nojs">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="customer.js"></script>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div id="wrap">
		<nav class="navbar navbar-inverse" role="navigation">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Carnegie Web Services</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul id="group-function" class="nav navbar-nav">
						<li class="active" id="view-account"><a href="AccountInfo.do">View
								Account</a></li>
						<li id="fund-manage"><a href="ResearchFund.do?fund_id=1">Land
								</a></li>
						<li id="fund-manage"><a href="ResearchFund.do?fund_id=1">Water
								</a></li>
						<li id="fund-manage"><a href="ResearchFund.do?fund_id=1">Sky
								</a></li>
					</ul>

					<ul class="nav navbar-nav navbar-right">

						<%
							CustomerBean customer = (CustomerBean) session
									.getAttribute("customer");
						%>

						<li><a style="color: white">Welcome! <%=customer.getFirstname()%>
								<%=customer.getLastname()%></a></li>
						<li><a href="logout.do">Logout</a></li>
						<li>&nbsp;</li>
						<br />
					</ul>
				</div>
			</div>
		</nav>
		<div class="container-fluid" style="margin-bottom: 80px">

			<div class="row">
				<div id="account-operation" class="col-sm-2">
					<div class="sidebar-nav">
						<div class="navbar navbar-default" role="navigation">
							<div class="navbar-collapse collapse sidebar-navbar-collapse">
								<ul id="sub-operation" class="nav navbar-nav">
									<li class="active" name="AccountInfo"><a
										href="AccountInfo.do">Account Information</a></li>
									<li name="ChangePwd"><a href="ChangePwd.do">Change
											Password</a></li>
									<li id="fund-manage"><a href="RequestCheck.do">Analytics</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>

				<div id="fund-operation" class="col-sm-2" style="display: none">
					<div class="sidebar-nav">
						<div class="navbar navbar-default" role="navigation">
							<div class="navbar-collapse collapse sidebar-navbar-collapse">
								<ul id="sub-operation" class="nav navbar-nav">
									<li class="active" name="SellFund"><a href="SellFund.do">Search on Twitter
											</a></li>
									<li name="BuyFund"><a href="BuyFund.do">Search on Flickr</a></li>
									<li name="ResearchFund"><a
										href="ResearchFund.do?fund_id=1">Show on Map</a></li>
									<li name="ResearchFund"><a href="ResearchFund.do?fund_id=1">Tweet</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				
				
				
				
				<div class="col-sm-10">