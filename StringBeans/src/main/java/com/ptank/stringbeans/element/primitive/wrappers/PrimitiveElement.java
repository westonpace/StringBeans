package com.ptank.stringbeans.element.primitive.wrappers;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.commons.beanutils.PropertyUtils;

import com.ptank.stringbeans.element.AbstractLanguageElement;
import com.ptank.stringbeans.element.BeanString;
import com.ptank.stringbeans.element.LanguageElement;
import com.ptank.stringbeans.element.Parameter;
import com.ptank.stringbeans.element.primitive.LanguagePrimitive;

@XmlRootElement
@XmlSeeAlso({PrimitiveActionElement.class,PrimitiveExpressionElement.class,PrimitiveNounElement.class})
public abstract class PrimitiveElement<T extends LanguagePrimitive> extends AbstractLanguageElement {

	protected T primitive;
	
	PrimitiveElement() {
		
	}
	
	public PrimitiveElement(T primitive) {
		this.primitive = primitive;
		reflectOnPrimitive();
	}
	
	@XmlElement
	public T getPrimitive() {
		return primitive;
	}
	
	void setPrimitive(T primitive) {
		this.primitive = primitive;
	}
	
	private void reflectOnPrimitive() {
		addParameters(getElementParameters());
		setBeanString(beanStringFromTrueString());
	}
		
	private BeanString beanStringFromTrueString() {
		return new BeanString(primitive.getBeanString());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Parameter<?>> getElementParameters() {
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(primitive);
		ArrayList<Parameter<?>> result = new ArrayList<Parameter<?>>();
		
		for(PropertyDescriptor descriptor : descriptors) {
			if(LanguageElement.class.isAssignableFrom(descriptor.getPropertyType())) {
				try {
					Parameter parameter = new Parameter(descriptor.getPropertyType());
					parameter.setName(descriptor.getName());
					parameter.setValue((LanguageElement)descriptor.getReadMethod().invoke(primitive));
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
			
}
