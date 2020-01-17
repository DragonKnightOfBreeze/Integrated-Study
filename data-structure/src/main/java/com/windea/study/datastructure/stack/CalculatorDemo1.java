package com.windea.study.datastructure.stack;

//逆波兰计算器
//输入一个后缀表达式，使用栈计算其结果
//支持小括号和多位数整数

import java.util.ArrayDeque;
import java.util.Deque;

public class CalculatorDemo1 {
	public static void main(String[] args) {
		//先定义逆波兰表达式
		//(3+4)*5-6 => 3 4 + 5 * 6 -
		var suffixExpression = "3 4 + 5 * 6 -";
		execute(suffixExpression);
	}

	public static void execute(String input) {
		var expressions = input.split("\\s+");

		//这里只需要一个栈即可
		Deque<String> stack = new ArrayDeque<>();
		for(String expression : expressions) {
			if(expression.matches("\\d+")) {
				//如果表达式是一个数，则直接入栈
				stack.push(expression);
			} else {
				//如果表达式是一个符号，则立即进行一次运算
				var number2 = Integer.parseInt(stack.pop());
				var number1 = Integer.parseInt(stack.pop());
				var result = calculate(number1, number2, expression);
				stack.push("" + result);
			}
		}
		//最后留在栈中的即是运算结果
		var finalResult = stack.pop();
		System.out.println("运算结果：" + finalResult);
	}

	private static int calculate(int number1, int number2, String operator) {
		switch(operator) {
			case "+":
				return number1 + number2;
			case "-":
				return number1 - number2;
			case "*":
				return number1 * number2;
			case "/":
				return number1 / number2;
			case "%":
				return number1 % number2;
			case "^":
				return (int) Math.pow(number1, number2);
			default:
				throw new IllegalArgumentException("非法的运算符。");
		}
	}
}
