package com.ptank.stringbeans.stdlib.io.actions;

import com.ptank.stringbeans.element.Action;
import com.ptank.stringbeans.element.primitive.PrimitiveAction;

public class AndThen implements PrimitiveAction {

	private Action thisAction;
	
	private Action that;

	public Action getThis() {
		return thisAction;
	}
	
	public void setThis(Action thisAction) {
		this.thisAction = thisAction;
	}

	public Action getThat() {
		return that;
	}

	public void setThat(Action that) {
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
