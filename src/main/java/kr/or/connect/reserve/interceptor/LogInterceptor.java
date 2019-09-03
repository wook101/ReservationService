package kr.or.connect.reserve.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	//컨트롤러 메서드가 실행된 후 실행됨
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		logger.debug("url - {} , 시간 - {}, 클라이언트 ip - {}",request.getRequestURI(), new Date(), request.getRemoteAddr());
		/*
		String viewName = "";
		try {
			viewName = modelAndView.getViewName();
			logger.debug("{} 가 종료 됩니다. {} view를 사용합니다.",handler.toString(), viewName);
		}catch(NullPointerException e) {
			logger.debug("{} 가 종료 됩니다.",handler.toString());
		}
		*/
	}
	
	//컨트롤러 메서드가 실행되기 전 실행됨
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//logger.debug("url - {} , 시간 - {}, 클라이언트 ip - {}",request.getRequestURI(),new Date(), request.getRemoteAddr());
		//logger.debug("{} 를 호출했습니다.",handler.toString());
		return true;
	}
}
