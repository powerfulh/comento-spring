package com.comento.oracleSpringBoot.webController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sendCookie")
public class SendCookieC extends WebC {
	@GetMapping
	public String index(HttpServletRequest r, HttpServletResponse res) {
		
		return "sendCookie";
	}
	@GetMapping("send")
	@ResponseBody
	public String cookieByAjax(HttpServletRequest r) {
		String res = "";
		for(Cookie item: r.getCookies()) {
			res += String.format("%s: %s%n", item.getName(), item.getValue());
		}
		return res;
	}
}
