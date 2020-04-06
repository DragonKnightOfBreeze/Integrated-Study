package com.windea.study.datastructure.recursion;

public class RecursionTest {
    public static void main(String[] args) {
        System.out.println(factorial(5));
    }

    //如果想写成尾递归的形式，需要考虑更多
    public static int factorial(int n) {
        if(n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
