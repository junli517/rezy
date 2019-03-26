package com.rezy.dialog.model.enums;

/**
 * @ClassName: ApiCodeEnum
 * @Description: 返回码枚举
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月25
 */
public enum ApiCodeEnum {

	//参数错误
	PARAM_ERROR("-1","参数信息错误！"),
	//成功
	SUCCESS("0000", "成功"),
	//失败
	FAIL("9999", "失败"),
	//未登录或登录失效
	NOT_LOGIN("6666", "请登录");

	private String code;
	private String msg;

	private ApiCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
