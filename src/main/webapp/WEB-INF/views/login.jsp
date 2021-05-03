<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width==device-width, initial-scale=1.0 ">
<title>로그인 페이지</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/login.css"></c:url>">
		<script src="/resources/jquery/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div class="main">
		<div class="login_title_logo_area">
			<img class="login_logo_img"
				src="img/login/login_logo.png">
			<div class="login_title_text">예약</div>
		</div>

		<form class="login_form_area" method="post" action="reserveCheck">
			<div class="login_form_top_area">
				<h5>비회원 예약확인</h5>
			</div>
			<div class="login_form_center_area">
				<div class="reservation_e-mail_input_text">예약자 이메일 입력</div>
				<div>
					<input class="login_input_text" type="text" name="email">
				</div>
				<div>
					<input class="login_input_btn" type="submit" value="내 예약 확인">
				</div>
			</div>
		</form>
			
	</div>
</body>

<script type="text/javascript"
	src="<c:url value="/resources/js/login.js"></c:url>"></script>

</html>