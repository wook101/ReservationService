package kr.or.connect.reserve.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reserve.dao.ReserveDao;
import kr.or.connect.reserve.dto.ProductPrice;
import kr.or.connect.reserve.dto.ReservationUserForm;
import kr.or.connect.reserve.dto.ReserveProduct;
import kr.or.connect.reserve.service.ReserveService;

@Service
public class ReserveServiceImpl implements ReserveService {

	@Autowired
	ReserveDao reserveDao;

	// 상품 정보
	@Override
	public ReserveProduct reserveProduct(int product_id) {
		return reserveDao.reserveProduct(product_id);
	}

	//예약가능 상품 가격 리스트
	@Override
	public List<ProductPrice> productPrices(int product_id) {
		return reserveDao.productPrices(product_id);
	}

	// 예매자 폼 정보 입력
	@Override
	public void reserveInfoInsert(int product_id, int display_info_id, ReservationUserForm form) {
		reserveDao.reserveInfoInsert(product_id, display_info_id, form);

	}

}
