document.addEventListener("DOMContentLoaded",function(){
	init();
});
function init(){
	moveToMain();					//메인 페이지로 이동
	email_reload();					//이메일 새로고침
	total_price_coma_apply();		//총 가격 콤마 적용
	reservationListCountion();		//전체, 이용예정, 이용완료, 취소·환불 카운팅
	confirmCancelBtn();				//예약확정 취소
	reviewWriteBtn();				//예매자 리뷰 남기기 
};
//메인페이지로 
function moveToMain(){
	$('#logo').click(function(){
		location.href="/";
	});
}
//새로고침
function email_reload(){
	$('.e-mail').click(function(){
		location.reload();
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
//총 가격 콤마 적용
function total_price_coma_apply(){
	var len= $('.expected_price').length;
	for(var i=0;i<len;i++){
		var totalPriceInnerText = $('.expected_price')[i].innerText;
		var apply_TotalPrice = priceComma(totalPriceInnerText);
		$('.expected_price')[i].innerText = apply_TotalPrice+" 원";
	}
}
//전체, 예약확정, 이용완료, 취소·환불 영역 카운팅
function reservationListCountion(){
	$('#summaryInfo_all_num').text($('#rearvationInfo_area li').length);				//전체 수 
	$('#summaryInfo_expected_num').text($('#rearvation_comfirm_area li').length);	//예약확정 수
	$('#summaryInfo_complete_num').text($('#rearvation_complete_area li').length);	//이용완료 수
	$('#summaryInfo_cancel_num').text($('#rearvation_cancel_area li').length);		//취소·환불 수
}
function sendAjax(url,reservation_info_id){
	$.ajax({
		method:"POST",
		url:url,
		contentType:'application/json',
		data:JSON.stringify({reservation_info_id:reservation_info_id}),
		success:function(){
			location.reload();
		}
	});
}
//예약확정 취소
function confirmCancelBtn(){
	var length = $('.cancel_btn').length;
	if(length!=0){
		for(var i=0;i<length;i++){
			$('.cancel_btn')[i].addEventListener("click",function(e){
				var val = confirm("취소하시겠습니까?");
				if(val){
					//확인
					var reservation_info_id = e.target.id;
					var url = "/confirmCancel";
					sendAjax(url,reservation_info_id);//예약확정 > 취소된예약 갱신
				}else{
					//취소
				}
			});
		}
	}
}
//예매자 리뷰 남기기 
function reviewWriteBtn(){
	var len = $('.reservation_reivew_btn').length;
	for(var i=0;i<len;i++){
		$('.reservation_reivew_btn')[i].addEventListener("click",function(){
			var btnIdx = $('.reservation_reivew_btn').index(this);
			$('.formData')[btnIdx].submit();
		});
	}
}

