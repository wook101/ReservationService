package kr.or.connect.reserve.daoImpl;

import static kr.or.connect.reserve.dao.ReserveDaoSqls.*;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reserve.dao.MainDao;
import kr.or.connect.reserve.dto.Product;
import kr.or.connect.reserve.dto.PromotionImage;

@Repository
public class MainDaoImpl implements MainDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> categoryrowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<PromotionImage> promotionrowMapper = BeanPropertyRowMapper.newInstance(PromotionImage.class);
	
	public MainDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/*---------------메인(main) 페이지-------------*/
	// 카테고리 유형별 상품들
	@Override
	public List<Product> selectProducts(int category_id, int start, int limit) {
		Map<String, Integer> param = new HashMap<>();
		param.put("start", start);
		param.put("limit", limit);
		String sql = "";
		switch (category_id) {
		case 1:
			sql = SELECT_EXHIBITIONLIST;
			break;
		case 2:
			sql = SELECT_MUSICALLIST;
			break;
		case 3:
			sql = SELECT_CONCERTLIST;
			break;
		case 4:
			sql = SELECT_CLASSICLIST;
			break;
		case 5:
			sql = SELECT_THEATERLIST;
			break;
		default:
			sql = SELECT_FULLLIST;
		}
		return jdbc.query(sql, param, categoryrowMapper);
	}

	// 카테고리 유형별 상품 개수
	@Override
	public int productCount(int category_id) {
		String sql = "";
		switch (category_id) {
		case 1:
			sql = COUNT_EXHIBITIONLIST;
			break;
		case 2:
			sql = COUNT_MUSICALLIST;
			break;
		case 3:
			sql = COUNT_CONCERTLIST;
			break;
		case 4:
			sql = COUNT_CLASSICLIST;
			break;
		case 5:
			sql = COUNT_THEATERLIST;
			break;
		default:
			sql = COUNT_FULLLIST;
		}
		return jdbc.queryForObject(sql, Collections.emptyMap(), Integer.class);
	}

	// 프로모션 이미지들
	@Override
	public List<PromotionImage> selectPromotionImages() {
		return jdbc.query(SELECT_PROMOTION_IMAGES, promotionrowMapper);
	}

}
