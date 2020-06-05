package com.iu.s1;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorRestController {

	@ExceptionHandler(NullPointerException.class)
	public String error1() {
		return "Error";
	}
	
}
