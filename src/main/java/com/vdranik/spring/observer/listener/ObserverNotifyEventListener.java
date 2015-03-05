package com.vdranik.spring.observer.listener;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationListener;

import com.vdranik.spring.observer.event.ObserverNotifyEvent;

public class ObserverNotifyEventListener implements ApplicationListener<ObserverNotifyEvent>, BeanNameAware {

	private String name;

	@Override
	public void onApplicationEvent(ObserverNotifyEvent event) {
		System.out.println("NOTIFY EVENT with Observer " + event.getSource() + " for Listener " + name);
	}

	@Override
	public void setBeanName(String name) {
		this.name = name;
	}
}