<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>CFS</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="index.css" rel="stylesheet">    
    <style type="text/css">

    </style>
</head>

<body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<form class="form-signin" role="form" method="post" action="login.do">
		<h2 class="form-signin-heading">Please log in</h2>
	    <label for="inputEmail" class="sr-only">Email address</label>
    	<input type="text" name="email" id="inputEmail" class="form-control" placeholder="Email address" value="${form.email}">
    	<br>
    	<label for="inputPassword" class="sr-only">Password</label>
	    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password">
		<br><br>
		<h4>Login as:</h4>
		<input type="submit" class="btn btn-lg btn-primary" name="action" value="Customer"/>
		<input type="submit" class="btn btn-lg btn-primary" name="action" value="Employee"/>
		<h4>Not register yet?</h4>
		<h4>Click <a href="register.do">here</a> to register as a new user.</h4>
	</form>
</body>
</html>
