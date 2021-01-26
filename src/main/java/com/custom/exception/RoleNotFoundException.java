package com.custom.exception;

public class RoleNotFoundException extends RuntimeException {

	public RoleNotFoundException(String name, String code) {
		super("No role exits with name: " + name + " and code :" + code);
	}

	public RoleNotFoundException(long id) {
		super("No role exits with id: " + id);
	}

}
