package kr.or.connect.reserve.daoimpl;

import static kr.or.connect.reserve.dao.ReserveDaoSqls.INSERT_RESERVATION_USER_COMMENT;
import static kr.or.connect.reserve.dao.ReserveDaoSqls.SELECT_PRODUCT_INFO_BYID;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reserve.dao.ReviewWriteDao;
import kr.or.connect.reserve.dto.Product;
import kr.or.connect.reserve.dto.ReviewWriteFrom;

@Repository
public class ReviewWriteDaoImpl implements ReviewWriteDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> productRowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ReviewWriteDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	// 상품 정보
	@Override
	public Product product(int product_id) {
		Map<String, Integer> param = Collections.singletonMap("id", product_id);
		return jdbc.queryForObject(SELECT_PRODUCT_INFO_BYID, param, productRowMapper);
	}

	
	// 한줄평 남기기
	public void addUserReview(ReviewWriteFrom info, String email) {
		Map<String, Object> param = new HashMap<>();
		// param.put("id", info.getId());
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
