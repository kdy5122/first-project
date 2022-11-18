<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<link rel="stylesheet" type="text/css" href="style.css">

</head>

<body onload="showImage()">
	

			<%@include file="userHeader.jsp"%>
		
	
	

	<section>
		<div id="bodyimg">
			<img id="introing">
		</div>

		<nav>
			<ul class="mapmenu" style="padding: 50px;">
				<li><h5>찾아 오시는길</h5></li>
				<li><i class="icon-location"></i>주소 : 대구 광역시 OO0구 XXXX로 000길 00</li>
				<li><i class="icon-phone3"></i>전화 :053-000-0000 </a></li>
				<li>영업시간 : 10:00 ~ 22:00</li>
			</ul>
			
			<ul  style="padding-bottom : 50px;">
				<iframe
				src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3233.8490206344586!2d128.50391831526358!3d35.852705480154704!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3565e539748d77cf%3A0xc9a0370fd6c120fa!2z7JiB64Ko6riw7Iig7KeB7JeF7KCE66y47ZWZ6rWQ!5e0!3m2!1sko!2skr!4v1665727747797!5m2!1sko!2skr"
				width="64%" height="400px" alloowfullscreen="" aria-hidden="false"
				tabindex="0" ></iframe>
			</ul>
		</nav>
	</section>
	
	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="ion-ios-arrow-up"></i></a>
	</div>
	
	<%@include file="footer.jsp"%>
</body>
</html>