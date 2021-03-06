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
</head>

<body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>


	<form class="form-signin" role="form" method="post" action="login.do">
		<h2 class="form-signin-heading">Please log in</h2>
			<jsp:include page="error.jsp" />
	    <label for="inputEmail" class="sr-only">Username</label>
    	<input type="text" name="username" id="input-username" class="form-control" placeholder="Username" value="${form.username }" required>
    	<br>
    	<label for="inputPassword" class="sr-only">Password</label>
	    <input type="password" name="password" id="input-password" class="form-control" placeholder="Password" required>
		<br><br>
		<h4>Login as:</h4>
		<input type="submit" class="btn btn-lg btn-primary" name="action" value="Twitter"/>
		<input type="submit" class="btn btn-lg btn-primary" name="action" value="Flickr"/>
	</form>
</body>
</html>
