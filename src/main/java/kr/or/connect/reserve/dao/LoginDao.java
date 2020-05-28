package kr.or.connect.reserve.dao;

public interface LoginDao {
	public int selectEmailCheck(String email);	//이메일 유무 확인
}
