package kr.or.connect.reserve.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.reserve.dto.ReserveUser;
import kr.or.connect.reserve.service.ReserveCheckService;


@Controller
public class ReserveCheckController {
		@Autowired
		ReserveCheckService reserveCheckService;
	
		// 예약확인 페이지
		@GetMapping("/reserveCheck")
		public String getReserveCheck(HttpSession session, ModelMap modelMap) {
			String emailInfo = (String) session.getAttribute("emailInfo"); // 세션에 있는 이메일을 가져온다.
			if (emailInfo == null) // 세션에 이메일 정보가 없는경우 메인페이지로 이동
				return "redirect:/";
			List<ReserveUser> reserveUsers = reserveCheckService.reserveUsers(emailInfo); //예약자들 정보
			modelMap.addAttribute("reserveUsers", reserveUsers);
			return "reserveCheck";
		}
	
		// 이메일 검증 성공 할 경우
		@PostMapping("/reserveCheck")
		public String postReserveCheck(@RequestParam(name = "email") String emailInfo, HttpSession session,
				ModelMap modelMap) {
			session.setAttribute("emailInfo", emailInfo); // 세션에 이메일 저장
			List<ReserveUser> reserveUsers = reserveCheckService.reserveUsers(emailInfo); //예약자들 정보																											// 리스트
			modelMap.addAttribute("reserveUsers", reserveUsers);
			return "reserveCheck";
		}
	
		// 예약확정 취소
		@ResponseBody
		@PostMapping(value = "/confirmCancel", produces = "application/text; charset=UTF8")
		public String postAjaxConfirmCancelBtn(@RequestBody Map<String, Integer> param) {
			int reservation_info_id = param.get("reservation_info_id"); // reservation_info테이블에서 해당 id
			reserveCheckService.reserveCancel(reservation_info_id, 2); // cancel_flag를 2로 변경(2:취소된 예약)
			return "예약취소!!";
		}
}
