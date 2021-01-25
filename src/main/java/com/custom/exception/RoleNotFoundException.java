package com.custom.exception;

public class RoleNotFoundException extends RuntimeException{
	
	private String name;
	private String code;
	
	public RoleNotFoundException(String name, String code) {
		super("No role exits with name: " + name +" and code :" + code);
	}

}
