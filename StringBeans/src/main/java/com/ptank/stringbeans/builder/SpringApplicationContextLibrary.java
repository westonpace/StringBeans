package com.ptank.stringbeans.builder;

import java.util.Collection;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContextLibrary implements Library,ApplicationContextAware {

	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	@Override
	public <T> Collection<T> retrieveItems(Class<T> elementClass) {
		return applicationContext.getBeansOfType(elementClass).values();
	}

	
	
}
