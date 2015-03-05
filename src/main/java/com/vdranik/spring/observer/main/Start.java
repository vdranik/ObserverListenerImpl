package com.vdranik.spring.observer.main;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vdranik.spring.observer.bean.ConcreteObserver;
import com.vdranik.spring.observer.bean.ConcreteSubject;
import com.vdranik.spring.observer.bean.impl.Subject;

public class Start {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.registerShutdownHook();
		Subject subject = context.getBean(ConcreteSubject.class);

		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setInitMethodName("init");
		beanDefinition.setDestroyMethodName("destroy");
		beanDefinition.setBeanClass(ConcreteObserver.class);
		beanDefinition.setLazyInit(true);
		beanDefinition.setAbstract(false);
		beanDefinition.setAutowireCandidate(true);
		beanDefinition.setScope("prototype");
		beanFactory.registerBeanDefinition("concreteObserver", (BeanDefinition) beanDefinition);

		ConcreteObserver concreteObserver1 = context.getBean("concreteObserver", ConcreteObserver.class);
		ConcreteObserver concreteObserver2 = context.getBean("concreteObserver", ConcreteObserver.class);

		System.out.println("LISTEN ADD ===========================================================");
		subject.addObserver(concreteObserver1);
		subject.addObserver(concreteObserver2);

		System.out.println("LISTEN NOTIFY_1 ======================================================");
		subject.notifyObservers("Hello");

		System.out.println("LISTEN REMOVE ========================================================");
		subject.removeObserver(concreteObserver1);

		System.out.println("LISTEN NOTIFY_2 ======================================================");
		subject.notifyObservers("Spring!");
	}
}