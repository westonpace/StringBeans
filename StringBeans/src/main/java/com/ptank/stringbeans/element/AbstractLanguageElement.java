package com.ptank.stringbeans.element;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.ptank.stringbeans.element.BeanString.BeanStringComponent;
import com.ptank.stringbeans.element.BeanString.ComponentType;

public class AbstractLanguageElement implements LanguageElement {

	private Map<String,Parameter<?>> parameters = new HashMap<String,Parameter<?>>();
	private BeanString beanString;
	
	protected void addParameter(Parameter<?> parameter) {
		parameters.put(parameter.getName(), parameter);
	}
	
	protected void addParameters(Collection<Parameter<?>> parameters) {
		for(Parameter<?> parameter : parameters) {
			addParameter(parameter);
		}
	}

	/* (non-Javadoc)
	 * @see com.ptank.stringbeans.element.LanguageElement#getBeanString()
	 */
	@Override
	public BeanString getBeanString() {
		return beanString;
	}

	public void setBeanString(BeanString beanString) {
		this.beanString = beanString;
	}
	
	/* (non-Javadoc)
	 * @see com.ptank.stringbeans.element.LanguageElement#getParameters()
	 */
	@Override
	public Map<String,Parameter<?>> getParameters() {
		return parameters;
	}
	
	public String getFullBeanString() {
		String result = "";
		for(BeanStringComponent component : beanString) {
			if(component.getComponentType() == ComponentType.TEXT) {
				result += component.getValue();
			} else {
				Parameter<?> componentParameter = parameters.get(component.getValue());
				result += componentParameter.getValue().getFullBeanString();
			}
		}
		return result;
	}

}
