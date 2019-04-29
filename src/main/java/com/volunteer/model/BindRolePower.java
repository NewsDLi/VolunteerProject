package com.volunteer.model;

public class BindRolePower {
    private Long id;

    private Long powerId;

    private byte[] roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPowerId() {
        return powerId;
    }

    public void setPowerId(Long powerId) {
        this.powerId = powerId;
    }

    public byte[] getRoleId() {
        return roleId;
    }

    public void setRoleId(byte[] roleId) {
        this.roleId = roleId;
    }
}