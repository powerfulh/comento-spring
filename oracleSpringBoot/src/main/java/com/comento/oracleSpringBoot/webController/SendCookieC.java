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
		for(Cookie item: r.getCookies()) {
			System.out.printf("%s: %s%n", item.getName(), item.getValue());
		}
		Cookie c = new Cookie("test", "call");
		res.addCookie(c);
		return "sendCookie";
	}
	@GetMapping("send")
	@ResponseBody
	public String cookieByAjax(String key, String val, HttpServletRequest r) { // 이제 쿠키를 받아보자
		for(Cookie item: r.getCookies()) {
			System.out.printf("%s: %s%n", item.getName(), item.getValue());
		}
		return String.format("%s: %s", key, val);
	}
}
