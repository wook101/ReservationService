
function init(){
	moveToMain();//메인페이지로
	StarScoreApply();//예매자 한줄평 평점 및 별점처리
}
document.addEventListener("DOMContentLoaded",function(){
	init();
});
function moveToMain(){	//메인페이지로
	$('#productName').click(function(){	
		location.href="/ReservationService";
	});
}
//예매자 한줄평 평점 및 별점처리
function StarScoreApply(){
	var avgScore = $(".avg_score em").text();
	var star_width = avgScore*2;
	var star_width_decimal = (star_width-parseInt(star_width)).toFixed(1);
	var final_star_width;
	if (star_width_decimal < 0.5){
		final_star_width = parseInt(star_width);
	}
	else{
		final_star_width = parseInt(star_width)+0.5;
	} 
	$(".star_on").css('width',final_star_width*10+2+'%');	
}