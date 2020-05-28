package kr.or.connect.reserve.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.connect.reserve.dto.ComeRoute;
import kr.or.connect.reserve.dto.ProductDetail;
import kr.or.connect.reserve.dto.Review;
import kr.or.connect.reserve.service.DetailService;


@Controller
@RequestMapping("/detail")
public class DetailContoller {
		@Autowired
		DetailService detailService;
		
		//상품 상세 정보
		@GetMapping("/{id}")
		public String getDetailById(@PathVariable(name = "id") int product_id, ModelMap modelMap) {
			List<Integer> slideImageIdList = detailService.slideImages(product_id);		//슬라이드 이미지id 리스트
			ProductDetail productDetail = detailService.productDetail(product_id); //타이틀 영역 상품 상세 정보
			List<Review> reviews = detailService.reviews(product_id, 0, 3);	//예매자 한줄평 정보 //1~3행

			int total_imgs_cnt = 1 + slideImageIdList.size(); // 건수
			int reviewsCount = detailService.reviewsCount(product_id);
			modelMap.addAttribute("slideImageIdList", slideImageIdList);
			modelMap.addAttribute("total_imgs_cnt", total_imgs_cnt);
			modelMap.addAttribute("productDetail", productDetail);
			modelMap.addAttribute("reviews", reviews);
			modelMap.addAttribute("reviewsCount", reviewsCount);

			// 평균 평점
			float avgScore = 0;
			for (Review review : reviews) {
				avgScore += Float.parseFloat(review.getScore());
			}
			avgScore = Float.parseFloat(String.format("%.1f", avgScore / 3));
			modelMap.addAttribute("avgScore", avgScore);
			return "detail";
		}

		//상세정보
		@PostMapping("/detailInfo")
		public String getDetailInfo(@RequestBody Map<String, Integer> param, ModelMap modelMap) {
				Integer product_id = (Integer) param.get("product_id");
				ProductDetail productDetail = detailService.productDetail(product_id);
				modelMap.addAttribute("productDetail",productDetail);
				return "templates/DetailInfoTemplate";

		}
		//오시는길
		@PostMapping("/comeRoute")
		public String getComeRoute(@RequestBody Map<String, Integer> param, ModelMap modelMap) {
				Integer product_id = (Integer) param.get("product_id");
				ComeRoute comeRouteInfo = detailService.comeRouteinfo(product_id);
				modelMap.addAttribute("comeRouteInfo", comeRouteInfo);
				return "templates/ComeRouteTemplate";
		}
}
