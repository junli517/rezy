package com.rezy.dialog.service.redis;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisService
 * @Description: (描述)
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月27
 */
public interface RedisService {

	/**
	 * @Description: 保存key-value值
	 * @param key：键
	 * @param obj：值
	 * @param timeout：过期时间
	 * @param unit：时间单位
	 */
	public void saveObj(String key, Object value, long timeout, TimeUnit unit);

	/**
	 * @Description: 根据key查询value
	 * @param key
	 * @return
	 */
	public Object queryObj(String key);

	/**
	 * @Description: 移除key-value值
	 * @param key
	 * @return
	 */
	public boolean removeObj(String key);

	/**
	 * @Description: 设置失效时间
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public Boolean setExpireTime(String key, long timeout, TimeUnit unit);

	/**
	 * @Description: 清除hash结构redis的hashKey数据
	 * @param key
	 * @param hashKey
	 */
	public void removeHashKey(String key, String hashKey);

	/**
	 * @Description: 获取hash结构的value
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public Object getHashValue(String key, String hashKey);

	/**
	 * @Description: 保存hash结构的数据
	 * @param key
	 * @param hashKey
	 * @param value
	 * @param timeout
	 * @param unit
	 */
	public void saveHashObject(String key, String hashKey, String value, long timeout);

}
