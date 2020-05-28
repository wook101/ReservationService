package kr.or.connect.reserve.dao;

import java.util.List;

import kr.or.connect.reserve.dto.Product;
import kr.or.connect.reserve.dto.PromotionImage;

public interface MainDao {
	public List<Product> selectProducts(int category_id, int start, int limit); // 카테고리 유형별 상품 리스트
	public int productCount(int category_id);							   		// 카테고리 유형별 상품 개수
	public List<PromotionImage> selectPromotionImages();						//프로모션 이미지 리스트
}
