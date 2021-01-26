package com.custom.exception;

public class UserExistsException extends RuntimeException {
	
	public UserExistsException() {
		super("User already exists");
	}
	public UserExistsException(String phoneNumber) {
		super("User already exists with phoneNumber:"+phoneNumber);
	}
}
