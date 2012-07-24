package com.ptank.stringbeans.element;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.ptank.stringbeans.element.io.AnyTypeAdapter;

@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Noun<T> extends LanguageElement {

	public T get();
	
}
