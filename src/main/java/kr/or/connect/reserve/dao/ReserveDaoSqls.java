package kr.or.connect.reserve.dao;

public class ReserveDaoSqls {
	//메인페이지 쿼리
	public static final String SELECT_FULLLIST = "select product.id, file_name as \"imgPath\", description, place_name, content  from product,display_info,product_image,file_info where product.id=display_info.product_id and product.id=product_image.product_id and product_image.file_id=file_info.id and product_image.type=\"th\" group by product.id order by product.id limit :start, :limit";
	public static final String SELECT_PROMOTION_IMAGES = "select promotion.id, file_name as \"imgPath\" from promotion,product_image,file_info where promotion.product_id=product_image.product_id and product_image.file_id=file_info.id and product_image.type=\"th\"";
	
	public static final String SELECT_EXHIBITIONLIST = "select product.id, description, file_name as \"imgPath\", place_name, content from category, product,product_image,file_info,display_info where category.id=product.category_id and product.id=product_image.product_id and product_image.file_id=file_info.id and product.id=display_info.product_id and category.id=\"1\" and product_image.type=\"th\" group by product.id order by product.id limit :start, :limit";
	public static final String SELECT_MUSICALLIST = "select product.id, description, file_name as \"imgPath\", place_name, content from category, product,product_image,file_info,display_info where category.id=product.category_id and product.id=product_image.product_id and product_image.file_id=file_info.id and product.id=display_info.product_id and category.id=\"2\" and product_image.type=\"th\" group by product.id order by product.id limit :start, :limit";
	public static final String SELECT_CONCERTLIST = "select product.id, description,file_name as \"imgPath\", place_name, content from category, product,product_image,file_info,display_info  where category.id=product.category_id and product.id=product_image.product_id and product_image.file_id=file_info.id and product.id=display_info.product_id and category.id=\"3\" and product_image.type=\"th\" group by product.id order by product.id limit :start, :limit";
	public static final String SELECT_CLASSICLIST = "select product.id, description, file_name as \"imgPath\", place_name, content from category, product,product_image,file_info,display_info  where category.id=product.category_id and product.id=product_image.product_id and product_image.file_id=file_info.id and product.id=display_info.product_id and category.id=\"4\" and product_image.type=\"th\" group by id order by id limit :start, :limit";
	public static final String SELECT_THEATERLIST = "select product.id, description, file_name as \"imgPath\", place_name, content from category, product,product_image,file_info,display_info  where category.id=product.category_id and product.id=product_image.product_id and product_image.file_id=file_info.id and product.id=display_info.product_id and category.id=\"5\" and product_image.type=\"th\" group by product.id order by product.id limit :start, :limit";
	//@rownum:=@rownum+1 as \"id\"    ;; , (select @rownum:=0) id 
	
	public static final String COUNT_FULLLIST = "select count(distinct product.id) from product,display_info,product_image,file_info where product.id=display_info.product_id and product.id=product_image.product_id and product_image.file_id=file_info.id and product_image.type=\"th\";";
	public static final String COUNT_EXHIBITIONLIST = "select count(distinct product.id) from category, product,product_image,file_info,display_info where category.id=product.category_id and product.id=product_image.product_id and product_image.file_id=file_info.id and product.id=display_info.product_id and category.id=\"1\" and product_image.type=\"th\";";
	public static final String COUNT_MUSICALLIST = "select count(distinct product.id) from category, product,product_image,file_info,display_info where category.id=product.category_id and product.id=product_image.product_id and product_image.file_id=file_info.id and product.id=display_info.product_id and category.id=\"2\" and product_image.type=\"th\";";
	public static final String COUNT_CONCERTLIST = "select count(distinct product.id) from category, product,product_image,file_info,display_info where category.id=product.category_id and product.id=product_image.product_id and product_image.file_id=file_info.id and product.id=display_info.product_id and category.id=\"3\" and product_image.type=\"th\";";
	public static final String COUNT_CLASSICLIST = "select count(distinct product.id) from category, product,product_image,file_info,display_info where category.id=product.category_id and product.id=product_image.product_id and product_image.file_id=file_info.id and product.id=display_info.product_id and category.id=\"4\" and product_image.type=\"th\";";
	public static final String COUNT_THEATERLIST = "select count(distinct product.id) from category, product,product_image,file_info,display_info where category.id=product.category_id and product.id=product_image.product_id and product_image.file_id=file_info.id and product.id=display_info.product_id and category.id=\"5\" and product_image.type=\"th\";";
	
