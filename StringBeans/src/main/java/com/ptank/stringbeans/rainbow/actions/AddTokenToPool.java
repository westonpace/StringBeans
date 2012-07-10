package com.ptank.stringbeans.rainbow.actions;

import com.ptank.stringbeans.element.Noun;
import com.ptank.stringbeans.element.primitive.PrimitiveAction;
import com.ptank.stringbeans.rainbow.core.BasicRainbowElement;
import com.ptank.stringbeans.rainbow.core.Color;

public class AddTokenToPool extends BasicRainbowElement implements PrimitiveAction {

	private Noun<Color> color;
	private Noun<Integer> num;

	public Noun<Color> getColor() {
		return color;
	}

	public void setColor(Noun<Color> color) {
		this.color = color;
	}

	public Noun<Integer> getNum() {
		return num;
	}

	public void setNum(Noun<Integer> num) {
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
