package org.af.service.spring;

import org.af.service.RunSpringServiceDemo;
import org.af.service.annotation.MyComponentScan;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class AFAnnotationConfigApplicationContext {

	public AFAnnotationConfigApplicationContext(Class<?> configClass) throws IOException {

		//是否扫描注解
		MyComponentScan annotation = configClass.getAnnotation(MyComponentScan.class);

		if(annotation == null){
			return;
		}

		String basePackage = annotation.basePackage();

		if(StringUtils.isEmpty(basePackage)){
			return;
		}
		//扫描包
		ClassLoader classLoader = configClass.getClassLoader();

		Enumeration<URL> resources = classLoader.getResources(basePackage.replace(".", "/"));

		while(resources.hasMoreElements()) {

			URL url = resources.nextElement();

			String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
			File file = new File(filePath);

			if (!file.exists()) {
				continue;
			}


			System.out.println(filePath);;
		}


		//转换成bean定义

		//初始化前-后置处理器

		//初始化

		//初始化之后-后置处理器

		//代理-JDK/cglib代理

		//AOP-事务
	}

	/**
	 * 得到文件名称
	 *
	 * @param file      文件
	 * @param fileNames 文件名
	 * @return {@link List}<{@link String}>
	 */
	private List<String> getFileNames(File file, List<String> fileNames) {

		File[] files = file.listFiles();

		for (File childFile : files) {

			//如果是文件夹
			if (childFile.isDirectory()) {
				getFileNames(childFile, fileNames);
			} else {
				//
				fileNames.add(childFile.getName());
			}
		}
		return fileNames;
	}
}
