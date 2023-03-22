package org.af.service.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.ResolvableType;

/**
 * @author lhq
 * @date 2023/03/22
 */
public class MyApplicationEventMulticaster implements ApplicationEventMulticaster {


	@Override
	public void addApplicationListener(ApplicationListener<?> listener) {

	}

	@Override
	public void addApplicationListenerBean(String listenerBeanName) {

	}

	@Override
	public void removeApplicationListener(ApplicationListener<?> listener) {

	}

	@Override
	public void removeApplicationListenerBean(String listenerBeanName) {

	}

	@Override
	public void removeAllListeners() {

	}

	@Override
	public void multicastEvent(ApplicationEvent event) {

	}

	@Override
	public void multicastEvent(ApplicationEvent event, ResolvableType eventType) {

	}
}
