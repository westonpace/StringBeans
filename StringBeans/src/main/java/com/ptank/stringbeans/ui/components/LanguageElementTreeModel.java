package com.ptank.stringbeans.ui.components;

import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.ptank.stringbeans.element.Parameter;
import com.ptank.stringbeans.element.primitive.LanguagePrimitive;
import com.ptank.stringbeans.reflection.LanguageElementReflector;

public class LanguageElementTreeModel implements TreeModel {

	private LanguagePrimitive model;
	
	private class ElementNode {
		
		private LanguagePrimitive element;
		private String name;

		public ElementNode(LanguagePrimitive element) {
			this.element = element;
		}
		
		public ElementNode(LanguagePrimitive element, String name) {
			this.element = element;
			this.name = name;
		}
		
		public LanguagePrimitive getElement() {
			return element;
		}

		public String toString() {
			if(name != null) {
				return name + ": " + element.getBeanString();
			} else {
				return element.getBeanString();
			}
		}
	}
	
	public LanguageElementTreeModel(LanguagePrimitive model) {
		this.model = model;
	}
	
	@Override
	public Object getRoot() {
		return new ElementNode(model);
	}

	@Override
	public Object getChild(Object parent, int index) {
		Parameter<?> childParameter = new LanguageElementReflector(((ElementNode)parent).getElement()).getElementParameters().get(index);
		return new ElementNode(childParameter.getValue(), childParameter.getName());
	}

	@Override
	public int getChildCount(Object parent) {
		return new LanguageElementReflector(((ElementNode)parent).getElement()).getElementParameters().size();
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
		List<Parameter<?>> parameters = new LanguageElementReflector(((ElementNode)parent).getElement()).getElementParameters();
		int index = 0;
		for(Parameter<?> parameter : parameters) {
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
