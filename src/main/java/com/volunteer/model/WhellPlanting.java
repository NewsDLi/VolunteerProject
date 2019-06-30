package com.volunteer.model;

import java.io.Serializable;
import java.util.Date;

public class WhellPlanting  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 843395303449040083L;

	private Long id;
	
	private String pic;
	
	private String desc;
	
	private String linkAddress;
	
	private Date version;
	
	private String time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
