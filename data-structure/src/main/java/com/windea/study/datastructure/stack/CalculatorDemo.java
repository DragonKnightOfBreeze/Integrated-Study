package com.windea.study.datastructure.stack;

//使用栈完成表达式的计算思路
//通过一个索引遍历我们的表达式
//如果发现是一个数字，则直接入数栈
//如果发现是一个符号：
//  如果当前符号栈为空，则直接入栈。
//  如果当前符号栈不为空，则比较优先级，
//  如果当前符号优先级较小或等于，则需要立即进行一次计算，
//  取出数栈中两个数和符号栈中一个符号进行计算，
//  将结果入数栈，然后将当前符号入符号栈。
//  如果当前符号优先级较大，则直接入栈。
//当表达式扫描完毕后，按顺序从数栈和符号栈中取值进行运算
//最后数栈只有一个数字，即为表达式的结果

//确保栈顶的符号较下面的符号优先级较高

import java.util.*;

public class CalculatorDemo {
	public static void main(String[] args) {
		//接受用户输入
		var scanner = new Scanner(System.in);
		while(true) {
			System.out.println("请输入表达式。输入'exit'退出程序。");
			var input = scanner.next();
			//当输入exit时，退出程序
			if(Objects.equals(input, "exit"))
				break;
			//否则视为表达式，进行计算
			execute(input);
		}
	}

	//允许多位运算数
	//允许任意数量的合法空格
	//不兼容小数
	//不兼容括号

	private static final Set<String> operators1 = Set.of("+", "-");
	private static final Set<String> operators2 = Set.of("*", "/", "%");
	private static final Set<String> operators3 = Set.of("^");

	public static void execute(String input) {
		//去除首位空白后按照数字与非数字边界分割字符串，同时去除空白
		//不能一次性在边界插入空格
		var expressions = getExpressions(input);
		Stack<Integer> numberStack = new ArrayStack<>(30);
		Stack<String> operatorStack = new ArrayStack<>(30);
		var isNumber = true;
		for(var expression : expressions) {
			if(isNumber) {
				//如果发现是一个数字，则直接入数栈
				var number = Integer.parseInt(expression);
				numberStack.push(number);
			} else {
				//如果发现是一个符号，则按情况处理
				//如果当前符号栈为空，则直接入栈
				//如果当前符号栈不为空，则比较优先级
				if(!operatorStack.isEmpty()) {
					var priority = getPriority(expression);
					var lastOperator = operatorStack.peek();
					var lastPriority = getPriority(lastOperator);
					//如果当前符号优先级较大，则直接入栈
					//如果当前符号优先级较小或等于，则需要立即进行一次计算，然后将当前符号入符号栈
					if(priority <= lastPriority) {
						//注意下面的才是第一个运算数
						pushResult(numberStack, operatorStack);
					}
				}
				operatorStack.push(expression);
			}
			isNumber = !isNumber;
		}
		//当表达式扫描完毕后，按顺序从数栈和符号栈中取值进行运算
		while(!operatorStack.isEmpty()) {
			pushResult(numberStack, operatorStack);
		}
		//最后数栈只有一个数字，即为表达式的结果
		var finalResult = numberStack.pop();
		System.out.println("计算结果：" + finalResult);
	}

	private static String[] getExpressions(String input) {
		return input.trim().replaceAll("([+\\-*/%^()])", " $1 ").split("\\s+");
	}

	private static void pushResult(Stack<Integer> numberStack, Stack<String> operatorStack) {
		var number2 = numberStack.pop();
		var number1 = numberStack.pop();
		var operator = operatorStack.pop();
		var result = calculate(number1, number2, operator);
		numberStack.push(result);
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

	private static int getPriority(String operator) {
		if(operators1.contains(operator)) {
			return 1;
		} else if(operators2.contains(operator)) {
			return 2;
		} else if(operators3.contains(operator)) {
			return 3;
		} else {
			throw new IllegalArgumentException("非法的运算符。");
		}
	}
}
