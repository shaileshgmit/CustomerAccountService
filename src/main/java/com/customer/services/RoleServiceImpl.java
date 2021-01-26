package com.customer.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.custom.exception.ExceptionResponse;
import com.custom.exception.RoleCodeExistsException;
import com.custom.exception.RoleNameExistsException;
import com.custom.exception.RoleNotFoundException;
import com.customer.model.Role;
import com.customer.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public ResponseEntity<Role> saveRole(Role role) {
		
		Optional<Role> optionalRole = null;
		try {
			optionalRole = roleRepository.findByRoleCode(role.getRoleCode());
			if(optionalRole.isPresent()) {
				throw new RoleCodeExistsException(role.getRoleCode());
			}
			
			optionalRole = roleRepository.findByRoleName(role.getRoleName());
			if(optionalRole.isPresent()) {
				throw new RoleNameExistsException(role.getRoleName());
			}
			return new ResponseEntity<Role>(roleRepository.save(role),HttpStatus.OK);
		}
		catch (RoleCodeExistsException ex) {
			ExceptionResponse response = getExceptionResponse(ex,"Exists");
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);

		}
		catch (RoleNameExistsException ex) {
			ExceptionResponse response = getExceptionResponse(ex,"Exists");
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);

		}
		catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
		

	}

	@Override
	public ResponseEntity<Role> updateRole(Role role, long id) {
		Optional<Role> roleOptional;
		try {
			roleOptional = roleRepository.findById(id);

			if (!roleOptional.isPresent()) {
				throw new RoleNotFoundException(role.getRoleName(),role.getRoleCode());
			}
			roleOptional = roleRepository.findByRoleCode(role.getRoleCode());
			if(roleOptional.isPresent()) {
				throw new RoleCodeExistsException(role.getRoleCode());
			}
			
			roleOptional = roleRepository.findByRoleName(role.getRoleName());
			if(roleOptional.isPresent()) {
				throw new RoleNameExistsException(role.getRoleName());
			}
			role.setId(id);
			role.setRoleCode(role.getRoleCode());
			role.setRoleName(role.getRoleName());
			return new ResponseEntity<Role>(roleRepository.save(role),HttpStatus.OK); 
		}
		
		catch (RoleNotFoundException ex) {
			ExceptionResponse response = getExceptionResponse(ex,"NOT_FOUND");
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);

		}
		catch (RoleCodeExistsException ex) {
			ExceptionResponse response = getExceptionResponse(ex,"Exists");
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);

		}
		catch (RoleNameExistsException ex) {
			ExceptionResponse response = getExceptionResponse(ex,"Exists");
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);

		}
		catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		

	}

	@Override
	public ResponseEntity<List<Role>> getAllRoles() {
		List<Role> roleList = null;
		try {
			roleList = roleRepository.findAll();
			if (roleList.size() == 0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return new ResponseEntity<List<Role>>(roleList, HttpStatus.OK);
			 
		}
		
		catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@Override
	public ResponseEntity<Role> getRole(long id) {
		Optional<Role> role;
		try {
			role = roleRepository.findById(id);
			if (!role.isPresent())
				throw new RoleNotFoundException(id);

			return new ResponseEntity<Role>(role.get(), HttpStatus.OK);
		}
		catch (RoleNotFoundException ex) {
			ExceptionResponse response = getExceptionResponse(ex,"NOT_FOUND");
			return new ResponseEntity(response, HttpStatus.NOT_FOUND);

		}
		catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

	@Override
	public ResponseEntity<Void> deleteRole(long id) {
		Optional<Role> role;
		try {
			role = roleRepository.findById(id);
			if (!role.isPresent())
				throw new RoleNotFoundException(id);

			roleRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch (RoleNotFoundException ex) {
			ExceptionResponse response = getExceptionResponse(ex,"NOT_FOUND");
			return new ResponseEntity(response, HttpStatus.NOT_FOUND);

		}
		catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		

	}
	
	protected ExceptionResponse getExceptionResponse(Exception ex,String errorCode) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode(errorCode);
		response.setErrorMessage(ex.getMessage());
		response.setTimestamp(LocalDateTime.now());
		return response;

	}

}
