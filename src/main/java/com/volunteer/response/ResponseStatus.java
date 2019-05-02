package com.volunteer.response;

/**
 * 
 * @author NewsDLee
 * @date 2019年4月14日20:20:12
 * @desc 返回的状态码和信息
 *
 */
public enum ResponseStatus {

	FAIL("fail", "1000001"),
	SUCCESS("success", "1000002"),
	PERMISSION("not have permission", "1000200");
	
	private ResponseStatus(String message, String code) {
		this.message = message;
		this.code = code;
	}

	private String message;
	
	private String code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
