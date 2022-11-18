<%@page import="dao.ReviewDAO" %>
<%@page import="vo.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  
 isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
   <meta charset="utf-8">
<title>후기상세</title>
  
<style>
.review_img  {
 width:100%;
 height:100%;
 object-fit:cover;}
 
.img-fluid{
  width:500px;
 height:500px;
 object-fit:cover;
 
 }

</style>
<script type="text/javascript">
function delet(){
	confirm("삭제하시겠습니까?");
}
</script>

	</head>
	<body>
		<jsp:include page="../userHeader.jsp"></jsp:include>
		


	
		<div class="colorlib-about">
			<div class="container">
				<div class="row row-pb-lg"> <!-- --- -->
					<div class="col-sm-6 mb-3">
					
						<div class="video colorlib-video" >
							
									<c:choose>
									<c:when test="${article.rev_fileName!=null}">
										
									<img src="reviewUpload/${article.rev_fileName}" class="img-fluid" />
									</c:when>
									<c:otherwise>
										<img src="image/noImage.png" class="img-fluid" />
									</c:otherwise>
								</c:choose>
							
						</div>
					
					</div>
					<div class="col-sm-6">
						<div class="about-wrap">
							
								<h4><a href="productDetail.po?prod_id=${article.prod_id}">해당 상품 구매하기</a></h4>
								
							
							
							 <p>
							 <h3>
							 	<c:if test="${article.rev_score == 5}">★★★★★</c:if>
								<c:if test="${article.rev_score == 4}">★★★★</c:if>
								<c:if test="${article.rev_score == 3}">★★★</c:if>
								<c:if test="${article.rev_score == 2}">★★</c:if>
								<c:if test="${article.rev_score == 1}">★</c:if>
							 </h3>
							 </p>
							<p>${requestScope.article.rev_content}</p>
			
						</div>
					</div>	
				</div>
				
					<p>작성일: ${requestScope.article.rev_date} 작성자: ${article.mem_id}</p>
				
					<!--후기상세페이지는 목록을 타고 들어와야하므로... -->
					<input type="button" value="후기목록" onclick="location.href='rev_boardList.do'">
					
					<!-- 로그인한 사람과 글작성자가 같을시 나오는 버튼  -->
					<c:if test="${(article.mem_id==sessionScope.mem_id) or (sessionScope.mem_grade == '2')}"><!-- update같은경우 글작성view를 가져오고 한번더 처리, 삭제는 바로처리 -->
						<input type="button" value="수정" onclick="location.href='revUpdateForm.do?board_no=${article.board_no}&rev_fileName=${article.rev_fileName}'" >
						<input type="button" value="삭제" onclick="delet(); location.href='revDeletePro.do?board_no=${article.board_no}&rev_fileName=${article.rev_fileName}'">
					</c:if>
					
			</div>
		
		</div>

	
	
	
	
	</body>
</html>

