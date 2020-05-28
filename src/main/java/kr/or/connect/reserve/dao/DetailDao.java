package kr.or.connect.reserve.dao;

import java.util.List;

import kr.or.connect.reserve.dto.ComeRoute;
import kr.or.connect.reserve.dto.Review;
import kr.or.connect.reserve.dto.ProductDetail;


public interface DetailDao {
		public List<Integer> selectSlideImages(int product_id);//좌우 슬라이드 이미지id 리스트
		public ProductDetail selectProductDetail(int product_id); //상품 상세 정보
		public List<Review> selectReviews(int product_id, int start, int limit); //예매자 한줄평 리스트
		public int reviewsCount(int product_id);	//예매자 한줄평 리스트 수
		public ComeRoute selectComeRoute(int product_id); //오시는 길	
}
