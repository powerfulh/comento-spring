package com.comento.oracleSpringBoot.webController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorC implements ErrorController {
	@GetMapping("error")
	public String error(HttpServletRequest req, Model m) {
		String err = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
		m.addAttribute("msg", err);
		
		return "index"; // 개발자 도구를 열고 테스트하면 개발자 도구가 뭔가 이상한 요청을 해서 에러로 들어오는 경우가 있다 260913
	}
}
