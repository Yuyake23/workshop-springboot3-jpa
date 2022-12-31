package com.udemy.course.services.exceptions;

import java.io.Serial;

public class DatabaseException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	public DatabaseException() {
		super("Data integrity violation");
	}

	public DatabaseException(String msg) {
		super(msg);
	}

}
