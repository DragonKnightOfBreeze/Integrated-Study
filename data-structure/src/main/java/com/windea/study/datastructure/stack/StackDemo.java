package com.windea.study.datastructure.stack;

public class StackDemo {
    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack<>(5);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.forEach(System.out::println);
        System.out.println();
        System.out.println(stack.pop());
        System.out.println();
        stack.forEach(System.out::println);
        System.out.println();
        System.out.println(stack.peek());
        System.out.println();
        stack.forEach(System.out::println);
    }
}
