package com.rezy.dialog.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法参数锁，依赖RedisLock处理<br>
 * 类名+方法+参数实现灵活的方法分布式锁
 * 
 * @author zhuowei.luo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface ParamLock {
	
	/**
	 * 参数名称 
	 */
	String value();
	
	/**
	 * 对象参数 
	 */
	boolean objectParam() default false;
	
	/**
	 * 参数为空是否抛空指针异常
	 */
	boolean hasNullPointerException() default false;

}
