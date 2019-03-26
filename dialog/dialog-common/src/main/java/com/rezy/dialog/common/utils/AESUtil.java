package com.rezy.dialog.common.utils;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * @ClassName: AESUtil
 * @Description: AES加密解密工具类
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月25
 */
public class AESUtil {

	// 加密算法
	private static final String ENCRYPT_ALG = "AES";
	// 默认加密解密编码格式
	private static final String ENCODE_DEFAULT = "UTF-8";
	// 密码模式(无向量-ECB/PKCS7Padding加密)
	private static final String CIPHER_MODE_S7 = "AES/ECB/PKCS7Padding";
	// 密码模式(有向量-ECB/PKCS7Padding加密)
	private static final String CIPHER_MODE_S5 = "AES/ECB/PKCS5Padding";
	// 32位秘钥长度
	private static final int SECRET_KEY_SIZE = 32;
	// 密钥长度不够位数默认填充0(也可以用空格'字符填充)
	private static final byte PADDING_CHAR_DEFAULT = 0;

	/**
	 * @Description: AES-128位-无向量-ECB/PKCS7Padding加密
	 * @param content：加密内容
	 * @param key：密钥
	 * @param encoding：加密编码(默认"UTF-8")
	 * @return String(aes加密后转base64)
	 * @throws Exception
	 */
	public static String aesPKCS7PaddingEncrypt(String content, String key, String encoding) throws Exception {
		// 未设置编码使用默认编码
		encoding = (encoding == null ? ENCODE_DEFAULT : encoding);
		// 不能写Security.addProvider(new BouncyCastleProvider()); 防止内存溢出
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			// PKCS7Padding需要额外添加 BouncyCastleProvider()的provider
			Security.addProvider(new BouncyCastleProvider());
		}
		Cipher cipher = Cipher.getInstance(CIPHER_MODE_S7);
		byte[] realKey = getSecretKey(key, encoding);
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(realKey, ENCRYPT_ALG));
		byte[] data = cipher.doFinal(content.getBytes(ENCODE_DEFAULT));
		String result = new Base64().encodeToString(data);
		return result;
	}

	/**
	 * @Description: AES-128位-无向量-ECB/PKCS7Padding解密
	 * @param content：解密内容
	 * @param key：密钥
	 * @param encoding：解密编码(默认"UTF-8")
	 * @return String(aes解密后转base64)
	 * @throws Exception
	 */
	public static String aesPKCS7PaddingDecrypt(String content, String key, String encoding) throws Exception {
		// 未设置编码使用默认编码
		encoding = (encoding == null ? ENCODE_DEFAULT : encoding);
		byte[] decodeBytes = Base64.decodeBase64(content);
		Cipher cipher = Cipher.getInstance(CIPHER_MODE_S7);
		byte[] realKey = getSecretKey(key, encoding);
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(realKey, ENCRYPT_ALG));
		byte[] realBytes = cipher.doFinal(decodeBytes);
		return new String(realBytes, ENCODE_DEFAULT);
	}

	/**
	 * @Description: 对密钥key处理，如密钥长度不够位数用0填充(也可以空格字符填充)
	 * @param key：秘钥
	 * @param encoding：编码
	 * @return byte[]
	 * @throws Exception
	 */
	private static byte[] getSecretKey(String key, String encoding) throws Exception {
		byte[] realKey = new byte[SECRET_KEY_SIZE];
		byte[] byteKey = key.getBytes(encoding);
		for (int i = 0; i < realKey.length; i++) {
			if (i < byteKey.length) {
				realKey[i] = byteKey[i];
			} else {
				realKey[i] = PADDING_CHAR_DEFAULT;
			}
		}
		return realKey;
	}

	/**
	 * @Description: AES-128位-无向量-ECB/PKCS5Padding加密
	 * @param content：加密内容
	 * @param key：密钥
	 * @param encoding：加密编码(默认"UTF-8")
	 * @return String(aes加密后转base64)
	 * @throws Exception
	 */
	public static String aesPKCS5PaddingEncrypt(String content, String key, String encoding) throws Exception {
		// 未设置编码使用默认编码
		encoding = (encoding == null ? ENCODE_DEFAULT : encoding);
		Cipher cipher = Cipher.getInstance(CIPHER_MODE_S5);
		byte[] realKey = getSecretKey(key, encoding);
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(realKey, ENCRYPT_ALG));
		byte[] data = cipher.doFinal(content.getBytes(ENCODE_DEFAULT));
		String result = new Base64().encodeToString(data);
		return result;
	}

	/**
	 * @Description: AES-128位-无向量-ECB/PKCS5Padding解密
	 * @param content：解密内容
	 * @param key：密钥
	 * @param encoding：解密编码(默认"UTF-8")
	 * @return String(aes解密后转base64)
	 * @throws Exception
	 */
	public static String aesPKCS5PaddingDecrypt(String content, String key, String encoding) throws Exception {
		// 未设置编码使用默认编码
		encoding = (encoding == null ? ENCODE_DEFAULT : encoding);
		byte[] decodeBytes = Base64.decodeBase64(content);
		Cipher cipher = Cipher.getInstance(CIPHER_MODE_S5);
		byte[] realKey = getSecretKey(key, encoding);
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(realKey, ENCRYPT_ALG));
		byte[] realBytes = cipher.doFinal(decodeBytes);
		return new String(realBytes, ENCODE_DEFAULT);
	}
}
