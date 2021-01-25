package com.custom.exception;

public class UserNotFoundException extends RuntimeException{
	
	private long id;
	
	public UserNotFoundException(long id) {
		super("No User exits with id: " + id);
	}

}
