package com.ptank.stringbeans.reflection;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import com.ptank.stringbeans.element.BeanString;
import com.ptank.stringbeans.element.Parameter;
import com.ptank.stringbeans.element.BeanString.BeanStringComponent;
import com.ptank.stringbeans.element.BeanString.ComponentType;
import com.ptank.stringbeans.element.primitive.LanguagePrimitive;

public class LanguageElementReflector {

	private LanguagePrimitive target;
	
	public LanguageElementReflector(LanguagePrimitive target) {
		this.target = target;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Parameter<?>> getElementParameters() {
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(target);
		ArrayList<Parameter<?>> result = new ArrayList<Parameter<?>>();
		
		for(PropertyDescriptor descriptor : descriptors) {
			if(LanguagePrimitive.class.isAssignableFrom(descriptor.getPropertyType())) {
				try {
					Parameter parameter = new Parameter(descriptor.getPropertyType());
					parameter.setName(descriptor.getName());
					parameter.setValue((LanguagePrimitive)descriptor.getReadMethod().invoke(target));
					result.add(parameter);
				} catch(InvocationTargetException ex) {
					throw new RuntimeException("Error reflecting on parameter: " + descriptor.getName(),ex);
				} catch (IllegalArgumentException ex) {
					throw new RuntimeException("Error reflecting on parameter: " + descriptor.getName(),ex);
				} catch (IllegalAccessException ex) {
					throw new RuntimeException("Error reflecting on parameter: " + descriptor.getName(),ex);
				}
			}
		}
		
		return result;
	}
	
	public Parameter<?> getElementParameter(String name) {
		return findParameter(getElementParameters(), name);
	}
	
	private Parameter<?> findParameter(List<Parameter<?>> parameters, String name) {
		for(Parameter<?> parameter : parameters) {
			if(parameter.getName().equals(name)) {
				return parameter;
			}
		}
		return null;
	}
	
	public String getFullBeanString() {
		String result = "";
		BeanString beanString = new BeanString(target.getBeanString());
		List<Parameter<?>> parameters = getElementParameters();
		for(BeanStringComponent component : beanString) {
			if(component.getComponentType() == ComponentType.TEXT) {
				result += component.getValue();
			} else {
				Parameter<?> componentParameter = findParameter(parameters,component.getValue());
				result += new LanguageElementReflector(componentParameter.getValue()).getFullBeanString();
			}
		}
		return result;
	}
}
