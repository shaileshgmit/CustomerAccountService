package com.customer.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customer.dto.UserDto;
import com.customer.model.User;
import com.customer.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @PreAuthorize("hasAnyRole('Customer', 'Branch Manager')")
	@RequestMapping(value="user",method=RequestMethod.GET)
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
    @PreAuthorize("hasAnyRole('Customer', 'Branch Manager')")
	@RequestMapping(value="user/{id}",method=RequestMethod.GET)
	public UserDto getUser(@PathVariable long id){
		return userService.getUser(id);
	}
	
	@PreAuthorize("hasRole('Branch Manager')")
	@RequestMapping(value="useradd",method=RequestMethod.POST)
	public UserDto addUser( @RequestBody UserDto userDto){
		
		return (UserDto) userService.saveUser(userDto);
		
	}
	
	@PreAuthorize("hasRole('Branch Manager')")
	@PutMapping(value="user/{id}")
	public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable("id") long id){
		
		return userService.updateUser(userDto,id);
		
	}
	
	@PreAuthorize("hasRole('Branch Manager')")
	@RequestMapping(value="user/{id}",method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable long id){
		userService.deleteUser(id);
		
	}
}
