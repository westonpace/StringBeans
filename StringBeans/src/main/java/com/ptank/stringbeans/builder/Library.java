package com.ptank.stringbeans.builder;

import java.util.List;

import com.ptank.stringbeans.element.LanguageElement;

public interface Library<T extends LanguageElement> {

	public List<String> getItemNames();
	
	public T buildItem(String name);
	
}
