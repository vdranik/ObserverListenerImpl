package com.vdranik.spring.observer.listener;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationListener;

import com.vdranik.spring.observer.event.ObserverAddEvent;

public class ObserverAddEventListener implements ApplicationListener<ObserverAddEvent>, BeanNameAware {
	private String name;

	@Override
	public void onApplicationEvent(ObserverAddEvent event) {
		System.out.println("ADD EVENT with Observer " + event.getSource() + " for Listener " + name);
	}

	@Override
	public void setBeanName(String name) {
		this.name = name;
	}

}
