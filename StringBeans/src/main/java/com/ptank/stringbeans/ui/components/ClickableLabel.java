package com.ptank.stringbeans.ui.components;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import com.ptank.stringbeans.element.LanguageElement;
import com.ptank.stringbeans.ui.core.Event;

@SuppressWarnings("serial")
public class ClickableLabel extends JLabel implements MouseListener {

	private static final Color greenish = new Color(179,238,58);
	private static final Color blueish = new Color(192,204,254);
	private LanguageElement element;
	private boolean selected = false;
	
	public Event<LabelClickedEvent> labelClickedEvent = new Event<LabelClickedEvent>();
	
	public class LabelClickedEvent {
		
		private ClickableLabel source;
		private LanguageElement element;
		private int clickModifiers;
		
		public LabelClickedEvent(ClickableLabel source, LanguageElement element, int clickModifiers) {
			this.source = source;
			this.element = element;
			this.clickModifiers = clickModifiers;
		}
		
		public LanguageElement getElement() {
			return element;
		}
		
		public int getClickModifiers() {
			return clickModifiers;
		}
		
		public ClickableLabel getSource() {
			return source;
		}
	}
	
	public ClickableLabel(LanguageElement element) {
		super();
		this.element = element;
		setText(element.getFullBeanString());
		init();
	}

	public ClickableLabel(String beanName) {
		super();
		setText("{" + beanName + "}");
		init();
	}
	
	public void init() {
		this.addMouseListener(this);
		setOpaque(true);
		setBackground(greenish);
	}

	//TODO - Tie into focus?
	public void setSelected(boolean newValue) {
		if(newValue == this.selected) {
			return;
		}
		this.selected = newValue;
		if(selected) {
			setBackground(blueish);
			repaint();
		} else {
			setBackground(greenish);
			repaint();
		}
	}
	
	public boolean getSelected() {
		return selected;
	}
	
	public LanguageElement getElement() {
		return element;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		labelClickedEvent.fireEvent(new LabelClickedEvent(this,element,e.getModifiersEx()));
	}



	@Override
	public void mousePressed(MouseEvent e) {
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
