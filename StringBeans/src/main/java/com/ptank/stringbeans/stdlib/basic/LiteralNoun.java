package com.ptank.stringbeans.stdlib.basic;

import com.ptank.stringbeans.element.primitive.PrimitiveNoun;

public class LiteralNoun<T> implements PrimitiveNoun<T> {

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
