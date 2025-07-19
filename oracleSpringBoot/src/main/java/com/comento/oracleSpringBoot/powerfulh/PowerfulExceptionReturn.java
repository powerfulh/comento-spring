package com.comento.oracleSpringBoot.powerfulh;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.comento.oracleSpringBoot.powerfulh")
public class PowerfulExceptionReturn {
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String badReq(MethodArgumentNotValidException e) {
		return e.getFieldError().getDefaultMessage();
	}
}
