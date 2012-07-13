package com.ptank.stringbeans.element.io;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.ptank.stringbeans.element.io.PrimitiveElementXmlAdapter.PrimitiveElementXmlRecord;
import com.ptank.stringbeans.element.primitive.wrappers.PrimitiveActionElement;

public class PrimitiveElementXmlAdapter extends XmlAdapter<PrimitiveElementXmlRecord, PrimitiveActionElement>{

	@XmlRootElement
	public static class PrimitiveElementXmlRecord {
		
		private Class<?> primitiveClass;

		public PrimitiveElementXmlRecord() {
			
		}
		
		public PrimitiveElementXmlRecord(Class<?> primitiveClass) {
			this.primitiveClass = primitiveClass;
		}
		
		public Class<?> getPrimitiveClass() {
			return primitiveClass;
		}

		public void setPrimitiveClass(Class<?> primitiveClass) {
			this.primitiveClass = primitiveClass;
		}
		
	}
	
	@Override
	public PrimitiveElementXmlRecord marshal(PrimitiveActionElement v) throws Exception {
		return new PrimitiveElementXmlRecord(v.getPrimitive().getClass());
	}

	@Override
	public PrimitiveActionElement unmarshal(PrimitiveElementXmlRecord v) throws Exception {
		return (PrimitiveActionElement) v.getPrimitiveClass().newInstance();
	}

}
