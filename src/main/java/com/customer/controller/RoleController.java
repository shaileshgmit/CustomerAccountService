package com.customer.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customer.model.Role;
import com.customer.model.User;
import com.customer.services.RoleService;
import com.customer.services.UserService;

@RestController
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    
	@RequestMapping(value="role",method=RequestMethod.GET)
	public List<Role> getAllRoles(){
		return roleService.getAllRoles();
	}
	
	@RequestMapping(value="role/{id}",method=RequestMethod.GET)
	public Role getRole(@PathVariable long id){
		return roleService.getRole(id);
	}
	
	@RequestMapping(value="role",method=RequestMethod.POST)
	public void addRole( @RequestBody Role role){
		
		roleService.saveRole(role);
		
	}
	
	@PutMapping(value="role/{id}")
	public ResponseEntity<Object> updateRole(@RequestBody Role role, @PathVariable("id") long id){
		
		return roleService.updateRole(role,id);
		
	}
	
	@RequestMapping(value="role/{id}",method=RequestMethod.DELETE)
	public void deleteRole(@PathVariable long id){
		roleService.deleteRole(id);
		
	}
}
