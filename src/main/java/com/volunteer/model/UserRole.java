package com.volunteer.model;

import java.io.Serializable;
import java.util.Date;

public class UserRole  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8028802537878960514L;

	private Long id;

    private String roleName;

    private Integer roleType;

    private Date version;

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
        this.roleName = roleName == null ? null : roleName.trim();
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