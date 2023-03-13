package org.af.spring;

import org.af.annotation.MyComponentScan;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class AFAnnotationConfigApplicationContext {


	Map<String , MyBeanDefinition> BeanDefinitionMap = new ConcurrentHashMap<>();

	public AFAnnotationConfigApplicationContext(Class<?> configClass) throws IOException, ClassNotFoundException {

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

		List<String> classPathList = new ArrayList<>();
		while(resources.hasMoreElements()) {

			URL url = resources.nextElement();

			String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
			File file = new File(filePath);

			if (!file.exists()) {
				continue;
			}
			//获取
			this.getClassFilePathList(file,classPathList,basePackage);
		}

		MyRootBeanDefinition rootBeanDefinition;
		//封装bean定义
		for (String classPath : classPathList) {
			Class<?> aClass = Class.forName(classPath);

			rootBeanDefinition = new MyRootBeanDefinition();

			rootBeanDefinition.setBeanClassName(aClass.getName());
			rootBeanDefinition.setLazyInit(false);
			//单例
			rootBeanDefinition.setScope("singleton");

			//bean定义map
			BeanDefinitionMap.put(aClass.getSimpleName(),rootBeanDefinition);
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
	private List<String> getClassFilePathList(File file, List<String> fileNames,String basePackage) {

		File[] files = file.listFiles();

		for (File childFile : files) {

			//如果是文件夹
			if (childFile.isDirectory()) {
				getFileNames(childFile, fileNames, basePackage);
			} else {

				String pat = childFile.getAbsolutePath();
				//不是class文件，即跳过
				if(!pat.matches("^(([\\s\\S]*).class)$")){
					continue;
				}

				//
				String path = childFile.getAbsolutePath().replace("\\",".");

				//类路径
				String classPath = path.substring(path.indexOf(basePackage));
				//
				fileNames.add(classPath);
		}
		}
		return fileNames;
	}
}
