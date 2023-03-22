package org.af.service.annotation;

import java.lang.annotation.*;

/**
 * 自定义扫描注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyComponentScan {

	/**
	 * 要扫的包
	 * @return
	 */
	String basePackage() default "";
}
