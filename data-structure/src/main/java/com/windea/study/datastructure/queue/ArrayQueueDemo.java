package com.windea.study.datastructure.queue;

//队列
//使用数组模拟队列

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        var queue = new ArrayQueue<Integer>(4);
        char key; //接受用户输入
        var scanner = new Scanner(System.in);
        //输出一个菜单
        while(true) {
            System.out.println("s(show) 显示队列");
            System.out.println("e(exit) 退出程序");
            System.out.println("a(add) 加入数据");
            System.out.println("g(get) 得到数据");
            System.out.println("p(peek) 显示数据");
            key = scanner.next().charAt(0);

            switch(key) {
                case 's':
                    queue.forEach(System.out::println);
                    break;
                case 'a':
                    try {
                        System.out.println("请输入值：");
                        var value = scanner.nextInt();
                        queue.add(value);
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        var value1 = queue.get();
                        System.out.println("得到的值：" + value1);
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        var value2 = queue.peek();
                        System.out.println("显示的值：" + value2);
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    System.out.println("程序退出。");
                    System.exit(0);
            }
        }
    }
}

