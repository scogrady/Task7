<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="customer/template-top.jsp" />

  <c:forEach var="error" items="${errors}">
  	<c:if test="${!(empty errors) }">
  	<br><font color=red> ${error }</font>
  	</c:if>
  	</c:forEach>
  	
<jsp:include page="customer/template-bottom.jsp" />

