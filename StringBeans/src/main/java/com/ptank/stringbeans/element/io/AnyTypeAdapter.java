package com.ptank.stringbeans.element.io;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AnyTypeAdapter extends XmlAdapter<Object, Object>{

	@Override
	public Object marshal(Object obj) throws Exception {
		return obj;
	}

	@Override
	public Object unmarshal(Object obj) throws Exception {
		return obj;
	}

}
