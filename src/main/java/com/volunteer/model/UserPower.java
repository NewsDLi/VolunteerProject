package com.volunteer.model;

import java.io.Serializable;
import java.util.Date;

public class UserPower  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5373621353378716330L;

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
        this.powerName = powerName == null ? null : powerName.trim();
    }

    public String getPowerUrl() {
        return powerUrl;
    }

    public void setPowerUrl(String powerUrl) {
        this.powerUrl = powerUrl == null ? null : powerUrl.trim();
    }

    public String getPowerPicUrl() {
        return powerPicUrl;
    }

    public void setPowerPicUrl(String powerPicUrl) {
        this.powerPicUrl = powerPicUrl == null ? null : powerPicUrl.trim();
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }
}