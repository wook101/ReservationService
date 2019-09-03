package kr.or.connect.reserve.dao;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.reserve.config.ApplicationConfig;
import kr.or.connect.reserve.dto.CategoryList;
import kr.or.connect.reserve.dto.DetailCommentList;
import kr.or.connect.reserve.dto.DetailTitleInfo;
import kr.or.connect.reserve.dto.ReviewWriteFromVo;

import static kr.or.connect.reserve.dao.ReserveDaoSqls.*;
public class ReserveDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		ReserveDao reserveDao = ac.getBean(ReserveDao.class);
	
	}

}
