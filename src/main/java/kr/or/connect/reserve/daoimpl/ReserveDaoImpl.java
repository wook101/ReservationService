package kr.or.connect.reserve.daoimpl;

import static kr.or.connect.reserve.dao.ReserveDaoSqls.INSERT_RESERVATION_INFO;
import static kr.or.connect.reserve.dao.ReserveDaoSqls.SELECT_PRODUCT_PRICE_INFO_BYID;
import static kr.or.connect.reserve.dao.ReserveDaoSqls.SELECT_RESERVE_PRODUCT_INFO_BYID;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reserve.dao.ReserveDao;
import kr.or.connect.reserve.dto.ProductPrice;
import kr.or.connect.reserve.dto.ReservationUserForm;
import kr.or.connect.reserve.dto.ReserveProduct;

@Repository
public class ReserveDaoImpl implements ReserveDao {

	
	private NamedParameterJdbcTemplate jdbc;

	private RowMapper<ReserveProduct> reserveProductRowMapper = BeanPropertyRowMapper.newInstance(ReserveProduct.class);
	private RowMapper<ProductPrice> productPricesRowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);

	public ReserveDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	// 상품 정보
	public ReserveProduct reserveProduct(int product_id) {
		Map<String, Integer> param = Collections.singletonMap("id", product_id);
		return jdbc.queryForObject(SELECT_RESERVE_PRODUCT_INFO_BYID, param, reserveProductRowMapper);
	}

	// 예약가능 상품 가격 리스트
	public List<ProductPrice> productPrices(int product_id) {
		Map<String, Integer> param = Collections.singletonMap("id", product_id);
		return jdbc.query(SELECT_PRODUCT_PRICE_INFO_BYID, param, productPricesRowMapper);
	}

	// 예약 정보 삽입
	public void reserveInfoInsert(int product_id, int display_info_id, ReservationUserForm form) {
		Map<String, Object> param = new HashMap<>();
		param.put("product_id", product_id);
		param.put("display_info_id", display_info_id);
		param.put("reservation_name", form.getReservation_user());
		param.put("reservation_tel", form.getReservation_tel());
		param.put("reservation_email", form.getReservation_email());
		param.put("reservation_date", new Date());
		param.put("cancel_flag", 0);
		param.put("create_date", new Date());
		param.put("modify_date", new Date());
		param.put("reservation_total_price", form.getReservation_total_price());
		jdbc.update(INSERT_RESERVATION_INFO, param);

	}

}
