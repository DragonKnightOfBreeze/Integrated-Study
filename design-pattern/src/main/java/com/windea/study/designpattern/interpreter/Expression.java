package com.windea.study.designpattern.interpreter;

import java.util.Map;

public abstract class Expression {
	public abstract int interpret(Map<String, Integer> var);
}
