package com.comento.oracleSpringBoot;

import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.comento.oracleSpringBoot.webController")
public class ExceptionReturn {
	@ExceptionHandler
	public String exception(BindException e, Model m) {
		m.addAttribute("msg", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return "index";
	}
	
	@ExceptionHandler
	public String unexpectedException(Exception e, Model m) {
		e.printStackTrace();
		m.addAttribute("msg", "server error");
		return "index";
	}
}
