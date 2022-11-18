<%@page import="vo.QBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<meta charset="UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	<title>테니소모임 수정 게시판</title>
	<script type="text/javascript">
	function modifyboard(){
				
		modifyform.submit();
	}
	</script>
	<style type="text/css">
   #registForm{
      width: 500px;
      height: 600px;
      margin:auto; 
   }   
   h4{
      text-align: center; padding-top:20px; color:black;
   }
   table{
      margin:auto;
      width: 600px;
      }
   #td_left{
      text-align: center;
   }
    
   #commandCell{
      text-align: center;
      margin-bottom: 10px;
   }
   #writeForm{background-color: white;}
   #table-first{ margin:0 auto; width: 60%; border-color: black;  font-size: 12px; border-color: #e1e4e1;}
    @media all and (max-width: 768px){
     #table-first{width:80%;}
    }
 @media all and (max-width: 415px){
     #table-first{width:100%;}
    }   
</style>
</head>



<body>
<%@include file="/userHeader.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<!-- 게시판 등록 -->

<section id="writeForm">
<form action="tboardReplyModifyPro.bo" method="post" name = "modifyform">
<input type = "hidden" name = "TBOARD_NUM" value = "${article.TBOARD_NUM}"/>
<h4>QNA 문의수정</h4>
<table border="1" id="table-first" class="table table-bordered">
	<tr>
		<td id="td_left">
			<label for = "TBOARD_NAME">작성자 ID</label>
		</td>
		<td>
			 ${article.MEM_ID}
		</td>
	</tr>

	<tr>
		<td id="td_left">
			<label for = "TBOARD_SUBJECT" >제 목</label>
		</td>
		<td>
			<input name="TBOARD_SUBJECT" type="text" id = "TBOARD_SUBJECT" value = "${article.TBOARD_SUBJECT}"  class="form-control"/>
		</td>
	</tr>
	<tr>
		<td id="td_left">
			<label for = "TBOARD_CONTENT">내 용</label>
		</td>
		<td>
			<textarea id = "TBOARD_CONTENT" name="TBOARD_CONTENT" cols="60" rows="12"  class="form-control" style="font-size:12px;">${article.TBOARD_CONTENT}</textarea>
		</td>
	</tr>
</table>
	<div id = "commandCell">
	         <%--로그인 한 사람의 본인의 작성글만 수정할 수 있는 조건문 --%>
	        <c:choose>
	        <c:when test="${mem_id == article.MEM_ID}">  
			<a href="javascript:modifyboard()" class="btn btn-outline-dark" style="font-size:12px;">수정</a>&nbsp;&nbsp;
			</c:when>
			<c:when test="${mem_id != article.MEM_ID }">			
            </c:when>
			</c:choose>
			<a href="javascript:history.go(-1)" class="btn btn-outline-dark" style="font-size:12px;">뒤로</a>
	</div>
</form>
</section>
<%@include file="../footer.jsp"%>
</body>
</html>