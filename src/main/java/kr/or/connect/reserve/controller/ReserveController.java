package kr.or.connect.reserve.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reserve.dao.ReserveDao;
import kr.or.connect.reserve.dto.CategoryList;
import kr.or.connect.reserve.dto.ComeRouteInfo;
import kr.or.connect.reserve.dto.DetailCommentList;
import kr.or.connect.reserve.dto.DetailTitleInfo;
import kr.or.connect.reserve.dto.ProductInfo;
import kr.or.connect.reserve.dto.ProductPriceInfo;
import kr.or.connect.reserve.dto.PromotionImages;
import kr.or.connect.reserve.dto.ReservationListInfo;
import kr.or.connect.reserve.dto.ReservationUserFormVo;
import kr.or.connect.reserve.dto.ReserveProductInfo;
import kr.or.connect.reserve.dto.ReviewWriteFromVo;
import kr.or.connect.reserve.service.ReserveService;

import static kr.or.connect.reserve.dao.ReserveDaoSqls.*;

@Controller
public class ReserveController {

	@Autowired
	ReserveService reserveService;

	@Autowired
	ReserveDao reserveDao;

	// 메인페이지
	@GetMapping("/")
	public String mainPage(@RequestParam(required = false, defaultValue = "0") int start, ModelMap modelMap) {
		List<CategoryList> fullList = reserveService.getCategoryList(SELECT_FULLLIST, start);
		int listCount = reserveService.getCategoryListCount(COUNT_FULLLIST);
		List<PromotionImages> promotionImagesList = reserveService.getPromotionImages();
		modelMap.addAttribute("fullList", fullList);
		modelMap.addAttribute("promotionImagesList", promotionImagesList);
		modelMap.addAttribute("listCount", listCount);
		return "mainpage";
	}
	
	
	//메인페이지 //카테고리,더보기
	@PostMapping("/ajax")
	public String getCategory(@RequestBody Map<String, Integer> param, ModelMap modelMap) throws IOException {
		Integer category_id = (Integer) param.get("category_id");
		Integer start = (Integer) param.get("startNum");
		List<CategoryList> lists = null;
		int listCount = 0;
		switch (category_id) {
		case 1:
			lists = reserveService.getCategoryList(SELECT_EXHIBITIONLIST, start);
			listCount = reserveService.getCategoryListCount(COUNT_EXHIBITIONLIST);
			break;
		case 2:
			lists = reserveService.getCategoryList(SELECT_MUSICALLIST, start);
			listCount = reserveService.getCategoryListCount(COUNT_MUSICALLIST);
			break;
		case 3:
			lists = reserveService.getCategoryList(SELECT_CONCERTLIST, start);
			listCount = reserveService.getCategoryListCount(COUNT_CONCERTLIST);
			break;
		case 4:
			lists = reserveService.getCategoryList(SELECT_CLASSICLIST, start);
			listCount = reserveService.getCategoryListCount(COUNT_CLASSICLIST);
			break;
		case 5:
			lists = reserveService.getCategoryList(SELECT_THEATERLIST, start);
			listCount = reserveService.getCategoryListCount(COUNT_THEATERLIST);
			break;
		default:
			lists = reserveService.getCategoryList(SELECT_FULLLIST, start);
			listCount = reserveService.getCategoryListCount(COUNT_FULLLIST);
		}
		modelMap.addAttribute("Lists", lists);
		modelMap.addAttribute("listCount", listCount);
		// ajax post요청이오면 값을 ListsTemplate.jsp페이지에 전달한다.
		return "templates/MainListsTemplate";
	}
	
	// 상세 페이지
	@GetMapping("/detail/{id}")
	public String getDetailById(@PathVariable(name = "id") int product_id, ModelMap modelMap) {
		List<Integer> productImageIdList = reserveService.getImageSlideById(product_id);// 좌우 슬라이딩 이미지 리스트
		DetailTitleInfo detailTitleInfo = reserveService.getDetailTitleById(product_id); // 상세페이지 상품 정보
		List<DetailCommentList> commentlist = reserveService.getCommentList(product_id, 0);// 예매자 한줄평 정보 //1~3행

		int total_imgs_cnt = 1 + productImageIdList.size(); // 건수
		int detailCommentListCount = reserveService.getDetailCommentListCount(product_id);
		modelMap.addAttribute("productImageIdList", productImageIdList);
		modelMap.addAttribute("total_imgs_cnt", total_imgs_cnt);
		modelMap.addAttribute("detailTitleInfo", detailTitleInfo);
		modelMap.addAttribute("commentlist", commentlist);
		modelMap.addAttribute("detailCommentListCount", detailCommentListCount);

		// 평균 평점
		float avgScore = 0;
		for (DetailCommentList dcl : commentlist) {
			avgScore += Float.parseFloat(dcl.getScore());
		}
		avgScore = Float.parseFloat(String.format("%.1f", avgScore / 3));
		modelMap.addAttribute("avgScore", avgScore);
		return "detail";
	}

