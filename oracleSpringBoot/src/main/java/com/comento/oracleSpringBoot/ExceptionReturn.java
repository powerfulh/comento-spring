package com.comento.oracleSpringBoot;

import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionReturn {
	@ExceptionHandler
	public String exception(BindException e, Model m) {
		m.addAttribute("msg", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return "index";
	}
}
