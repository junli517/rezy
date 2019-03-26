package com.rezy.dialog.model.vo.base;

import java.sql.SQLException;

import com.rezy.dialog.common.exceptions.ServiceException;
import com.rezy.dialog.model.enums.ApiCodeEnum;

/**
 * @ClassName: ExceptionResult
 * @Description: 异常返回结果
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月25
 */
public class ExceptionResult extends ApiResult {

	private static final long serialVersionUID = 1L;

	private String exception;

	public ExceptionResult setException(Exception e) {
		if (e != null) {
			String message = e.getMessage();
			if (e instanceof ServiceException) {
				this.exception = "ServiceException:" + message;
			} else if (e instanceof NullPointerException) {
				this.exception = "NullPointerException:" + message;
			} else if (e instanceof SQLException) {
				this.exception = "SQLException:" + message;
			} else {
				if (e.getCause() != null && e.getClass().getName().startsWith("org.springframework.dao")) {
					e = (Exception) e.getCause();
				}
				this.exception = e.getClass().getName() + ":" + e.getMessage();
			}
		}
		return this;
	}

	@Override
	public ExceptionResult setCode(String code) {
		super.setCode(code);
		return this;
	}

	@Override
	public ExceptionResult setMsg(String msg) {
		super.setMsg(msg);
		return this;
	}

	public String getException() {
		return exception;
	}

	public static ExceptionResult fail(String msg) {
		ExceptionResult result = new ExceptionResult();
		result.setCode(ApiCodeEnum.FAIL.getCode());
		result.setMsg(msg != null ? msg : ApiCodeEnum.FAIL.getMsg());
		return result;
	}
}
