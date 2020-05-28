package kr.or.connect.reserve.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reserve.dao.ReserveCheckDao;
import kr.or.connect.reserve.dto.ReserveUser;
import kr.or.connect.reserve.service.ReserveCheckService;

@Service
public class ReserveCheckImpl implements ReserveCheckService {
	
	@Autowired
	ReserveCheckDao reserveCheckDao;
	
	//예약자들 정보
	@Override
	public List<ReserveUser> reserveUsers(String email) {
		return reserveCheckDao.selectReserveUsers(email);
	}
	
	//예약 확정 > 취소된 예약으로 cancel_flag값 갱신
	@Override
	public void reserveCancel(int id, int flagVal) {
		reserveCheckDao.updateReserveCancel(id, flagVal);
	}
	
	

}