	public static final String SELECT_DETAIL_TITLE_BYID = "select product.id, file_name as \"imgPath\", description, content from product,product_image,file_info where product.id=product_image.product_id and product_image.file_id=file_info.id and product_image.type=\"ma\" and product.id=:id";
	
	//상세페이지 쿼리
	public static final String SELECT_DEATIL_COMMENTLIST = "select reservation_info_id,description,score,comment,email, replace(left(reservation_user_comment.modify_date,10),'-','.') as \"modify_date\" from reservation_user_comment, product where reservation_user_comment.product_id=product.id and product_id=:product_id limit :start, :limit";
	public static final String COUNT_DEATIL_COMMENTLIST ="select count(*) from reservation_user_comment where product_id=:product_id";
	
	//오시는 길 정보 
	public static final String SELECT_COME_ROUTE_BYID = "select product.id,description,place_lot,place_street,place_name,tel from display_info,product where display_info.product_id=product.id group by id having id=:id";
	//좌우 이미지 슬라이드
	public static final String SELECT_ECT_IMAGES_BYID ="select id from product_image where type=\"et\" and product_id=:id";
	
	//예약하기 페이지
	public static final String SELECT_RESERVE_PRODUCT_INFO_BYID="select product.id, description, file_name as \"imgPath\", place_name, opening_hours from product,file_info,display_info,product_image where product.id=display_info.product_id and product.id=product_image.product_id and product_image.file_id=file_info.id and type=\"ma\" group by id having id=:id";
	
	//예약하기 페이지 //상품가격 정보
	public static final String SELECT_PRODUCT_PRICE_INFO_BYID="select product_price.id,product_price.price_type_name,price_type_real_name, price, discount_rate, create_date, modify_date from product_price, price_type_real_name_info where product_price.price_type_name=price_type_real_name_info.price_type_name and product_id=:id";
	
	//예약하기 페이지 //예매자 정보 삽입
	public static final String INSERT_RESERVATION_INFO="insert into reservation_info(product_id,display_info_id,reservation_name,reservation_tel,reservation_email,reservation_date,cancel_flag,create_date,modify_date, reservation_total_price) values(:product_id,:display_info_id,:reservation_name,:reservation_tel,:reservation_email,:reservation_date,:cancel_flag,:create_date,:modify_date,:reservation_total_price)";
	
	
	//예약하기 페이지 //예매자 이메일 정보 삽입
	//public static final String INSERT_RESERVATION_EMAIL_INFO="insert into reservation_email_info(email) values(:email)"; 
	
	
	//로그인 체크 // 행의 수가 0이면 존재지않음 1이상이면 존재
	public static final String LOGIN_INFO_CHECK_BY_EMAIL="select count(*) from reservation_info where reservation_email=:email";
	
	
	//예약확인 페이지  //예약확정 취소버튼 눌렀을때 //예약 유형 업데이트 //예약 확정 -> 취소된 예약 (cancel_flag 0에서2로 갱신)
	public static final String UPDATE_RESERVATION_CHECK_CANCEL_FLAG ="update reservation_info set cancel_flag=:flagVal where id=:id";
	
	//예매자 폼 정보 가져오기
	public static final String SELECT_RESERVATION_INFO_BY_EMAIL="select reservation_info.id,product_id,display_info_id,description as \"product_name\",reservation_name,reservation_tel,reservation_email,reservation_date,cancel_flag,reservation_info.create_date,reservation_info.modify_date,reservation_total_price from product,reservation_info where product.id=reservation_info.product_id and reservation_email=:email";
	
	//상품 id로 정보검색
	public static final String SELECT_PRODUCT_INFO_BYID="select * from product where id=:id";
	
	//한줄평 남기기 //리뷰 정보 삽입
	public static final String INSERT_RESERVATION_USER_COMMENT = "insert into reservation_user_comment(product_id,reservation_info_id,score,comment,email,create_date,modify_date) values(:product_id,:reservation_info_id,:score,:comment,:email,:create_date,:modify_date)";
	
}
