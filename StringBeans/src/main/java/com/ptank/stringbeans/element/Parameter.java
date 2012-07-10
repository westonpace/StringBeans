package com.ptank.stringbeans.element;


public class Parameter<T extends LanguageElement> {

	private Class<T> parameterType;
	private T value;
	private String name;
	
	public Parameter(Class<T> parameterType) {
		this.parameterType = parameterType;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Class<T> getParameterType() {
		return parameterType;
	}

	public void setParameterType(Class<T> parameterType) {
		this.parameterType = parameterType;
	}
	
}
