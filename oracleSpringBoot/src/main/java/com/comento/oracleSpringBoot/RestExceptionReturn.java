package com.comento.oracleSpringBoot;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.comento.oracleSpringBoot.rest")
public class RestExceptionReturn {
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String exception(BindException e) {
		return e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
	}
	@ExceptionHandler
	public String reqParamMissing(MissingServletRequestParameterException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler
	public String unexpectedException(Exception e) {
		e.printStackTrace();
		return "unexpectedException";
	}
}
