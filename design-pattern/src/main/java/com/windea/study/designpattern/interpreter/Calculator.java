package com.windea.study.designpattern.interpreter;

import java.util.Map;
import java.util.Stack;

public class Calculator {
    private Expression expression;

    //TODO
    public Calculator(String expStr) {
        //使用栈安排运算的先后顺序
        Stack<Expression> stack = new Stack<>();
        //将表达式拆分成字符数组
        char[] charArray = expStr.toCharArray();

        Expression left;
        Expression right;
        //遍历字符数组，针对不同的情况，做相应处理
        for(int i = 0; i < charArray.length; i++) {
            switch(charArray[i]) {
                case '+':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[i + 1]));
                    stack.push(new AddExpression(left, right));
                    break;
                case '-':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[i + 1]));
                    stack.push(new SubExpression(left, right));
                    break;
                default:
                    stack.push(new VarExpression(String.valueOf(charArray[i])));
                    break;
            }
        }
        this.expression = stack.pop();
    }

    public int run(Map<String, Integer> var) {
        //传递expression的解释方法执行计算
        return this.expression.interpret(var);
    }
}
