<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width==device-width, initial-scale=1.0 ">
<title>내 예약 확인</title>
<link rel="stylesheet" href="<c:url value="/resources/css/reservationCheck.css"></c:url>">
</head>
<body>
	<div class="main">
		<nav class="gnb">
		<div class="logo_area">
			<img id="logo"
				src="img/main/naverLogo.PNG">
			<div class="reserv">예약</div>
		</div>
		<div class="e-mail">${emailInfo }</div>
		</nav>

		<div class="summaryInfo_area">
			<div class="summaryInfo_block">
				<div>
					<img class="summaryInfo_block_img"
						src="img/reservationCheck/all_img.png"><br>
					<div class="summaryInfo_block_text">전체</div>
				</div>
				<div id="summaryInfo_all_num" class="summaryInfo_block_num"></div>
			</div>
			<div class="summaryInfo_block">
				<div>
					<img class="summaryInfo_block_img"
						src="img/reservationCheck/expected_img.png"><br>
					<div class="summaryInfo_block_text">예약확정</div>
				</div>
				<div id="summaryInfo_expected_num" class="summaryInfo_block_num"></div>
			</div>
			<div class="summaryInfo_block">
				<div>
					<img class="summaryInfo_block_img"
						src="img/reservationCheck/completed_img.png"><br>
					<div class="summaryInfo_block_text">이용완료</div>
				</div>
				<div id="summaryInfo_complete_num" class="summaryInfo_block_num"></div>
			</div>
			<div class="summaryInfo_block">
				<div>
					<img class="summaryInfo_block_img"
						src="img/reservationCheck/cancel_img.png"><br>
					<div class="summaryInfo_block_text">취소·환불</div>
				</div>
				<div id="summaryInfo_cancel_num" class="summaryInfo_block_num"></div>
			</div>
		</div>

		<div id="rearvationInfo_area">
			<!-- 예약 확정 -->
			<c:set var="flag_0_count" value="0" />
			<c:forEach items="${reserveUsers }" var="list"
				varStatus="status">
				<c:if test="${list.cancel_flag==0 }">
					<c:set var="flag_0_count" value="${status.count }" />
				</c:if>
			</c:forEach>
			<c:if test="${flag_0_count!=0 }">
				<div id="rearvation_comfirm_area">
					<div class="reservation_img_area">
						<img id="reservation_img"
							src="img/reservationCheck/confirm_reservation_img.png">
						<span id="reservation_text">예약 확정</span>
					</div>
					<div class="top_greenbar_area">
						<img id="greenbar_left_img"
							src="img/reservationCheck/greenbar_left.png">
						<img id="greenbar_center_img"
							src="img/reservationCheck/greenbar_center.png">
						<img id="greenbar_right_img"
							src="img/reservationCheck/greenbar_right.png">
					</div>

					<ul class="rearvation_ul_area">
						<c:forEach items="${reserveUsers}" var="list">
							<c:if test="${list.cancel_flag ==0 }">
								<li class="rearvation_li_area">
									<div class="rearvation_li_top_area">
										<div class="rearvation_li_top_title_area">
											<em>No.000000${list.id}</em>
											<h2>${list.product_name }</h2>
										</div>
										<img id="share_img"
											src="img/reservationCheck/share_img.png">
									</div>
									<div class="rearvation_li_middle_area">
										<div>
											<i>일정</i><span>2000.0.00.(월)2000.0.00.(일)</span>
										</div>
										<div>
											<i>내역</i><span>내역이 없습니다.</span>
										</div>
										<div>
											<i>장소</i><span>내역이 없습니다.</span>
										</div>
										<div>
											<i>업체</i><span>업체명이 없습니다.</span>
										</div>
									</div>
									<div class="rearvation_li_bottom_area">
										<div class="expected_price_area">
											<div class="expected_price_text">결제 예정금액</div>
											<div class="expected_price">${list.reservation_total_price}</div>
										</div>
										<div>
											<input id="${list.id}" class="cancel_btn" type="button"
												value="취소">
										</div>
									</div>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
				
			</c:if>
			<!-- 이용 완료 -->
			<c:set var="flag_1_count" value="0" />
			<c:forEach items="${reserveUsers }" var="list"
				varStatus="status">
				<c:if test="${list.cancel_flag==1 }">
					<c:set var="flag_1_count" value="${status.count }" />
				</c:if>
			</c:forEach>
			<c:if test="${flag_1_count!=0 }">
				<div id="rearvation_complete_area">

					<div class="reservation_img_area">
						<img id="reservation_img"
							src="img/reservationCheck/confirm_reservation_img.png">
						<span id="reservation_text">이용 완료</span>
					</div>
					<div class="top_graybar_area">
						<img id="graybar_left_img"
							src="img/reservationCheck/graybar_left.png">
						<img id="graybar_center_img"
							src="img/reservationCheck/graybar_center.png">
						<img id="graybar_right_img"
							src="img/reservationCheck/graybar_right.png">
					</div>

					<ul class="rearvation_ul_area">
						<c:forEach items="${reserveUsers}" var="list">
							<c:if test="${list.cancel_flag ==1 }">
								<li class="rearvation_li_area">
									<form class="formData" action="reviewWrite/${list.product_id }" method="post">
										<input name="product_id" type="hidden" value="${list.product_id }">
										<input name="reservation_info_id" type="hidden" value="${list.id }">
									</form>
									<div class="rearvation_li_top_area">
										<div class="rearvation_li_top_title_area">
											<em>No.000000${list.id}</em>
											<h2>${list.product_name }</h2>
										</div>
										<img id="share_img"
											src="img/reservationCheck/share_img.png">
									</div>
									<div class="rearvation_li_middle_area">
										<div>
											<i>일정</i><span>2000.0.00.(월)2000.0.00.(일)</span>
										</div>
										<div>
											<i>내역</i><span>내역이 없습니다.</span>
										</div>
										<div>
											<i>장소</i><span>내역이 없습니다.</span>
										</div>
										<div>
											<i>업체</i><span>업체명이 없습니다.</span>
										</div>
									</div>
									<div class="rearvation_li_bottom_area">
										<div class="expected_price_area">
											<div class="expected_price_text">결제 예정금액</div>
											<div class="expected_price">${list.reservation_total_price}</div>
										</div>
										<div>
											<input class="reservation_reivew_btn" type="button"
												value="예매자 리뷰 남기기">
										</div>
									</div>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<!-- 취소된 예약 -->
			<c:set var="flag_2_count" value="0" />
			<c:forEach items="${reserveUsers }" var="list"
				varStatus="status">
				<c:if test="${list.cancel_flag==2 }">
					<c:set var="flag_2_count" value="${status.count }" />
				</c:if>
			</c:forEach>
			<c:if test="${flag_2_count!=0 }">
				<div id="rearvation_cancel_area">
					<div class="reservation_img_area">
						<img id="reservation_img"
							src="img/reservationCheck/cancel_reservation_img.png">
						<span id="reservation_text">취소된 예약</span>
					</div>
					<div class="top_graybar_area">
						<img id="graybar_left_img"
							src="img/reservationCheck/graybar_left.png">
						<img id="graybar_center_img"
							src="img/reservationCheck/graybar_center.png">
						<img id="graybar_right_img"
							src="img/reservationCheck/graybar_right.png">
					</div>

					<ul class="rearvation_ul_area">
						<c:forEach items="${reserveUsers}" var="list">
							<c:if test="${list.cancel_flag ==2 }">
								<li class="rearvation_li_area">
									<div class="rearvation_li_top_area">
										<div class="rearvation_li_top_title_area">
											<em>No.000000${list.id}</em>
											<h2>${list.product_name }</h2>
										</div>
										<img id="share_img"
											src="img/reservationCheck/share_img.png">
									</div>
									<div class="rearvation_li_middle_area">
										<div>
											<i>일정</i><span>2000.0.00.(월)2000.0.00.(일)</span>
										</div>
										<div>
											<i>내역</i><span>내역이 없습니다.</span>
										</div>
										<div>
											<i>장소</i><span>내역이 없습니다.</span>
										</div>
										<div>
											<i>업체</i><span>업체명이 없습니다.</span>
										</div>
									</div>
									<div class="rearvation_li_bottom_area">
										<div class="expected_price_area">
											<div class="expected_price_text">결제 예정금액</div>
											<div class="expected_price">${list.reservation_total_price}</div>
										</div>
									</div>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</c:if>
		</div>

	</div>
</body>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/reserveCheck.js"></c:url>"></script>
</html>