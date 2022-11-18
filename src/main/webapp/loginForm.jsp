<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<%@include file="userHeader.jsp"%>
	<section id="loginFormArea">
	<div class="colorlib-product">
		<div style="padding-left:5%; padding-right:5%; padding-bottom:5%;">
			<div style="border: 1px solid gold; padding-top: 50px; padding-bottom: 50px;">
				<form action="login" method="POST">

					<b style="font-size: 40px; color: gold;">LOGIN</b>

					<div class="td_right">
						<input type="text" placeholder="아이디" name="mem_id" id="id"
							style="width: 50%; text-align: center;"/>
					</div>	

					<div class="td_right" style="padding-top: 5px;">
						<input type="password" placeholder="비밀번호" name="mem_pwd" id="passwd"
							style="width: 50%; height:44px; text-align: center;"/>
					</div>

					<div style="padding-top: 10px;">
						<input type="submit" value="로그인" id="selectButton"
							style="width: 60px;" />
					</div>
					
					<div style="padding-top: 5px;">	
						<button type="button" onclick="location.href='memberIdFindForm.me';"> 아이디찾기 </button>		
						<button type="button" onclick="location.href='memberPwFindForm.me';"> 비밀번호찾기 </button>		
					</div>


				</form>
			</div>
		</div>
		</div>
	</section>
	<%@include file="footer.jsp"%>
</body>
</html>