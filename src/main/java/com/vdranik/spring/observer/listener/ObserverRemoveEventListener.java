package com.vdranik.spring.observer.listener;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationListener;

import com.vdranik.spring.observer.event.ObserverRemoveEvent;

public class ObserverRemoveEventListener implements ApplicationListener<ObserverRemoveEvent>, BeanNameAware {

	private String name;

	@Override
	public void onApplicationEvent(ObserverRemoveEvent event) {
		System.out.println("REMOVE EVENT with Observer " + event.getSource() + " for Listener " + name);
	}

	@Override
	public void setBeanName(String name) {
		this.name = name;
	}
}
