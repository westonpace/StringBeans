package com.ptank.stringbeans.rainbow.actions;

import com.ptank.stringbeans.element.primitive.PrimitiveAction;
import com.ptank.stringbeans.element.primitive.PrimitiveNoun;
import com.ptank.stringbeans.rainbow.core.BasicRainbowElement;
import com.ptank.stringbeans.rainbow.core.Color;

public class AddTokenToPool extends BasicRainbowElement implements PrimitiveAction {

	private PrimitiveNoun<Color> color;
	private PrimitiveNoun<Integer> num;

	public PrimitiveNoun<Color> getColor() {
		return color;
	}

	public void setColor(PrimitiveNoun<Color> color) {
		this.color = color;
	}

	public PrimitiveNoun<Integer> getNum() {
		return num;
	}

	public void setNum(PrimitiveNoun<Integer> num) {
		this.num = num;
	}

	@Override
	public String getBeanString() {
		return "add {num} {color} tokens to the pool";
	}

	@Override
	public void doAction() {
		for(int i = 0; i < num.get(); i++) {
			getModel().getTokenPool().addToken(color.get());
		}
	}

}
