package com.ptank.stringbeans.element.primitive.wrappers;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.ptank.stringbeans.element.Action;
import com.ptank.stringbeans.element.io.PrimitiveElementXmlAdapter;
import com.ptank.stringbeans.element.primitive.PrimitiveAction;

@XmlRootElement
@XmlJavaTypeAdapter(PrimitiveElementXmlAdapter.class)
public class PrimitiveActionElement extends PrimitiveElement<PrimitiveAction> implements Action {
	
	public PrimitiveActionElement(PrimitiveAction primitive) {
		super(primitive);
	}

	@Override
	public void doAction() {
		primitive.doAction();
	}

}
