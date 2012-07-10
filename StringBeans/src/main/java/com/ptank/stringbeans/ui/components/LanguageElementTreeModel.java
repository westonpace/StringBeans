package com.ptank.stringbeans.ui.components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.ptank.stringbeans.element.LanguageElement;
import com.ptank.stringbeans.element.Parameter;

public class LanguageElementTreeModel implements TreeModel {

	private LanguageElement model;
	private List<Parameter<?>> orderedParameters = new ArrayList<Parameter<?>>();
	
	private class ElementNode {
		
		private LanguageElement element;
		private String name;

		public ElementNode(LanguageElement element) {
			this.element = element;
		}
		
		public ElementNode(LanguageElement element, String name) {
			this.element = element;
			this.name = name;
		}
		
		public LanguageElement getElement() {
			return element;
		}

		public String toString() {
			if(name != null) {
				return name + ": " + element.getBeanString();
			} else {
				return element.getBeanString().toString();
			}
		}
	}
	
	public LanguageElementTreeModel(LanguageElement model) {
		this.model = model;
		orderedParameters = new ArrayList<Parameter<?>>(model.getParameters().values());
	}
	
	@Override
	public Object getRoot() {
		return new ElementNode(model);
	}

	@Override
	public Object getChild(Object parent, int index) {
		return new ElementNode(orderedParameters.get(index).getValue(), orderedParameters.get(index).getName());
	}

	@Override
	public int getChildCount(Object parent) {
		return orderedParameters.size();
	}

	@Override
	public boolean isLeaf(Object node) {
		return getChildCount(node) == 0;
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		throw new RuntimeException("Not supported");
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		int index = 0;
		for(Parameter<?> parameter : orderedParameters) {
			if(parameter.getValue().equals(child)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		return;
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		return;
	}

}
