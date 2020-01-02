package com.windea.study.designpattern.interpreter;

import java.util.Map;

public class AddExpression extends OperatorExpression {
	public AddExpression(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public int interpret(Map<String, Integer> var) {
		return left.interpret(var) + right.interpret(var);
	}
}
