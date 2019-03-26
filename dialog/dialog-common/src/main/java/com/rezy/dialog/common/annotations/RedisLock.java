package com.rezy.dialog.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName:  RedisLock
 * @Description: 基于Redis的分布式锁。配合ParamLock可实现分布式方法参数锁
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月27 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisLock {

	/**     
	 * @Description: 全局key，默认为 ClassSimpleName.MethodName
	 * @return     
	 */  
	String globalKey() default "";
	
	/**     
	 * @Description: 过期时间(秒)，防止死锁，默认3分钟
	 * @return     
	 */  
	long expireOfSecond() default 180;
	
	/**     
	 * @Description: 等待锁轮询间隔时间(毫秒)，默认100毫秒
	 * @return     
	 */  
	long pollingOfMillisecond() default 100;
	
	/**     
	 * @Description: 超过最大等待时间是否抛异常, true超时抛异常，false超时强制执行（默认true）
	 * @return     
	 */  
	boolean exceedMaxWaitException() default true;
	
	/**     
	 * @Description: 最大等待时间(秒)，默认3分钟
	 * @return     
	 */  
	long maxWaitOfSecond() default 180;
	
}
