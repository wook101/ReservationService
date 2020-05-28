<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width==device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="<c:url value="/resources/css/detail.css"></c:url>">
<title>상세 페이지</title>
</head>
<body>
	<div class="main" id="${productDetail.id }">
		<div id="img_slide">
			<img class="title_img" src="../img/main/${productDetail.imgPath }">
			<c:if test="${not empty slideImageIdList }">
				<c:forEach items="${slideImageIdList }" var="list">
					<img class="title_img"
						src="../img/main/${productDetail.id }_et_${list}.png">
				</c:forEach>
			</c:if>

		</div>
		<div class="title_main">
			<nav class="gnb">
			<div class="logo">
				<img src="../img/main/reserve.png" alt="예약 이미지">
			</div>
			<div class="reservation_login">
				<c:choose>
					<c:when test="${sessionScope.emailInfo != null}">${emailInfo }</c:when>
					<c:otherwise>예약확인</c:otherwise>
				</c:choose>
			</div>
			</nav>
			<div class="arrows_title_area">
				<div id="oval_area">
					<span id="current_img_number">1</span> <span id="total_imgs_cnt">/
						${total_imgs_cnt}</span>
				</div>
				<img id="arrows_left_btn" src="../img/main/arrows_left.png">
				<div class="centerTitle">${productDetail.description }</div>
				<img id="arrows_right_btn" src="../img/main/arrows_right.png">
			</div>
		</div>
		<div class="detail_content">${productDetail.content}</div>
		<div class="expandBtn">
			<div class="more"></div>
		</div>
		<div class="eventInfo">
			<div class="eventInfo_area">
				<img class="giftImg" src="../img/main/gift.png">
				<h5>이벤트 정보</h5>
			</div>
			<div class="eventInfo_content">
				<p>[네이버 예약 특별할인]</p>
				<p>R석 50%,S석 60% 할인</p>
			</div>
		</div>
		<div class="reserving">
			<img id="reserving_img" src="../img/main/reserving_img.png">예매하기
		</div>
		<div class="reserve_user_comment">
			<h5 class="comment_title">예매자 한줄평</h5>
			<div class="avg_score_area">
				<span class="star_off"> <img class="img_off"
					src="../img/main/star_off.png"> <span class="star_on">
						<img class="img_on" src="../img/main/star_on.png">
				</span>
				</span> <span class="avg_score"> <em>${avgScore}</em> <span>/
						5.0</span>
				</span> <span class="comment_count"><i
					id="${reviewsCount}" class="comment_cnt">${reviewsCount}건</i>
					등록</span>
			</div>

			<c:forEach items="${reviews }" var="list" varStatus="status">
				<li class="comment_list">
					<div>
						<h3>${productDetail.description }</h3>
						<c:if test="${ status.index eq 0}">
							<img class="comment_img"
								src="../img/main/${productDetail.imgPath }">
						</c:if>
						<p class="comment">${list.comment }</p>
					</div>
					<div class="comment_log_area">
						<h5 class="score">${list.score }</h5>
						<p class="user_id">| ${list.email } |</p>
						<p class="visit_day">${list.modify_date } 방문</p>
					</div>
				</li>
			</c:forEach>
		</div>
		<div class="comment_more">예매자 한줄평 더보기-></div>

		<div class="top_info_area">
			<div id="detail_info" class="current_select">상세정보</div>
			<div id="come_route">오시는길</div>
		</div>
		<article class="bottom_detail">
		<h5>[소개]</h5>
		<p>${productDetail.content}</p>
		<h5>[공지사항]</h5>
		<img class="notice_img" src="../img/main/notice.PNG"> </article>
		<div class="top" onclick="window.scrollTo(0,0)">↑TOP</div>

	</div>
</body>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/detail.js"></c:url>"></script>
</html>