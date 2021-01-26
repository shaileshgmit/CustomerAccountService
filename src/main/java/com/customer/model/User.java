package com.customer.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;


@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"userName", "password","phoneNumber"}))
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="userId")
	private Long id;
	private String userName;
	private Date dateOfBirth;
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=false,fetch=FetchType.EAGER)
	@JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="role_id"))
	
	private Role role;
	
	
	
	@NotNull
	private char gender;
	//@Column(unique=true)
	private String phoneNumber;
	private String password;

	public User() {

	}

	public User(long id, String userName, Date dateOfBirth, char gender, String phoneNumber,String password, Role role) {
		super();
		this.id = id;
		this.userName = userName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	public User(long id, String userName, char gender, String phoneNumber,String password) {
		super();
		this.id = id;
		this.userName = userName;
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
	@NotNull
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	

}
