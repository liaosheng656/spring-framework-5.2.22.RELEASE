package org.af.spring;

/**
 * @author lhq
 * @date 2023/03/13
 */
public interface MyBeanDefinition {

	void setBeanClassName(String beanClassName);

	String getBeanClassName();

	void setScope(String scope);

	String getScope();

	void setLazyInit(boolean lazyInit);

	boolean isLazyInit();

	boolean isSingleton();

	boolean isPrototype();

}
