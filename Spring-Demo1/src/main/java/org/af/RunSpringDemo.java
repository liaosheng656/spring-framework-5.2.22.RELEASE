package org.af;

import org.af.annotation.MyComponentScan;
import org.af.spring.AFAnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

//@Configuration
/**
 * 手写Sring
 */
//@Configuration
@MyComponentScan(basePackage = "org.af")
@ComponentScan("org.af")
public class RunSpringDemo {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		//创建容器对象
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RunSpringDemo.class);
//		context.getBean()

//		AFAnnotationConfigApplicationContext context = new AFAnnotationConfigApplicationContext(RunSpringDemo.class);
		System.out.println("执行完了-----2222");

	}

}
