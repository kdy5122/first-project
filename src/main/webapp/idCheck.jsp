<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.* ,javax.sql.*, javax.naming.*"%>

<%
//<순서1-2>
String openInit = "false";
if (request.getParameter("openInit") != null) {
	openInit = request.getParameter("openInit");//openInit = "true"가 됨
}
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>ID 중복체크</title>
</head>

<script>
	//<순서1-3> : 사용자가 회원가입폼(opener인 부모 창)에서 입력한 id가 자동 셋팅됨
	function init() {
		if (
<%=openInit%>
	) {//openInit가 "true"이면
			//opener인 부모 창의 u_id의 입력된 값을 가져와 아래 idCheck에 그대로 셋팅한다.
			document.getElementById("idCheck").value = opener.document
					.getElementById("mem_id").value;
		}
	}
	//<순서1-5> : ★★사용가능한 chk_id값을 회원가입창(=부모창=opener)의 아이디 입력란("u_id")의 값으로 그대로 셋팅
	function useId(chk_id) {
		//부모창에 접근하려면 opener를 사용해서 접근해야 한다.
		//opener.chkId = true;
		//opener.idcheck = chk_id.trim();
		opener.document.getElementById("mem_id").value = chk_id.trim();
		window.close();
	}
</script>

<!--<순서1-1>.[아이디중복확인]창이 열리면 onload이벤트에 의해 init()함수가 호출도어 실행된다.-->
<body onload="init()"
	style="background-color: #000000; text-align: center; color: #ffffff;">
	<section>
		<div style="padding: 10px;">
			<div
				style="border: 1px solid gold; padding-top: 50px; padding-bottom: 50px;">
				<form action="idCheckProcess.jsp" method="post" name="f">
					<b style="color: gold;">아이디 중복 체크</b>

					<div class="td_right" style="padding-top: 10px;">
						<input type="text" placeholder="아이디" name="idCheck" id="idCheck"
							style="width: 50%; text-align: center;">
					</div>

					<div style="padding-top: 10px;">
						<input type="submit" value="중복확인" id="submit">
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
</html>

<%
//<순서1-4>
//★★idCheckProcess.jsp로 처리하고 다시 돌아온 후이므로 "html"밑에 써야 함

request.setCharacterEncoding("utf-8");

//idCheckProcess.jsp에서 파라미터로 전송한 chk_id와 useable
String chk_id = request.getParameter("chk_id");
//if(request.getParameter("chk_id")!=null && !request.getParameter("chk_id").trim().equals("")){ //주석 후
if (chk_id != null && !chk_id.trim().equals("")) {
	String useable = request.getParameter("useable");
	/*처음으로 [아이디중복확인]창이 떴을때는 수평선이 없음.
	이후, idCheckProcess.jsp로 처리하고 다시 돌아온 후 수평선 나타남*/
	out.println("<hr/>");

	if (useable.equals("NO")) {
		out.println("<p>" + chk_id + "는(은)<br> 사용 불가능한 아이디입니다.<br> 다시 시도하세요.</p>");

	} else {
		out.println("<p>" + chk_id + "는(은)<br> 사용가능한 아이디입니다." + "<br>");
		//★★사용가능한 chk_id값을 회원가입창(=부모창=opener)의 아이디 입력란("u_id")의 값으로 그대로 셋팅
		out.println("<a style=\" background-color : #000000; color : gold;\" href ='javascript:useId(\"" + chk_id
		+ "\")'>사용하기</a></p>");
	}
}
%>
