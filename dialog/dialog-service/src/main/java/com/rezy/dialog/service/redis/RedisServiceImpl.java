package com.rezy.dialog.service.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: RedisServiceImpl
 * @Description: (描述)
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月27
 */

@Transactional
@Service("redisService")
public class RedisServiceImpl implements RedisService {

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Override
	public void saveObj(String key, Object obj, long timeout, TimeUnit unit) {
		ValueOperations<String, Object> operation = redisTemplate.opsForValue();
		operation.set(key, obj, timeout, unit);
	}

	@Override
	public Object queryObj(String key) {
		ValueOperations<String, Object> operation = redisTemplate.opsForValue();
		return operation.get(key);
	}

	@Override
	public boolean removeObj(String key) {
		if (redisTemplate.hasKey(key)) {
			return redisTemplate.delete(key);
		}
		return true;
	}

	@Override
	public Boolean setExpireTime(String key, long timeout, TimeUnit unit) {
		return redisTemplate.expire(key, timeout, unit);
	}

	@Override
	public void removeHashKey(String key, String hashKey) {
		HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
		ops.delete(key, hashKey);
	}

	@Override
	public Object getHashValue(String key, String hashKey) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] keyArray = serializer.serialize(key);// key
				byte[] hashKeyArray = serializer.serialize(hashKey);// hashKey
				// 保存
				byte[] hGet = connection.hGet(keyArray, hashKeyArray);
				if (hGet != null && hGet.length > 0) {
					return new String(hGet);
				}
				return null;
			}
		});
	}

	@Override
	public void saveHashObject(String key, String hashKey, String value, long timeout) {
		redisTemplate.setEnableTransactionSupport(false);
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] keyArray = serializer.serialize(key);// key
				byte[] hashKeyArray = serializer.serialize(hashKey);// hashKey
				byte[] valueArray = serializer.serialize(value);// value
				// 设置默认的key-field-value
				if (connection.hGet(keyArray, keyArray) == null) {
					connection.hSet(keyArray, keyArray, keyArray);
					connection.expire(keyArray, timeout);// 过期时间
				}
				// 保存
				connection.hSet(keyArray, hashKeyArray, valueArray);
				return true;
			}
		});
	}
}
