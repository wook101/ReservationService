package kr.or.connect.reserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.connect.reserve.dto.ProductPrice;
import kr.or.connect.reserve.dto.ReservationUserForm;
import kr.or.connect.reserve.dto.ReserveProduct;
import kr.or.connect.reserve.service.ReserveService;


@Controller
public class ReserveController {
	
	@Autowired
	ReserveService reserveService;
	
	//예약하기 페이지
	@GetMapping("/reserve/{id}")
	public String getReserve(@PathVariable(name = "id") int product_id, ModelMap modelMap) {
		ReserveProduct reserveProduct = reserveService.reserveProduct(product_id); // 상품 정보
		List<ProductPrice> productPrices = reserveService.productPrices(product_id); // 가격 정보
		modelMap.addAttribute("reserveProduct", reserveProduct);
		modelMap.addAttribute("productPrices", productPrices);
		return "reserve";
	}
	
	//예약 폼 정보 삽입
	@PostMapping("/reserve/{id}") // 폼에서 넘어온 예매자 정보 db에 삽입 후 메인페이지로 리다이렉트
	public String postReserve(@PathVariable(name = "id") int product_id, ReservationUserForm form) {
		int display_info_id = product_id; // 원래 display_id를 삽입해야함
		reserveService.reserveInfoInsert(product_id, display_info_id, form);
		return "redirect:/";
	}
		

}
