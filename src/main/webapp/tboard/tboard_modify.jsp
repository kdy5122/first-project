<%@page import="vo.TBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<meta charset="UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	<title>테니스모임 글 수정</title>
	
	<style type="text/css">
   #writeForm{background-color: white;}
   h4{
      text-align: center; padding-top:20px ; color:black;
   }
   #td_left{
      text-align: center;
   }
    
   #commandCell{
      text-align: center;
      margin-bottom: 15px;
   }
   #table-first{ margin:0 auto; width: 40%; border-color: black;  font-size: 12px; border-color: #e1e4e1;}
   #img{width: 100%; height: 150px;}
   #previewP{width: 100%; height: 150px;}
   
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
<!-- 게시판 등록 -->
<c:set var="nowPage"  value="${pageInfo.page}"/>

<section id="writeForm">
<form action="tboardModifyPro.bo" method="post" name = "modifyform" enctype="multipart/form-data">
<input type = "hidden" name = "TBOARD_NUM" value = "${article.TBOARD_NUM}"/>
<input type="hidden" name="page"    value="${nowPage}" /> \
<h4>모임게시판 글수정</h4>
<table border="1" id="table-first" class="table table-bordered">
	<tr>
		<td id="td_left">
			<label for = "TBOARD_NAME">작성자 ID</label>
		</td>
		<td style="text-align:left">
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
		
			<c:if test="${article.TBOARD_IMAGE!=null }"> <%--기존에 이미지가 있다면--%>
			<input type="hidden" id="TBOARD_PIMAGE" name="TBOARD_PIMAGE" value="${article.TBOARD_IMAGE}"> <%--이미지 수정안하고 수정시 히든으로 기존의 이미지값보냄--%>
			<img src="${pageContext.request.contextPath}/tboardUpload/${article.TBOARD_IMAGE}" id="previewP"> <%--기존 이미지 표시--%>			
			</c:if>

			<img id="preview" style="width:100%; height: 150px; display: none;"/> <%--새로 선택한 이미지 표시 --%>


		</td>
	 </tr>
	 	 

		<tr>
		  <td id="td_left"><label for="TBOARD_FILE"> 파일 첨부 </label></td>
		  <td><input name="TBOARD_FILE" class="form-control" type="file" id="TBOARD_FILE" style="font-size:12px;"/>
		  <%--input hidden으로 파일이 설정되지않았다면 기존의 파일보내고 , 파일첨부 오른쪽에 기존파일명 표시 해놓고--%>
		  </td>
		  
		</tr>

	   
	   
	   	<tr>
					<td id="td_left"><label for="TBOARD_IMAGE"> 내용 이미지 첨부 </label></td>
					<td><input type="file" name="TBOARD_IMAGE" class="form-control" id="TBOARD_IMAGE" style="font-size:12px;"
					accept="image/gif, image/jpeg, image/png, image/jfif" onchange="fileCheck(this),readURL(this)" />					
					</td>
		</tr>
	   
		
      		
	         <tr>
				<td id="td_left">모임 장소</td>
				<td>
				  <div class="input-group mb-3">	
					<input type="text" name="detailAddress" id="address"size="30" class="form-control" value="${article.TBOARD_ADDRESS }">
					<button type="button" id="searchBtn" class="btn btn-outline-dark" style="font-size:12px;" >주소입력</button>
				  </div>	
				</td>					
			</tr>								
			<tr>
				 <td colspan="2">
						<div id="map" style="width:100%;height:300px;"></div>
				 </td>
		    </tr>	 

	   
	  
	
</table>
	<div id = "commandCell">
	         <%--로그인 한 사람의 본인의 작성글만 수정할 수 있는 조건문 --%>
	         <c:choose>
	         <c:when test="${mem_id == article.MEM_ID}">
			<a href="javascript:modifyboard()" class="btn btn-outline-dark" style="font-size:12px;" onclick="check();">수정</a>&nbsp;&nbsp;
			 </c:when>
			 <c:when test="${mem_id != article.MEM_ID}">
			 
			</c:when>
			
			</c:choose>			
			<a href="javascript:history.go(-1)" class="btn btn-outline-dark" style="font-size:12px;">뒤로가기</a>
			
	</div>
	
	<%--카카오 API 의 위도, 경도값이 담긴값을 hidden으로 form에 실어 보낸다--%>
	
	<input type="hidden" id="TBOARD_PLACELA" name="TBOARD_PLACELA" value="">
    <input type="hidden" id="TBOARD_PLACEMA" name="TBOARD_PLACEMA" value="">
 
</form>
</section>      
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4b4a735c0046067425af367f34003e43&libraries=services"></script>
    <script type="text/javascript" src="tboard/javascript/tboard_modify_script.js"></script>


<jsp:include page="/footer.jsp"/>
</body>
</html>
