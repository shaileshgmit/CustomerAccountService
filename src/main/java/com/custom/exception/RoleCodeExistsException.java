package com.custom.exception;

public class RoleCodeExistsException extends RuntimeException {
	
	public RoleCodeExistsException(String roleCode) {
		super("Role Code already exists:" +  roleCode);
	}
}
