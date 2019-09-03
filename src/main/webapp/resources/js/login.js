function init(){
	moveToMain();				//메인페이지로 이동
	formValidCheck();			//이메일 유효성 검사
	myReservationCheckBtn();	//내 예약 확인
};
document.addEventListener("DOMContentLoaded",function(){
	init();
});
//메인페이지로 이동
function moveToMain(){
	$('.login_logo_img').click(function(){
		location.href="/ReservationService";
	});
}
//이메일 유효성 검사
function formValidCheck(){
	var errorComment = '이메일 형식이 틀렸습니다.';
	//텍스트 박스 안 포커스일때 
	$('.login_input_text').focus(function(e){
		var e_mail_validation = (/^[a-zA-Z0-9]+@[a-zA-Z0-9\.]+\.[a-zA-Z]{2,3}$/).test($(e.target).val());
		if($(e.target).val()!=e_mail_validation)
			$(e.target).css('color','gray').css('font-size','22px').val('');
	});
	//텍스트 박스 밖으로 나갔을 떄
	$('.login_input_text').blur(function(e){
		var e_mail_validation = (/^[a-zA-Z0-9]+@[a-zA-Z0-9\.]+\.[a-zA-Z]{2,3}$/).test($(e.target).val());
		if(e_mail_validation==false){
			$(e.target).css('color','red').css('font-size','16px').val(errorComment);
		}
	});
}
//내 예약 확인
function myReservationCheckBtn(){
	$('.login_input_btn').click(function(e){
		e.preventDefault();  //submit 중지
		var e_mail_validation = (/^[a-zA-Z0-9]+@[a-zA-Z0-9\.]+\.[a-zA-Z]{2,3}$/).test($('.login_input_text').val());
		if(e_mail_validation){	//이메일 유효성 검증 성공
			sendAjax();
		}else{
			console.log('올바르지 않는 이메일입니다.');
		}
	});
}
function sendAjax(){
	$.ajax({
		method:'post',
		url:'/ReservationService/login',
		contentType:'application/json',
		data:JSON.stringify({email:$('.login_input_text').val()}),
		success: function(data){
			//이메일 DB에 존재 유무 성공 //0이면 이메일정보 없음
			if(data==0){
				$('.login_input_text').css('color','red').css('font-size','16px').val('이메일이 일치하지 않습니다.');
			}else{
				$('.login_form_area').submit(); //post 검증 후 submit
			}
		}
	});
}
