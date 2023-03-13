package org.af;

import org.af.annotation.MyComponentScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

//@Configuration
/**
 * 手写Sring
 */
@MyComponentScan(basePackage = "org.af")
public class RunSpringDemo {

	public static void main(String[] args) {

		//创建容器对象
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RunSpringDemo.class);
//		context.getBean()

		System.out.println("执行完了-----");

	}

}
