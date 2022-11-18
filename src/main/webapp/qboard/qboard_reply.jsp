<%@page import="vo.QBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<title>MVC 게시판</title>
<script language="javascript">
	</script>
<style type="text/css">


h4 {
	text-align: center;
	padding-top: 20px;
	color:black;
}
#writeForm{background-color: white;}



#commandCell {
	text-align: center;
	margin-bottom: 10px;
	background-color: white;
}

#td_left{
 text-align: center; font-size: 12px;

}
#table1{border-color: #e1e4e1; width: 55%; margin:0 auto;}
 
 
 @media all and (max-width: 768px){
     #table-first{width:80%;}
    }
 @media all and (max-width: 415px){
     #table-first{width:100%;}
    }   
</style>
</head>
<body>
<jsp:include page="/userHeader.jsp"></jsp:include>
<c:set var="nowPage" value="${pageInfo.page}"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<!-- 게시판 답변 -->


	<section id="writeForm">
		<h4>관리자 답변</h4>
		<form action="qboardReplyPro.qna" method="post" name="boardform">
		    <input type="hidden" id="loginID" name="loginID" value="${mem_id}">
			<input type="hidden" name="page"          value="${nowPage}" /> 
			<input type="hidden" name="qboard_num"    value="${article.QBOARD_NUM}">
			<input type="hidden" name="QBOARD_RE_REF" value="${article.QBOARD_RE_REF}"> 
			<input type="hidden" name="QBOARD_RE_LEV" value="${article.QBOARD_RE_LEV}"> 
			<input type="hidden" name="QBOARD_RE_SEQ" value="${article.QBOARD_RE_SEQ}">
			<table border="1" id="table1" class="table table-bordered">
		   		<tr>
					<td id="td_left">관리자ID</td>
					<td class="td_right" style="text-align: left">${mem_id}</td>
				</tr>
				<tr>
					<td id="td_left"><label for="QBOARD_SUBJECT">제 목</label></td>
					<td class="td_right"><input name="QBOARD_SUBJECT" type="text"
						id="QBOARD_SUBJECT" size="35" class="form-control"/></td>
				</tr>
				<tr>
					<td id="td_left"><label for="QBOARD_CONTENT">내 용</label></td>
					<td><textarea id="QBOARD_CONTENT" name="QBOARD_CONTENT"
							cols="40" rows="10" class="form-control"></textarea></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="답변글등록" class="btn btn-outline-dark" style="font-size:12px;"/>&nbsp;&nbsp; <input
					type="reset" value="다시작성" class="btn btn-outline-dark" style="font-size:12px;"/>
			</section>
		</form>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
