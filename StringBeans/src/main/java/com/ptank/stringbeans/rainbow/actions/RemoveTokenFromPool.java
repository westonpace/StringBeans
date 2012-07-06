package com.ptank.stringbeans.rainbow.actions;

import com.ptank.stringbeans.core.Action;
import com.ptank.stringbeans.core.Noun;
import com.ptank.stringbeans.rainbow.core.BasicRainbowElement;
import com.ptank.stringbeans.rainbow.core.Color;

public class RemoveTokenFromPool extends BasicRainbowElement implements Action{

	private Noun<Color> color;
	private Noun<Integer> num;
	
	public Noun< Color> getColor() {
		return color;
	}

	public void setColor(Noun< Color> color) {
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
		return "remove {num} {color} tokens from pool";
	}

	@Override
	public void doAction() {
		for(int i = 0; i < num.get(); i++) {
			getModel().getTokenPool().removeToken(color.get());
		}
	}

}
