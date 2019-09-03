function init(){
	moveToMain();			//메인페이지로
	new ReviewStarScore();	//리뷰쓰기 별점 기능
	textNumCount();			//텍스트 박스 글자수 카운팅
	thumbnailAdd()			//썸네일 이미지 추가
	thumbnailClose()		//썸네일 이미지 닫기
	reviewRegister();		//리뷰등록
}	
document.addEventListener("DOMContentLoaded",function(){
	init();
});
function moveToMain(){	//메인페이지로
	$('#productName').click(function(){	
		location.href="/ReservationService";
	});
}
//리뷰쓰기 별점 기능
function ReviewStarScore(){
	this.starImgEvent();
}
ReviewStarScore.prototype = {
	starImgEvent : function(){
		var object_this = this;	//ReviewStarScore
		this.grayStar(object_this);
		this.redStar(object_this);
	},
	grayStar : function(object_this){	//회색 별														
		var grayLen = $('.grayStarImg').length;
		var grayIdx;
		for(var i=0;i<grayLen;i++){
			$('.grayStarImg')[i].addEventListener('click',function(){
				grayIdx = $('.grayStarImg').index(this);
				for(var j=0;j<=grayIdx;j++){
					$('.grayStarImg')[j].style.display="none";
					$('.redStarImg')[j].style.display="";
				}
				object_this.starScoreUpdate();
			});
		}
	},
	redStar : function(object_this){	//빨간색 별												
		var redLen = $('.redStarImg').length;	
		var redIdx;
		for(var i=0;i<redLen-1;i++){
			$('.redStarImg')[i].addEventListener('click',function(){
				redIdx = $('.redStarImg').index(this);
				for(var j=redIdx+1;j<redLen;j++){
					if($('.redStarImg')[j].style.display=="none"){
						break;
					}
					$('.redStarImg')[j].style.display="none";
					$('.grayStarImg')[j].style.display="";
				}
				object_this.starScoreUpdate();			
			});
		}
	},
	starScoreUpdate : function(){	//리뷰 별점 갱신
		var score=0;
		var len = $('.redStarImg').length;
		for(var i=0;i<len;i++){
			if($('.redStarImg')[i].style.display=="")
				score +=1
		}
		$('#starScore').text(score);
	}
}
//텍스트 박스 글자수 카운팅
function textNumCount(){
	$('#writeTextBox').keyup(function(){
		var textNumCnt = $(this).val().length;
		if(textNumCnt > 400)
			$(this).val($(this).val().substring(0,400)); //글자수 제한
		else
			$('#textNumber').text(textNumCnt);
	});
}
//썸네일 이미지 추가
function thumbnailAdd(){
	$('#fileAdd').change(function(e){
		var image = e.target.files[0];
		if(!fileValidation(image.type)){
			alert('지원하지 않는 이미지 파일 확장자입니다. ex) jpg, png만 가능');
			$(this).val('');	//파일 값 리셋
			return;
		}
		var url = window.URL.createObjectURL(image);
		$('#thumbnailImg').attr('src',url).css('display','');
		$('#closeBtnImg').css('display','');
	});
	
}
//썸네일 이미지 닫기
function thumbnailClose(){
	$('#closeBtnImg').click(function(){
		$('#fileAdd').val('');					//파일 값 리셋
		$('#thumbnailImg').css('display','none');
		$('#closeBtnImg').css('display','none');
	});
}
//이미지 파일 유효성 검사 jpg,png만가능
function fileValidation(ImgType){
	var result = ['image/jpeg','image/jpg','image/png'].indexOf(ImgType) > -1;
	return result;
}
//리뷰등록
function reviewRegister(){
	$('#reviewRegisterBtn').click(function(){
		var starScore = $('#starScore').text();
		$('[name="score"]').val(starScore);
		$('#write_content_area').submit();		//upload로 post 전송
	});
}