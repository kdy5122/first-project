<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>MVC 게시판</title>
<style>

	#passForm{
		width:400px;
		margin:150px auto auto auto;
		border : 1px solid orange;
	}
	
	
	table{margin:auto auto auto auto; }
	tr{line-height: 100px;}
	
</style>
</head>
<body>
<section id = "passForm">
<form name="deleteForm" action="productDeletePro.po?prod_id=${prod_id}&prod_image=${prod_image}" 
	method="post">
<table>

<tr>
	<td>
		<input type="submit" value = "삭제확인"/>
		&nbsp;&nbsp;
		<input type = "button" value = "돌아가기" onClick ="javascript:history.go(-1)"/>
	</td>
</tr>

</table>

</form>
</section>
</body>
</html>
