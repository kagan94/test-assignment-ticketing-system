package com.sportygroup.ticketingsystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.LOCKED;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(LockingException.class)
	public ResponseEntity<String> handleLockingException(LockingException ex) {
		return ResponseEntity.status(LOCKED).body(ex.getMessage());
	}
}
