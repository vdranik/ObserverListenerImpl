package com.vdranik.spring.observer.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.vdranik.spring.observer.bean.impl.Observer;
import com.vdranik.spring.observer.bean.impl.Subject;
import com.vdranik.spring.observer.event.ObserverAddEvent;
import com.vdranik.spring.observer.event.ObserverNotifyEvent;
import com.vdranik.spring.observer.event.ObserverRemoveEvent;

public class ConcreteSubject implements Subject, ApplicationContextAware, BeanNameAware, BeanFactoryAware, InitializingBean, ApplicationEventPublisherAware {

	private ApplicationEventPublisher applicationEventPublisher;
	private List<Observer> observers = new ArrayList<Observer>();

	public ConcreteSubject() {
		System.out.println("create ConcreteSubject");
	}

	@Override
	public List<Observer> getObservers() {
		return observers;
	}

	@Override
	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}

	@Override
	public void addObserver(Observer observer) {
		this.observers.add(observer);
		applicationEventPublisher.publishEvent(new ObserverAddEvent(observer));
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
		applicationEventPublisher.publishEvent(new ObserverRemoveEvent(observer));
	}

	@Override
	public void removeAllObservers() {
		getObservers().clear();
	}

	@Override
	public void notifyObservers(String message) {
		for (Observer observer : getObservers()) {
			applicationEventPublisher.publishEvent(new ObserverNotifyEvent(message));
			observer.notify(message);
		}
	}

	public void init() {
		System.out.println(this + " init ConcreteSUBJECT");
	}

	public void destroy() {
		System.out.println(this + " destroy ConcreteSUBJECT");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(this + " afterPropertiesSet");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println(this + " BEAN FACTORY - " + beanFactory);
	}

	@Override
	public void setBeanName(String name) {
		System.out.println(this + " bean name " + name);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println(this + " applicationContext " + applicationContext);
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
}