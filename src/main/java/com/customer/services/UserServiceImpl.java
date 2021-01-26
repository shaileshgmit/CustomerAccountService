package com.customer.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.custom.exception.*;
import com.customer.dto.UserDto;
import com.customer.model.Role;
import com.customer.model.User;
import com.customer.repository.RoleRepository;
import com.customer.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	ModelMapper mapper;

	public ResponseEntity<UserDto> saveUser(UserDto userDto) {

		String roleName = userDto.getRole().getRoleName();
		String roleCode = userDto.getRole().getRoleCode();
		String name = userDto.getUserName();
		String phoneNumber=userDto.getPhoneNumber();
		String password=userDto.getPassword();
		User user = null;
		Optional<Role> OptionalRole = null;
		Role role = null;
		try {
			OptionalRole = roleRepository.findByRoleNameAndRoleCode(roleName, roleCode);
			if (!OptionalRole.isPresent()) {
				throw new RoleNotFoundException(roleName, roleCode);
			}
			role = entityManager.getReference(Role.class, OptionalRole.get().getId());
			user = userRepository.findByPhoneNumberAndUserNameAndPassword(phoneNumber,name,password);
			if(user != null) {
				throw new UserExistsException();
			}
			
			user = new User();
			user.setUserName(userDto.getUserName());
			user.setPassword(userDto.getPassword());
			user.setDateOfBirth(userDto.getDateOfBirth());
			user.setGender(userDto.getGender());
			user.setPhoneNumber(userDto.getPhoneNumber());
			user.setRole(role);
			return new ResponseEntity<UserDto>(mapper.map(userRepository.saveAndFlush(user), UserDto.class),
					HttpStatus.OK);
		} catch (RoleNotFoundException ex) {
			ExceptionResponse response = getExceptionResponse(ex,"NOT_FOUND");

			return new ResponseEntity(response, HttpStatus.NOT_FOUND);

		} 
		
		catch (UserExistsException ex) {
			ExceptionResponse response = getExceptionResponse(ex,"BAD_REQUEST");

			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);

		}
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	public ResponseEntity<UserDto> updateUser(UserDto userDto, long id) {

		String roleName;
		String roleCode;
		Role role = null;
		User userModel = null;
		Optional<Role> OptionalRole = null;
		String phoneNumber=userDto.getPhoneNumber();

		try {
			Optional<User> userOptional = userRepository.findById(id);

			if (!userOptional.isPresent()) {
				throw new UserNotFoundException(id);
			}
			roleName = userDto.getRole().getRoleName();
			roleCode = userDto.getRole().getRoleCode();
			OptionalRole = roleRepository.findByRoleNameAndRoleCode(roleName, roleCode);
			if (!OptionalRole.isPresent()) {
				throw new RoleNotFoundException(roleName, roleCode);
			}
			
			role = entityManager.getReference(Role.class, OptionalRole.get().getId());
			
			userModel = userOptional.get();
			userModel.setId(id);
			userModel.setRole(role);
			userModel.setUserName(userDto.getUserName());
			userModel.setGender(userDto.getGender());
			
			if(!userModel.getPhoneNumber().equals(phoneNumber)) {
				if(userRepository.findByPhoneNumber(phoneNumber) != null) {
					throw new UserExistsException(phoneNumber);
				}
			}
			userModel.setPhoneNumber(phoneNumber);
			userModel.setDateOfBirth(userDto.getDateOfBirth());
			userModel.setPassword(userDto.getPassword());
			return new ResponseEntity<UserDto>(mapper.map(userRepository.saveAndFlush(userModel), UserDto.class),
					HttpStatus.OK);
		}
		
		catch (UserNotFoundException ex) {
			ExceptionResponse response = getExceptionResponse(ex,"NOT_FOUND");
			return new ResponseEntity(response, HttpStatus.NOT_FOUND);

		} catch (RoleNotFoundException ex) {
			ExceptionResponse response = getExceptionResponse(ex,"NOT_FOUND");

			return new ResponseEntity(response, HttpStatus.NOT_FOUND);

		} 
		catch (UserExistsException ex) {
			ExceptionResponse response = getExceptionResponse(ex,"BAD_REQUEST");

			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);

		}
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	public ResponseEntity<List<UserDto>> getAllUsers() {

		List<User> usersList = null;
		List<UserDto> userDtoList = null;
		try {
			usersList = userRepository.findAll();
			userDtoList = usersList.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
			if (userDtoList.size() == 0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return new ResponseEntity<List<UserDto>>(userDtoList, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	public ResponseEntity<UserDto> getUser(long id) {

		Optional<User> user = null;
		UserDto userDto = null;
		try {
			user = userRepository.findById(id);
			if (!user.isPresent())
				throw new UserNotFoundException(id);
				
			userDto = mapper.map(userRepository.saveAndFlush(user.get()), UserDto.class);
			return ResponseEntity.of(Optional.of(userDto));
		} 
		catch (UserNotFoundException ex) {
			ExceptionResponse response = getExceptionResponse(ex,"NOT_FOUND");
			return new ResponseEntity(response, HttpStatus.NOT_FOUND);

		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	public ResponseEntity<Void> deleteUser(long id) {
		Optional<User> user = null;
		try {
			user = userRepository.findById(id);
			if (!user.isPresent())
				throw new UserNotFoundException(id);
			user.get().setRole(null);
			userRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (UserNotFoundException ex) {
			ExceptionResponse response = getExceptionResponse(ex,"NOT_FOUND");
			return new ResponseEntity(response, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			e.printStackTrace();
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
