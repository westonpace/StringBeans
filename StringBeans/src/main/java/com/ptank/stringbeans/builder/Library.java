package com.ptank.stringbeans.builder;

import java.util.Collection;

public interface Library {

	public <T> Collection<T> retrieveItems(Class<T> elementClass);
	
}
