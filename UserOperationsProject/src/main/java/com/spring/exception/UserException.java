package com.spring.exception;

import java.util.Set;

import com.spring.entity.User;

import jakarta.validation.ConstraintViolation;

public class UserException extends Exception {

	public UserException() {
	}

	public UserException(String message) {
		super(message);
	}

	public UserException(String message, Set<ConstraintViolation<User>> violations) {
		super(message, (Throwable) violations);
	}

}
