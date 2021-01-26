package com.customer.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.customer.dto.UserDto;

public interface UserService {

	public ResponseEntity<UserDto> saveUser(UserDto userDto);
	public ResponseEntity<UserDto> updateUser(UserDto userDto, long id);
	public ResponseEntity<List<UserDto>> getAllUsers();
	public ResponseEntity<UserDto> getUser(long id);
	public ResponseEntity<Void> deleteUser(long id);
}

