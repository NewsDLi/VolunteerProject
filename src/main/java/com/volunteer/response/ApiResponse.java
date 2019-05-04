package com.volunteer.response;

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
	private T data;

	
	public ApiResponse() {
		super();
	}

	public ApiResponse(String message, String code, T data) {
		super();
		this.message = message;
		this.code = code;
		this.data = data;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> ApiResponse<T> build(ResponseStatus successMessage, T data) {
		return new ApiResponse<T>(successMessage.getMessage(), successMessage.getCode(), data);
	}
	
}
