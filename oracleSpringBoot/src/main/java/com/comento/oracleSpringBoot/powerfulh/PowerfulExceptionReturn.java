package com.comento.oracleSpringBoot.powerfulh;

import org.springframework.dao.DuplicateKeyException;
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
	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String needAuthenticate(NoSessionNumber e) {
		return e.getMessage();
	}
	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String sqlError(DuplicateKeyException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String unexpected(RuntimeException e) {
		return e.getMessage();
	}
}

class NoSessionNumber extends RuntimeException {
	private static final long serialVersionUID = 1L;
}