package org.af.service;

import org.af.service.annotation.MyComponentScan;
import org.af.service.spring.AFAnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * 手写Sring
 */
@MyComponentScan(basePackage = "org.af.service")
public class RunSpringServiceDemo {

	public static void main(String[] args) throws IOException {

		//初始化Spring容器
		AFAnnotationConfigApplicationContext context = new AFAnnotationConfigApplicationContext(RunSpringServiceDemo.class);



	}
}
