<%@page
	import="org.eclipse.jdt.internal.compiler.lookup.IQualifiedTypeResolutionListener"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.Cart"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
</html>
<head>
<meta charset="UTF-8">
<title>header 부분</title>
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="style.css">


<script type="text/javascript">
	var imgArray = new Array();
	imgArray[0] = "image/home1.jpg";
	imgArray[1] = "image/home2.jpg";
	imgArray[2] = "image/home3.jpg";

	function showImage() {
		var imgNum = Math.round(Math.random() * 2);
		var objImg = document.getElementById("introing");
		objImg.src = imgArray[imgNum];
		setTimeout(showImage, 2000);
	}
</script>

<style>
* {
	font-family: 'Gowun Dodum', sans-serif;
}
</style>
<%-- link rel : 현재문서와 외부리소스 사이의 연관관계--%>

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700"
	rel="stylesheet">

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

				<%
				String id = (String) session.getAttribute("mem_id");
				//Member loginMember=(Member)session.getAttribute("loginMember");
				String mem_grade = (String) session.getAttribute("mem_grade");
				ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");

				if (id == null) {
				%>
				<li><a href="loginForm.jsp">[Login]</a></li>
				<li><a href="joinForm.jsp">[회원가입]</a></li>
				<li class="cart"><a href="cart.jsp"><i
						class="icon-shopping-cart"></i> Cart[0]</a></li>
				<%
				} else {
				if (mem_grade.equals("2")) {
				%>
					
				<li>관리자님 환영합니다.</li>
				<li><a href="managerHeader.jsp">[관리자 모드]</a></li>
				<li><a href="index.jsp">[일반 모드]</a></li>
				<li><a href="logout">[로그아웃]</a></li>
				<%
				} else {
				%>
				<li class="has-dropdown"><%=id%>님 환영합니다.</a>
					<ul class="dropdown">
						<li><a href="userView.me">마이페이지</a></li>
						<li><a href="userOrderView.od">나의주문내역</a></li>
					</ul>
				<li><a href="logout">[로그아웃]</a></li>
				<%
				if (cartList == null) {
				%>
				<li class="cart"><a href="cart.jsp"><i
						class="icon-shopping-cart"></i> Cart[0]</a></li>
				<%
				} else {
				%>
				<li class="cart"><a href="cartList.od"><i
						class="icon-shopping-cart"></i> Cart[<%=cartList.size()%>]</a></li>
				<%
				}
				}
				}
				%>

			</ul>
		</nav>



		<div class="header-logo"  style="text-align: center;">
			<a href="index.jsp"><img src="image/logo.jpg" height="80%"
				width="80%"></a>
		</div>

		<!-- <nav class="header-menu"> -->
		<nav class="colorlib-nav" role="navigation">
			<ul class="header-menu2">


				<li class="has-dropdown"><a href="#">Supplies</a>
					<ul class="dropdown">
						<li><a href="productListView.po?prod_kind=racket">Racket</a></li>
						<li><a href="productListView.po?prod_kind=acc">ACC</a></li>

					</ul>
				<li><a href="rev_boardList.do">Review</a></li>
				<li><a href="qboardList.qna">Q&A</a></li>
				<li><a href="tboardList.bo">TogetherTennis</a></li>
				</ul>
		
		
		<div class="sale">
				<div class="container">
					<div class="row">
						<div class="col-sm-8 offset-sm-2 text-center">
							<div class="row">
								<div class="owl-carousel2">
									<div class="item">
										<div class="col">
											<h3><a href="#">※ Welcome Tennis Supplies Shop! </a></h3>
										</div>
									</div>
									<div class="item">
										<div class="col">
											<h3><a href="#">주변 사람들과 즐거운 테니스 경기 약속을 잡아보세요!!! </a></h3>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</nav>
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