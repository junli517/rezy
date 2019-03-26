package com.rezy.dialog.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: ParamLock
 * @Description: 方法参数锁，依赖RedisLock处理.类名+方法+参数实现灵活的方法分布式锁
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface ParamLock {

	/**     
	 * @Description: 参数名称
	 * @return     
	 */  
	String value();

	/**     
	 * @Description: 对象参数
	 * @return     
	 */  
	boolean objectParam() default false;

	/**     
	 * @Description: 参数为空是否抛空指针异常
	 * @return     
	 */  
	boolean hasNullPointerException() default false;

}
