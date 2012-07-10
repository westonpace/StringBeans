package com.ptank.stringbeans.element;

import java.util.Map;

public interface LanguageElement {

	public abstract BeanString getBeanString();

	public abstract Map<String, Parameter<?>> getParameters();

	public abstract String getFullBeanString();
	
}