package kr.or.connect.reserve.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reserve.dao.ReserveDao;
import kr.or.connect.reserve.dto.CategoryList;
import kr.or.connect.reserve.dto.ComeRouteInfo;
import kr.or.connect.reserve.dto.DetailCommentList;
import kr.or.connect.reserve.dto.DetailTitleInfo;
import kr.or.connect.reserve.dto.ProductPriceInfo;
import kr.or.connect.reserve.dto.PromotionImages;
import kr.or.connect.reserve.dto.ReservationListInfo;
import kr.or.connect.reserve.dto.ReservationUserFormInfo;
import kr.or.connect.reserve.dto.ReservationUserFormVo;
import kr.or.connect.reserve.dto.ReserveProductInfo;
import kr.or.connect.reserve.service.ReserveService;



@Service
public class ReserveServiceImpl implements ReserveService{
	
	@Autowired
	 ReserveDao reserveDao;
	
	@Override
	@Transactional
	public  List<CategoryList> getCategoryList(String sql, Integer start) {
		// TODO Auto-generated method stub
		List<CategoryList> categoryList = reserveDao.selectAll(sql, start, ReserveService.MAIN_LIMIT);
		return categoryList;
	}
	
	@Override
	@Transactional
	public List<PromotionImages> getPromotionImages() {
		// TODO Auto-generated method stub
		List<PromotionImages> promotionImages = reserveDao.selectAll();
		return promotionImages;
	}
	
	@Override
	public int getCategoryListCount(String sql) {
		int categoryListCount = reserveDao.CategoryListCount(sql);
		return categoryListCount;
	}
	
	@Override
	@Transactional
	public DetailTitleInfo getDetailTitleById(Integer id){
		DetailTitleInfo dti = reserveDao.selectById(id);
		return dti;
	}
	
	@Override
	public List<DetailCommentList> getCommentList(int id, Integer start){
		List<DetailCommentList> commentlist = reserveDao.commentSelectById(id, start, ReserveService.DETAIL_LIMIT);
		return commentlist;
	}
	
	@Override
	public int getDetailCommentListCount(int product_id) {
		int detailCommentListCount = reserveDao.DetailCommentListCount(product_id);
		return detailCommentListCount;
	}
	
	@Override
	public List<DetailCommentList> getReviewCommentList(int product_id, Integer start){
		return reserveDao.commentSelectById(product_id, start, ReserveService.REVIEW_LIMIT);
	}
	
	@Override
	public ComeRouteInfo getComeRouteById(int id){
		return reserveDao.comeRouteSelectById(id);
	}
	
	@Override
	public List<Integer> getImageSlideById(int id){
		return reserveDao.imageSlideSelectByid(id);
	}
	
	//예약페이지 // 상품 정보
	@Override
	public ReserveProductInfo getReserveProductInfoById(int id) {
		return reserveDao.productInfoSelectById(id);
	}
	
	//상품 가격 정보
	@Override
	public List<ProductPriceInfo> getProductPriceInfoById(int id){
		return reserveDao.productPriceInfoSelectById(id);
	}
	
	//로그인 이메일 체크
	@Override
	public int getloginCheckByEmail(String email) {
		int emailCount = reserveDao.loginCheckSelectByEmail(email);
		return emailCount;
	}
	
	
	//예매자 폼 정보 입력
	@Override
	public void setReservationInfoInsert(int product_id, int display_info_id, ReservationUserFormVo vo) {
		ReservationUserFormInfo info = new ReservationUserFormInfo();
		info.setProduct_id(product_id);
		info.setDisplay_info_id(display_info_id);
		info.setReservation_name(vo.getReservation_user());
		info.setReservation_tel(vo.getReservation_tel());
		info.setReservation_email(vo.getReservation_email());
		info.setReservation_date(new Date());
		info.setCancel_flag(0);
		info.setCreate_date(new Date());
		info.setModify_date(new Date());
		info.setReservation_total_price(vo.getReservation_total_price());
		reserveDao.reservationInfoInsert(info);
		
	}
	//이메일을 통해 예매자 폼 정보 가져오기 
	@Override
	public List<ReservationListInfo> getReservationInfoListByEmail(String email){
		return reserveDao.reservationListInfoSelectByEmail(email);
	}
	
	//예약 확정 > 취소된 예약으로 cancel_flag값 갱신
	@Override
	public void setReservationCheckUpdateById(int id, int flagVal) {
		reserveDao.reservationCheckUpdateById(id, flagVal);
	}
}
