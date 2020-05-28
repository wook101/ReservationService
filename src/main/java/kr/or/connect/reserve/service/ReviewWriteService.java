package kr.or.connect.reserve.service;

import kr.or.connect.reserve.dto.Product;
import kr.or.connect.reserve.dto.ReviewWriteFrom;

public interface ReviewWriteService {
	public Product product(int product_id);		// 상품정보
	public void addUserReview(ReviewWriteFrom info, String email); //한줄평 남기기
}
