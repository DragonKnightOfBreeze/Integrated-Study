package com.windea.study.designpattern.interpreter;

import java.util.Map;

public class VarExpression extends Expression {
    private String key;

    public VarExpression(String key) {
        this.key = key;
    }

    @Override
    public int interpret(Map<String, Integer> var) {
        return var.get(key);
    }
}
