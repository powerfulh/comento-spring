package com.comento.oracleSpringBoot;

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
		
		return "index";
	}
}
