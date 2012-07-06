package com.ptank.stringbeans.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class InMemoryLibrary implements Library {

	private HashMap<Class<?>,List<?>> library = new HashMap<Class<?>,List<?>>();

	@SuppressWarnings("unchecked")
	@Override
	public <T> Collection<T> retrieveItems(Class<T> elementClass) {
		return (List<T>)library.get(elementClass);
	}

	@SuppressWarnings("unchecked")
	public <T> void addItem(T item, Class<T> itemClass) {
		if(!library.containsKey(itemClass)) {
			library.put(itemClass, new ArrayList<T>());
		}
		
		List<T> itemList = (List<T>) library.get(itemClass);
		itemList.add(item);
	}
	
}
