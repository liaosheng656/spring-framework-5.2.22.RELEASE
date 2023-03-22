package org.af.spring;

import org.af.annotation.MyComponent;
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
 * @author lhq
 * @date 2023/3/13
 *
 */
public class AFAnnotationConfigApplicationContext {


	Map<String , MyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

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

		System.out.println(classPathList);

		MyRootBeanDefinition rootBeanDefinition;

		//封装bean定义
		for (String classPath : classPathList) {

			Class<?> aClass = null;
			try {
				//这里用的是反射，Spring里面用的ASM技术获取类的信息
				aClass = Class.forName(classPath);
			}catch (Exception e){

				e.printStackTrace();
				continue;
			}

			MyComponent MyComponent = aClass.getAnnotation(MyComponent.class);

			if(MyComponent == null){
				continue;
			}

			if(aClass.isInterface()){
				continue;
			}

			String beanName = aClass.getSimpleName();
			rootBeanDefinition = new MyRootBeanDefinition();

			//父类
			Class<?> superclass = aClass.getSuperclass();
			if(!superclass.getName().equals(Object.class.getName())){

				rootBeanDefinition.setBeanClassName(superclass.getName());
				beanName = superclass.getSimpleName();
			}

			//父接口
			Class<?>[] interfaces = aClass.getInterfaces();
			if(interfaces != null && interfaces.length > 0){
				for (Class<?> cls : interfaces) {
					rootBeanDefinition.setBeanClassName(cls.getName());
					beanName = cls.getSimpleName();
				}
			}

			String newBeanName = MyComponent.beanName();
			if(!StringUtils.isEmpty(newBeanName)){
				beanName = newBeanName;
			}

			//首字母小写

			char[] chars = beanName.toCharArray();
			chars[0] = Character.toLowerCase(chars[0]);
			beanName = new String(chars);
//			beanName = beanName.substring(0,1).toLowerCase()+beanName.substring(1);
			rootBeanDefinition.setLazyInit(MyComponent.lazy());
			//单例
			rootBeanDefinition.setScope(MyComponent.scope());

			//bean定义map
			beanDefinitionMap.put(beanName,rootBeanDefinition);
		}

		System.out.println(beanDefinitionMap);

		//实例化之前

		//实例化

		//实例化之后

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
				this.getClassFilePathList(childFile, fileNames, basePackage);
			} else {

				String pat = childFile.getAbsolutePath();
				//不是class文件，即跳过
				if(!pat.matches("^(([\\s\\S]*).class)$")){
					continue;
				}

				//
				String path = childFile.getAbsolutePath().replace("\\",".");

				//类路径
				String classPath = path.substring(path.indexOf(basePackage),path.lastIndexOf(".class"));
				//
				fileNames.add(classPath);
		}
		}
		return fileNames;
	}
}
