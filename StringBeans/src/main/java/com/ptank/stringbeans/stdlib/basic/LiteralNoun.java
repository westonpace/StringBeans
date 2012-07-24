package com.ptank.stringbeans.stdlib.basic;

import javax.xml.bind.annotation.XmlElement;

import com.ptank.stringbeans.element.primitive.PrimitiveNoun;

public class LiteralNoun<T> implements PrimitiveNoun<T> {

	private T literalValue;
	
	LiteralNoun() {
		
	}
	
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
	
	@XmlElement
	T getLiteral() {
		return literalValue;
	}
	
	void setLiteral(T value) {
		this.literalValue = value;
	}

}
