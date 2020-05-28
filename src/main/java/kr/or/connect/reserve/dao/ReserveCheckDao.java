package kr.or.connect.reserve.dao;

import java.util.List;

import kr.or.connect.reserve.dto.ReserveUser;

public interface ReserveCheckDao {
	public List<ReserveUser> selectReserveUsers(String email); //예약자들 정보
	public void updateReserveCancel(int id, int flagVal);	//예약취소 flag 갱신
}
