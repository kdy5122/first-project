<%@page import="org.eclipse.jdt.internal.compiler.lookup.IQualifiedTypeResolutionListener"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header 부분</title>
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="style.css">

<style>
*{font-family: 'Gowun Dodum', sans-serif;}

</style>

  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="css/icomoon.css">
	<!-- Ion Icon Fonts-->
	<link rel="stylesheet" href="css/ionicons.min.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.min.css">

	<!-- Magnific Popup -->
	<link rel="stylesheet" href="css/magnific-popup.css">

	<!-- Flexslider  -->
	<link rel="stylesheet" href="css/flexslider.css">

	<!-- Owl Carousel -->
	<link rel="stylesheet" href="css/owl.carousel.min.css">
	<link rel="stylesheet" href="css/owl.theme.default.min.css">
	
	<!-- Date Picker -->
	<link rel="stylesheet" href="css/bootstrap-datepicker.css">
	<!-- Flaticons  -->
	<link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">

	<!-- Theme style  -->
	<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<header>
		<nav class="colorlib-nav" role="navigation">
			<ul class="header-menu1" style="margin: 0px";>
				<li  class="has-dropdown">관리자님 환영합니다.
					<ul class="dropdown">
					 <li><a href="userView.me">마이페이지</a></li>
					</ul>
				<li><a href="managerHeader.jsp">[관리자 모드]</a></li>
				<li><a href="index.jsp">[일반 모드]</a></li>
				<li><a href="logout">[로그아웃]</a></li>
			</ul>
		</nav>

		
		
		
		
		<div class="header-logo" style="text-align: center;">
			<a href="index.jsp"><img src="image/logo.jpg" height="80%"
				width="80%"></a>
		</div>
	
	
	
	
	
		<nav class="colorlib-nav" role="navigation">
			<ul class="header-menu2">
				<li><a href="memberListAction.me">회원관리</a></li>
				<li class="has-dropdown"><a href="productForm.po">상품등록/관리</a>
					<ul class="dropdown">
						<li><a href="productForm.po">상품등록</a></li>
						<li><a href="productListManager.po">상품관리</a></li>
					</ul>
				<li class="has-dropdown"><a href="manageOrderList.od">주문관리/매출조회</a>
					<ul class="dropdown">
						<li><a href="manageOrderList.od">실시간<br>주문관리</a></li>
						<li><a href="salesList.od">매출조회</a></li>
						<li><a href="userOrderMoney.od">회원별<br>구매금액</a></li>
					</ul>
				<li><a href="qboardList.qna">QNA관리</a></li>
			</ul>
		</nav>
		<div class="header-notice" style="padding: 20px;">
			<marquee> ※관리자님!!! 오늘 하루도 화이팅 입니다!!! </marquee>
		</div>
	</header>
	
	
	
	
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- popper -->
	<script src="js/popper.min.js"></script>
	<!-- bootstrap 4.1 -->
	<script src="js/bootstrap.min.js"></script>
	<!-- jQuery easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Flexslider -->
	<script src="js/jquery.flexslider-min.js"></script>
	<!-- Owl carousel -->
	<script src="js/owl.carousel.min.js"></script>
	<!-- Magnific Popup -->
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/magnific-popup-options.js"></script>
	<!-- Date Picker -->
	<script src="js/bootstrap-datepicker.js"></script>
	<!-- Stellar Parallax -->
	<script src="js/jquery.stellar.min.js"></script>
	<!-- Main -->
	<script src="js/main.js"></script>
</body>
</html>