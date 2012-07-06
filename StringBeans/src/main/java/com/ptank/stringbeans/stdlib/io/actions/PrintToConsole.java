package com.ptank.stringbeans.stdlib.io.actions;

import com.ptank.stringbeans.element.primitive.PrimitiveAction;
import com.ptank.stringbeans.element.primitive.PrimitiveNoun;

public class PrintToConsole implements PrimitiveAction {

	private PrimitiveNoun<String> messageToPrint;
	
	public void setMessageToPrint(PrimitiveNoun<String> messageToPrint) {
		this.messageToPrint = messageToPrint;
	}
	
	public PrimitiveNoun<String> getMessageToPrint() {
		return messageToPrint;
	}

	@Override
	public void doAction() {
		System.out.println(messageToPrint.get());
	}

	@Override
	public String getBeanString() {
		return "print {messageToPrint} to the console";
	}
	
}
