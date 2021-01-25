package com.customer.dto;

import java.util.Date;


import com.customer.model.Role;

public class UserDto {
	
	
	private Long id;
	private String userName;
	private Date dateOfBirth;
	private Role role;
	private char gender;
	private String phoneNumber;
	private String password;
	
	public UserDto() {
		
	}
	
	
	
	public UserDto(String userName, Date dateOfBirth, Role role, char gender, String phoneNumber, String password) {
		super();
		this.userName = userName;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
