<%@page import="vo.PageInfo"%>
<%@page import="vo.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html>
<head>
<title>라켓상품목록</title>
<meta charset="utf-8">

</head>



<body>
	<jsp:include page="../userHeader.jsp"></jsp:include>
	<jsp:include page="../boot.jsp"></jsp:include>


					<div class="menu text-center">
						<p>
							<a href="productListView.po?prod_kind=racket">테니스 라켓</a> 
							<a href="productListView.po?prod_kind=acc">ACC</a>
<a href="searchPriceForm.po?line=0&prod_kind=${param.prod_kind}"> <svg
						xmlns="http://www.w3.org/2000/svg" width="16" height="16"
						fill="currentColor" class="bi bi-sort-numeric-up-alt"
						viewBox="0 0 16 16">
				  <path fill-rule="evenodd"
							d="M11.36 7.098c-1.137 0-1.708-.657-1.762-1.278h1.004c.058.223.343.45.773.45.824 0 1.164-.829 1.133-1.856h-.059c-.148.39-.57.742-1.261.742-.91 0-1.72-.613-1.72-1.758 0-1.148.848-1.836 1.973-1.836 1.09 0 2.063.637 2.063 2.688 0 1.867-.723 2.848-2.145 2.848zm.062-2.735c.504 0 .933-.336.933-.972 0-.633-.398-1.008-.94-1.008-.52 0-.927.375-.927 1 0 .64.418.98.934.98z" />
				  <path
							d="M12.438 8.668V14H11.39V9.684h-.051l-1.211.859v-.969l1.262-.906h1.046zM4.5 13.5a.5.5 0 0 1-1 0V3.707L2.354 4.854a.5.5 0 1 1-.708-.708l2-1.999.007-.007a.498.498 0 0 1 .7.006l2 2a.5.5 0 1 1-.707.708L4.5 3.707V13.5z" />
				</svg> 높은가격순
				</a> 
				
				<a href="searchPriceForm.po?line=1&prod_kind=${param.prod_kind}">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-sort-numeric-down-alt" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M11.36 7.098c-1.137 0-1.708-.657-1.762-1.278h1.004c.058.223.343.45.773.45.824 0 1.164-.829 1.133-1.856h-.059c-.148.39-.57.742-1.261.742-.91 0-1.72-.613-1.72-1.758 0-1.148.848-1.836 1.973-1.836 1.09 0 2.063.637 2.063 2.688 0 1.867-.723 2.848-2.145 2.848zm.062-2.735c.504 0 .933-.336.933-.972 0-.633-.398-1.008-.94-1.008-.52 0-.927.375-.927 1 0 .64.418.98.934.98z"/>
  <path d="M12.438 8.668V14H11.39V9.684h-.051l-1.211.859v-.969l1.262-.906h1.046zM4.5 2.5a.5.5 0 0 0-1 0v9.793l-1.146-1.147a.5.5 0 0 0-.708.708l2 1.999.007.007a.497.497 0 0 0 .7-.006l2-2a.5.5 0 0 0-.707-.708L4.5 12.293V2.5z"/>
</svg>
				
				 낮은가격순
				</a>
							
						</p>
					</div>
<br><br>



	<c:if test="${listRacket !=null && pageInfo.listCount >0 }">



		<div class="colorlib-featured">
			<div class="container">
			
				<div class="row">


					<!-- 상품 이미지 반복되는 구간 -->

					<c:forEach var="racket" items="${listRacket}" varStatus="status">
						<div class="col-sm-4 text-center">

							<c:if test="${sessionScope.mem_grade == '2'}">
								<input type="submit" value="수정"
									onclick="location='productModifyForm.po?prod_id=${racket.prod_id}'">
								<input type="submit" value="삭제"
									onclick="location='productDeleteForm.po?prod_id=${racket.prod_id}'">
								<br></br>
							</c:if>

							<a href="productDetail.po?prod_id=${racket.prod_id} ">
								<div class="featured">
									<div class="featured-img featured-img-2"
										style="background-image: url(productUpload/${racket.prod_image});">


									</div>

									<h5>${racket.prod_name}</h5>
									
									￦<fmt:formatNumber pattern="###,###,###원" value="${racket.prod_price}" />
									

								</div>
							</a> <br>
							<br>
							<br>
							<br>
						</div>

					</c:forEach>



				</div>
			</div>
		</div>





		<div class="colorlib-product">
			<div class="container">

				<!-- 페이지 처리 갖다붙히기 -->
				<div class="row">
					<div class="col-md-12 text-center">
						<div class="block-27">
							<ul>
								<!-- 페이지 이전 버튼 -->
								<c:choose>
									<c:when test="${pageInfo.page<=1}">
										<li><i class="ion-ios-arrow-back"></i></li>
										<!-- 1보다 작으면 a태그걸지않는다. -->
									</c:when>
									<c:otherwise>
										<li><a href="productListView.po?page=${pageInfo.page-1 }&prod_kind=${param.prod_kind}"><i
												class="ion-ios-arrow-back"></i></a></li>
									</c:otherwise>
								</c:choose>

								<!-- 페이지번호 처리 -->
								<c:forEach var="i" begin="${pageInfo.startPage }"
									end="${pageInfo.endPage }" step="1">


									<c:choose>
										<c:when test="${pageInfo.page==i}">
											<li class="active"><span>${i}</span></li>
											<!-- a 태그 걸지 않고 active 상태  -->
										</c:when>
										<c:otherwise>
											<li><a href="productListView.po?page=${i}&prod_kind=${param.prod_kind}">${i}</a></li>
										</c:otherwise>
									</c:choose>

								</c:forEach>

								<!-- 페이지 다음 버튼 -->


								<c:choose>
									<c:when test="${pageInfo.page==pageInfo.maxPage}">
										<li><i class="ion-ios-arrow-forward"></i></li>
										<!-- 현재페이지가 마지막페이지면 a태그걸지않는다. -->
									</c:when>
									<c:otherwise>
										<li><a href="productListView.po?page=${pageInfo.page+1 }&prod_kind=${param.prod_kind}"><i
												class="ion-ios-arrow-forward"></i></a></li>
									</c:otherwise>
								</c:choose>

							</ul>

						</div>
					</div>
				</div>



			</div>
		</div>
	</c:if>


	<c:if test="${pageInfo.listCount==0 }">
		<font size="6">등록된 상품이 없습니다.</font>
	</c:if>



	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="ion-ios-arrow-up"></i></a>
	</div>



</body>
</html>



