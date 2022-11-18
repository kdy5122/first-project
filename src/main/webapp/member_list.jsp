<%@ page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>

</head>
<body>
	<%@include file="managerHeader.jsp"%>

	<section>
		<div style="padding-left: 5%; padding-right: 5%; padding-bottom: 5%;">
			<div
				style="border: 1px solid gold; padding-top: 50px; padding-bottom: 50px;">
				<form>
					<div style="padding-bottom: 10px;">
						<b style="font-size: 40px; color: gold;">회원목록</b>
					</div>

					<div align="center" style="padding-top: 10px;">
						<table border="1" width="90%">
							<tr align="center">
								<th>아이디</th>
								<th>이름</th>
								<th>정보 보기</th>
								<th>삭제</th>

							</tr>

							<c:forEach var="member" items="${memberList}">
								<tr align="center">
									<td>${member.mem_id}</td>
									<td>${member.mem_name}</td>
									<td><a style="text-decoration: none; "
										href="memberViewAction.me?id=${member.mem_id}">정보보기</a></td>
									<td><a style="text-decoration: none;"
										href="memberDeleteAction.me?id=${member.mem_id}">삭제</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</form>
				
				<div style="padding-top: 20px;">
			<tr>
				<!-- 페이지 이전 버튼 -->
				<c:choose>
					<c:when test="${pageInfo.page<=1}">
						<td></td>
						<!-- 1보다 작으면 a태그걸지않는다. -->
					</c:when>
					<c:otherwise>
						<td><a href="memberListAction.me?page=${pageInfo.page-1 }"><i>이전</i></a></td>
					</c:otherwise>
				</c:choose>

				<!-- 페이지번호 처리 -->
				<c:forEach var="i" begin="${pageInfo.startPage }"
					end="${pageInfo.endPage }" step="1">


					<c:choose>
						<c:when test="${pageInfo.page==i}">
							<td><span>${i}</span></td>
							<!-- a 태그 걸지 않고 active 상태  -->
						</c:when>
						<c:otherwise>
							<td><a href="memberListAction.me?page=${i}">${i}</a></td>
						</c:otherwise>
					</c:choose>

				</c:forEach>

				<!-- 페이지 다음 버튼 -->


				<c:choose>
					<c:when test="${pageInfo.page==pageInfo.maxPage}">
						<td></td>
						<!-- 현재페이지가 마지막페이지면 a태그걸지않는다. -->
					</c:when>
					<c:otherwise>
						<td><a href="memberListAction.me?page=${pageInfo.page+1 }"><i>다음</i></a></td>
					</c:otherwise>
				</c:choose>

			</tr>

		</div>
				
			</div>
		</div>

		

	</section>

	<%@include file="footer.jsp"%>
</body>
</html>