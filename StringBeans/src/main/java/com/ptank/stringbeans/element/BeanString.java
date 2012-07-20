package com.ptank.stringbeans.element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.ptank.stringbeans.element.BeanString.BeanStringComponent;
import com.ptank.stringbeans.element.io.BeanStringXmlAdapter;

@XmlJavaTypeAdapter(BeanStringXmlAdapter.class)
public class BeanString implements Iterable<BeanStringComponent> {

	public enum ComponentType {
		PLACEHOLDER,
		TEXT;
	}
	
	public class BeanStringComponent {
		
		private ComponentType componentType;
		private String value;
		
		public ComponentType getComponentType() {
			return componentType;
		}
		public void setComponentType(ComponentType componentType) {
			this.componentType = componentType;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public BeanStringComponent(ComponentType type, String value) {
			this.componentType = type;
			this.value = value;
		}
		
	}
	
	private String beanString;
	private List<BeanStringComponent> components;
	
	public BeanString(String beanString) {
		this.beanString = beanString;
	}

	private void initComponents() {
		components = new ArrayList<BeanStringComponent>();
		String [] leadingBits = beanString.split("\\{");
		if(!leadingBits[0].isEmpty()) {
			components.add(new BeanStringComponent(ComponentType.TEXT,leadingBits[0]));
		}
		for(int i = 1; i < leadingBits.length; i++) {
			String [] partition = leadingBits[i].split("\\}");
			if(!partition[0].isEmpty()) {
				components.add(new BeanStringComponent(ComponentType.PLACEHOLDER,partition[0]));
			}
			if(partition.length > 1 && !partition[1].isEmpty()) {
				components.add(new BeanStringComponent(ComponentType.TEXT,partition[1]));
			}
		}
	}
	
	public synchronized List<BeanStringComponent> getComponents() {
		if(components == null) {
			initComponents();
		}
		return components;
	}

	@Override
	public Iterator<BeanStringComponent> iterator() {
		return getComponents().iterator();
	}
	
	public String toString() {
		return beanString;
	}
	
}
