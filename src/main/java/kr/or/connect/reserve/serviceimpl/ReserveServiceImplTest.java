package kr.or.connect.reserve.serviceimpl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.reserve.config.ApplicationConfig;
import kr.or.connect.reserve.dto.CategoryList;
import kr.or.connect.reserve.service.ReserveService;

import static kr.or.connect.reserve.dao.ReserveDaoSqls.*;
public class ReserveServiceImplTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		ReserveService reserveService = ac.getBean(ReserveService.class);
		/*
		List<CategoryList> list = reserveService.getFullList(0);
		for(CategoryList f: list) {
			System.out.println(f);
		}
		*/
		System.out.println(reserveService.getReserveProductInfoById(2));

		
	}

}
