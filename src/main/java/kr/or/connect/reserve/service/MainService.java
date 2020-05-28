package kr.or.connect.reserve.service;

import java.util.List;

import kr.or.connect.reserve.dto.Product;
import kr.or.connect.reserve.dto.PromotionImage;

public interface MainService {
	public static final int MAIN_LIMIT = 4;
	public List<Product> products(int category_id, int start);			//카테고리 유형별 상품들
	public List<PromotionImage> getPromotionImages();					//프로모션 이미지들
	public int productCount(int category_id);							//카테고리 유형별 상품 개수
	
}
