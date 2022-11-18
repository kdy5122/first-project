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


function removeAll(){
	
	
	if(confirm(" 장바구니를 비우시겠습니까? ")==true){
		location.href="cartRemoveAll.od"
	}else{
	return false;
	}
}



function checkQtyUp(prod_id, qty) {		
	location.href="cartListQtyUp.od?prod_id="+prod_id+"&qty="+qty;	
} 
 
//장바구니 항목 수량 감소 요청을 할 때 '현재 수량이 1이 아닐 경우만 수량 감소' 요청을 하게 처리하는 함수
function checkQtyDown(prod_id, qty){
	if(qty != 1){//현재 수량이 1이 아니면 (즉, 1보다 크면, 2이상이면)
		//장바구니 항목 수량 감소 요청을 함. ★이 때, m_id값이 한글이면 인코딩처리하여 한글파라미터로 전송 
		location.href="cartListQtyDown.od?prod_id="+prod_id;
	}
} 
</script>
</head>


<body>
<jsp:include page="userHeader.jsp"></jsp:include>
	<div class="colorlib-product">
			<div class="container">
				<div class="row row-pb-lg">
					<div class="col-md-10 offset-md-1">
						<div class="process-wrap">
							<div class="process text-center active">
								<p><span>01</span></p>
								<h3>장바구니</h3>
							</div>
							<div class="process text-center">
								<p><span>02</span></p>
								<h3>주문등록</h3>
							</div>
							<div class="process text-center">
								<p><span>03</span></p>
								<h3>주문완료</h3>
							</div>
						</div>
					</div>
				</div>
			<c:if test="${cartList == null ||cartList.size()==0}">	
				<h2>장바구니에 담긴 물건이 없습니다.</h2>
			</c:if>
				
			<c:if test="${cartList != null && cartList.size()>0}">	
			<div class="row row-pb-lg">
							<div class="col-md-12">
							
									<div class="product-name d-flex">
										<div class="one-forth text-center px-4">
											<span>상품 </span>
										</div>
										<div class="one-eight text-center">
											<span>가격</span>
										</div>
										<div class="one-eight text-center">
											<span>수량</span>
										</div>
										<div class="one-eight text-center">
											<span>총금액</span>
										</div>
										<div class="one-eight text-center px-4">
											<span>삭제</span>
										</div>
									</div>
						<!-- 장바구니 에 반복될 부분 -->
						<c:forEach var="cart" items="${cartList}" varStatus="status">
									<div class="product-cart d-flex">
										<div class="one-forth">
											<a href="productDetail.po?prod_id=${cart.prod_id}">
											<div class="product-img" style="background-image: url(productUpload/${cart.image});">
											</div>
											</a>
											<div class="display-tc">
												<h3>${cart.prod_name}</h3>
											</div>
										</div>
										<div class="one-eight text-center">
											<div class="display-tc">
												<span class="price"><fmt:formatNumber pattern="###,###,###원" value="${cart.price}"/></span>
											</div>
										</div>
										<div class="one-eight text-center">
											<div class="display-tc">
												<input type="text" id="quantity" name="o_amount" class="form-control input-number text-center" value="${cart.qty}" min="1" max="100">
												
											</div>
												<div class="display-tc">
								<a href="javascript:checkQtyUp('${cart.prod_id}', ${cart.qty})"	>		
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square" viewBox="0 0 16 16">
 						 <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
 						 <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
								</svg>
								</a>

								  <a href="javascript:checkQtyDown('${cart.prod_id}', ${cart.qty})">	
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-dash-square" viewBox="0 0 16 16">
  						<path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
 						 <path d="M4 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 4 8z"/>
						</svg>
								</a>
								
											</div>
											 
										</div>
										<div class="one-eight text-center">
											<div class="display-tc">
												<span class="price">
											<fmt:formatNumber pattern="###,###,###원" value="${cart.totalMoney}"/>
												
												
												</span>
											</div>
										</div>
										<div class="one-eight text-center">
											<div class="display-tc">
												<a href="cartRemove.od?prod_id=${cart.prod_id}" class="closed"></a>
											</div>
										</div>
									</div>	
							</c:forEach>		
									<!-- 장바구니 에 반복될 부분 -->						
							
										
				<div class="row row-pb-lg">
					<div class="col-md-12">
						<div class="total-wrap">
							<div class="row" style="margin-top:15px">
								<div class="col-sm-8" style="margin-top:15px">
									<input type="button" class="btn btn-primary btn-addtocart" onclick="removeAll();" value="장바구니비우기">
									<input type="button" class="btn btn-primary btn-addtocart" onclick="location.href='productListView.po?prod_kind=racket'" value="쇼핑계속하기">
									<input type="button" class="btn btn-primary btn-addtocart" onclick="location.href='cartOrderform.od?total=${total}'" value="주문하기">
									
								</div>
								
								<div class="col-sm-4 text-center">
									<div class="total">
										
										<div class="grand-total">
										
											
											
											<p><span><strong>Total:</strong></span><fmt:formatNumber pattern="###,###,###원" value="${total}"/></span></p>
									
												
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		
	</c:if>

	</body>
</html>