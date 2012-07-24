package com.ptank.stringbeans.element;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.ptank.stringbeans.element.io.AnyTypeAdapter;

@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Action extends LanguageElement {

	public void doAction();
	
}
