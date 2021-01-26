package com.customer.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.customer.model.Role;

public interface RoleService {
	public ResponseEntity<Role> saveRole(Role role);

	public ResponseEntity<Role> updateRole(Role role, long id);

	public ResponseEntity<List<Role>> getAllRoles();

	public ResponseEntity<Role> getRole(long id);

	public ResponseEntity<Void> deleteRole(long id);
}
