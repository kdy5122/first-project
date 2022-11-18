<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>테니스샵</title>
</head>
<body>
	<div>
		<%-- <jsp:include page="userHeader.jsp" /> --%>
		<%@ include file="userHeader.jsp" %>
	</div>
	
	<c:if test="${showPage ne null }">
	<section id="section">	
		<div>
			<jsp:include page="${showPage }" />
		</div>	
	</section>
	</c:if>
	
	<div>
		<%-- <jsp:include page="userFooter.jsp" /> --%>
		 <%@ include file="footer.jsp" %>
	</div>
</body>
</html>