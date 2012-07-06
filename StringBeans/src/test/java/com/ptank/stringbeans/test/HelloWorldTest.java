package com.ptank.stringbeans.test;

import javax.swing.JFrame;
import javax.swing.JTree;

import org.junit.Test;

import com.ptank.stringbeans.core.Action;
import com.ptank.stringbeans.stdlib.basic.LiteralNoun;
import com.ptank.stringbeans.stdlib.io.actions.AndThen;
import com.ptank.stringbeans.stdlib.io.actions.PrintToConsole;
import com.ptank.stringbeans.ui.components.ClickableBeanString;
import com.ptank.stringbeans.ui.components.LanguageElementTreeModel;

public class HelloWorldTest {

	private static Action createHelloWorldAction() {
		PrintToConsole helloAction = new PrintToConsole();
		PrintToConsole worldAction = new PrintToConsole();
		helloAction.setMessageToPrint(new LiteralNoun<String>("Hello"));
		worldAction.setMessageToPrint(new LiteralNoun<String>("World"));
		AndThen andThenAction = new AndThen();
		andThenAction.setThis(helloAction);
		andThenAction.setThat(worldAction);
		return andThenAction;
	}
	
	@Test
	public void testHelloWorldAction() {
		Action action = HelloWorldTest.createHelloWorldAction();
		action.doAction();
	}
	
	public static void main(String args[]) {
		Action action = createHelloWorldAction();
		
		LanguageElementTreeModel treeModel = new LanguageElementTreeModel(action);
		
		JFrame mainFrame = new JFrame("Test");
		JTree tree = new JTree(treeModel);
		
		ClickableBeanString beanString = new ClickableBeanString(action);
		
		//mainFrame.add(tree);
		mainFrame.add(beanString);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
