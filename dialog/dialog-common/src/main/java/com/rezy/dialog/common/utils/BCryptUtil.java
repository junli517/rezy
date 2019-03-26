package com.rezy.dialog.common.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @ClassName: EncrptUtil
 * @Description: BCrypt工具类
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月25
 */
public class BCryptUtil {

	/**
	 * @Description: 对密码加密
	 * @param password
	 * @return
	 */
	public static String encodePassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	/**     
	 * @Description: 校验密码和encodedPassword是否匹配
	 * @param password
	 * @param encodedPassword
	 * @return     
	 */  
	public boolean matches(String password, String encodedPassword) {
		return BCrypt.checkpw(password, encodedPassword);
	}
}
