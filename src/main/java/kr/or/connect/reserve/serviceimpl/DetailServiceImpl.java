package kr.or.connect.reserve.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reserve.dao.DetailDao;
import kr.or.connect.reserve.dto.ComeRoute;
import kr.or.connect.reserve.dto.Review;
import kr.or.connect.reserve.dto.ProductDetail;
import kr.or.connect.reserve.service.DetailService;


@Service
public class DetailServiceImpl implements DetailService{
	
	@Autowired
	DetailDao detailDao;


	//좌우 슬라이드 이미지id 리스트
	@Override
	public List<Integer> slideImages(int product_id) {
		return detailDao.selectSlideImages(product_id);
	}
	
	//상품 상세 정보
	@Override
	public ProductDetail productDetail(int product_id) {
		return detailDao.selectProductDetail(product_id);
	}
	
	//예매자 한줄평 리스트
	@Override
	public List<Review> reviews(int product_id, int start, int limit) {
		return detailDao.selectReviews(product_id, start, limit);
	}
	
	//예매자 한줄평 리스트 수
	@Override
	public int reviewsCount(int product_id) {
		return detailDao.reviewsCount(product_id);
	}
	
	//오시는 길
	@Override
	public ComeRoute comeRouteinfo(int product_id) {
		return detailDao.selectComeRoute(product_id);
	}

	
	
}
