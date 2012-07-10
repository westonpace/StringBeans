package com.ptank.stringbeans.test;

import javax.swing.JFrame;
import javax.swing.JTree;

import org.junit.Test;

import com.ptank.stringbeans.builder.InMemoryLibrary;
import com.ptank.stringbeans.builder.Library;
import com.ptank.stringbeans.element.Action;
import com.ptank.stringbeans.element.primitive.PrimitiveAction;
import com.ptank.stringbeans.element.primitive.wrappers.PrimitiveActionElement;
import com.ptank.stringbeans.element.primitive.wrappers.PrimitiveNounElement;
import com.ptank.stringbeans.stdlib.basic.LiteralNoun;
import com.ptank.stringbeans.stdlib.io.actions.AndThen;
import com.ptank.stringbeans.stdlib.io.actions.PrintToConsole;
import com.ptank.stringbeans.ui.components.ClickableBeanString;
import com.ptank.stringbeans.ui.components.ElementChooser;
import com.ptank.stringbeans.ui.components.ElementChooser.ElementChosenEvent;
import com.ptank.stringbeans.ui.components.LanguageElementTreeModel;
import com.ptank.stringbeans.ui.core.Event.EventListener;

public class HelloWorldTest {

	private static PrimitiveAction createHelloWorldAction() {
		PrintToConsole helloAction = new PrintToConsole();
		PrintToConsole worldAction = new PrintToConsole();
		helloAction.setMessageToPrint(new PrimitiveNounElement<String>(new LiteralNoun<String>("Hello")));
		worldAction.setMessageToPrint(new PrimitiveNounElement<String>(new LiteralNoun<String>("World")));
		AndThen andThenAction = new AndThen();
		andThenAction.setThis(new PrimitiveActionElement(helloAction));
		andThenAction.setThat(new PrimitiveActionElement(worldAction));
		return andThenAction;
	}
	
	private static Library createSimpleLibrary() {
		InMemoryLibrary result = new InMemoryLibrary();
		result.addItem(AndThen.class);
		result.addItem(PrintToConsole.class);
		return result;
	}
	
	@Test
	public void testHelloWorldAction() {
		PrimitiveAction action = HelloWorldTest.createHelloWorldAction();
		action.doAction();
	}
	
	public static void main(String args[]) {
		PrimitiveAction action = createHelloWorldAction();
		PrimitiveActionElement actionElement = new PrimitiveActionElement(action);
		
		LanguageElementTreeModel treeModel = new LanguageElementTreeModel(actionElement);
		
		Library basicLibrary = createSimpleLibrary();
		
		JFrame mainFrame = new JFrame("Test");
		
		JTree tree = new JTree(treeModel);
		ClickableBeanString beanString = new ClickableBeanString(actionElement);
		ElementChooser elementChooser = new ElementChooser(basicLibrary, Action.class);
		
		elementChooser.elementChosenEvent.addListener(new EventListener<ElementChooser.ElementChosenEvent>() {

			@Override
			public void onEvent(ElementChosenEvent event) {
				System.out.println(event.getChosenElement() + " was chosen");
			}
			
		});
		
		//mainFrame.add(tree);
		//mainFrame.add(beanString);
		mainFrame.add(elementChooser);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
