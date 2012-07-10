package com.ptank.stringbeans.element.primitive.wrappers;

import com.ptank.stringbeans.element.Expression;
import com.ptank.stringbeans.element.primitive.PrimitiveExpression;

public class PrimitiveExpressionElement extends PrimitiveElement<PrimitiveExpression> implements Expression {

	public PrimitiveExpressionElement(PrimitiveExpression primitive) {
		super(primitive);
	}

	@Override
	public boolean evaluate() {
		return primitive.evaluate();
	}

}
