package com.windea.study.designpattern.interpreter;

public abstract class OperatorExpression extends Expression {
    protected Expression left;
    protected Expression right;

    public OperatorExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
