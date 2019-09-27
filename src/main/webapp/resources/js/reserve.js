//티켓 수 플러스 이미지 버튼
function ticket_plus_btn(ticketType){
	$('#'+ticketType+'_plus_btn').click(function(){
		var ticketCnt = Number($('#'+ticketType+'_ticket_cnt').text());
		if(ticketCnt==0){
			$('#'+ticketType+'_minus_btn').css('display','block');
			$('#'+ticketType+'_minus_disabled_btn').css('display','none');
			$('#'+ticketType+'_ticket_cnt').attr('class','current_ticket_cnt_active');
			//가격 색깔 검은색으로
			$('#'+ticketType+'_price_area').css('color','black');
		}
		var val = ticketCnt + 1;
		$('#'+ticketType+'_ticket_cnt').text(val);
		
		//콤마 찍고 총 가격 적용
		var ticketPrice = priceCommaRemove($('#'+ticketType+'_ticketing_price').text());
		var totalTicketPrice = priceCommaRemove($('#'+ticketType+'_total_ticket_price').text());		
		var Totalval = totalTicketPrice + ticketPrice;
		$('#'+ticketType+'_total_ticket_price').text(priceComma(Totalval));
		
		//총 티켓수 적용
		var totalTicketCount = Number($('#total_ticketCount').text())+1;
		$('#total_ticketCount').text(totalTicketCount);
		
		//총 티켓 가격 적용
		var reservation_total_price = Number($('#total_price_input').val())+ticketPrice;
		$('#total_price_input').val(reservation_total_price);
		
	});
}

