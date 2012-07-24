package com.ptank.stringbeans.element.primitive.wrappers;

import com.ptank.stringbeans.element.Noun;
import com.ptank.stringbeans.element.primitive.PrimitiveNoun;

public class PrimitiveNounElement<T> extends PrimitiveElement<PrimitiveNoun<T>> implements Noun<T> {

	PrimitiveNounElement() {
		super();
	}
	
	public PrimitiveNounElement(PrimitiveNoun<T> primitive) {
		super(primitive);
	}

	@Override
	public T get() {
		return primitive.get();
	}

}
