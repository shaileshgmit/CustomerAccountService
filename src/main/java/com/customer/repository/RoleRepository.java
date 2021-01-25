package com.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	

	Optional<Role> findByRoleNameAndRoleCode(String roleName, String roleCode);

}
