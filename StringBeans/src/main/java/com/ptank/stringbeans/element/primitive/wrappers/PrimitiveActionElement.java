package com.ptank.stringbeans.element.primitive.wrappers;

import javax.xml.bind.annotation.XmlRootElement;

import com.ptank.stringbeans.element.Action;
import com.ptank.stringbeans.element.primitive.PrimitiveAction;

@XmlRootElement
public class PrimitiveActionElement extends PrimitiveElement<PrimitiveAction> implements Action {
	
	public PrimitiveActionElement() {
		super();
	}
	
	public PrimitiveActionElement(PrimitiveAction primitive) {
		super(primitive);
	}

	@Override
	public void doAction() {
		primitive.doAction();
	}

}