//티켓 수 마이너스 이미지 버튼
function ticket_minus_btn(ticketType){
	$('#'+ticketType+'_minus_btn').click(function(){
		var ticketCnt = Number($('#'+ticketType+'_ticket_cnt').text());
		if(ticketCnt==1){
			$('#'+ticketType+'_minus_btn').css('display','none');
			$('#'+ticketType+'_minus_disabled_btn').css('display','block');
			$('#'+ticketType+'_ticket_cnt').attr('class','current_ticket_cnt_disabled');
			//가격 색깔 회색으로
			$('#'+ticketType+'_price_area').css('color','#b7b2b2');
		}
		var val = ticketCnt - 1;
		$('#'+ticketType+'_ticket_cnt').text(val);
		
		//콤마 찍고 총 가격 적용
		var ticketPrice = priceCommaRemove($('#'+ticketType+'_ticketing_price').text());
		var totalTicketPrice = priceCommaRemove($('#'+ticketType+'_total_ticket_price').text());		
		var Totalval = totalTicketPrice - ticketPrice;
		$('#'+ticketType+'_total_ticket_price').text(priceComma(Totalval));
		
		//총 티켓수 적용
		var totalTicketCount = Number($('#total_ticketCount').text())-1;
		$('#total_ticketCount').text(totalTicketCount);
		
		
		//총 티켓 가격 적용
		var reservation_total_price = Number($('#total_price_input').val())-ticketPrice;
		$('#total_price_input').val(reservation_total_price);
		
		
	});
}
//가격 콤마 찍기 
function priceComma(price){
	var str = price + "";
	var len,point,val;
	len = str.length;
	point = len % 3;

	val=str.substring(0,point);
	while(point < len){
		if(point==0){
			val += str.substring(point,point+3);
		}else{
			val += ','+str.substring(point,point+3);
		}
		point += 3; 
	}
	return val;
}
//가격 콤마 풀기 
function priceCommaRemove(priceStr){
	if(priceStr==0) return 0;
	reg = /\D/g;
	var val = priceStr.replace(reg,'');
	return Number(val);
}
//이용자 약관 전체동의 활성화-비활성화
function userTermsAllAgreeBtn(){
	//비활성화
	$('#user_agree_title_img_active').click(function(){
		$('#user_agree_title_img_active').css('display','none');
		$('#user_agree_title_img_disabled').css('display','block');
		//배경
		$('.reservation_btn_area').css('background-color','#b9b9b9').css('cursor','default');
		
	});
	
	//활성화
	$('#user_agree_title_img_disabled').click(function(){
		//예매자 정보가 모두 입력되고 티켓 수가 1이상일때 예약하기 버튼 활성화
		if(formValidation()){
			$('#user_agree_title_img_disabled').css('display','none');
			$('#user_agree_title_img_active').css('display','block');
			//배경
			$('.reservation_btn_area').css('background-color','#20bf34').css('cursor','pointer');	
		}
		
		
	});
}
//이용자 개인정보 수집 약관 접기-펼치기
function userTermsPrivateInfoToggle(){
	//개인정보 수집 및 이용 동의
	$('#userTermsPrivateInfo_1_Btn').click(function(e){
		var imgPath;
		var text = $(e.target).text();
		if(text=="접기"){
			$('.user_agree_privateInfo_1_content_area').css('display','none');
			$(e.target).text('보기');
			imgPath = '../img/reserve/terms_bottom_arrow.png';
		}else{
			$('.user_agree_privateInfo_1_content_area').css('display','block');
			$(e.target).text('접기');
			imgPath = '../img/reserve/terms_top_arrow.png';
		}
		//접기-펼치기 이미지 변경
		$('#terms_top_arrow_img_1').attr('src',imgPath);
	});
	//개인정보 제3자 제공 동의
	$('#userTermsPrivateInfo_2_Btn').click(function(e){
		var imgPath;
		var text = $(e.target).text();
		if(text=="접기"){
			$('.user_agree_privateInfo_2_content_area').css('display','none');
			$(e.target).text('보기');
			imgPath = '../img/reserve/terms_bottom_arrow.png';
		}else{
			$('.user_agree_privateInfo_2_content_area').css('display','block');
			$(e.target).text('접기');
			imgPath = '../img/reserve/terms_top_arrow.png';
		}
		//접기-펼치기 이미지 변경
		$('#terms_top_arrow_img_2').attr('src',imgPath);
	});
	
}
function formValidation(){
	var errorComment = '형식이 틀렸습니다.';
	if($('#reservationUser_input').val()!=''&&
	   $('#phoneNumber_input').val()!=''&&
	   $('#phoneNumber_input').val()!= errorComment&&
	   $('#e_mail_input').val()!=''&&
	   $('#e_mail_input').val()!= errorComment&&
	   Number($('#total_ticketCount').text())!=0){
		return true;
	}
	return false;
}
function formValidationCheck(){
	var errorComment = '형식이 틀렸습니다.';
	//연락처 유효성 검사
	$('#phoneNumber_input').focus(function(e){
		if($(e.target).val()==errorComment)
			$(e.target).css('color','black').val('');
	});
	$('#phoneNumber_input').blur(function(e){
		var phoneNumber = $(e.target).val().match(/^01[0169]-\d{4}-\d{4}$/);
		if(phoneNumber==null){
			$(e.target).css('color','red').val(errorComment);
		}
	});
	
	//이메일 유효성 검사
	$('#e_mail_input').focus(function(e){
		if($(e.target).val()==errorComment)
			$(e.target).css('color','black').val('');
	});
	$('#e_mail_input').blur(function(e){
		var e_mail = $(e.target).val().match(/^[a-zA-Z0-9\_\.\-]+@[a-zA-Z0-9\.]+\.[a-zA-Z]{2,3}$/);
		if(e_mail==null){
			$(e.target).css('color','red').val(errorComment);
		}
	});
	
	
}
//각 리스트에 연령별 티켓 가격 적용
function ticketList_price_apply(){
	var ticket_type_list = document.querySelectorAll('#ticket_select_area li');
	var price_type_name = '';
	for(var i=0;i<ticket_type_list.length;i++){
		//성인(A), 청소년(Y), 유아(B), 셋트(S), 장애인(D), 지역주민(C), 어얼리버드(E) VIP(V), R석(R), B석(Q), S석(P), 평일(D)'
		price_type_name = ticket_type_list[i].id;
		//연령별 티켓 수 추가,제거 버튼
		ticket_plus_btn(price_type_name);
		ticket_minus_btn(price_type_name);
	}
}

//예약하기 
function reservationBtn(){
	$('.reservation_btn_area').click(function(e){
		if($('#user_agree_title_img_active').css('display')=='block'){
			//var total_price = Number($('#total_price_input').val());
			$('#reservation_user_form').submit();
		}
	});

}

function init(){
	//각 리스트에 연령별 티켓 가격 적용
	ticketList_price_apply();
	//이용자 약관 전체동의
	userTermsAllAgreeBtn();
	//이용자 약관 개인정보 수집 접기-펼치기
	userTermsPrivateInfoToggle();
	//입력 폼 유효성 검사
	formValidationCheck();
	//예약하기
	reservationBtn();
};

document.addEventListener("DOMContentLoaded",function(){
	init();
});
	
