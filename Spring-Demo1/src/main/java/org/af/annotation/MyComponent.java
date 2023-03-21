package org.af.annotation;

import java.lang.annotation.*;

/**
 * @author lhq
 * @date 2023/03/13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyComponent {

	/**
	 * bean的名称
	 */
	String beanName() default "";

	/**
	 * 是否懒加载
	 */
	boolean lazy() default false;

	/**
	 *范围：单例或多例
	 */
	String scope() default "singleton";
}
