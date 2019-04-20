package com.volunteer.model;

import java.util.Date;

public class UserPower {
	private Long id;
	private String powerName;
	private String powerUrl;
	private String powerPicUrl;

private Date version;

public Long getId() {	
	return id;	
}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public String getPowerUrl() {
		return powerUrl;
	}

	public void setPowerUrl(String powerUrl) {
		this.powerUrl = powerUrl;
	}

	public String getPowerPicUrl() {
		return powerPicUrl;
	}

	public void setPowerPicUrl(String powerPicUrl) {
		this.powerPicUrl = powerPicUrl;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

}