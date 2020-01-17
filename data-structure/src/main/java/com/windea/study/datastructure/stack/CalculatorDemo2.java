package com.windea.study.datastructure.stack;

//将中缀表达式转换为后缀表达式

//1. 初始化两个栈，符号栈s1和存储中间结果的栈s2
//2. 从左到右扫描后缀表达式
//3. 遇到操作数时，将其压入s2
//4. 遇到运算符时，比较其与s1栈顶运算符的优先级
//  4.1. 如果s1为空，或者栈顶运算符为左括号，则直接将此运算符压入s1
//  4.2. 如果优先级比栈顶运算符的高，也入栈
//  4.3. 否则，将s1栈顶的运算符弹出并压入到s2中，再次与s1中新的栈顶运算符比较
//5. 遇到括号时：
//  5.1. 如果是左括号，则直接压入s1
//  5.2. 如果是右括号，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止。并舍弃这对括号。
//6. 重复步骤2~5，直到表达式的最右边
//7. 将s1中剩余的运算符依次弹出并压入s2
//8. 依次弹出s2中的元素并输出，结果的逆序即为对应的后缀表达式。

import java.util.*;

public class CalculatorDemo2 {
	public static void main(String[] args) {
		//接受用户输入
		var scanner = new Scanner(System.in);
		while(true) {
			System.out.println("请输入表达式。输入'exit'退出程序。");
			var input = scanner.next();
			//当输入exit时，退出程序
			if(Objects.equals(input, "exit"))
				break;
			//否则视为中缀表达式，转换为后缀表达式
			var suffixExpression = convertToSuffixExpression(input);
			execute(suffixExpression);
		}
	}

	private static String[] getExpressions(String input) {
		return input.trim().replaceAll("([+\\-*/%^()])", " $1 ").split("\\s+");
	}

	public static String convertToSuffixExpression(String input) {
		var expressions = getExpressions(input);
		Deque<String> opStack = new ArrayDeque<>();
		//这里可以直接使用list替代
		Deque<String> tempStack = new ArrayDeque<>();
		for(String expression : expressions) {
			if(expression.matches("\\d+")) {
				//如果是一个操作数，则压入临时栈
				tempStack.push(expression);
			} else if("(".equals(expression)) {
				//如果是左括号，则压入符号栈
				opStack.push(expression);
			} else if(")".equals(expression)) {
				//如果是右括号，则依次弹出符号栈栈顶运算符，压入临时栈，直到遇到左括号为止
				//需要舍弃这对括号
				while(true) {
					var value = opStack.pop();
					if("(".equals(value))
						break;
					tempStack.push(value);
				}
			} else {
				//如果是一个运算符
				while(true) {
					//这个循环内不可能遇到左右括号
					if(opStack.isEmpty() || Objects.equals(opStack.peek(), "(")) {
						//如果符号栈为空，或者栈顶为左括号，则直接压入操作符栈
						opStack.push(expression);
						break;
					} else if(getPriority(expression) > getPriority(opStack.peek())) {
						//如果优先级比栈顶运算符的高，也入栈
						opStack.push(expression);
						break;
					} else {
						//否则将符号栈顶的运算符弹出并压入到临时栈中
						//再次与符号栈的新的栈顶运算符比较
						var value = opStack.pop();
						tempStack.push(value);
					}
				}
			}
		}
		//这里将临时栈中所有元素弹出并依次压入符号栈
		while(!tempStack.isEmpty()) {
			var value = tempStack.pop();
			opStack.push(value);
		}
		var stringBuilder = new StringBuilder();
		while(!opStack.isEmpty()) {
			stringBuilder.append(opStack.pop());
			stringBuilder.append(" ");
		}
		var suffixExpression = stringBuilder.toString().trim();
		System.out.println("对应的后缀表达式：" + suffixExpression);
		return suffixExpression;
	}

	public static void execute(String input) {
		var expressions = input.split(" ");
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

	private static int getPriority(String operator) {
		if("+".equals(operator) || "-".equals(operator)) {
			return 1;
		} else if("*".equals(operator) || "/".equals(operator) || "%".equals(operator)) {
			return 2;
		} else if("^".equals(operator)) {
			return 3;
		} else {
			throw new IllegalArgumentException("非法的运算符：" + operator);
		}
	}
}