	//상세페이지/상세정보
	@PostMapping("/detail/detailInfo")
	public String getDetailInfo(@RequestBody Map<String, Integer> param, ModelMap modelMap) {
			Integer product_id = (Integer) param.get("product_id");
			DetailTitleInfo detailTitleInfo = reserveService.getDetailTitleById(product_id);
			modelMap.addAttribute("detailTitleInfo", detailTitleInfo);
			return "templates/DetailInfoTemplate";

	}
	//상세페이지/오시는길
	@PostMapping("/detail/comeRoute")
	public String getComeRoute(@RequestBody Map<String, Integer> param, ModelMap modelMap) {
			Integer product_id = (Integer) param.get("product_id");
			ComeRouteInfo comeRouteList = reserveService.getComeRouteById(product_id);
			modelMap.addAttribute("comeRouteList", comeRouteList);
			return "templates/ComeRouteTemplate";
	}

	//한줄평 페이지
	@GetMapping("/review/{id}")
	public String getReviewById(@PathVariable(name = "id") int product_id, ModelMap modelMap) {
		DetailTitleInfo detailTitleInfo = reserveService.getDetailTitleById(product_id); // prouct 정보(title,content 등등)
		List<DetailCommentList> reviewCommentList = reserveService.getReviewCommentList(product_id, 0); // 예매자 한줄평 정보 //1~3행
		int detailCommentListCount = reserveService.getDetailCommentListCount(product_id); // 총 건수
		modelMap.addAttribute("detailTitleInfo", detailTitleInfo);
		modelMap.addAttribute("reviewCommentList", reviewCommentList);
		modelMap.addAttribute("detailCommentListCount", detailCommentListCount);
		// 평균 평점
		float avgScore = 0;
		if(detailCommentListCount!=0) {
			for (DetailCommentList dcl : reviewCommentList) {
				avgScore += Float.parseFloat(dcl.getScore());
			}
			avgScore = Float.parseFloat(String.format("%.1f", avgScore / (detailCommentListCount)));
		}
		modelMap.addAttribute("avgScore", avgScore);
		return "review";
	}

	//예약하기 페이지
	@GetMapping("/reserve/{id}")
	public String getReserveById(@PathVariable(name = "id") int id, ModelMap modelMap) {
		ReserveProductInfo reserveProductInfo = reserveService.getReserveProductInfoById(id); // 상품 정보
		List<ProductPriceInfo> productPriceInfo = reserveService.getProductPriceInfoById(id); // 가격 정보
		modelMap.addAttribute("reserveProductInfo", reserveProductInfo);
		modelMap.addAttribute("productPriceInfo", productPriceInfo);
		return "reserve";
	}
	
	//예약하기 페이지 //폼정보 삽입
	@PostMapping("/reserveFormInfo/{id}") // 폼에서 넘어온 예매자 정보 db에 삽입 후 메인페이지로 리다이렉트
	public String postReserve(@PathVariable(name = "id") int productId, ReservationUserFormVo reservationUserFormVo) {
		int display_info_id = productId; // 원래 display_id를 삽입해야함
		reserveService.setReservationInfoInsert(productId, display_info_id, reservationUserFormVo);
		return "redirect:/";
	}

	//로그인 페이지
	@GetMapping("/login")
	public String getReserveLogin() {
		return "login";
	}

	//이메일정보 존재여부 확인 / 컨트롤러 return타입 void
	@PostMapping("/login")
	public void getReserveCheck(@RequestBody Map<String, Object> param, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/plain"); // 순수 Text로 응답을 해주겠다
		response.setCharacterEncoding("UTF-8"); // 응답하는 Text의 Encoding을 설정한다
		PrintWriter writer = response.getWriter(); // Response Body에 응답을 싣기 위해서 Writer객체를 하나 가져온다
		String email = (String) param.get("email"); // ajax json으로 받을때 requestBody
		int emailCount = reserveService.getloginCheckByEmail(email); // 이메일이 존재하는지 emailValue가 0이면 존재안함// 1이상이면 존재
		if (emailCount == 0) { // ajax result값 보냄 0이면 db에 이메일 존재하지않고, 1이면 존재 //가져온 Write 객체에 응답할 Text를 작성한다.
			writer.write('0');
		} else {
			writer.write('1');
		}
		// 응답을 보낸다.
		writer.flush();
		writer.close();

	}
	
