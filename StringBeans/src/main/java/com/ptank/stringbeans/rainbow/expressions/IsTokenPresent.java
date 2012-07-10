package com.ptank.stringbeans.rainbow.expressions;

import com.ptank.stringbeans.element.Noun;
import com.ptank.stringbeans.element.primitive.PrimitiveExpression;
import com.ptank.stringbeans.rainbow.core.BasicRainbowElement;
import com.ptank.stringbeans.rainbow.core.Color;

public class IsTokenPresent extends BasicRainbowElement implements PrimitiveExpression {

	private Noun<Color> color;
	
	public Noun<Color> getColor() {
		return color;
	}

	public void setColor(Noun<Color> color) {
		this.color = color;
	}

	@Override
	public String getBeanString() {
		return "{color} token is present";
	}

	@Override
	public boolean evaluate() {
		return getModel().getTokenPool().isTokenPresent(color.get());
	}
	
}
