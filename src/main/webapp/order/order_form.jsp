<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@page import="vo.ProductBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<script type="text/javascript" src="./resources/js/validation.js"></script>


<!-- jQuery -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>



<script type="text/javascript">
	function CheckOrder() {

		const regName = /^[가-힣a-zA-Z]{2,}$/;
		const regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		const regCall = /^\d{3}\d{3,4}\d{4}$/;

		//이름 유효성
		if (od.o_name.value == "") {
			alert("수령인을 입력해주세요");
			return od.o_name.focus();
		} else if (!regName.test(document.od.o_name.value.trim())) {//이름에 특수문자가 들어간 경우
			alert("이름이 잘못 입력되었습니다.(영어 대소문자와 한글만 입력가능합니다.)");
			return document.od.o_name.select();//입력한 부분에 블록 설정되어 바로 수정가능한 상태로 만듦
		} else

		//휴대폰 유효성
		if (od.o_phone.value == "") {
			alert("받으시는분 전화번호를 입력해주세요");
			return od.o_phone.focus();
		} else if (!regCall.test(document.od.o_phone.value.trim())) {
			alert("휴대폰번호가 잘못 입력되었습니다. 숫자로만 입력해주세요.");
			return document.od.o_phone.select();//입력한 부분에 블록 설정되어 바로 수정가능한 상태로 만듦
		} else

		//이메일 유효성	
		if (od.o_email.value == "") {
			alert("이메일을 입력해주세요");
			return od.o_email.focus();
		} else if (!regEmail.test(document.od.o_email.value.trim())) {
			alert("이메일 형식이 올바르지 않습니다.");
			return document.od.o_email.select();//입력한 부분에 블록 설정되어 바로 수정가능한 상태로 만듦
		} else

		if (od.o_address1.value == "") {
			alert("우편번호를 입력해주세요");
			return od.o_address1.focus();
		}
		if (od.o_address2.value == "") {
			alert("주소를 입력해주세요");
			return od.o_address2.focus();
		}
		if (od.o_address3.value == "") {
			alert("상세주소를 입력해주세요");
			return od.o_address3.focus();
		}

		od.submit();

	}

	function form_reset() {
		if (confirm(" 다시쓰시겠습니까? ") == true) {
			od.reset();
		} else {
			return false;
		}

	}
</script>



<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function sample6_execDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) { //[선택]시 입력값 세팅
				console.log(data);

				document.getElementById("o_address1").value = data.zonecode;//우편번호

				var roadAddr = data.roadAddress;//도로명 주소
				var jibunAddr = data.jibunAddress;//지번 주소

				if (roadAddr != '') {//도로명 주소가 있으면 도로명 주소가 등록되고
					document.getElementById("o_address2").value = roadAddr;
				} else if (jibunAddr != '') {//도로명주소가 없고 지번주소만 있으면 지번주소를 등록함
					document.getElementById("o_address2").value = jibunAddr;
				}

				//만약 지번주소 대신 무조건 도로명 주소만 입력하고 싶다면 위의 if~else 대신	
				//document.getElementById("address1").value = data.roadAddress;
				//document.getElementById("address1").value = roadAddr;

				//상세주소 필드에 커서를 두어 바로 입력가능한 상태로 만듦
				document.getElementById("o_address3").focus();
			}
		}).open();
	}
</script>
<script type="text/javascript">
	function addFunc() {

		let point = parseInt(document.getElementById("point").innerHTML.trim());
		let price = parseInt(document.getElementById("price").innerHTML.trim());
		
		  
		let pointBox = parseInt(document.getElementById("pointBox").value);

		
		
		if (price > point) {
			if (pointBox > price) {
				document.getElementById("pointBox").value = point+"점";
			}
			if (pointBox > point) {
				document.getElementById("pointBox").value = point+"점";
			}
		}

		else if (price < point) {
			if (pointBox > price) {
				document.getElementById("pointBox").value = price+"점";
			}
			if (pointBox > point) {
				document.getElementById("pointBox").value = price+"점";
			}

		}
		
		sumFunc();
		document.od.point.value=pointBox;
		
	}

	function sumFunc() {
		let price = parseInt(document.getElementById("price").innerHTML.trim());

		let pointBox = parseInt(document.getElementById("pointBox").value);
		document.getElementById("sum").innerHTML = price - pointBox+"원";
		
		
	}
</script>



<title>주문 등록</title>


