<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<title>MVC 게시판</title>
<style>

	#passForm{
		height:300px;
		background-color: white;
	}
	
	
	table{	border : 1px solid #e1e4e1; margin:100px auto auto auto; width: 300px;}
	tr{line-height: 100px;}
	
</style>
</head>
<body>
<jsp:include page="/userHeader.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<section id = "passForm">
<form name="deleteForm" action="tboardDeletePro.bo?tboard_num=${tboard_num}" method="post">
<input type = "hidden" name = "page" value="${page}"/>
<table>

<tr>
	<td>
		<input type="submit" class="btn btn-outline-dark" value = "삭제확인" />
		&nbsp;&nbsp;
		<input type = "button" class="btn btn-outline-dark" value = "돌아가기" onClick ="javascript:history.go(-1)"/>
	</td>
</tr>

</table>

</form>
</section>
<jsp:include page="/footer.jsp"/>
</body>
</html>
