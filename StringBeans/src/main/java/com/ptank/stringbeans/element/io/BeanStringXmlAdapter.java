package com.ptank.stringbeans.element.io;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.ptank.stringbeans.element.BeanString;

public class BeanStringXmlAdapter extends XmlAdapter<String, BeanString> {

	@Override
	public String marshal(BeanString v) throws Exception {
		return v.toString();
	}

	@Override
	public BeanString unmarshal(String v) throws Exception {
		return new BeanString(v);
	}

}
