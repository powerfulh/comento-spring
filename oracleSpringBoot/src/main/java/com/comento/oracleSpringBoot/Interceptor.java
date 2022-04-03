package com.comento.oracleSpringBoot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Interceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
//		String sid = (String) req.getSession().getAttribute("SID");
		return true;
	}
	public void postHandle(HttpServletRequest req, HttpServletResponse response, Object obj, ModelAndView mav) {
		System.out.println("User request: " + req.getRequestURI());
	}
}
