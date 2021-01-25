package com.customer.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.custom.exception.*;
import com.customer.dto.UserDto;
import com.customer.model.Role;
import com.customer.model.User;
import com.customer.repository.RoleRepository;
import com.customer.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	@PersistenceContext
	EntityManager entityManager;
	
	
	public UserDto saveUser(UserDto userDto) {

		String roleName = userDto.getRole().getRoleName();
		String roleCode = userDto.getRole().getRoleCode();
		Optional<Role>  OptionalRole = roleRepository.findByRoleNameAndRoleCode(roleName,roleCode);
		if(!OptionalRole.isPresent()) {
			throw new RoleNotFoundException(roleName, roleCode);
		}
		Role role = entityManager.getReference(Role.class, OptionalRole.get().getId());
		User user  = new User();
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setDateOfBirth(userDto.getDateOfBirth());
		user.setGender(userDto.getGender());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setRole(role);
		ModelMapper mapper = new ModelMapper();
		 return mapper.map(userRepository.saveAndFlush(user),UserDto.class);
		
		

	}

	public UserDto updateUser(UserDto userDto, long id) {
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if (!userOptional.isPresent())
			throw new UserNotFoundException(id);
		String roleName = userDto.getRole().getRoleName();
		String roleCode = userDto.getRole().getRoleCode();
		Optional<Role>  OptionalRole = roleRepository.findByRoleNameAndRoleCode(roleName, roleCode);
		if(!OptionalRole.isPresent()) {
			throw new RoleNotFoundException(roleName, roleCode);
		}
		Role role = entityManager.getReference(Role.class, OptionalRole.get().getId());

		User userModel = userOptional.get();
		userModel.setId(id);
		userModel.setRole(role);
		userModel.setUserName(userDto.getUserName());
		userModel.setGender(userDto.getGender());
		userModel.setPhoneNumber(userDto.getPhoneNumber());
		userModel.setDateOfBirth(userDto.getDateOfBirth());
		userModel.setPassword(userDto.getPassword());
		ModelMapper mapper = new ModelMapper();
		return mapper.map(userRepository.saveAndFlush(userModel),UserDto.class);

	}

	public List<User> getAllUsers() {
		return userRepository.findAll();

	}

	public UserDto getUser(long id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException(id);
		ModelMapper mapper = new ModelMapper();
		return mapper.map(userRepository.saveAndFlush(user.get()),UserDto.class);
	}

	public void deleteUser(long id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException(id);
		user.get().setRole(null);
		userRepository.deleteById(id);

	}

}
