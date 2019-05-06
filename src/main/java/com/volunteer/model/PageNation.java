package com.volunteer.model;

public class PageNation {

	private String kewWords;
	
	private Integer groupteam;
	
	private Long roles;
	
	private int begin;
	
	private int pagesize;

	public String getKewWords() {
		return kewWords;
	}

	public void setKewWords(String kewWords) {
		this.kewWords = kewWords;
	}

	public Integer getGroupteam() {
		return groupteam;
	}

	public void setGroupteam(Integer groupteam) {
		this.groupteam = groupteam;
	}

	public Long getRoles() {
		return roles;
	}

	public void setRoles(Long roles) {
		this.roles = roles;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public PageNation(String kewWords, Integer groupteam, Long roles, int begin, int pagesize) {
		super();
		this.kewWords = kewWords;
		this.groupteam = groupteam;
		this.roles = roles;
		this.begin = begin;
		this.pagesize = pagesize;
	}

}
