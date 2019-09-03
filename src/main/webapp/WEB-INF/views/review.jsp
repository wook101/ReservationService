<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width==device-width, initial-scale=1.0 ">
<link rel="stylesheet"
	href="<c:url value="/resources/css/review.css"></c:url>">
<title>한줄평 페이지</title>
</head>
<body>
	<div class="main">
		<div class="review_title">
			<img id="backBtn" onclick="history.back()"
				src="/ReservationService/img/main/backArrow.png">
			<h2 id="productName">${detailTitleInfo.description }</h2>
		</div>

		<div class="reserve_user_comment">
			<h5 class="comment_title">예매자 한줄평</h5>
			<div class="avg_score_area">
				<span class="star_off"> <img class="img_off"
					src="/ReservationService/img/main/star_off.png"> <span
					class="star_on"> <img class="img_on"
						src="/ReservationService/img/main/star_on.png">
				</span>
				</span> <span class="avg_score"> <em>${avgScore }</em> <span>/
						5.0</span>
				</span> <span class="comment_count"><i
					id="${detailCommentListCount}" class="comment_cnt">${detailCommentListCount}건</i>
					등록</span>
			</div>

			<c:forEach items="${reviewCommentList }" var="list"
				varStatus="status">
				<li class="comment_list">
					<div>
						<h3>${detailTitleInfo.description }</h3>
						<c:if test="${ status.index eq 0}">
							<img class="comment_img"
								src="/ReservationService/img/main/${detailTitleInfo.imgPath }">
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
	</div>
</body>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/review.js"></c:url>"></script>

</html>