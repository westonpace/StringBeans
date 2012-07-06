package com.ptank.stringbeans.stdlib.io.actions;

import com.ptank.stringbeans.core.Action;
import com.ptank.stringbeans.core.Noun;

public class PrintToConsole implements Action {

	private Noun<String> messageToPrint;
	
	public void setMessageToPrint(Noun<String> messageToPrint) {
		this.messageToPrint = messageToPrint;
	}
	
	public Noun<String> getMessageToPrint() {
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
