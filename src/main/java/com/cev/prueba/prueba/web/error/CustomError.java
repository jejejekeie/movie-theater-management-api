package com.cev.prueba.prueba.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CustomError extends RuntimeException{

	public CustomError(String message) {
		super(message);
		
	}
}
