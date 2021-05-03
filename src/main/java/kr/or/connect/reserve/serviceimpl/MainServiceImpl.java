package kr.or.connect.reserve.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reserve.dao.MainDao;
import kr.or.connect.reserve.dto.Product;
import kr.or.connect.reserve.dto.PromotionImage;
import kr.or.connect.reserve.service.MainService;

@Service
public class MainServiceImpl implements MainService{
	
	@Autowired
	MainDao mainDao;
	
	//카테고리 유형별 상품들
	@Override
	public List<Product> products(int category_id, int start) {
		return mainDao.selectProducts(category_id, start, MainService.MAIN_LIMIT);
	}
	
	//프로모션 이미지들
	@Override
	public List<PromotionImage> getPromotionImages() {
		return mainDao.selectPromotionImages();
	}
	
	//카테고리 유형별 상품 개수
	@Override
	public int productCount(int category_id) {
		return mainDao.productCount(category_id);
	}
	
}
