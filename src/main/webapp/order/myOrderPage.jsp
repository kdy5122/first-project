<%@page import="vo.PageInfo"%>
<%@page import="vo.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"
 isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<title>리뷰글목록</title> 
<meta charset="utf-8">

<style>
img.img-fluid{
 width:100%;
 height:100%;
 object-fit:cover;
}
</style>

	 
<script>


</script>
</head>


<body>
<jsp:include page="../userHeader.jsp"></jsp:include>
	<div class="colorlib-loader"></div>

	<div id="page">
		
		<div class="breadcrumbs">
			<div class="container">
				<div class="row">
					
				</div>
			</div>
		</div>


		<div class="colorlib-product">
			<div class="container">
				<div class="row row-pb-lg">
					<div class="col-md-10 offset-md-1">
						<div class="process-wrap">
							<h2>나의 주문내역</h2>
							(최근 6개월 이내)
						</div>
					</div>
				</div>
				
				<c:if test="${userOrderList != null && userOrderList.size() > 0 }">	
				<div class="row row-pb-lg">
					<div class="col-md-12">
						<div class="product-name d-flex">
							<div class="one-eight text-center">
								<span>주문날짜</span>
							</div>
							
							<div class="one-forth text-center px-4">
									<span>주문번호</span>	
							</div>
							
							
							<div class="one-eight text-center">
								<span>총가격</span>
							</div>
							<div class="one-eight text-center">
								<span>주문상태</span>
							</div>
							<div class="one-eight text-center">
								<span>상세보기</span>
							</div>
						</div>	
					</div>
				</div>
				
				<c:forEach var="order" items="${userOrderList}" varStatus="status">
				<div class="product-cart d-flex">
										
										
										<div class="one-eight text-center">
											${order.o_date}
											
											
											

										</div>
											<div class="one-forth text-center px-4" style="text-decoration:underline">
											<a href="userOrderDetail.od?order_id=${order.order_id}">
												${order.order_id} 
											</a>
											</div>
										
										
										
										<div class="one-eight text-center">
											
												
											<fmt:formatNumber pattern="###,###,###원" value="${order.totalPrice}"/>
											
										</div>
										<div class="one-eight text-center">
										
												
											${order.o_status} 
											
										</div>
										<div class="one-eight text-center">
										
													
											<input type="button" value="취소/반품" onclick= "location.href='userCancleOrder.od?order_id=${order.order_id}'">&nbsp;
											
										</div>
									</div>	
								
				<div class="row row-pb-lg">
					
				</div>
	</c:forEach>
				
					<div class="col-md-12 text-center">
						<div class="block-27">
		               <ul>
		               	<!-- 페이지 이전 버튼 -->
			            <c:choose>
			            <c:when test="${pageInfo.page<=1}">
			           		 <li><i class="ion-ios-arrow-back"></i></li> <!-- 1보다 작으면 a태그걸지않는다. -->
			            </c:when>
			            <c:otherwise> 
			            	 <li><a href="userOrderView.od?page=${pageInfo.page-1 }"><i class="ion-ios-arrow-back"></i></a></li>
			            </c:otherwise>
			            </c:choose>

		                 <!-- 페이지번호 처리 -->
		               	<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
		               
		                 		 
			                 <c:choose>
				           		 <c:when test="${pageInfo.page==i}">
				           			 <li class="active"><span>${i}</span></li><!-- a 태그 걸지 않고 active 상태  -->
				          		  </c:when>
				          	   <c:otherwise> 
				            	  <li><a href="userOrderView.od?page=${i}">${i}</a></li>
				        	    </c:otherwise>
				            </c:choose>
		                 
		                  </c:forEach>
		                  
		                  <!-- 페이지 다음 버튼 -->
		                
		                  
		                  <c:choose>
				            <c:when test="${pageInfo.page==pageInfo.maxPage}">
				           		 <li><i class="ion-ios-arrow-forward"></i></li> <!-- 현재페이지가 마지막페이지면 a태그걸지않는다. -->
				            </c:when>
				            <c:otherwise> 
				            	 <li><a href="userOrderView.od?page=${pageInfo.page+1 }"><i class="ion-ios-arrow-forward"></i></a></li>
				            </c:otherwise>
			            </c:choose>
	
		               </ul>
		               
		            </div>
					</div>
	





</c:if>
	
	
	
	
	
	
	
	
	
	
	<c:if test="${userOrderList eq null}">
		<div>주문한 내역이 없습니다.</div>
	</c:if>
				</div>
			</div>
		</div>
	</body>
</html>