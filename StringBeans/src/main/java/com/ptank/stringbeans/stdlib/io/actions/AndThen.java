package com.ptank.stringbeans.stdlib.io.actions;

import com.ptank.stringbeans.core.Action;

public class AndThen implements Action {

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
