package kr.or.connect.reserve.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reserve.dto.Product;
import kr.or.connect.reserve.dto.ReviewWriteFrom;
import kr.or.connect.reserve.service.FileService;
import kr.or.connect.reserve.service.ReviewWriteService;

@Controller
public class ReviewWriteContoller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ReviewWriteService reviewWriteService;
	
	
	// 한줄평 남기기
	@PostMapping("/reviewWrite/{id}")
	public String postReviewWrite(@RequestParam(name = "product_id") int product_id,
			@RequestParam(name = "reservation_info_id") int reservation_info_id, ModelMap modelMap) {
		Product productInfo = reviewWriteService.product(product_id);
		modelMap.addAttribute("productInfo", productInfo);
		modelMap.addAttribute("reservation_info_id", reservation_info_id);
		return "reviewWrite";
	}

	// 리뷰 등록 (서버에 파일업로드 및 리뷰저장)
	@PostMapping("/reviewRegister")
	public String postReviewRegister(@RequestParam(name = "file") MultipartFile file,
			ReviewWriteFrom reviewWriteFrom, ModelMap modelMap, HttpSession session) {
		String email = (String) session.getAttribute("emailInfo");
		int product_id = reviewWriteFrom.getProduct_id();
		reviewWriteService.addUserReview(reviewWriteFrom, email); // 한줄평 남기기
																			
		if (!file.isEmpty()) {
			logger.debug("리뷰등록 업로드!!");
			// 파일 업로드
			try (FileOutputStream fos = new FileOutputStream(
					FileService.pathDir + "uploadFile/" + file.getOriginalFilename()); // 업로드 경로
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					InputStream is = file.getInputStream();
					BufferedInputStream bis = new BufferedInputStream(is);) {
				int readCount = 0;
				byte[] buffer = new byte[1024];
				while ((readCount = bis.read(buffer)) != -1) {
					bos.write(buffer, 0, readCount);
				}
			} catch (Exception e) {
				logger.debug("리뷰등록 파일 업로드 error");
			}
		}
		return "redirect:/review/" + product_id;
	}


	

}
