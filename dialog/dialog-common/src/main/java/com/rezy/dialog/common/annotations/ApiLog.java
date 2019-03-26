package com.rezy.dialog.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName:  ApiLog
 * @Description: Api Log 日志.切面log日志输出记录注解
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月25 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ApiLog {

	/**     
	 * @Description: Api描述
	 * @return     
	 */  
	String value() default "";
	
	/**     
	 * @Description: logger value. 
	 * @return     
	 */  
	String loggerValue() default "";
	
	/**     
	 * @Description: 是否打印日志
	 * @return     
	 */  
	boolean log() default true;	
}