</head>
<body>

	<jsp:include page="../userHeader.jsp" />
	<jsp:include page="../boot.jsp" />



	<div id="page">
		<div class="colorlib-product">
			<div class="container">


				<div class="row row-pb-lg">
					<div class="col-md-10 offset-md-1">
						<div class="process-wrap">
							<div class="process text-center active">
								<p>
									<span>01</span>
								</p>
								<h3>장바구니</h3>
							</div>
							<div class="process text-center active">
								<p>
									<span>02</span>
								</p>
								<h3>주문등록</h3>
							</div>
							<div class="process text-center">
								<p>
									<span>03</span>
								</p>
								<h3>주문완료</h3>
							</div>
						</div>
					</div>
				</div>







				<div class="row row-pb-lg">
					<div class="col-md-12">
						<div class="product-name d-flex">
							<div class="one-forth text-left px-4">
								<span>상품</span>
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


						<!-- 장바구니에 반복될 부분 -->
						<div class="product-cart d-flex">
							<div class="one-forth">
								<a href="productDetail.po?prod_id=${param.prod_id}">
									<div class="product-img" style="background-image: url(productUpload/${param.prod_image});">
									</div>
								</a>
								<div class="display-tc">
									<h3>${param.prod_name}</h3>
								</div>
							</div>
							<div class="one-eight text-center">
								<div class="display-tc">
									<span class="price"><fmt:formatNumber
											pattern="###,###,###원" value="${param.prod_price}" /></span>
								</div>
							</div>
							<div class="one-eight text-center">
								<div class="display-tc">
									<input type="text" id="quantity" name="o_amount"
										class="form-control input-number text-center"
										value="${param.o_amount}" min="1" max="100">
								</div>
							</div>
							<div class="one-eight text-center">
								<div class="display-tc">
									<span class="price"> <c:set var="sum"
											value="${param.prod_price*param.o_amount}"></c:set> <fmt:formatNumber
											pattern="###,###,###원" value="${sum}" />


									</span>
								</div>
							</div>
							<div class="one-eight text-center">
								<div class="display-tc">
									<a href="#" class="closed"></a>
								</div>
							</div>
						</div>
						<!-- 장바구니 에 반복될 부분 -->




						<div class="row row-pb-lg">
							<div class="col-md-12">
								<div class="total-wrap">
									<div class="row">

										<div class="col-sm-4 text-center">
											<div class="total">
												<div class="sub">
													<p>
														<span>구매금액:</span> <span id="price"> ${sum }원</span>
													</p>
													<p>
														<span>사용가능포인트:</span> <span id="point">${mem_point}점</span>
													</p>
													<p>
														<span>포인트 사용:</span> <span><input type="text"
															id="pointBox" style="text-align: center" value="0" name="pointBox"
															size="8" class="form-control input-number text-center" 
															onKeyup="if(window.event.keyCode==13){addFunc()}"/></span>
													</p>

												</div>
												<div class="grand-total">

													<p>
														<span><strong>실구매금액:<span id="sum">${sum}원</span>
														</strong></span>
													</p>

												</div>
											</div>
										</div>



									</div>
								</div>
							</div>
						</div>



					</div>

					<div class="col-lg-8">
						<form method="post" name="od" class="colorlib-form"
							action="ProductOrder.po">
							<h2>주문정보입력</h2>
							<div class="row">
								<div class="col-md-12"></div>

								<div class="col-md-6">
									<div class="form-group">
										<label for="fname">받는분</label> <input type="text" id="fname"
											name="o_name" class="form-control"
											placeholder="Your firstname">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="lname">전화번호</label> <input type="text" id="lname"
											name="o_phone" class="form-control" placeholder="'-없이'">
									</div>
								</div>

								<div class="col-md-12">
									<div class="form-group">
										<label for="companyname">이메일</label> <input type="text"
											id="companyname" name="o_email" class="form-control"
											placeholder="Company Name">
									</div>
								</div>
								<div class="col-md-6"></div>

								<div class="form-group"></div>



								<div class="col-md-12">
									<div class="form-group">
										<label for="fname">주소</label>
										<div class="col-md-6">

											<input type="button" value="우편번호 찾기"
												onclick="sample6_execDaumPostcode();" padding="10px"
												required />
										</div>

										<div class="form-group">

											<input type="text" id="o_address1" name="o_address1"
												class="form-control" placeholder="우편번호">

										</div>


										<input type="text" id="o_address2" name="o_address2"
											class="form-control" placeholder="주소">
									</div>
									<div class="form-group">
										<input type="text" id="o_address3" name="o_address3"
											class="form-control" placeholder="상세주소">
									</div>
								</div>

								<div class="col-md-12">
									<div class="form-group">
										<label for="companyname">배송요청사항</label> <input type="text"
											id="towncity" name="o_require" class="form-control"
											placeholder="요청사항">
									</div>
								</div>




							</div>

							<div class="row">
								<div class="col-md-12 text-center">
									<input type="submit" value="주문하기" class="btn btn-primary"
										onclick="CheckOrder(); return false;"> <input
										type="button" value="다시쓰기" class="btn btn-primary"
										onclick="form_reset();"> <input type="button"
										value="주문" class="btn btn-primary" onclick="form_reset();">
								</div>
							</div>




							<input type="hidden" name="prod_id" value="${param.prod_id}" />
							<input type="hidden" name="prod_name" value="${param.prod_name}" />
							<input type="hidden" name="totalPrice" value="${sum}" /> <input
								type="hidden" name="prod_price" value="${param.prod_price}" />
							<input type="hidden" name="o_amount" value="${param.o_amount}" /> 
							<input type="hidden" name="point" value="0"/>
							<input type="hidden" name="usePoint" value="0"/>
						
						</form>
					</div>


				</div>
			</div>
		</div>





		<div class="gototop js-top">
			<a href="#" class="js-gotop"><i class="ion-ios-arrow-up"></i></a>
		</div>
</body>
</html>