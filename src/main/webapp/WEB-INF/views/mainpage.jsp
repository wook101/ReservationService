<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width==device-width, initial-scale=1.0">
<title>예약서비스</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/main.css"></c:url>">
<link rel="shortcut icon" href="#">
</head>
<body>
	<div class="main">

			<header>
				<a id="logo" href="javascript:location.reload()"></a>
				<div id="logout">
					<c:choose>
						<c:when test="${sessionScope.emailInfo != null}">로그아웃</c:when>
						<c:otherwise>예약</c:otherwise>
					</c:choose>	
				</div>
				<div class="e-mail">
					<c:choose>
						<c:when test="${sessionScope.emailInfo != null}">${emailInfo }</c:when>
						<c:otherwise>예약확인</c:otherwise>
					</c:choose>
				</div>
			</header>

			 <!-- promotion이미지 -->
			<div class="promotion">
				<div class="slide_imgs">
					
					<ul>
						<c:forEach items="${promotionImagesList }" var="reserve"
							varStatus="status">
							<li><img class="slide"
								src="img/main/${reserve.imgPath }">
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<ul class="category">
				<li class="tabpanel_current">전체리스트</li>
				<li class="tabpanel">전시</li>
				<li class="tabpanel">뮤지컬</li>
				<li class="tabpanel">콘서트</li>
				<li class="tabpanel">클래식</li>
				<li class="tabpanel">연극</li>
			</ul>

		
		
		<div class="category_count">
			<div class="text">
				바로 예매 가능한 전시, 공연, 행사가
				<p>${listCount}개</p>
				있습니다
			</div>
		</div>
		<div class="product_List">
			<!-- product_List 템플레이팅 -->
			<div class="left_list">
				<c:forEach items="${fullList }" var="reserve" varStatus="status">
					<c:if test="${status.index%2 eq 0 }">
						<li id="${reserve.id}"><img
							src="img/main/${reserve.imgPath }">
							<h3>${reserve.description }</h3>
							<h5>${reserve.place_name }</h5>
							<p>${reserve.content }</p></li>
					</c:if>
				</c:forEach>
			</div>
			<div class="right_list">
				<c:forEach items="${fullList }" var="reserve" varStatus="status">
					<c:if test="${status.index%2 eq 1 }">
						<li id="${reserve.id}"><img
							src="img/main/${reserve.imgPath }">
							<h3>${reserve.description }</h3>
							<h5>${reserve.place_name }</h5>
							<p>${reserve.content }</p></li>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="view_more">더보기</div>
		<div class="top" onclick="window.scrollTo(0,0)">↑TOP</div>
	</div>
</body>

<script type="text/javascript"
	src="<c:url value="/resources/js/main.js"></c:url>"></script>
</html>