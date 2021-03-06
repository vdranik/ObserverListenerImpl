package com.vdranik.spring.observer.event;

import org.springframework.context.ApplicationEvent;

import com.vdranik.spring.observer.bean.impl.Observer;

public class ObserverRemoveEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public ObserverRemoveEvent(Observer observer) {
		super(observer);
	}

	@Override
	public Observer getSource() {
		return (Observer) super.getSource();
	}

}
