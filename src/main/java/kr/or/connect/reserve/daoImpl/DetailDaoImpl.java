package kr.or.connect.reserve.daoImpl;

import static kr.or.connect.reserve.dao.ReserveDaoSqls.*;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reserve.dao.DetailDao;
import kr.or.connect.reserve.dto.ComeRoute;
import kr.or.connect.reserve.dto.Review;
import kr.or.connect.reserve.dto.ProductDetail;


@Repository
public class DetailDaoImpl implements DetailDao {
	
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductDetail> productDetailRowMapper = BeanPropertyRowMapper.newInstance(ProductDetail.class);
	private RowMapper<Review> reviewRowMapper = BeanPropertyRowMapper.newInstance(Review.class);
	private RowMapper<ComeRoute> comeRouteRowMapper = BeanPropertyRowMapper.newInstance(ComeRoute.class);
	
	public DetailDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	

	//좌우 슬라이드 이미지id 리스트
	@Override
	public List<Integer> selectSlideImages(int product_id) {
		Map<String, Integer> param = Collections.singletonMap("id", product_id);
		return jdbc.queryForList(SELECT_ECT_IMAGES_BYID, param, Integer.class);
	}
	
	//타이틀 영역 상품 상세 정보
	@Override
	public ProductDetail selectProductDetail(int product_id) {
		try {
			Map<String, Integer> param = Collections.singletonMap("id", product_id);
			return jdbc.queryForObject(SELECT_DETAIL_TITLE_BYID, param, productDetailRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	//예매자 한줄평 리스트
	@Override
	public List<Review> selectReviews(int product_id, int start, int limit) {
		Map<String, Integer> param = new HashMap<>();
		param.put("product_id", product_id);
		param.put("start", start);
		param.put("limit", limit);
		return jdbc.query(SELECT_DEATIL_REVIEWS, param, reviewRowMapper);
	}
	
	//예매자 한줄평 리스트 수
	@Override
	public int reviewsCount(int product_id) {
		Map<String, Integer> param = Collections.singletonMap("product_id", product_id);
		return jdbc.queryForObject(COUNT_DEATIL_REVIEWS, param, Integer.class);
	}
	
	//오시는 길
	@Override
	public ComeRoute selectComeRoute(int product_id) {
		try {
			Map<String, Integer> param = Collections.singletonMap("id", product_id);
			return jdbc.queryForObject(SELECT_COME_ROUTE_BYID, param, comeRouteRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
