document.addEventListener("DOMContentLoaded",function(){
  init();
});
function init(){
  logout();								//로그아웃
  reservationCheck();					//예약확인
  sliding_imgs();						//자동 이미지 슬라이딩
  new SelectEvent();					//카테고리,상품리스트,더보기 선택 
}
//로그아웃
function logout(){
	  var logout = document.querySelector('#logout');
	  if(logout.innerText=="로그아웃")
		  logout.style.cursor="pointer";
	  else
		  logout.style.cursor="default";
	  logout.addEventListener("click",function(e){
		  var text = e.target.innerText;
		  if(text=="로그아웃"){
			  location.href="/logout";
		  }
	  });
}
//예약확인
function reservationCheck(){
	document.querySelector('.e-mail').addEventListener("click",function(e){
		var page="";
		var eventText = e.target.innerText;
		if(eventText=="예약확인")
			page="/login";
		else
			page="/reserveCheck";
		location.href=page;
	});
}
//자동 이미지 슬라이딩
function sliding_imgs(){
  var timeid=null;
  var cnt = 1
  var imgs_length = document.querySelector(".slide_imgs").firstElementChild.childElementCount;
  function animate(){
      var ul = document.querySelector(".slide_imgs ul");
      var imgWidth = document.querySelector(".slide_imgs ul").offsetWidth/11;
      ul.style.transition="all 0.5s";
      ul.style.left = (-1)*cnt*imgWidth+"px";
      cnt++;
      if(cnt==imgs_length){
        clearTimeout(timeid);
        setTimeout(function(){
          var ul = document.querySelector(".slide_imgs ul");
          ul.style.transition="none";
          var li;
          for(var i=0;i<imgs_length-1;i++){
            li = document.querySelector(".slide_imgs li");
            ul.append(li);
          }
          ul.style.left="0px";
          cnt=1;
          startAnimate();
        },500);
      }
  }
  function startAnimate(){
    timeid = setInterval(animate,2000);
  }
  startAnimate();
}

/***********생성자패턴/prototype**********/
//선택생성자(카테고리,상품리스트,더보기) 
function SelectEvent(){
	this.left_list = document.querySelector(".left_list");		//상품리스트 왼쪽 줄
	this.right_list = document.querySelector(".right_list");	//상품리스트 오른쪽 줄
	this.category_select();										//카테고리 선택
	this.productListSelect(); 									//상품 리스트 선택
	this.moreView();											//더보기 선택
}
SelectEvent.prototype = {
		//카테고리 선택
		category_select : function(){
				var tabpanel_current = null;
			  	var tabpanel = document.querySelectorAll(".tabpanel_current, .tabpanel");
			  	var category_id=0;
			  	for(var i=0;i<tabpanel.length;i++){
			  		tabpanel[i].addEventListener("click",function(e){
				        tabpanel_current = document.querySelector(".tabpanel_current");
				        tabpanel_current.className="tabpanel";
				        e.target.className="tabpanel_current";
				        category_id = this.category_id_result(e.target.innerHTML);
				        var jsonData = {"category_id":category_id
				        				,"startNum":0};
				        this.ajaxCall(jsonData,"category");
			    }.bind(this));
			}
		},
		//ajax(카테고리,더보기)
		ajaxCall : function(jsonData,types){
			var xhr = new XMLHttpRequest();
			xhr.open("post","/more",true);
		 	xhr.setRequestHeader('Content-Type', 'application/json;charset=utf-8');
		 	xhr.onreadystatechange = function(){
		 			if(xhr.readyState == 4){
		 			  if(xhr.status == 200){
		 				//더보기를 누르면 ListsTemplates을 추가한다.
						var ListTemplates = xhr.responseText.split("------");	//ListsTemplates.jsp를 '------'로 잘라 left와 right에 list에  랜더링 한다.
						var left_list_data = ListTemplates[0];					//왼쪽 상품 리스트
						var right_list_data = ListTemplates[1];					//오른쪽 상품 리스트
						//카테고리 탭들 눌렀을때
						if(types==="category"){
							this.left_list.innerHTML = left_list_data;
							this.right_list.innerHTML = right_list_data;
							if(document.querySelector(".view_more").style.display==="none")
								document.querySelector(".view_more").style.display="block"; //더보기 버튼 띄움
						}
						//더보기 버튼 눌렀을때
						else if(types==="moreView"){
							this.left_list.innerHTML += left_list_data;
							this.right_list.innerHTML += right_list_data;
							var weblistCount = this.left_list.childElementCount + 			//웹페이지에 보이는 상품 리스트 수
											   this.right_list.childElementCount;
							var dblistCount = Number(ListTemplates[2]);						//서버에 저장되어있는 상품 리스트 수
							if(weblistCount===dblistCount){
								document.querySelector(".view_more").style.display="none"; //더보기 버튼 숨김
							}
						}
						
		 			  }else{
		 				  alert("요청오류!");
		 			  }
		 			 this.productListSelect();												//상품리스트 이벤트 재등록											
		 		  }
		 	  }.bind(this)
		 	xhr.send(JSON.stringify(jsonData));
		},
		//카테고리 id
		category_id_result : function(categoryName){
			var category_id = 0;
			switch(categoryName){
				case "전시" : category_id=1; break;
				case "뮤지컬": category_id=2; break;
				case "콘서트": category_id=3; break;
				case "클래식": category_id=4; break;
				case "연극" : category_id=5; break;
				default : break;
			};
			return category_id;
		},
		//상품 리스트 선택
		productListSelect : function(){
			var product_id = 0;
			for(var i=0,len=this.left_list.childElementCount;i<len;i++){
				this.left_list.children[i].addEventListener("click",function(e){
					product_id = e.target.parentNode.id;
					location.href="/detail/"+product_id;
				});
			}
			for(var i=0,len=this.right_list.childElementCount;i<len;i++){
				this.right_list.children[i].addEventListener("click",function(e){
					product_id = e.target.parentNode.id;
					location.href="/detail/"+product_id;
				});
			}
		},
		//더보기
		moreView : function(){
			var moreViewBtn = document.querySelector(".view_more");
			moreViewBtn.addEventListener("click",function(){
				var proudct_list_count = this.left_list.childElementCount + this.right_list.childElementCount;
				var currentCategory = document.querySelector(".tabpanel_current").innerHTML;
				var category_id = this.category_id_result(currentCategory);
				var jsonData = {"category_id": category_id
								,"startNum": proudct_list_count};
				this.ajaxCall(jsonData,"moreView");
			}.bind(this));
		}
		
};