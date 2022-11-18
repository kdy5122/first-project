<%@page import="vo.PageInfo"%>
<%@page import="vo.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<title>리뷰글목록</title>
<meta charset="utf-8">

<style>
img.img-fluid {
	width: 100%;
	height: 100%;
	object-fit: cover;
}
</style>
<script type="text/javascript">
	function orderStatus() {
		let o_status = document.getElementById("process").value;
		let order_id = document.getElementById("order_id").value;
	

		location.href = "updateOrderStatus.od?o_status=" + o_status
				+ "&order_id=" +order_id;
	
	}
</script>



</head>


<body>
	<jsp:include page="../userHeader.jsp"></jsp:include>

	<div class="colorlib-loader"></div>

	<div id="page">

		<div class="colorlib-product">
			<div class="container">
				<div class="row">
					<div class="col-md-12 text-center">
						<div class="block-27">

							<h3 class="head">주문상세 조회</h3>

							<br>
	<input type="hidden" id="order_id" value="${param.order_id} " name="order_id">
	<input type="hidden" id="mem_id" value="${orderUserInfo[8]}" name="mem_id">
	
							<div class="col-sm-8 offset-sm-2 text-center colorlib-heading">

								<c:if test="${sessionScope.mem_grade==2}">
									<div class="desc">
										<h5>
											주문상태 <select name="o_status" id="process"
												onchange="orderStatus()">
												<option value="주문대기">주문대기</option>
												<option value="주문승인">주문승인</option>
												<option value="배송중">배송중</option>
												<option value="처리완료">처리완료</option>
												
											</select>
										</h5>
								
									
									</div>
									<br>

									<div class="review">

										<div class="desc">
											<h5>상품번호</h5>

											<c:forEach var="orderList" items="${orderDetailList}">
										<a href="">상품번호: ${orderList.prod_id} X ${orderList.o_amount}ea</a>
			
												<br>

											</c:forEach>
										</div>
									</div>
								</c:if>

							

								<div class="review">

									<div class="desc">
										<h5>상품명</h5>

										<c:forEach var="orderList" items="${orderDetailList}">
										'${orderList.prod_name}' x ${orderList.o_amount}ea<br>

										</c:forEach>
									</div>
								</div>
								<div class="review">
									<div class="desc">
										<h5>총가격</h5>
										<fmt:formatNumber pattern="###,###,###원" value="${ orderUserInfo[7]}" /><br>
										사용포인트:<fmt:formatNumber pattern="###,###,###원" value="${ orderUserInfo[9]}" /><br>
										실 결제금액
										<fmt:formatNumber pattern="###,###,###원" value="${ orderUserInfo[7]-orderUserInfo[9]}" />


									</div>
								</div>


								<div class="review">
									<div class="desc">
										<h5>수령인</h5>
										${ orderUserInfo[0]}<br> ${ orderUserInfo[1]}<br> ${ orderUserInfo[2]}<br>

									</div>
								</div>

								<div class="review"></div>

								<div class="review">
									<div class="desc">
										<h5>배송지</h5>
										(${orderUserInfo[3]})<br> ${orderUserInfo[4]}
										${orderUserInfo[5]}

									</div>
								</div>
								<div class="review">
									<div class="desc">
										<h5>배송요청사항</h5>
										${orderUserInfo[6]}
											${orderPoint}

									</div>
								</div>
								
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

















