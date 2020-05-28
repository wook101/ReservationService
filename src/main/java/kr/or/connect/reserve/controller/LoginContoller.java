package kr.or.connect.reserve.controller;


import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.reserve.service.LoginService;

@Controller
public class LoginContoller {
		@Autowired
		LoginService loginService;
	
		//로그인 페이지
		@GetMapping("/login")
		public String getReserveLogin() {
			return "login";
		}
	
		//이메일 존재여부 확인 
		@ResponseBody
		@PostMapping("/login")
		public Map<String, Boolean> reserveCheck(@RequestBody Map<String, Object> param) {
			String email = (String) param.get("email"); // ajax json으로 받을때 requestBody
			Integer emailCheck = loginService.emailCheck(email); // 이메일이 존재하는지 emailCheck가 0이면 존재안함// 1이상이면 존재
			Boolean result = true; 
			if (emailCheck==0) { //0이면 존재안함 , 1이상이면 존재 
				result = false;
			} 
			return Collections.singletonMap("success", result);
		}
	
		// 로그아웃
		@GetMapping("/logout")
		public String getLogOut(HttpSession session) {
			if (session.getAttribute("emailInfo") != null)
				session.invalidate();
			return "login";
		}

}
