package com.ptank.stringbeans.stdlib.basic;

import com.ptank.stringbeans.core.Noun;

public class LiteralNoun<T> implements Noun<T> {

	private T literalValue;
	
	public LiteralNoun(T literalValue) {
		this.literalValue = literalValue;
	}
	
	@Override
	public String getBeanString() {
		return "'" + literalValue.toString() + "'";
	}

	@Override
	public T get() {
		return literalValue;
	}

}
