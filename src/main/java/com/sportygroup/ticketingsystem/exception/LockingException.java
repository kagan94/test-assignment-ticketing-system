package com.sportygroup.ticketingsystem.exception;

import java.io.Serial;

public class LockingException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public LockingException(String message) {
		super(message);
	}
}
