<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="databeans.TwitterBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

	<h1>Hi</h1>
	<% TwitterBean[] tweets = (TwitterBean[]) request.getSession(false).getAttribute("tweets"); %>
				<c:forEach var="tweet" items="${tweets}">
					<li class="list-group-item">${tweet.getText() } ${tweet.getUser() }</li>
				</c:forEach>
	<form role="form" method="post" action="PostTweet.do">
		<h2>Type your tweet and post</h2>
	    <label for="tweet" class="sr-only">Tweet</label>
    	<input type="text" name="text" id="tweet-text" class="form-control" placeholder="text"  required>
		<input type="submit" class="btn btn-lg btn-primary" name="action" value="Post"/>
	</form>
</body>
</html>
