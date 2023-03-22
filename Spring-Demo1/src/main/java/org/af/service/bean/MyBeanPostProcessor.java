package org.af.service.bean;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public  Object postProcessAfterInitialization(Object bean, String beanName){

		return bean;
	}
}
