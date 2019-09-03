package kr.or.connect.reserve.service;

import java.util.List;

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

public interface ReserveService {
	//public static final String path ="reserveImgFile/";											//호스팅 파일 저장 경로
	public static final String path = "c:/Users/ehddn/Desktop/예약서비스 프로젝트/";  //로컬 파일 저장 경로
	public static final Integer MAIN_LIMIT = 4;
	public static final Integer DETAIL_LIMIT = 3;
	public static final Integer REVIEW_LIMIT = 15; //끝까지
	public List<CategoryList> getCategoryList(String sql, Integer start);
	public List<PromotionImages> getPromotionImages();
	public int getCategoryListCount(String sql);
	public DetailTitleInfo getDetailTitleById(Integer id);
	public List<DetailCommentList> getCommentList(int product_id, Integer start);
	public int getDetailCommentListCount(int product_id);
	public List<DetailCommentList> getReviewCommentList(int product_id, Integer start);
	public ComeRouteInfo getComeRouteById(int id);
	public List<Integer> getImageSlideById(int id);
	public ReserveProductInfo getReserveProductInfoById(int id);
	public List<ProductPriceInfo> getProductPriceInfoById(int id);
	public int getloginCheckByEmail(String email);
	public void setReservationInfoInsert(int product_id, int display_info_id, ReservationUserFormVo vo);
	public List<ReservationListInfo> getReservationInfoListByEmail(String email);
	public void setReservationCheckUpdateById(int id, int flagVal);
}
