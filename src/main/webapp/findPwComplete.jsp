<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored = "false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 결과</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div style="padding-left:5%; padding-right:5%; padding-bottom:5%; ">
		<div style="border: 1px solid gold; padding-top: 50px; padding-bottom: 50px; text-align: center;">
			<form>
				<div style="padding-bottom : 30px;">
					<b style="color: gold;">고객님의 임시 비밀번호는</b><br>
					<b style=" color: gold;">[ </b>
					<b style="color: White;">${ramdom_password} </b>
					<b style=" color: gold;"> ]</b><br>
					<b style=" color: gold;">입니다.<br> 비밀번호 변경은 마이페이지 에서 가능합니다. </b>
				</div>
				<div>
					<button type="button" onclick="location.href='loginForm.jsp';"> 로그인 </button>
				</div>
				<div style="padding-top: 5px;">
					<button type="button" onclick="location.href='memberIdFindForm.me';"> 아이디찾기 </button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>