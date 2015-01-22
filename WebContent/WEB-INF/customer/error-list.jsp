<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <c:forEach var="error" items="${errors}">
  	<c:if test="${!(empty errors) }">
  	<br><font color=red> ${error }</font>
  	</c:if>
  	</c:forEach>
  	
