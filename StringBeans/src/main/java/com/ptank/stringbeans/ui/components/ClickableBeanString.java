package com.ptank.stringbeans.ui.components;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ptank.stringbeans.element.BeanString;
import com.ptank.stringbeans.element.BeanString.BeanStringComponent;
import com.ptank.stringbeans.element.BeanString.ComponentType;
import com.ptank.stringbeans.element.primitive.LanguagePrimitive;
import com.ptank.stringbeans.reflection.LanguageElementReflector;
import com.ptank.stringbeans.ui.components.ClickableLabel.LabelClickedEvent;
import com.ptank.stringbeans.ui.core.Event.EventListener;

@SuppressWarnings("serial")
public class ClickableBeanString extends JPanel {

	private LanguagePrimitive model;
	private LanguageElementReflector modelReflector;
	
	public ClickableBeanString(LanguagePrimitive model) {
		setModel(model);
	}
	
	private void setModel(LanguagePrimitive model) {
		this.model = model;
		this.modelReflector = new LanguageElementReflector(model);
		updateChildren();
	}
	
	private void updateChildren() {
		removeAll();
		BeanString beanString = new BeanString(model.getBeanString());
		for(BeanStringComponent beanStringComponent : beanString) {
			if(beanStringComponent.getComponentType() == ComponentType.TEXT) {
				addTextItem(beanStringComponent.getValue());
			} else {
				addBeanItem(beanStringComponent.getValue());
			}
		}
		repaint();
		invalidate();
	}
	
	private void addTextItem(String text) {
		add(new JLabel(text));
	}
	
	private void addBeanItem(String beanName) {
		LanguagePrimitive langElement = modelReflector.getElementParameter(beanName).getValue();
		ClickableLabel label = null;
		if(langElement == null) {
			label = new ClickableLabel(beanName);
		} else {
			label = new ClickableLabel(langElement);
		}
		label.labelClickedEvent.addListener(new LabelClickHandler());
		add(label);
	}
	
	private class LabelClickHandler implements EventListener<LabelClickedEvent> {

		@Override
		public void onEvent(LabelClickedEvent event) {
			setModel(event.getElement());
		}
		
	}
	
}
