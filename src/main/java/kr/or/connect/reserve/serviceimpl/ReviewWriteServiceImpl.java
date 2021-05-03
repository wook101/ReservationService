package kr.or.connect.reserve.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kr.or.connect.reserve.dao.ReviewWriteDao;
import kr.or.connect.reserve.dto.Product;
import kr.or.connect.reserve.dto.ReviewWriteFrom;
import kr.or.connect.reserve.service.ReviewWriteService;

@Service
public class ReviewWriteServiceImpl implements ReviewWriteService{

	@Autowired
	ReviewWriteDao reviewWriteDao;
	
	//상품정보
	@Override
	public Product product(int product_id) {
		return reviewWriteDao.product(product_id);
	}
	
	//한줄평 남기기
	@Override
	public void addUserReview(ReviewWriteFrom info, String email) {
		reviewWriteDao.addUserReview(info, email);
	}
	
	
	
}
