package com.volunteer.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author RichLi
 *
 */
public class Honer  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3875028847531812242L;

	private Long id;

    private String gray;

    private String light;

    private String title;

    private Boolean isLight;

    private Integer sort;

    private Date version;

    private Integer range;

    private Boolean isClickSend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGray() {
        return gray;
    }

    public void setGray(String gray) {
        this.gray = gray == null ? null : gray.trim();
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light == null ? null : light.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Boolean getIsLight() {
        return isLight;
    }

    public void setIsLight(Boolean isLight) {
        this.isLight = isLight;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Boolean getIsClickSend() {
        return isClickSend;
    }

    public void setIsClickSend(Boolean isClickSend) {
        this.isClickSend = isClickSend;
    }

	@Override
	public String toString() {
		return "Honer [id=" + id + ", gray=" + gray + ", light=" + light + ", title=" + title + ", isLight=" + isLight
				+ ", sort=" + sort + ", version=" + version + ", range=" + range + ", isClickSend=" + isClickSend + "]";
	}
    
}