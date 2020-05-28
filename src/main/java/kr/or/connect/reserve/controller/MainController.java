package kr.or.connect.reserve.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.reserve.dto.Product;
import kr.or.connect.reserve.dto.PromotionImage;
import kr.or.connect.reserve.service.MainService;


@Controller
public class MainController {
	
	
	@Autowired
	MainService mainService;
	
	// 메인페이지
	@GetMapping("/")
	public String mainPage(@RequestParam(required = false, defaultValue = "0") int start, ModelMap modelMap) {
		int category_id = 0;
		List<Product> fullProducts = mainService.products(category_id, start);
		int productCount = mainService.productCount(category_id);
		List<PromotionImage> promotionImages = mainService.getPromotionImages();
		modelMap.addAttribute("fullProducts", fullProducts);
		modelMap.addAttribute("promotionImages", promotionImages);
		modelMap.addAttribute("productCount", productCount);
		return "main";
	}
	
	
	//카테고리 더보기
	@PostMapping("/more")
	public String getCategory(@RequestBody Map<String, Integer> param, ModelMap modelMap) throws IOException {
		int category_id = (Integer) param.get("category_id");
		int start = (Integer) param.get("startNum");
		List<Product> products = mainService.products(category_id, start);
		int productCount = mainService.productCount(category_id);
		
		modelMap.addAttribute("products", products);
		modelMap.addAttribute("productCount", productCount);
		// ajax post요청이오면 값을 ListsTemplate.jsp페이지에 전달한다.
		return "templates/MainListsTemplate";
	}
}
