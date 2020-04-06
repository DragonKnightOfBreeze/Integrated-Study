package com.windea.study.interview.equality;

@SuppressWarnings("ALL")
public class StringAndIntegerEqualityDemo {
    public static void main(String[] args) {
        System.out.println("1" == "1");
        System.out.println("1" == new String("1"));
        System.out.println("1" == String.valueOf(1)); //必定是实例化的

        System.out.println("------------");

        System.out.println(1 == 1);
        System.out.println(1 == new Integer(1));
        System.out.println(1 == Integer.valueOf(1)); //返回包装类型，使用常量池
        System.out.println(1 == Integer.parseInt("1")); //返回原始类型

        System.out.println(200 == 200);
        System.out.println(200 == new Integer(200));
        System.out.println(200 == Integer.valueOf(200)); //返回包装类型，使用常量池
        System.out.println(200 == Integer.parseInt("200")); //返回原始类型

        System.out.println((Integer) 1 == (Integer) 1);
        System.out.println((Integer) 200 == (Integer) 200);
    }
}
