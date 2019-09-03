package kr.or.connect.reserve.dao;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import static kr.or.connect.reserve.dao.ReserveDaoSqls.*;

import kr.or.connect.reserve.dto.CategoryList;
import kr.or.connect.reserve.dto.ComeRouteInfo;
import kr.or.connect.reserve.dto.DetailCommentList;
import kr.or.connect.reserve.dto.DetailTitleInfo;
import kr.or.connect.reserve.dto.ProductInfo;
import kr.or.connect.reserve.dto.ProductPriceInfo;
import kr.or.connect.reserve.dto.PromotionImages;
import kr.or.connect.reserve.dto.ReservationListInfo;
import kr.or.connect.reserve.dto.ReservationUserFormInfo;
import kr.or.connect.reserve.dto.ReserveProductInfo;
import kr.or.connect.reserve.dto.ReviewWriteFromVo;

@Repository
public class ReserveDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CategoryList> categoryrowMapper = BeanPropertyRowMapper.newInstance(CategoryList.class);
	private RowMapper<PromotionImages> promotionrowMapper = BeanPropertyRowMapper.newInstance(PromotionImages.class);
	private RowMapper<DetailTitleInfo> detailtitlerowMapper = BeanPropertyRowMapper.newInstance(DetailTitleInfo.class);
	private RowMapper<DetailCommentList> detailCommentrowMapper = BeanPropertyRowMapper.newInstance(DetailCommentList.class);
	private RowMapper<ComeRouteInfo> comeRouterowMapper = BeanPropertyRowMapper.newInstance(ComeRouteInfo.class);
	private RowMapper<ReserveProductInfo> reserveProductInfoRowMapper = BeanPropertyRowMapper.newInstance(ReserveProductInfo.class);
	private RowMapper<ProductPriceInfo> productPriceInfoRowMapper = BeanPropertyRowMapper.newInstance(ProductPriceInfo.class);
	private RowMapper<ReservationListInfo> reservationInfoMapper = BeanPropertyRowMapper.newInstance(ReservationListInfo.class);
	private RowMapper<ProductInfo> productInfoMapper = BeanPropertyRowMapper.newInstance(ProductInfo.class);
	
	public ReserveDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	/*---------------메인(mainpage) 페이지-------------*/
	//카테고리 상품 리스트
	public List<CategoryList> selectAll(String sql, Integer start, Integer limit){
		Map<String, Integer> param = new HashMap<>();
		param.put("start", start);
		param.put("limit", limit);
		return jdbc.query(sql, param, categoryrowMapper);
	}
	//카테고리 건수
	public int CategoryListCount(String sql) {
		return jdbc.queryForObject(sql, Collections.emptyMap(), Integer.class);
	}
	/*---------------상세(detail) 페이지-------------*/
	//프로모션 이미지들
	public List<PromotionImages> selectAll(){
		return jdbc.query(SELECT_PROMOTION_IMAGES, promotionrowMapper);
	}
	//타이틀 정보
	public DetailTitleInfo selectById(Integer id) {
		try {
			Map<String,Integer> param = Collections.singletonMap("id", id);
			return jdbc.queryForObject(SELECT_DETAIL_TITLE_BYID,  param, detailtitlerowMapper);	
		}catch(EmptyResultDataAccessException e) {
			return null;
		}	
	}
	//한줄평 상세정보
	public List<DetailCommentList> commentSelectById(int product_id, Integer start, Integer limit){
		Map<String,Integer> param = new HashMap<>();
		param.put("product_id", product_id);
		param.put("start", start);
		param.put("limit", limit);
		return jdbc.query(SELECT_DEATIL_COMMENTLIST, param, detailCommentrowMapper);
	}
	
	//한줄평 건수
	public int DetailCommentListCount(int product_id) {
		Map<String,Integer> param = Collections.singletonMap("product_id", product_id);
		return jdbc.queryForObject(COUNT_DEATIL_COMMENTLIST, param, Integer.class);
	}
	
	//오시는길
	public ComeRouteInfo comeRouteSelectById(int id) {
		try {
			Map<String,Integer> param = Collections.singletonMap("id", id);
			return jdbc.queryForObject(SELECT_COME_ROUTE_BYID,  param, comeRouterowMapper);	
		}catch(EmptyResultDataAccessException e) {
			return null;
		}	
	}

	//좌우 이미지 슬라이드
	public List<Integer> imageSlideSelectByid(int id){
		Map<String,Integer> param = Collections.singletonMap("id", id);
		return jdbc.queryForList(SELECT_ECT_IMAGES_BYID, param, Integer.class);
	}
	
	/*---------------예약하기(reserve) 페이지-------------*/
	 //상품 정보
	public ReserveProductInfo productInfoSelectById(int id) {
		Map<String,Integer> param = Collections.singletonMap("id", id);
		return jdbc.queryForObject(SELECT_RESERVE_PRODUCT_INFO_BYID, param, reserveProductInfoRowMapper);
	}
	
	//가격정보
	public List<ProductPriceInfo> productPriceInfoSelectById(int id){
		Map<String,Integer> param = Collections.singletonMap("id",id);
		return jdbc.query(SELECT_PRODUCT_PRICE_INFO_BYID, param, productPriceInfoRowMapper);
	}
	//예약 정보 테이블에 예매자 정보 삽입
	public void reservationInfoInsert(ReservationUserFormInfo info) {
		Map<String,Object> param = new HashMap<>();
		//param.put("id", info.getId());
		param.put("product_id", info.getProduct_id());
		param.put("display_info_id", info.getDisplay_info_id());
		param.put("reservation_name", info.getReservation_name());
		param.put("reservation_tel", info.getReservation_tel());
		param.put("reservation_email", info.getReservation_email());
		param.put("reservation_date", info.getReservation_date());
		param.put("cancel_flag", info.getCancel_flag());
		param.put("create_date", info.getCreate_date());
		param.put("modify_date", info.getModify_date());
		param.put("reservation_total_price", info.getReservation_total_price());
		jdbc.update(INSERT_RESERVATION_INFO, param);
	}
	
	/*--------------로그인(login) 페이지-------------*/
	//로그인 이메일 체크
	public int loginCheckSelectByEmail(String email) {
		Map<String,String> param = Collections.singletonMap("email", email);
		int emailCount = jdbc.queryForObject(LOGIN_INFO_CHECK_BY_EMAIL, param, Integer.class);
		return emailCount;
	}
	
	
	/*---------------예약확인(reservationCheck) 페이지-------------*/
	//이메일 검색으로 예매자 폼 정보 가져오기
	public List<ReservationListInfo> reservationListInfoSelectByEmail(String email) {
		Map<String,String> param = Collections.singletonMap("email", email);
		return jdbc.query(SELECT_RESERVATION_INFO_BY_EMAIL, param, reservationInfoMapper);
	}
	
	
	//예약 유형 갱신 //예약 확정 -> 취소된 예약
	public void reservationCheckUpdateById(int id, int flagVal) {
		Map<String,Integer> param = new HashMap<>();
		param.put("id", id);
		param.put("flagVal", flagVal);
		jdbc.update(UPDATE_RESERVATION_CHECK_CANCEL_FLAG, param);
	}
	
	//상품정보 
	public ProductInfo productInFoSelectById(int id) {
		Map<String,Integer> param = Collections.singletonMap("id", id);
		return jdbc.queryForObject( SELECT_PRODUCT_INFO_BYID, param, productInfoMapper);
	}
	
	/*---------------한줄평 남기기(reviewWrite) 페이지-------------*/
	//한줄평 남기기
	public void reservationUserCommentInsert(ReviewWriteFromVo info, String email) {
		Map<String,Object> param = new HashMap<>();
		//param.put("id", info.getId());
		param.put("product_id", info.getProduct_id());
		param.put("reservation_info_id", info.getReservation_info_id());
		param.put("score", info.getScore());
		param.put("comment", info.getComment());
		param.put("email", email);
		param.put("create_date", new Date());
		param.put("modify_date", new Date());
		jdbc.update(INSERT_RESERVATION_USER_COMMENT, param);
	}
	
}
