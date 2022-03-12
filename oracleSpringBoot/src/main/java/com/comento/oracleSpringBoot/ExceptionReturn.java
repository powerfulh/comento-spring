package com.comento.oracleSpringBoot;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionReturn {
	@ExceptionHandler
	public String exception(BindException e) {
		// 400으로 떨어지게 수정 예정
		return e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
	}
}
