package com.ptank.stringbeans.rainbow.core;

import java.util.ArrayList;
import java.util.List;

public class TokenPool {

	private List<Color> tokens = new ArrayList<Color>();
	
	public void addToken(Color token) {
		tokens.add(token);
	}
	
	public void removeToken(Color token) {
		tokens.remove(token);
	}
	
	public boolean isTokenPresent(Color token) {
		return tokens.contains(token);
	}
	
}
