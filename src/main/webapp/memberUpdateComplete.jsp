<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored = "false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원수정완료</title>
</head>
<body>
	<div style="padding-left : 5%; padding-right : 5%; padding-bottom : 5%; text-align: center;">
		<div style="border: 1px solid gold; padding-top: 50px; padding-bottom: 50px;">
			<form>
				<div style="padding-bottom : 30px;">
					<b style="color: gold;">[</b>
					<b style="color: width;">${sessionScope.mem_name}</b>
					<b style="color: gold;">]님의<br>정보 수정이 완료되었습니다.</b>
				</div>
					<button type="button" onclick="location.href='memberListAction.me';"> 확인 </button>
			</form>
		</div>
	</div>
</body>
</html>