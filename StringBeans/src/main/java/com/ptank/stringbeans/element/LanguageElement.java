package com.ptank.stringbeans.element;

import java.util.Map;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.ptank.stringbeans.element.io.AnyTypeAdapter;

@XmlJavaTypeAdapter(AnyTypeAdapter.class)
@XmlSeeAlso({Action.class,Noun.class,Expression.class})
public interface LanguageElement {

	@XmlTransient
	public abstract BeanString getBeanString();

	public abstract Map<String, Parameter<?>> getParameters();

	public abstract String getFullBeanString();
	
}