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
		
		return "index"; // 지금 보니 평범한 루트 진입 컨트롤이 없어서 홈 요청마다 이걸 타서 /error 로그가 하나씩 쌓이고 있다 ㅋㅋ 일단 지금은 에러가 홈으로 알고 있자 250628 
	}
}
