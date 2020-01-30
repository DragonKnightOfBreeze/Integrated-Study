package com.windea.study.interview;

/**
 * 有n步台阶，一次只能上1步或者2步，共有多少种写法
 */
public class Question1 {
	public static void main(String[] args) {
		System.out.println(count1(1));
		System.out.println(count1(2));
		System.out.println(count1(3));
		System.out.println(count1(4));
		System.out.println(count1(5));
	}

	private static int count1(int n) {
		if(n <= 0) {
			throw new IllegalArgumentException();
		} else if(n <= 2) {
			return n;
		} else {
			return count1(n - 1) + count1(n - 2);
		}
	}

	private static int count2(int n) {
		if(n <= 0) {
			throw new IllegalArgumentException();
		} else if(n <= 2) {
			return n;
		}
		int one = 2; //第一次走1步的走法 11 12
		int two = 1; //第一次走2步的走法 21
		int sum = 0; //总计的走法
		//从最后第n步到最后第3步
		for(int i = 3; i <= n; i++) {
			sum = two + one;
			two = one; //i+1后，第一次走2步等同于第一次走1步
			one = sum; //i+1后，第一次走1步等同于总计的走法
		}
		return sum;
	}
}
