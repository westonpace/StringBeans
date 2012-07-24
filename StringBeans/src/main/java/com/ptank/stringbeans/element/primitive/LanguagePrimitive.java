package com.ptank.stringbeans.element.primitive;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.ptank.stringbeans.element.io.AnyTypeAdapter;

@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface LanguagePrimitive {

	@XmlTransient
	public String getBeanString();
	
}
