<%@page import="vo.PageInfo"%>
<%@page import="vo.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"
 isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<title>리뷰글목록</title> 
<meta charset="utf-8">

<style>

</style>

</head>


<body>
	<jsp:include page="../userHeader.jsp"></jsp:include>
		

		<div class="colorlib-product">
			<div class="container">
				<div class="row">
				
				
				
					<div class="col-sm-8 offset-sm-2 text-center colorlib-heading">
						<h2>Review</h2><!-- css h2 fontfamily 7340 -->
						
						<a href="boardWriteForm.do">
						<h5>작성하기
							<svg xmlns="http://www.w3.org/2000/svg" width="17px" height="19px" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
		 					 <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
							</svg>
						</h5>
						</a>
					</div>
				</div>	
		
		
		<!-- 후기 테이블에서 데이터 불러오기 -->
		<c:if test="${listReview !=null && pageInfo.listCount >0 }">	
		<div class="row row-pb-md">
				
				
				<c:forEach var="review" items="${listReview}" varStatus="status" >
					<div class="col-sm-4 text-center">
					
					
					<a href="revDetailView.do?board_no=${review.board_no}" >
							<div class="featured">
							
								<c:choose>
									<c:when test="${review.rev_fileName!=null}">
									<div class="featured-img featured-img-2" style="background-image: url(reviewUpload/${review.rev_fileName});">
									</c:when>

									<c:otherwise>
									<div class="featured-img featured-img-2" style="background-image: url(image/noImage.png);">
									</c:otherwise>
								</c:choose>
								
					
									</div>
									
									<div class="desc"  >
								
								<h2>
								<c:if test="${review.rev_score == 5}">★★★★★</c:if>
								<c:if test="${review.rev_score == 4}">★★★★</c:if>
								<c:if test="${review.rev_score == 3}">★★★</c:if>
								<c:if test="${review.rev_score == 2}">★★</c:if>
								<c:if test="${review.rev_score == 1}">★</c:if>
								</h2>
								
								<span class="price">작성자 : ${review.mem_id}</span>
									</div>
								</a>
								<br>
								<br>
								<br>
							
							
								
								
								<!-- 업로드 파일이 없는경우 다른이미지 대체  -->
								<!-- reviewUpload/sm_ -->
								<!-- reviewUpload/${review.rev_fileName} -->
						
						
							
							
							<div >
							
							
						</div>
							
							
							
						</div>
					</div>
					
					
				</c:forEach>
				<div class="w-100"></div>
			
	</div>
				<div class="row">
					<div class="col-md-12 text-center">
						<div class="block-27">
		               <ul>
		               	<!-- 페이지 이전 버튼 -->
			            <c:choose>
			            <c:when test="${pageInfo.page<=1}">
			           		 <li><i class="ion-ios-arrow-back"></i></li> <!-- 1보다 작으면 a태그걸지않는다. -->
			            </c:when>
			            <c:otherwise> 
			            	 <li><a href="rev_boardList.do?page=${pageInfo.page-1 }"><i class="ion-ios-arrow-back"></i></a></li>
			            </c:otherwise>
			            </c:choose>

		                 <!-- 페이지번호 처리 -->
		               	<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
		               
		                 		 
			                 <c:choose>
				           		 <c:when test="${pageInfo.page==i}">
				           			 <li class="active"><span>${i}</span></li><!-- a 태그 걸지 않고 active 상태  -->
				          		  </c:when>
				          	   <c:otherwise> 
				            	  <li><a href="rev_boardList.do?page=${i}">${i}</a></li>
				        	    </c:otherwise>
				            </c:choose>
		                 
		                  </c:forEach>
		                  
		                  <!-- 페이지 다음 버튼 -->
		                
		                  
		                  <c:choose>
				            <c:when test="${pageInfo.page==pageInfo.maxPage}">
				           		 <li><i class="ion-ios-arrow-forward"></i></li> <!-- 현재페이지가 마지막페이지면 a태그걸지않는다. -->
				            </c:when>
				            <c:otherwise> 
				            	 <li><a href="rev_boardList.do?page=${pageInfo.page+1 }"><i class="ion-ios-arrow-forward"></i></a></li>
				            </c:otherwise>
			            </c:choose>
	
		               </ul>
		               
		            </div>
					</div>
				</div>
	    
</c:if>

<c:if test="${pageInfo.listCount==0 }">
<font size="6">등록된 리뷰가 없습니다.</font>
</c:if>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="ion-ios-arrow-up"></i></a>
	</div>
	
	

	</body>
</html>