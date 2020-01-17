package com.windea.study.datastructure.sort;

//排序算法
//* 内部排序（使用内存）
//  * 插入排序
//    * 直接插入排序
//    * 希尔排序
//  * 选择排序
//    * 简单选择排序
//    * 堆排序
//  * 交换排序
//    * 快速交换排序
//    * 冒泡排序
//  * 归并排序
//  * 基数排序（桶排序）
//* 外部排序（使用内存和外存结合）

//时间复杂度O(n) - 算法的执行时间
//时间频度T(n) - 算法中语句的执行次数

//随着时间频度的增大：常数项忽略→低阶项忽略→系数忽略
//一般情况下，若有f(n)，使得T(n)/f(n)的极限值为不等于0的常数，则称f(n)是T(n)的同数量级函数，记作T(n)=O(f(n))，称O(f(n))为算法的渐进时间复杂度，简称时间复杂度。

//计算时间复杂度：
//* 用1代替运算时间中的所有加法常数
//* 修改后的运算次数函数中，只保留最高阶项
//* 去除最高阶项的系数

//常见的时间复杂度：
//O(1) < O(log~2~n) < O(n) < O(nlog~2~n) <
//O(n^2) < O(n^3) < O(n^k) < O(2^n)

import java.util.Arrays;

public class SortAlgorithmDemo {
	public static void main(String[] args) {
		var input = new int[]{3, 9, 1, 10, 2, 8};
		System.out.println("排序前结果：" + Arrays.toString(input));
		directInsertSort(input);
		System.out.println("排序后结果：" + Arrays.toString(input));
	}

	/**
	 * 冒泡排序。
	 * <p>
	 * 时间复杂度：O(n^2)
	 * <p>
	 * 基本思路：
	 * 通过对待排序序列从前向后依次比较相邻元素的值，若发现逆序则交换，使值较大。
	 * 如果一趟比较下来没有进行过交换，就说明序列有序，因此在排序过程中需要设置一个flag判断元素是否进行过交换，从而减少不必要的排序。
	 */
	public static void bubbleSort(int[] input) {
		int temp;
		for(int i = 0; i < input.length - 1; i++) {
			boolean flag = true;
			//从左向右循环n-1次，循环中从左向右循环n-1-i次
			for(int j = 0; j < input.length - 1 - i; j++) {
				if(input[j] > input[j + 1]) {
					flag = false;
					temp = input[j];
					input[j] = input[j + 1];
					input[j + 1] = temp;
				}
			}
			//如果没有进行排序操作，则退出循环
			if(flag)
				break;
		}
	}

	/**
	 * 简单选择排序。
	 * <p>
	 * 时间复杂度：O(n^2) （实际比冒泡排序短）
	 * <p>
	 * 基本思路：
	 * 从待排序的数据中，按指定的规则选出某一元素，再按规定交换位置（每次开始选择的位置）后达到排序的目的。
	 */
	public static void simpleSelectSort(int[] input) {
		int temp;
		for(int i = 0; i < input.length - 1; i++) {
			//假定最小数为开始选择位置的数
			var minIndex = i;
			//循环比较确定最小数
			for(int j = i + 1; j < input.length; j++) {
				if(input[minIndex] > input[j]) {
					minIndex = j;
				}
			}
			//将最小数与开始选择位置的数交换位置
			if(minIndex != i) {
				temp = input[minIndex];
				input[minIndex] = input[i];
				input[i] = temp;
			}
		}
	}

	/**
	 * 直接插入排序。
	 * <p>
	 * 时间复杂度：O(n^2) （实际比冒泡排序短，比简单选择排序慢）
	 * <p>
	 * 基本思路：
	 * 把n个待排序元素看成一个有序表和一个无序表，开始时有序表中只包含一个元素，无序表中包含n-1个元素。排序过程中每次从无序表中取出一个元素，把它的排序码依次与有序表的排序码进行比较，插入到适当位置，成为新的有序表。
	 */
	public static void directInsertSort(int[] input) {
		int insertValue;
		int insertIndex;
		for(int i = 1; i < input.length; i++) {
			insertValue = input[i];
			insertIndex = i - 1;
			//直到可插入且还未插入为止
			while(insertIndex >= 0 && insertValue < input[insertIndex]) {
				//将待插入数前面的数后移
				input[insertIndex + 1] = input[insertIndex];
				insertIndex--;
			}
			//此时插入的位置已找到，insertIndex需要+1
			if(insertIndex + 1 != i) {
				input[insertIndex + 1] = insertValue;
			}
		}
	}
}
