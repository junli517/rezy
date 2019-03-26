
package com.rezy.dialog.web.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSONObject;
import com.rezy.dialog.common.annotations.NotLogin;
import com.rezy.dialog.common.constans.AppConstants;
import com.rezy.dialog.model.enums.ApiCodeEnum;
import com.rezy.dialog.model.vo.base.ApiResult;

/**
 * @version:（版本）
 * @Description: （描述）
 * @author: jun.li
 * @date: 2019年2月20日
 */

@Configuration
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private RedisTemplate<String, Object> simpleRedisTemplate;

	/**
	 * @Description:进入controller层之前拦截请求
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param handler
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object handler) throws Exception {
		// 有免登录注解(@NotLogin)放行
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			if (method.getMethodAnnotation(NotLogin.class) != null) {
				return true;
			}
		}

		// 判断请求头userToken
		String userToken = httpServletRequest.getHeader(AppConstants.USER_TOKEN);
		if (userToken == null || !simpleRedisTemplate.hasKey(userToken)) {
			// 用户没有登录或者token失效返回结果(请登录)
			return doHandle(httpServletResponse, ApiResult.code(ApiCodeEnum.NOT_LOGIN));
		} else {
			return true;
		}
	}

	private boolean doHandle(HttpServletResponse response, ApiResult result) throws IOException {
		if (response.getHeader("msg") != null) {
			result.setMsg(response.getHeader("msg"));
		}
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(JSONObject.toJSONString(result));
		pw.close();
		return false;
	}
}