	//예약확인 페이지
	@GetMapping("/reservationCheck")
	public String getReservationCheck(HttpSession session, ModelMap modelMap) {
		String emailInfo = (String) session.getAttribute("emailInfo"); 	// 세션에 있는 이메일을 가져온다.
		if (emailInfo == null) 															// 세션에 이메일 정보가 없는경우 메인페이지로 이동
			return "redirect:/";
		List<ReservationListInfo> reservationListInfo = reserveService.getReservationInfoListByEmail(emailInfo); // 이메일에 해당하는 예약자 정보리스트
		modelMap.addAttribute("reservationListInfo", reservationListInfo);
		return "reservationCheck";
	}

	// 이메일 검증 성공 할 경우
	@PostMapping("/reservationCheck")
	public String postReservationCheck(@RequestParam(name = "email") String emailInfo, HttpSession session,
			ModelMap modelMap) {
		session.setAttribute("emailInfo", emailInfo); // 세션에 이메일 저장
		List<ReservationListInfo> reservationListInfo = reserveService.getReservationInfoListByEmail(emailInfo); // 이메일에 해당하는 예약자 정보 리스트
		modelMap.addAttribute("reservationListInfo", reservationListInfo);
		return "reservationCheck";
	}

	// 예약확정 취소 (Ajax)
	@ResponseBody
	@PostMapping(value = "/confirmCancelAjax", produces = "application/text; charset=UTF8")
	public String postAjaxConfirmCancelBtn(@RequestBody Map<String, Integer> param) {
		int reservation_info_id = param.get("reservation_info_id");										// reservation_info테이블에서 해당 id
		reserveService.setReservationCheckUpdateById(reservation_info_id, 2); 					// cancel_flag를 2로 변경(2:취소된 예약)																									
		return "하하하성공!!";
	}

	// 로그아웃
	@GetMapping("/logout")
	public String getLogOut(HttpSession session) {
		if (session.getAttribute("emailInfo") != null)
			session.invalidate();
		return "login";
	}

	// 한줄평 남기기 
	@PostMapping("/reviewWrite/{id}")
	public String postReviewWrite(@RequestParam(name = "product_id") int productId,
			@RequestParam(name = "reservation_info_id") int reservation_info_id, ModelMap modelMap) {
		ProductInfo productInfo = reserveDao.productInFoSelectById(productId);
		modelMap.addAttribute("productInfo", productInfo);
		modelMap.addAttribute("reservation_info_id", reservation_info_id);
		return "reviewWrite";
	}

	// 리뷰 등록 (서버에 파일업로드 및 리뷰저장)
	@PostMapping("/reviewRegister")
	public String postReviewRegister(@RequestParam(name = "file") MultipartFile file,
			ReviewWriteFromVo reviewWriteFromVo, ModelMap modelMap, HttpSession session) {
		String email = (String) session.getAttribute("emailInfo");
		int product_id = reviewWriteFromVo.getProduct_id();
		reserveDao.reservationUserCommentInsert(reviewWriteFromVo, email); // 리뷰 DB에 폼정보 삽입 //ReviewWriteFromVo(상품id,예약정보id, 별점,코멘트,이메일)
		if(!file.isEmpty()) {
			//파일 업로드
			try(
					FileOutputStream fos = new FileOutputStream(ReserveService.path+"uploadFile/"+ file.getOriginalFilename());//업로드 경로
					InputStream is = file.getInputStream();
				)
			{
			 	int readCount = 0;
			 	byte[] buffer = new byte[1024];
			 	while((readCount = is.read(buffer)) != -1){
			 		fos.write(buffer,0,readCount);
			 	}
				 	
			}catch(Exception e) {
				System.out.println("file Save Error");
			}
		}
		return "redirect:/review/" + product_id;
	}

	
	

	// 이미지를 c드라이브 경로에서 가져옴
	@GetMapping("/img/{directory}/{imagename}.{extension}")
	public void download(HttpServletResponse response, @PathVariable(name = "directory") String directory,
			@PathVariable(name = "imagename") String imagename, @PathVariable(name = "extension") String extension) {
		
		String fileName = imagename;
		String filePath = ReserveService.path+"img/" + directory + "/" + imagename + "." + extension;
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		
		//파일 다운로드
		try (
				FileInputStream fis = new FileInputStream(filePath); 
				OutputStream out = response.getOutputStream();
			)
		{
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
		} catch (Exception e) {
			throw new RuntimeException("file Save Error");
		}
		
	}

}
