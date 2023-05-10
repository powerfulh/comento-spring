package com.comento.oracleSpringBoot;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionReturn {
	@ExceptionHandler
	public String exception(BindException e) {
		return e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
	}
	
	@ExceptionHandler
	public String unexpectedException(Exception e) {
		e.printStackTrace();
		return "unexpectedException";
	}
}
