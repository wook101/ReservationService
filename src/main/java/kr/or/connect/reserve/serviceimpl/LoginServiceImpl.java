package kr.or.connect.reserve.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reserve.dao.LoginDao;
import kr.or.connect.reserve.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao loginDao;
	
	//이메일 유무 확인
	@Override
	public int emailCheck(String email) {
		return loginDao.selectEmailCheck(email);
	}
}
