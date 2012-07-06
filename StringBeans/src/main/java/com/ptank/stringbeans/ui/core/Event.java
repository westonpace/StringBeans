package com.ptank.stringbeans.ui.core;

import java.util.ArrayList;
import java.util.List;

public class Event<T> {

	public static interface EventListener<T> {
		public void onEvent(T event);
	}
	
	private List<EventListener<T>> listeners = new ArrayList<EventListener<T>>();
	
	public void addListener(EventListener<T> l) {
		listeners.add(l);
	}
	
	public void removeListener(EventListener<T> l) {
		listeners.remove(l);
	}
	
	public void fireEvent(T event) {
		for(EventListener<T> listener : listeners) {
			listener.onEvent(event);
		}
	}
	
}
