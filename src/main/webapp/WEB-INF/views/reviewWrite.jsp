<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width==device-width, initial-scale=1.0">
<title>한줄평 남기기</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/reviewWrite.css"></c:url>">
</head>
<body>
	<div class="main">
		<div class="reviewWrite_title">
			<img id="backBtn" onclick="history.back()"
				src="/ReservationService/img/main/backArrow.png">
			<h2 id="productName">${productInfo.description}</h2>
		</div>
		<form id="write_content_area" method="post" action="../reviewRegister" enctype="multipart/form-data">
			<input type="hidden" name="product_id" value="${productInfo.id}">
			<input type="hidden" name="reservation_info_id" value="${reservation_info_id}">
			<input type="hidden" name="score" value="">
			<div class="write_content_top_area">
				<h3 id="top_text">별점과 이용경험을 남겨주세요.</h3>
				<div id="starScoreArea">
					<div id="star_area">
						<c:forEach begin="0" end="4">
							<img class="grayStarImg"
								src="/ReservationService/img/reviewWrite/star_gray.png">
							<img class="redStarImg" style="display: none"
								src="/ReservationService/img/reviewWrite/star_red.png">
						</c:forEach>
						<em id="starScore">0</em>
					</div>
					
				</div>
			</div>
			<textarea id="writeTextBox" name="comment"></textarea>
			<div class="filebox">
				<img id="fileBoxImg"
					src="/ReservationService/img/reviewWrite/fileImg.png">
				<label for="fileAdd">파일 추가</label> 
				<input type="file" name="file" id="fileAdd">
				<div id="textCount_area">
					<em id="textNumber">0</em>/400 (최소 5자이상)
				</div>
			</div>
			<div id="thumbnail_area">
				<img id="closeBtnImg" style="display: none"
					src="/ReservationService/img/reviewWrite/closeBtnImg.png">
				<img id="thumbnailImg" style="display: none">
			</div>
		</form>
		<div id="reviewRegisterBtn">리뷰 등록</div>
		<div class="top" onclick="window.scrollTo(0,0)">↑TOP</div>
	</div>
</body>
<script type="text/javascript"
	src="<c:url value="/resources/js/reviewWrite.js"></c:url>"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
</html>