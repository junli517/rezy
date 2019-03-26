package com.rezy.dialog.web.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.rezy.dialog.common.constans.AppConstants;
import com.rezy.dialog.common.exceptions.ParamCheckException;
import com.rezy.dialog.model.entity.UserEntity;

public class BaseController {

	@Autowired(required = false)
	protected HttpServletRequest request;

	/**
	 * 获取当前用户
	 */
	protected UserEntity getSessionUser() {
		return (UserEntity) request.getSession().getAttribute(AppConstants.USER_SESSION);
	}

	/**
	 * 获取当前用户id
	 */
	protected Integer getSessionUserId() {
		UserEntity user = getSessionUser();
		return user != null ? user.getId() : null;
	}

	/**
	 * 检查参数
	 * 
	 * @param isParamError 是否为错误的参数
	 * @param msg          错误提示
	 */
	protected void paramCheck(boolean isParamError, String msg) {
		if (isParamError) {
			throw new ParamCheckException(msg);
		}
	}

	protected void paramCheck(boolean isError, String code, String message) {
		if (isError) {
			throw new ParamCheckException(code, message);
		}
	}

}
