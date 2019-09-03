<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width==device-width, initial-scale=1.0">
<title>예약하기</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/reserve.css"></c:url>">
</head>
<body>
	<div class="main">
		<div class="reserve_product_info">
			<div class="reserve_title">
				<img id="backBtn" onclick="history.back()"
					src="../img/main/backArrow.png">
				<h2>${reserveProductInfo.description }</h2>
			</div>
			<div id="img_title_area">
				<h2>${reserveProductInfo.description }</h2>
			</div>
			<img id="reserve_title_img"
				src="../img/main/${reserveProductInfo.imgPath }">
			<div class="product_detail_info_area">
				<h4>장소</h4>
				<p>${reserveProductInfo.place_name }</p>
				<h4>관람시간</h4>
				<p>${reserveProductInfo.opening_hours }</p>
				<h4>요금</h4>
				<c:forEach items="${productPriceInfo}" var="list">
					<p>${list.price_type_real_name}/${list.price}원</p>
				</c:forEach>
			</div>
		</div>
		<ul id="ticket_select_area">
			<c:forEach items="${productPriceInfo}" var="list" varStatus="status">
				<li id="${list.price_type_name}" class="list_ticketing">
					<div class="ticket_cnt_select">
						<div class="ticket_plus_minus_area">
							<img id="${list.price_type_name}_minus_btn" class="minus_btn"
								style="display: none"
								src="../img/reserve/ticket_minus.png">
							<img id="${list.price_type_name}_minus_disabled_btn"
								class="minus_disabled_btn"
								src="../img/reserve/ticket_minus_disabled.png">
							<div id="${list.price_type_name}_ticket_cnt"
								class="current_ticket_cnt_disabled">0</div>
							<img id="${list.price_type_name}_plus_btn"
								src="../img/reserve/ticket_plus.png">
						</div>
						<div id="${list.price_type_name}_price_area" class="price_area">
							<i id="${list.price_type_name}_total_ticket_price"
								class="ticket_price">0</i>원
						</div>
					</div>
					<div class="ticketing_info_area">
						<h4>${list.price_type_real_name}</h4>
						<h4 id="${list.price_type_name}_ticketing_price">${list.price}원</h4>
						<p>${list.price}원(${list.discount_rate}%할인가)</p>
					</div>
				</li>
			</c:forEach>
		</ul>

		<div class="reservation_user_info_area">
			<div class="reservation_user_info_top">
				<h3>예매자 정보</h3>
				<p>필수입력</p>
				<img class="green_check"
					src="../img/reserve/green_check.png">
			</div>

			<form id="reservation_user_form" method="post"
				action="/ReservationService/reserveFormInfo/${reserveProductInfo.id }">
				<div class="reservation_input_area">
					<img class="green_check"
						src="../img/reserve/green_check.png">
					<i>예매자</i> <input id="reservationUser_input"
						name="reservation_user" placeholder="김동욱" type="text">
				</div>
				<div class="reservation_input_area">
					<img class="green_check"
						src="../img/reserve/green_check.png">
					<i>연락처</i> <input id="phoneNumber_input" name="reservation_tel"
						placeholder="ex) 010-1234-5678" type="text">
				</div>
				<div class="reservation_input_area">
					<img class="green_check"
						src="../img/reserve/green_check.png">
					<i>이메일</i> <input id="e_mail_input" name="reservation_email"
						placeholder="ex) ehddnr1021@naver.com" type="text">
				</div>
				<div class="reservation_content_area">
					<i>예매내용</i> <span>2017.2.17, 총 <em id="total_ticketCount">0</em>매
					</span>
				</div>
				<input id="total_price_input" name="reservation_total_price"
					value="0" type="hidden">
			</form>
		</div>

		<div class="user_terms_agreement_area">
			<div class="user_agree_title_area">
				<img id="user_agree_title_img_active" style="display: none;"
					src="../img/reserve/terms_agree_img_active.png">
				<img id="user_agree_title_img_disabled"
					src="../img/reserve/terms_agree_img.png">
				<h2>이용자 약관 전체동의</h2>
				<p>필수동의</p>
			</div>

			<div class="user_agree_content_area">
				<div class="user_agree_privateInfo_1_area">
					<img class="terms_sign_img"
						src="../img/reserve/terms_sign_img.png">
					<p>개인정보 수집 및 이용 동의</p>
					<img id="terms_top_arrow_img_1" class="terms_top_arrow_img"
						src="../img/reserve/terms_top_arrow.png">
					<i id="userTermsPrivateInfo_1_Btn">접기</i>
				</div>
				<div class="user_agree_privateInfo_1_content_area">
					<p>
						<개인정보 수집 및 이용 동의> <br>
						<br>
						1. 수집항목 : [필수]이름,연락처,[선택]이메일주소<br>
						<br>
						2. 수집 및 이용목적: 사용자회원과 예약이용자의 원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 분쟁조정
						해결을 위한 기록보존, 네이버 예약 이용 후 리뷰작성 
					</p>
				</div>
				<div class="user_agree_privateInfo_2_area">
					<img class="terms_sign_img"
						src="../img/reserve/terms_sign_img.png">
					<p>개인정보 제3자 제공 동의</p>
					<img id="terms_top_arrow_img_2" class="terms_bottom_arrow_img"
						src="../img/reserve/terms_bottom_arrow.png">
					<i id="userTermsPrivateInfo_2_Btn">보기</i>
				</div>
				<div class="user_agree_privateInfo_2_content_area"
					style="display: none;">
					<p>
						<개인정보 제3자 제공 동의> <br>
						<br>
						1. 수집항목 : [필수]이름,연락처,[선택]이메일주소<br>
						<br>
						2. 수집 및 이용목적: 사용자회원과 예약이용자의 원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 분쟁조정
						해결을 위한 기록보존, 네이버 예약 이용 후 리뷰작성 
					</p>
				</div>
			</div>
		</div>

		<footer id="footer">
		<div class="reservation_btn_area">
			<img id="reservation_btn_img_disabled"
				src="../img/reserve/reservation_btn.png">
			<div id="reservation_text">예약하기</div>
		</div>
		</footer>

	</div>

</body>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/reserve.js"></c:url>"></script>

</html>