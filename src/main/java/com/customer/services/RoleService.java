package com.customer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.custom.exception.UserNotFoundException;
import com.customer.model.Role;
import com.customer.model.User;
import com.customer.repository.RoleRepository;
import com.customer.repository.UserRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public void saveRole(Role role) {

		roleRepository.save(role);

	}

	public ResponseEntity<Object> updateRole(Role role, long id) {
		Optional<Role> roleOptional = roleRepository.findById(id);

		if (!roleOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		role.setId(id);
		roleRepository.save(role);
		return ResponseEntity.noContent().build();

	}

	public List<Role> getAllRoles() {
		return roleRepository.findAll();

	}

	public Role getRole(long id) {
		Optional<Role> role = roleRepository.findById(id);
		if (!role.isPresent())
			throw new UserNotFoundException(id);

		return role.get();
	}

	public void deleteRole(long id) {
		Optional<Role> role = roleRepository.findById(id);
		if (!role.isPresent())
			throw new UserNotFoundException(id);
		
		roleRepository.deleteById(id);

	}

}
