package com.volunteer.constant;

/**
 * 
 * @author NewsDLee
 * @date 2019年4月14日20:12:29
 * @param <T>
 * @desc 返回信息
 */
public class ApiResponse<T> {

	/**
	 * 返回的信息
	 */
	private String message;
	
	/**
	 * 返回的状态码
	 */
	private String code;
	
	/**
	 * 返回体
	 */
	private T date;

	
	public ApiResponse() {
		super();
	}

	public ApiResponse(String message, String code, T date) {
		super();
		this.message = message;
		this.code = code;
		this.date = date;
	}

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

	public T getDate() {
		return date;
	}

	public void setDate(T date) {
		this.date = date;
	}
	
	public ApiResponse<T> build(ResponseStatus successMessage, T date) {
		return new ApiResponse<>(successMessage.getMessage(), successMessage.getCode(), date);
	}
	
}
