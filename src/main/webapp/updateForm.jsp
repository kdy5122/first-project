<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>

<script type="text/javascript">
	function check() {

		if (f.mem_id.value == "") {
			alert("아이디를 입력해주세요.");
			return f.mem_id.focus();
		}

		if (f.mem_pwd.value == "") {
			alert("비밀번호를 입력해주세요.");
			return f.mem_pwd.focus();
		}

		if (f.mem_name.value == "") {
			alert("이름를 입력해주세요.");
			return f.mem_name.focus();
		}

		if (f.mem_email.value == "") {
			alert("이메일를 입력해주세요.");
			return f.mem_email.focus();
		}

		if (f.mem_call.value == "") {
			alert("전화번호를 입력해주세요.");
			return f.mem_call.focus();
		}

		if (f.mem_postcode.value == "") {
			alert("우편번호를 입력해주세요.");
			return f.mem_postcode.focus();
		}

		if (f.mem_address1.value == "") {
			alert("주소를 입력해주세요.");
			return f.mem_address1.focus();
		}

		if (f.mem_address2.value == "") {
			alert("상세주소를 입력해주세요.");
			return f.mem_address2.focus();
		}

		if (f.mem_address3.value == "") {
			alert("상세주소를 입력해주세요.");
			return f.mem_address3.focus();
		}

	}
</script>

<body>
	<%@ include file="userHeader.jsp"%>
	<section id="updateformArea">
		<div style="padding-left: 5%; padding-right: 5%; padding-bottom: 5%;">
			<div
				style="border: 1px solid gold; padding-top: 50px; padding-bottom: 50px;"center"">
				<form name="updateforom" action="memberUpdateAction.me"
					method="post">
					<b style="line-height: 45px; color: gold;">${member.mem_id }님의
						정보 수정</b>

					<div>
						아이디<br>
						<input type="text" placeholder="${member.mem_id }"
							name="mem_id" id="mem_id" style="width: 50%; text-align: center;"">
					</div>

					<div>
						비밀번호<br>
						<input type="text" placeholder="${member.mem_pwd }"
							name="mem_pwd" id="mem_pwd"
							style="width: 50%; text-align: center;"">
					</div>

					<div>
						이름<br>
						<input type="text" placeholder="${member.mem_name }"
							name="mem_name" id="mem_name"
							style="width: 50%; text-align: center;">
					</div>

					<div>
						이메일<br>
						<input type="text" placeholder="${member.mem_email }"
							name="mem_email" id="mem_email"
							style="width: 50%; text-align: center;">
					</div>

					<div>
						전화번호<br>
						<input type="text" placeholder="${member.mem_call }"
							name="mem_call" id="mem_call"
							style="width: 50%; text-align: center;">
					</div>

					<div>
						우편번호<br>
						<input type="text"
							placeholder="${member.mem_postcode }" name="mem_call"
							id="mem_call" style="width: 50%; text-align: center;">
					</div>

					<div>
						주소<br>
						<input type="text"
							placeholder="${member.mem_address1 }" name="mem_call"
							id="mem_call" style="width: 50%; text-align: center;">
					</div>

					<div>
						상세 주소<br>
						<input type="text" placeholder="${member.mem_address2 }"
							name="mem_call" id="mem_call"
							style="width: 50%; text-align: center;">
					</div>

					<div>
						등급<br>
						<input type="text" placeholder="${member.mem_grade }"
							name="mem_call" id="mem_call"
							style="width: 50%; text-align: center;">
					</div>

					<div>
						<input type="submit" value="가입하기" onclick="check();">
						
					</div>

					<div style="padding-top: 5px;">
						<input type="reset" value="다시작성">
					</div>

				</form>
			</div>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>