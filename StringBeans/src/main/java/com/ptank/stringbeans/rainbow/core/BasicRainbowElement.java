package com.ptank.stringbeans.rainbow.core;

import com.ptank.stringbeans.core.Model;

public class BasicRainbowElement {

	private RainbowModel model;

	public RainbowModel getModel() {
		return model;
	}

	@Model
	public void setModel(RainbowModel model) {
		this.model = model;
	}
	
}
