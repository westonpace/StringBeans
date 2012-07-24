package com.ptank.stringbeans.element;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ptank.stringbeans.element.BeanString.BeanStringComponent;
import com.ptank.stringbeans.element.BeanString.ComponentType;

@XmlRootElement
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
	@XmlTransient
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
	
	@SuppressWarnings("unchecked")
	public <T extends LanguageElement> void setParameter(String name, T newValue) {
		Parameter<T> parameter = (Parameter<T>) parameters.get(name);
		parameter.setValue(newValue);
	}
	
	public <T extends LanguageElement> void addParameter(String name, Class<T> parameterClass) {
		Parameter<T> parameter = new Parameter<T>(parameterClass);
		parameter.setName(name);
		parameters.put(name, parameter);
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
