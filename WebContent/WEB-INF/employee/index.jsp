<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="databeans.FavoriteBean" %>

<jsp:include page="template-top.jsp" />
<div class="col-xs-12 col-sm-9">
	<div class="jumbotron">
		<form class="form-signin" role="form" method="post" action="add.do">
			<h4 class="form-signin-heading">Add Your Favorite Web Site:</h4>
    		<label for="inputUrl" class="sr-only">Url</label>
    		<input type="text" id="inputUrl" name="url" class="form-control" placeholder="Url" value="${url}"/>
	    	<br>
    	
    		<label for="inputComment" class="sr-only">Comment</label>
    		<input type="text" id="inputComment" name="comment" class="form-control" placeholder="cccc">
			<br><br>
			<input type="submit" class="btn btn-lg btn-primary btn-block" name="action" value="Add Favorite"/>
		</form>
	</div>
	<div class="row">
		<c:forEach var = "favorite" items = "${favoriteList}">
			<div class="col-lg-4">
				<!--<h3 style="word-break:break-all"><a href= "click.do?id=${favorite.favoriteID}" target="_blank" onclick="location.reload()">${favorite.url }</a></h3>  -->
				<h3 style="word-break:break-all"><a href= "click.do?id=${favorite.favoriteID}">${favorite.url }</a></h3>
				<p>${favorite.comment}</p>
				<p>Click Count: ${favorite.clickCount}</p>
    			<form method="POST" action="remove.do">
                	<input type="hidden" name="id" value= "${favorite.favoriteID}"/>
                	<input class="btn btn-primary" type="submit" value="Delete"/>
    	        </form>
			</div>	
		</c:forEach>
	</div>
</div>
<jsp:include page="template-bottom.jsp" />
