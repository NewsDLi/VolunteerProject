package com.volunteer.model;

import java.util.Date;

public class UserInfo {
    private Long id;

    private String name;

    private Integer sex;

    private String idCard;

    private String worker;

    private String loginPhone;

    private String descption;

    private String hobby;

    private String userPic;

    private Date version;

    private Long updateBy;

    private Integer lifecycle;

    private Long roleId;

    private Integer groupTeam;

    private Boolean isGroupLeader;

    private String birthplace;

    private String nation;

    private Integer age;
    
    private Boolean isMessageBoard;
    
    private String honerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker == null ? null : worker.trim();
    }

    public String getLoginPhone() {
        return loginPhone;
    }

    public void setLoginPhone(String loginPhone) {
        this.loginPhone = loginPhone == null ? null : loginPhone.trim();
    }

    public String getDescption() {
        return descption;
    }

    public void setDescption(String descption) {
        this.descption = descption == null ? null : descption.trim();
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic == null ? null : userPic.trim();
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Integer lifecycle) {
        this.lifecycle = lifecycle;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getGroupTeam() {
        return groupTeam;
    }

    public void setGroupTeam(Integer groupTeam) {
        this.groupTeam = groupTeam;
    }

    public Boolean getIsGroupLeader() {
        return isGroupLeader;
    }

    public void setIsGroupLeader(Boolean isGroupLeader) {
        this.isGroupLeader = isGroupLeader;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace == null ? null : birthplace.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getIsMessageBoard() {
		return isMessageBoard;
	}

	public void setIsMessageBoard(Boolean isMessageBoard) {
		this.isMessageBoard = isMessageBoard;
	}

	public String getHonerId() {
		return honerId;
	}

	public void setHonerId(String honerId) {
		this.honerId = honerId;
	}

}