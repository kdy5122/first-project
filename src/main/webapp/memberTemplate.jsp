<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테니스샵</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<html>
<body>
	<div>
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
		 <%@ include file="footer.jsp" %>
	</div>
</body>
</html>