package com.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="roleId")
	private Long id;
	@Column(unique=true , updatable=false,insertable=false,nullable=false)
	private String roleName;
	@Column(unique=true, updatable=false,insertable=false,nullable=false)
	private String roleCode;
	
	
	public Role() {
		
	}
	
	public Role(Long id, String roleName, String roleCode) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.roleCode = roleCode;
	}
	
	public Role( String roleName, String roleCode) {
		super();
		this.roleName = roleName;
		this.roleCode = roleCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}	

}
