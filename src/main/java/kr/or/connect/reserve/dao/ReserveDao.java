package kr.or.connect.reserve.dao;

import java.util.List;

import kr.or.connect.reserve.dto.ProductPrice;
import kr.or.connect.reserve.dto.ReservationUserForm;
import kr.or.connect.reserve.dto.ReserveProduct;

public interface ReserveDao {
	
	public ReserveProduct reserveProduct(int product_id);	//상품 정보
	public List<ProductPrice> productPrices(int product_id); //예약가능 상품 가격 리스트
	public void reserveInfoInsert(int product_id, int display_info_id, ReservationUserForm form); //예약 정보 삽입
}
