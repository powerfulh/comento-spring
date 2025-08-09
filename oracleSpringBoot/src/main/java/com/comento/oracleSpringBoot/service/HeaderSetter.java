package com.comento.oracleSpringBoot.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class HeaderSetter extends BaseService {
	public void setAuthentication(HttpServletResponse res) {
		final String key = "Set-Cookie";
		final String setCookie = res.getHeader(key);
		if(setCookie == null) return;
		if(setCookie.contains("JSESSIONID=")) res.setHeader(key, setCookie + "; SameSite=None; Secure");
	}
}
