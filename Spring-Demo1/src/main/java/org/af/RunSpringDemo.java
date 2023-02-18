package org.af;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * 启动类
 * @author: fanjin
 * @date: 2023/02/17
 *
 */
@Configuration
public class RunSpringDemo {

	public static void main(String[] args) {

		//创建容器对象
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RunSpringDemo.class);
//		context.getBean()

		System.out.println("执行完了-----");

	}

}
