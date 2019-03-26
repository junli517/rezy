package com.rezy.dialog.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @ClassName: RandomUtil
 * @Description: 生成随机工具类
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月25
 */
public class RandomUtil {

	// 生成随机数最小默认数字
	private static final int MIN_NUMBER_DEFAULT = 0;
	// 生成随机字符串默认长度
	private static final int LENGTH_DEFAULT = 6;
	// 数字字母数组
	private static final char[] CHR_ARRAY = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z' };
	// 数字字母数组最大下标
	private static final int CHR_ARRAY_MAX_INDEX = 63;

	/**
	 * @Description: 生成指定范围随机数
	 * @param max：最大
	 * @param min：最小(不传默认为0)
	 * @return int
	 */
	public static int getNumber(Integer max, Integer min) {
		min = (min == null ? MIN_NUMBER_DEFAULT : min);
		return new Random().nextInt(max - min) + min;
	}

	/**
	 * @Description: 获取指定长度的数字字符串(默认长度6，多用于生成手机验证码)
	 * @param length：生成数字字符串长度
	 * @return: String
	 */
	public static String getNumberString(Integer length) {
		length = (length == null ? LENGTH_DEFAULT : length);
		int max = (int) Math.pow(10.0, ++length);
		int min = (int) Math.pow(10.0, length);
		String s = String.valueOf(getNumber(max, min));
		return s.substring(1);
	}

	/**
	 * @Description: 生成数字字母混合的字符串
	 * @param length：生成字符串的长度
	 * @return String
	 */
	public static String getString(Integer length) {
		length = (length == null ? LENGTH_DEFAULT : length);
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			buffer.append(CHR_ARRAY[random.nextInt(CHR_ARRAY_MAX_INDEX)]);
		}
		return buffer.toString();
	}

	/**
	 * @Description: 生成无下划线32位uuid
	 * @return: String
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * @Description: 通过UUID生成16位唯一订单号
	 * @return: String
	 */
	public static String getOrderIdUUID() {
		int firstNumber = getNumber(9, 1);
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		return firstNumber + String.format("%015d", hashCodeV);
	}
}
