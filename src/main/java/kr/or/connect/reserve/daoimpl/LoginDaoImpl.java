package kr.or.connect.reserve.daoimpl;

import static kr.or.connect.reserve.dao.ReserveDaoSqls.LOGIN_INFO_CHECK_BY_EMAIL;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reserve.dao.LoginDao;

@Repository
public class LoginDaoImpl implements LoginDao {
	private NamedParameterJdbcTemplate jdbc;
	public LoginDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	//이메일 유무 확인
	public int selectEmailCheck(String email) {
		Map<String, String> param = Collections.singletonMap("email", email);
		return jdbc.queryForObject(LOGIN_INFO_CHECK_BY_EMAIL, param, Integer.class);
	}
}
