package com.ptank.stringbeans.ui.components;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ptank.stringbeans.core.BeanString;
import com.ptank.stringbeans.core.BeanString.BeanStringComponent;
import com.ptank.stringbeans.core.BeanString.ComponentType;
import com.ptank.stringbeans.core.LanguageElement;
import com.ptank.stringbeans.reflection.LanguageElementReflector;
import com.ptank.stringbeans.ui.components.ClickableLabel.LabelClickedEvent;
import com.ptank.stringbeans.ui.core.Event.EventListener;

@SuppressWarnings("serial")
public class ClickableBeanString extends JPanel {

	private LanguageElement model;
	private LanguageElementReflector modelReflector;
	
	public ClickableBeanString(LanguageElement model) {
		setModel(model);
	}
	
	private void setModel(LanguageElement model) {
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
		LanguageElement langElement = modelReflector.getElementParameter(beanName).getValue();
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
