package com.comento.oracleSpringBoot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class Interceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
		System.out.println("User request: " + req.getRequestURI());
//		String sid = (String) req.getSession().getAttribute("SID");
		return true;
	}
}
