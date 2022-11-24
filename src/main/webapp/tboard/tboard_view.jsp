<%@page import="vo.TBoardBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	text-align: center;
	padding-top: 20px;
	color:black;}

#section1{background-color: white;}

#tdleft{

   text-align: center;
}

#table-first{
 margin:0 auto; 
 width: 80%; 
 border-color: black; font-size: 12px; 
 border-color: #e1e4e1; background-color: white;}

#commandList {
	margin: auto;
	text-align: center;
	padding-bottom: 20px;
	background-color: white;
}

#map{width: 100%; height: 200px;}

#img{width: 100%; height: 150px;}

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
<c:set var="nowPage" value="${page}"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<!-- 게시판 수정 -->
	<div class="container">
	<section id="section1">	
	<h4>테니스 모임 게시판 글보기</h4>	
	
	<table border="1" id="table-first" class="table table-bordered">
		  <tr>
			   <td id="tdleft">글쓴이 ID</td>
			   <td style="text-align:left">${article.MEM_ID}</td>
		  </tr>
		  
		  <tr>
			   <td id="tdleft">제 목 </td>			   
			   <td style="text-align:left">${article.TBOARD_SUBJECT}</td>
		  </tr>	
		  
	       <c:choose>
	       <c:when test="${article.TBOARD_FILE!=null}">
		  <tr>
			    <td id="tdleft">파일download</td>
			    <td>
				<a href="tboard/tboard_file_down.jsp?downFile=${article.TBOARD_FILE}"> ${article.TBOARD_FILE}
				</a>
				</td>
		  </tr>
		  </c:when>
		  <c:when test="${article.TBOARD_FILE==null }">
		    
		  </c:when>
		  </c:choose>             
		  <tr>
			   <td id="tdleft">내용</td>
			   <td>	   
	          <textarea cols="70" rows="12" class="form-control" style="font-size:12px;" readonly><c:out value="${article.TBOARD_CONTENT}"  /></textarea>
	          <c:choose>
	          <c:when test="${article.TBOARD_IMAGE!=null}">
	          <img src="${pageContext.request.contextPath}/tboardUpload/${article.TBOARD_IMAGE}" id="img">
	          </c:when>
	          <c:otherwise>
	          
	          </c:otherwise>
	          </c:choose>
			   </td>
		  </tr>

          <c:choose>
          <c:when test="${article.TBOARD_PLACELA != 'NULL' }">
		  <tr>
		    <td id="tdleft">모임장소 주소</td>
		    <td><input type="text" id="address" value="" size="50"  class="form-control" style="font-size:12px;" readonly/></td>
		  </tr>	
		  <tr>
		   <td id="tdleft">모임장소 지도(click)</td>
		   <td>
		   <div id="map">
		   </div>    
		   </td>
		  </tr>
		  </c:when>
		  <c:when test="${article.TBOARD_PLACELA == 'NULL'}"> 
		  
		  </c:when>		  
		  </c:choose>
		  
			</table>
	</section>

	<div id="commandList">
	    
	        <c:choose>
	        
	        <c:when test="${mem_id!=null}">
		    <a href="tboardReplyForm.bo?tboard_num=${article.TBOARD_NUM}&page=${nowPage}" class="btn btn-outline-dark" style="font-size:12px;">
			답변
			</a> 
			</c:when>
			
			
			<c:when test="${mem_id==null}">			 
			</c:when>
            </c:choose>
			
			
			
			<c:choose>
			<c:when test="${article.MEM_ID == mem_id}">		
					<a href="tboardModifyForm.bo?tboard_num=${article.TBOARD_NUM}&page=${nowPage}&lev=${article.TBOARD_RE_LEV}" class="btn btn-outline-dark" style="font-size:12px;">			
			        수정
					</a> 
			</c:when>
					
			<c:when test="${article.MEM_ID != mem_id }">			 
			</c:when>
			</c:choose>
		
		
		
		
		
			<c:choose>
			<c:when test="${article.MEM_ID==mem_id }">
			<a href="tboardDeleteForm.bo?tboard_num=${article.TBOARD_NUM}&page=${nowPage}" class="btn btn-outline-dark" style="font-size:12px;">
			삭제 
			</a> 
			</c:when>
			<c:when test="${article.MEM_ID != mem_id }">			 
			</c:when>
			</c:choose> 		
			<%-- <a href="boardList.bo?page=<%=nowPage%>">[목록]</a>&nbsp;&nbsp; --%>
			<a href="javascript:history.go(-1)" class="btn btn-outline-dark" style="font-size:12px;">뒤로가기</a>
						
	</div>
	</div>	
    <input type="hidden" id="TBOARD_PLACELA" name="TBOARD_PLACELA" value="${article.TBOARD_PLACELA }">
    <input type="hidden" id="TBOARD_PLACEMA" name="TBOARD_PLACEMA" value="${article.TBOARD_PLACEMA }">
	
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4b4a735c0046067425af367f34003e43&libraries=services"></script>
<script type="text/javascript" src="tboard/javascript/tboard_veiw_script.js">


</script>
<%@include file="/footer.jsp" %>
</body>
</html>
