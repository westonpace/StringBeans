package com.ptank.stringbeans.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import com.ptank.stringbeans.builder.Library;
import com.ptank.stringbeans.builder.PrimitivesLibrary.PrimitiveActionsLibrary;
import com.ptank.stringbeans.element.Action;
import com.ptank.stringbeans.element.primitive.PrimitiveAction;
import com.ptank.stringbeans.element.primitive.wrappers.PrimitiveActionElement;
import com.ptank.stringbeans.element.primitive.wrappers.PrimitiveElement;
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
	
	private static Library<?> createSimpleLibrary() {
		PrimitiveActionsLibrary result = new PrimitiveActionsLibrary();
		result.addPrimitiveClass(AndThen.class);
		result.addPrimitiveClass(PrintToConsole.class);
		return result;
	}
		
	@Test
	public void testHelloWorldAction() throws Exception {
		PrimitiveAction action = HelloWorldTest.createHelloWorldAction();
		action.doAction();
		PrimitiveActionElement helloWorldElement = new PrimitiveActionElement(action);
		JAXBContext context = JAXBContext.newInstance(PrimitiveElement.class,AndThen.class,PrintToConsole.class,LiteralNoun.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(helloWorldElement, System.out);
	}
	
	public static void main(String args[]) {
		PrimitiveAction action = createHelloWorldAction();
		PrimitiveActionElement actionElement = new PrimitiveActionElement(action);
		
		LanguageElementTreeModel treeModel = new LanguageElementTreeModel(actionElement);
		
		Library<?> basicLibrary = createSimpleLibrary();
		
		JFrame mainFrame = new JFrame("Test");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setPreferredSize(new Dimension(800,600));
		
		JTree tree = new JTree(treeModel);
		tree.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ClickableBeanString beanString = new ClickableBeanString(actionElement);
		beanString.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ClickableBeanString beanString2 = new ClickableBeanString(actionElement);
		beanString2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ElementChooser elementChooser = new ElementChooser(basicLibrary, Action.class);
		
		elementChooser.elementChosenEvent.addListener(new EventListener<ElementChooser.ElementChosenEvent>() {

			@Override
			public void onEvent(ElementChosenEvent event) {
				System.out.println(event.getChosenElement() + " was chosen");
			}
			
		});
		
		beanString.setPreferredSize(new Dimension(50,25));
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 2;
		c.weighty = 1;
		mainPanel.add(tree,c);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 1;
		mainPanel.add(beanString,c);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 1;
		mainPanel.add(beanString2,c);
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
