package com.ptank.stringbeans.element.primitive.wrappers;

import com.ptank.stringbeans.element.Action;
import com.ptank.stringbeans.element.primitive.PrimitiveAction;

public class PrimitiveActionElement extends PrimitiveElement<PrimitiveAction> implements Action {

	public PrimitiveActionElement(PrimitiveAction primitive) {
		super(primitive);
	}

	@Override
	public void doAction() {
		primitive.doAction();
	}

}
