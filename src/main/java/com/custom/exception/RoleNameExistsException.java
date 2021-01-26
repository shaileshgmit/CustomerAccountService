package com.custom.exception;

public class RoleNameExistsException extends RuntimeException {
	
	public RoleNameExistsException(String roleName) {
		super("Role Name already exists:" +  roleName);
	}
}
