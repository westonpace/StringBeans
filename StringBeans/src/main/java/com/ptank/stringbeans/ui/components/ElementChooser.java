package com.ptank.stringbeans.ui.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import com.ptank.stringbeans.builder.Library;
import com.ptank.stringbeans.element.Action;
import com.ptank.stringbeans.element.LanguageElement;
import com.ptank.stringbeans.element.primitive.PrimitiveAction;
import com.ptank.stringbeans.ui.core.Event;

public class ElementChooser extends JList implements MouseListener {

	public Event<ElementChosenEvent> elementChosenEvent = new Event<ElementChosenEvent>();
	
	private Library<?> elementLibrary;
	public void setElementLibrary(Library<?> elementLibrary) {
		this.elementLibrary = elementLibrary;
		updateList();
	}
	
	private Class<? extends LanguageElement> elementType;
	public void setElementType(Class<? extends LanguageElement> elementType) {
		this.elementType = elementType;
		updateList();
	}
	
	private DefaultListModel listModel = new DefaultListModel();
	
	public class ElementChosenEvent {
		private String chosenElement;
		
		public ElementChosenEvent(String chosenElement) {
			this.chosenElement = chosenElement;
		}
		
		public String getChosenElement() {
			return chosenElement;
		}
	}
	
	public ElementChooser() {
		super();
		setModel(listModel);
		addMouseListener(this);
	}
	
	public ElementChooser(Library elementLibrary, Class<? extends LanguageElement> elementType) {
		super();
		setModel(listModel);
		this.elementLibrary = elementLibrary;
		this.elementType = elementType;
		addMouseListener(this);
		updateList();
	}
	
	private void updateList() {
		listModel.clear();
		if(elementLibrary != null && elementType != null) {
			for(String elementName : elementLibrary.getItemNames()) {
				listModel.addElement(elementName);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String selectedElementName = (String) getSelectedValue();
		if(selectedElementName != null) {
			elementChosenEvent.fireEvent(new ElementChosenEvent(selectedElementName));
		}
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
