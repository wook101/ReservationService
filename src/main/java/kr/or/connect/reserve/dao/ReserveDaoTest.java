package kr.or.connect.reserve.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.reserve.config.ApplicationConfig;
import kr.or.connect.reserve.service.LoginService;
public class ReserveDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		LoginDao loginDao = ac.getBean(LoginDao.class);
		LoginService loginService = ac.getBean(LoginService.class);
		System.out.print(loginDao.selectEmailCheck("ehddnr1021@naver.com"));
		//System.out.print(loginService.emailCheck("ehddnr1021@naver.com"));
	
	}

}
