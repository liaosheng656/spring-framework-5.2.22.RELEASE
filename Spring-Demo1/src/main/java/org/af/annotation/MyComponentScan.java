package org.af.annotation;

import java.lang.annotation.*;

/**
 * 自定义扫描注解
 * @author 19129
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyComponentScan {

	/**
	 * 要扫的包
	 */
	String basePackage() default "";
}
