package com.rezy.dialog.common.exceptions;

/**
 * @ClassName: ServiceException
 * @Description: Service异常
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月25
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code = "9999";

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String code, String message) {
		this(message);
		this.code = code;
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String code, String message, Throwable cause) {
		this(message, cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
