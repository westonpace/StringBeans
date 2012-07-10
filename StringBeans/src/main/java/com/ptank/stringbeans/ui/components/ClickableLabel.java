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
	private LanguageElement element;
	
	public Event<LabelClickedEvent> labelClickedEvent = new Event<LabelClickedEvent>();
	
	public class LabelClickedEvent {
		
		private LanguageElement element;
		
		public LabelClickedEvent(LanguageElement element) {
			this.element = element;
		}
		
		public LanguageElement getElement() {
			return element;
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

	public LanguageElement getElement() {
		return element;
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		labelClickedEvent.fireEvent(new LabelClickedEvent(element));
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
