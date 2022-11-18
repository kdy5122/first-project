<%@page import="vo.QBoardBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	QBoardBean article = (QBoardBean)request.getAttribute("article");
	
    String nowPage = (String)request.getAttribute("page");
    String loginID = (String)session.getAttribute("mem_id"); //세션에 있는 ID불러오기 
    String mem_grade = (String)session.getAttribute("mem_grade");
%>

<!DOCTYPE html>
<html>

<head>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<title>MVC 게시판</title>
<style type="text/css">


h4 {
	text-align: center; color:black; margin-top: 10px;}

#sectionall{background-color: white;}

#staticMap{
 margin:0 auto;
}


#tdleft{
   text-align: center;
}

#table-first{ margin:0 auto; width: 60%; border-color: black; font-size: 12px; border-color: #e1e4e1; }

#commandList {
	margin: auto;
	width: 500px;
	text-align: center;
	padding-bottom: 20px;
	background-color: white;
}

 @media all and (max-width: 768px){
     #table-first{width:80%;}
    }
 @media all and (max-width: 415px){
     #table-first{width:100%;}
    }   

</style>
</head>

<body>
<jsp:include page="/userHeader.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<c:set var="nowPage" value="pageInfo.page"/>
	<!-- 게시판 수정 -->
		<div class="container">
	<section id="sectionall">
	      <h4>QNA 문의 글보기</h4>	
 		  <table id="table-first" class="table table-bordered">	
		  <tr>
		   <td id="tdleft">글쓴이 ID</td>
		   <td style="text-align:left">${article.MEM_ID}</td>
		  </tr>
		  
		  <tr>
		  <td id="tdleft">제 목 </td>
		  <td style="text-align:left">${article.QBOARD_SUBJECT}</td>
		  </tr>	
		   
		  <tr>
		   <td id="tdleft">내용</td>
		   <td>	   
<textarea cols="70" rows="12" class="form-control" style="font-size:12px;" readonly><c:out value="${article.QBOARD_CONTENT }"  /> </textarea>
		   </td>
		  </tr>
		</table>
	</section>

	<section id="commandList">
	   
             <c:if test="${mem_grade != null}">
				      <c:if test="${mem_grade=='2'}">
				      
				      <a href="qboardReplyForm.qna?qboard_num=${article.QBOARD_NUM}&page=${nowPage}" class="btn btn-outline-dark" style="font-size:12px;">
				        답변	
					  </a> 				   		    
				      </c:if>
		    </c:if>
	       
	       	         					
			<c:choose>
			<c:when test="${article.MEM_ID == mem_id}">
			<a href="qboardModifyForm.qna?qboard_num=${article.QBOARD_NUM}" class="btn btn-outline-dark" style="font-size:12px;">			
			수정
			</a> 	
			</c:when>
			<c:when test="${article.MEM_ID != mem_id}">	 
			</c:when>
			</c:choose>
			
					
			<c:choose>
			<c:when test="${article.MEM_ID == mem_id}">
			<a href="qboardDeleteForm.qna?qboard_num=${article.QBOARD_NUM}&page=${nowPage}" class="btn btn-outline-dark" style="font-size:12px;">
			삭제 
			</a> 
			</c:when>
			<c:when test="${article.MEM_ID != mem_id}">		 
			</c:when>
			</c:choose>
			
			<%-- <a href="boardList.qna?page=<%=nowPage%>">[목록]</a>&nbsp;&nbsp; --%>
			<a href="qboardList.qna" class="btn btn-outline-dark" style="font-size:12px;">뒤로가기</a>
						
	</section>
	</div>
  <jsp:include page="/footer.jsp"/>
</body>
</html>
