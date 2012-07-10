package com.ptank.stringbeans.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ptank.stringbeans.element.Action;
import com.ptank.stringbeans.element.Expression;
import com.ptank.stringbeans.element.LanguageElement;
import com.ptank.stringbeans.element.Noun;
import com.ptank.stringbeans.element.primitive.LanguagePrimitive;
import com.ptank.stringbeans.element.primitive.PrimitiveAction;
import com.ptank.stringbeans.element.primitive.PrimitiveExpression;
import com.ptank.stringbeans.element.primitive.PrimitiveNoun;
import com.ptank.stringbeans.element.primitive.wrappers.PrimitiveActionElement;
import com.ptank.stringbeans.element.primitive.wrappers.PrimitiveExpressionElement;
import com.ptank.stringbeans.element.primitive.wrappers.PrimitiveNounElement;

public abstract class PrimitivesLibrary<P extends LanguagePrimitive,T extends LanguageElement> implements Library<T> {

	private Map<String,Class<? extends P>> primitiveClasses = new HashMap<String,Class<? extends P>>();
	
	@Override
	public List<String> getItemNames() {
		ArrayList<String> result = new ArrayList<String>();
		for(Class<? extends P> primitiveClass : primitiveClasses.values()) {
			result.add(primitiveClass.getSimpleName());
		}
		return result;
	}

	@Override
	public T buildItem(String name) {
		Class<? extends P> primitiveClass = primitiveClasses.get(name);
		if(primitiveClass != null) {
			try {
				return wrapPrimitive(primitiveClass.newInstance());
			} catch (IllegalAccessException ex) {
				throw new RuntimeException(ex);
			} catch (InstantiationException ex) {
				throw new RuntimeException(ex);
			}
		}
		return null;
	}

	public void addPrimitiveClass(Class<? extends P> primitiveClass) {
		primitiveClasses.put(primitiveClass.getSimpleName(), primitiveClass);
	}
	
	protected abstract T wrapPrimitive(P primitive);
	
	public static class PrimitiveActionsLibrary extends PrimitivesLibrary<PrimitiveAction, Action> {

		@Override
		protected Action wrapPrimitive(PrimitiveAction primitive) {
			return new PrimitiveActionElement(primitive);
		}
		
	}
	
	public static class PrimitiveNounsLibrary extends PrimitivesLibrary<PrimitiveNoun<?>,Noun<?>> {

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		protected Noun<?> wrapPrimitive(PrimitiveNoun<?> primitive) {
			return new PrimitiveNounElement(primitive);
		}
		
	}
	
	public static class PrimitiveExpressionsLibrary extends PrimitivesLibrary<PrimitiveExpression,Expression> {

		@Override
		protected Expression wrapPrimitive(PrimitiveExpression primitive) {
			return new PrimitiveExpressionElement(primitive);
		}
		
	}
}
