package com.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customer.Util.JwtUtil;
import com.customer.model.AuthRequest;

@RestController
public class AuthRequestController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	public String getJwtToke(@RequestBody AuthRequest authRequest) throws Exception {
		 try {
			 final Authentication authentication = authManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
	            );
			 SecurityContextHolder.getContext().setAuthentication(authentication);
			 System.out.println("tetetetet"+authentication.getAuthorities());
			 return jwtUtil.generateToken(authentication);
	        } catch (Exception ex) {
	            throw new Exception("inavalid username/password");
	        }
		 	
	        
	}
}
