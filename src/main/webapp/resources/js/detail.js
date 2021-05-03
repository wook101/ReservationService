function init(){
	var product_id=document.querySelector(".main").getAttribute("id"); //전역 변수 상품id
	logo();						//로고버튼
	starScoreApply();			//예매자 한줄평 평점 및 별점처리
	reservationCheck();			//예약 확인
	imageSlideEvent();			//이미지 슬라이드 (좌,우)
	expand_fold();				//펼처보기-접기
	reserving(product_id);		//예매하기
	commentMore(product_id);	//예매자 한줄평 더보기
	detailInfo(product_id);		//상세정보 
	comeRoute(product_id);		//오시는 길
}
//로고버튼
function logo(){
	$(".logo").click(function(){
		history.back();
	});
}
//예매자 한줄평 평점 및 별점처리
function starScoreApply(){
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
//예약확인
function reservationCheck(){
	$('.reservation_login').click(function(e){
		var eventText = $(e.target).text().trim();
		var page = "";
		if(eventText=="예약확인")
			page = "/login";
		else
			page = "/reservationCheck"
		location.href=page;
	});
}

//이미지 슬라이드 (좌,우)
function imageSlideEvent(){
	$('#arrows_left_btn').click(function(){
		//배경 이미지 번호 감소
		var total_img_cnt = Number($('#total_imgs_cnt').text().replace(/.\D/,''));
		var current_img_number = Number($('#current_img_number').text());
		if(current_img_number == 1){
			$('#current_img_number').text(total_img_cnt);
		}
		else{
			$('#current_img_number').text(current_img_number-1);
		}
		//좌 슬라이딩
		var moveWidth = Number($('#img_slide').css('width').replace("px",''))/$('.title_img').length;
		$('#img_slide').prepend($('.title_img').clone()[$('.title_img').length-1]);
		$('#img_slide').css('transition','none');
		$('.title_img')[$('.title_img').length-1].remove();
		$('#img_slide').css('left',(-1)*moveWidth+"px");
		
		setTimeout(function(){
			$('#img_slide').css('transition','all 0.2s');
			$('#img_slide').css('left',0);
		},200);
		
	});
	$('#arrows_right_btn').click(function(){
		//배경 이미지 번호 증가
		var total_img_cnt = Number($('#total_imgs_cnt').text().replace(/.\D/,''));
		var current_img_number = Number($('#current_img_number').text());
		if(current_img_number == total_img_cnt){
			$('#current_img_number').text(1);
		}else{
			$('#current_img_number').text(current_img_number+1);
		}
		
		//우 슬라이딩
		var moveWidth = Number($('#img_slide').css('width').replace("px",''))/$('.title_img').length;
		$('#img_slide').css('left',(-1)*moveWidth+"px");
		setTimeout(function(){
			$('#img_slide').append($('.title_img').clone()[0]);
			$('#img_slide').css('transition','none');
			$('.title_img')[0].remove();
			$('#img_slide').css('left',0);
			
		},300);	
		$('#img_slide').css('transition','all 0.3s');
			
	});
	//이미지 슬라이드 좌우 화살표 none or block //이미지가 1개면  좌우 화살표 display = "none"
	if($('.title_img').length==1){
		$('#arrows_left_btn').css('display','none');
		$('#arrows_right_btn').css('display','none');
	}else{
		$('#arrows_left_btn').css('display','block');
		$('#arrows_right_btn').css('display','block');
	}
	
	//이미지 css
	$('#img_slide').css('width','calc(100%*len)'.replace('len',$('.title_img').length));
	$('.title_img').css('width','calc(100%/len)'.replace('len',$('.title_img').length));
}
//펼쳐보기-접기
function expand_fold(){
	if($('.detail_content').text().length < 91){
		$(".more").html("");
	}else{
		$(".more").html("펼쳐보기 ∨");
		$(".more").click(function(){
			if($(".more").hasClass("more")){
				$(".more").addClass("close").removeClass("more")
				$(".detail_content").css("display","block");
				$(".detail_content").css("height","auto");
				$(".close").html("접기 ∧");
			}else if($(".close").hasClass("close")){
				$(".close").addClass("more").removeClass("close")
				$(".detail_content").css("display","-webkit-box");
				$(".detail_content").css("height","73px");
				$(".more").html("펼쳐보기 ∨");
			}
		});
	}
	
}
//예매하기
function reserving(product_id){
	$('.reserving').click(function(){
		location.href="/reserve/"+product_id;
	});
}
//예매자 한줄평 더보기
function commentMore(product_id){
	$(".comment_more").click(function(){
		location.href="/review/"+product_id;
	});
}
//상세정보 
function detailInfo(product_id){
	$('#detail_info').click(function(){
		if($('#detail_info').attr('class')!="current_select"){
			$('#come_route').removeClass('current_select');
			$('#detail_info').addClass('current_select');
			$.ajax({
				method:"POST",
				url:"/detail/detailInfo",
				contentType:'application/json',
				data:JSON.stringify({"product_id":product_id}),
				success: function(data){
					$('.bottom_detail').html(data);
				}
			});
		}
	});
}
//오시는 길
function comeRoute(product_id){
	$('#come_route').click(function(){
		if($('#come_route').attr('class')!="current_select"){
			$('#detail_info').removeClass('current_select');
			$('#come_route').addClass('current_select');
			$.ajax({
				method:"POST",
				url:"/detail/comeRoute",
				contentType:'application/json',
				data:JSON.stringify({"product_id":product_id}),
				success: function(data){
					$('.bottom_detail').html(data);
				}
			});
		}
	});
}
document.addEventListener("DOMContentLoaded",function(){
	  init();
});
