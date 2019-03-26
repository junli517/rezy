package com.rezy.dialog.common.exceptions;

/**
 * @ClassName: ParamCheckException
 * @Description: 参数错误异常
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月25
 */
public class ParamCheckException extends ServiceException {

	private static final long serialVersionUID = 1L;
	private static final String PARAM_ERROR_CODE = "-1";

	public ParamCheckException(String message) {
		super(PARAM_ERROR_CODE, message);
	}

	public ParamCheckException(String code, String message) {
		super(code, message);
	}

	/*
	 * ParamCheckException 不需要stack信息重写 fillInStackTrace
	 * 
	 * @see 提高服务器性能
	 */
	@Override
	public synchronized Throwable fillInStackTrace() {
		// return super.fillInStackTrace();
		return null;
	}
}
