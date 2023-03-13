package org.af.spring;

/**
 * @author lhq
 * @date 2023/03/13
 */
public class MyRootBeanDefinition implements MyBeanDefinition{


	private String beanClassName;

	private String scope;

	private boolean lazyInit;

	@Override
	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
	}

	@Override
	public String getBeanClassName() {
		return this.beanClassName;
	}

	@Override
	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public String getScope() {
		return this.scope;
	}

	@Override
	public void setLazyInit(boolean lazyInit) {
		this.lazyInit = lazyInit;
	}

	@Override
	public boolean isLazyInit() {
		return this.lazyInit;
	}

	@Override
	public boolean isSingleton() {
		return this.scope.equals("singleton");
	}

	@Override
	public boolean isPrototype() {
		return this.scope.equals("prototype");
	}
}
