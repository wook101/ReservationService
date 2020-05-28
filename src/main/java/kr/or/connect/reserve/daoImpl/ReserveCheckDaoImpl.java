package kr.or.connect.reserve.daoImpl;

import static kr.or.connect.reserve.dao.ReserveDaoSqls.SELECT_RESERVATION_INFO_BY_EMAIL;
import static kr.or.connect.reserve.dao.ReserveDaoSqls.UPDATE_RESERVATION_CHECK_CANCEL_FLAG;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reserve.dao.ReserveCheckDao;
import kr.or.connect.reserve.dto.ReserveUser;

@Repository
public class ReserveCheckDaoImpl implements ReserveCheckDao{
	
	NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReserveUser> reserveUserRowMapper = BeanPropertyRowMapper.newInstance(ReserveUser.class);
	
	public ReserveCheckDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	//예약자들 정보
	@Override
	public List<ReserveUser> selectReserveUsers(String email) {
		Map<String,String> param = Collections.singletonMap("email", email);
		return jdbc.query(SELECT_RESERVATION_INFO_BY_EMAIL, param, reserveUserRowMapper);
	}
	
	//예약 취소 flag갱신
	@Override
	public void updateReserveCancel(int id, int flagVal) {
		Map<String,Integer> param = new HashMap<>();
		param.put("id", id);
		param.put("flagVal", flagVal);
		jdbc.update(UPDATE_RESERVATION_CHECK_CANCEL_FLAG, param);
	
	}
	
}
