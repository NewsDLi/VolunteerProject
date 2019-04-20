package com.volunteer.model;

import java.util.Date;

public class UserRole {
	private Long id;
	private Long roleValueId;
	private String roleName;
	private Integer roleType;
	private Date version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleValueId() {
		return roleValueId;
	}

	public void setRoleValueId(Long roleValueId) {
		this.roleValueId = roleValueId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

}