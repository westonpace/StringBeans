package com.ptank.stringbeans.ui.components;

import java.awt.event.InputEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ptank.stringbeans.element.BeanString;
import com.ptank.stringbeans.element.BeanString.BeanStringComponent;
import com.ptank.stringbeans.element.BeanString.ComponentType;
import com.ptank.stringbeans.element.LanguageElement;
import com.ptank.stringbeans.ui.components.ClickableLabel.LabelClickedEvent;
import com.ptank.stringbeans.ui.core.Event.EventListener;

@SuppressWarnings("serial")
public class ClickableBeanString extends JPanel {

	private LanguageElement model;
	
	private ClickableLabel selectedLabel = null;
	
	public ClickableBeanString(LanguageElement model) {
		setModel(model);
	}
	
	private void setModel(LanguageElement model) {
		this.model = model;
		updateChildren();
	}
	
	private void updateChildren() {
		removeAll();
		BeanString beanString = model.getBeanString();
		for(BeanStringComponent beanStringComponent : beanString) {
			if(beanStringComponent.getComponentType() == ComponentType.TEXT) {
				addTextItem(beanStringComponent.getValue());
			} else {
				addBeanItem(beanStringComponent.getValue());
			}
		}
		revalidate();
		repaint();
	}
	
	private void addTextItem(String text) {
		add(new JLabel(text));
	}
	
	private void select(ClickableLabel newValue) {
		if(selectedLabel == newValue) { 
			return;
		}
		if(selectedLabel != null) {
			selectedLabel.setSelected(false);
		} 
		newValue.setSelected(true);
		selectedLabel = newValue;
	}
	
	private void addBeanItem(String beanName) {
		LanguageElement langElement = model.getParameters().get(beanName).getValue();
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
			if((event.getClickModifiers() & InputEvent.CTRL_DOWN_MASK) == InputEvent.CTRL_DOWN_MASK) {
				select(event.getSource());
			} else {
				setModel(event.getElement());
			}
		}
		
	}
	
}
