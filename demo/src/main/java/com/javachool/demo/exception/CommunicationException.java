package com.javachool.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class CommunicationException extends RuntimeException {

	public CommunicationException(String message) {
		super(message);
	}
}
