package com.ptank.stringbeans.stdlib.io.actions;

import com.ptank.stringbeans.element.primitive.PrimitiveAction;

public class AndThen implements PrimitiveAction {

	private PrimitiveAction thisAction;
	
	private PrimitiveAction that;

	public PrimitiveAction getThis() {
		return thisAction;
	}
	
	public void setThis(PrimitiveAction thisAction) {
		this.thisAction = thisAction;
	}

	public PrimitiveAction getThat() {
		return that;
	}

	public void setThat(PrimitiveAction that) {
		this.that = that;
	}

	@Override
	public String getBeanString() {
		return "{this} and then {that}";
	}

	@Override
	public void doAction() {
		thisAction.doAction();
		that.doAction();
	}
	
}
