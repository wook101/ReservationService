package kr.or.connect.reserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.or.connect.reserve.dto.ProductDetail;
import kr.or.connect.reserve.dto.Review;
import kr.or.connect.reserve.service.DetailService;


@Controller
public class ReviewContoller {
		@Autowired
		DetailService detailService;
		
		// 한줄평 페이지
		@GetMapping("/review/{id}")
		public String getReviewById(@PathVariable(name = "id") int product_id, ModelMap modelMap) {
			ProductDetail productDetail = detailService.productDetail(product_id); // prouct 정보(title,content 등등)
			List<Review> reviews = detailService.reviews(product_id, 0, 15); // 예매자 한줄평 정보
			int reviewsCount = detailService.reviewsCount(product_id); // 총 건수
			modelMap.addAttribute("productDetail", productDetail);
			modelMap.addAttribute("reviews", reviews);
			modelMap.addAttribute("reviewsCount", reviewsCount);
			// 평균 평점
			float avgScore = 0;
			if (reviewsCount != 0) {
				for (Review review : reviews) {
					avgScore += Float.parseFloat(review.getScore());
				}
				avgScore = Float.parseFloat(String.format("%.1f", avgScore / (reviewsCount)));
			}
			modelMap.addAttribute("avgScore", avgScore);
			return "review";
			
		}
		
}
