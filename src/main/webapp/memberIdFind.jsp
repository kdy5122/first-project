<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 비밀번호 찾기</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div style="padding-left : 5%; padding-right : 5%; padding-bottom : 5%; ">
		<form action="memberIdFindAction.me" method="post"
			style="border: 1px solid gold; padding-top: 50px; padding-bottom: 50px; text-align: center;">
			<table>
				<div>
					<b style=" color: gold;">아이디 찾기</b>
				</div>
				<div  style="padding-top: 10px;">
					<input type="text" name="mem_email" placeholder="이메일 입력" required="required" style="width: 50%; text-align: center;"
							/>
				</div>
				<div  style="padding-top: 10px;">
					<input type="submit" value="아이디 찾기"/>
				</div>
			</table>
		</form>
</body>
</html>