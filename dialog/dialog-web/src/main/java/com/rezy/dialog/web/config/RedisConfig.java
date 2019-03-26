package com.rezy.dialog.web.config;

import java.lang.reflect.Method;
import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: RedisConfig
 * @Description: (描述)
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月26
 */

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuffer sb = new StringBuffer();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

	/**
	 * @Description: JdkSerializationRedisSerializer(默认)是最高效的。
	 *               Jackson2JsonRedisSerializer占用的内存最小，Jackson2JsonRedisSerializer可以跨项目序列化。
	 *               Jackson2JsonRedisSerializer序列化反序列化带泛型的List数据，使用Jackson2JsonRedisSerializer反序列化时报错。
	 *               Jackson2JsonRedisSerializer序列化带泛型的数据时，会以map的结构进行存储。
	 *               Jackson2JsonRedisSerializer反序列化是不能将map解析成对象。序列化存储时，转成JSON字符串可解决。
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		// 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
		Jackson2JsonRedisSerializer<JSON> serializer = new Jackson2JsonRedisSerializer<JSON>(JSON.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		serializer.setObjectMapper(mapper);
		template.setValueSerializer(serializer);
		template.setHashValueSerializer(serializer);
		// 使用StringRedisSerializer(简单的字符串序列化)来序列化和反序列化redis的key值
		template.setKeySerializer(new StringRedisSerializer());
		template.afterPropertiesSet();
		return template;
	}

	/**
	 * @Description: 缓存配置
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		// 生成一个默认配置，通过config对象即可对缓存进行自定义配置
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		// 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
		Jackson2JsonRedisSerializer<JSON> serializer = new Jackson2JsonRedisSerializer<JSON>(JSON.class);
		// 配置序列化
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
		config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer));
		config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));
		// 设置缓存的默认过期时间
		config.entryTtl(Duration.ofSeconds(1));
		// 不缓存空值
		config.disableCachingNullValues();
		RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config)
				.build();
		return cacheManager;
	}
}
