package com.rezy.dialog.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 基于Redis的分布式锁.
 * 
 * @desc 配合ParamLock可实现分布式方法参数锁
 * @author zhuowei.luo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisLock {

	/**
	 * 全局key 
	 * 
	 * @desc 默认为 ClassSimpleName.MethodName
	 */
	String globalKey() default "";
	
	/**
	 * 过期时间（秒）
	 * 
	 * @desc 防止死锁，默认3分钟
	 */
	long expireOfSecond() default 180;
	
	/**
	 * 等待锁轮询间隔时间 （毫秒）
	 * 
	 * @desc 默认100毫秒
	 */
	long pollingOfMillisecond() default 100;
	
	/**
	 * 超过最大等待时间是否抛异常 
	 * 
	 * @desc true超时抛异常，false超时强制执行（默认true）
	 */
	boolean exceedMaxWaitException() default true;
	
	/**
	 * 最大等待时间（秒） 
	 * 
	 * @desc 默认3分钟
	 */
	long maxWaitOfSecond() default 180;
	
}
