package kr.or.connect.reserve.service;

import java.util.List;

import kr.or.connect.reserve.dto.ReserveUser;

public interface ReserveCheckService {
	public List<ReserveUser> reserveUsers(String email); //예약자들 정보
	public void reserveCancel(int id, int flagVal); //예약 확정 > 취소된 예약으로 cancel_flag값 갱신

}
