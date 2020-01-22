package com.windea.study.datastructure.sort;

//查找算法
//* 线性查找
//* 二分查找
//* 插值查找
//* 斐波那契查找

import java.util.*;

public class SearchAlgorithmDemo {
	public static void main(String[] args) {
		//int[] input = {1, 9, 11, -1, 34, 89};
		int[] input = {1, 9, 11, 11, 13, 15, 20};
		int value = 11;
		int index = fibonacciSearch(input, value);
		System.out.println(index);
		var indices = interpolationSearchAll(input, value);
		System.out.println(indices);
	}

	/**
	 * 线性查找算法（返回找到的第一个值的坐标或-1）。
	 */
	public static int linearSearch(int[] input, int value) {
		for(int i = 0; i < input.length; i++) {
			if(input[i] == value) {
				return i;
			}
		}
		return -1;
	}

	public static List<Integer> linearSearchAll(int[] input, int value) {
		List<Integer> indices = new ArrayList<>();
		for(int i = 0; i < input.length; i++) {
			if(input[i] == value) {
				indices.add(i);
			}
		}
		return indices;
	}

	/**
	 * 二分查找算法（返回找到的第一个值的坐标或-1）。
	 * <p>
	 * 仅适用于有序数组。
	 */
	public static int binarySearch(int[] input, int value) {
		return binarySearch0(input, value, 0, input.length - 1);
	}

	private static int binarySearch0(int[] input, int value, int left, int right) {
		//当指定值小于最左值，或大于最右值，说明即使递归完整个数组，也仍然找不到
		//当最左值等于最右值时，返回最左值的索引
		if(value < input[0] || value > input[input.length - 1]) {
			return -1;
		} else if(input[0] == input[input.length - 1]) {
			return 0;
		}

		int mid = (left + right) / 2;
		int midValue = input[mid];
		if(value < midValue) {
			//如果指定值小于中间值，则继续查找左边的值
			return binarySearch0(input, value, left, mid - 1);
		} else if(value > midValue) {
			//如果指定值大于中间值，则继续查找右边的数组
			return binarySearch0(input, value, mid + 1, right);
		} else {
			//如果指定值等于中间值，则说明已找到
			return mid;
		}
	}

	public static List<Integer> binarySearchAll(int[] input, int value) {
		List<Integer> indices = new ArrayList<>();
		binarySearchAll0(input, value, 0, input.length - 1, indices);
		return indices;
	}

	private static void binarySearchAll0(int[] input, int value, int left, int right, List<Integer> indices) {
		//当指定值小于最左值，或大于最右值，说明即使递归完整个数组，也仍然找不到
		//当最左值等于最右值时，返回最左值的索引
		if(value < input[0] || value > input[input.length - 1]) {
			return;
		} else if(input[0] == input[input.length - 1]) {
			indices.add(0);
			return;
		}

		int mid = (left + right) / 2;
		int midValue = input[mid];
		if(value < midValue) {
			//如果指定值小于中间值，则继续查找左边的值
			binarySearchAll0(input, value, left, mid, indices);
		} else if(value > midValue) {
			//如果指定值大于中间值，则继续查找右边的数组
			binarySearchAll0(input, value, mid + 1, right, indices);
		} else {
			//如果指定值等于中间值，则说明已找到
			indices.add(mid);
			addOtherIndices(input, value, indices, mid);
		}
	}

	/**
	 * 插值查找算法（返回找到的第一个值的坐标或-1）。
	 * <p>
	 * 仅适用于有序数组。使用计算得到的插值作为中间值。
	 * <p>
	 * 注意事项：
	 * * 适用于数据量较大，关键字分布比较均匀的情况。
	 * * 关键字分布不均匀的情况下，不一定比二分查找更好。
	 */
	public static int interpolationSearch(int[] input, int value) {
		return interpolationSearch0(input, value, 0, input.length - 1);
	}

	private static int interpolationSearch0(int[] input, int value, int left, int right) {
		//当指定值小于最左值，或大于最右值，说明即使递归完整个数组，也仍然找不到
		//当最左值等于最右值时，返回最左值的索引
		if(value < input[0] || value > input[input.length - 1]) {
			return -1;
		} else if(input[0] == input[input.length - 1]) {
			return 0;
		}

		//左索引+长度*插值（指定值和左值的差/右值和左值的差）
		int mid = left + (right - left) * (value - input[left]) / (input[right] - input[left]);
		int midValue = input[mid];
		if(value < midValue) {
			//如果指定值小于中间值，则继续查找左边的值
			return interpolationSearch0(input, value, left, mid - 1);
		} else if(value > midValue) {
			//如果指定值大于中间值，则继续查找右边的数组
			return interpolationSearch0(input, value, mid + 1, right);
		} else {
			//如果指定值等于中间值，则说明已找到
			return mid;
		}
	}

	public static List<Integer> interpolationSearchAll(int[] input, int value) {
		List<Integer> indices = new ArrayList<>();
		interpolationSearchAll0(input, value, 0, input.length - 1, indices);
		return indices;
	}

	private static void interpolationSearchAll0(int[] input, int value, int left, int right, List<Integer> indices) {
		//当指定值小于最左值，或大于最右值，说明即使递归完整个数组，也仍然找不到
		//当最左值等于最右值时，返回最左值的索引
		if(value < input[0] || value > input[input.length - 1]) {
			return;
		} else if(input[0] == input[input.length - 1]) {
			indices.add(0);
			return;
		}

		int mid = left + (right - left) * (value - input[left]) / (input[right] - input[left]);
		int midValue = input[mid];
		if(value < midValue) {
			//如果指定值小于中间值，则继续查找左边的值
			interpolationSearchAll0(input, value, left, mid, indices);
		} else if(value > midValue) {
			//如果指定值大于中间值，则继续查找右边的数组
			interpolationSearchAll0(input, value, mid + 1, right, indices);
		} else {
			//如果指定值等于中间值，则说明已找到
			indices.add(mid);
			//向左边扫描，将所有找到的元素的下标放入索引列表
			addOtherIndices(input, value, indices, mid);
		}
	}

	private static void addOtherIndices(int[] input, int value, List<Integer> indices, int mid) {
		//向左边扫描，将所有找到的元素的下标放入索引列表
		int tempLeft = mid - 1;
		while(tempLeft >= 0 && input[tempLeft] == value) {
			indices.add(tempLeft--);
		}
		//向右边扫描，将所有找到的元素的下标放入索引列表
		int tempRight = mid + 1;
		while(tempRight <= input.length - 1 && input[tempRight] == value) {
			indices.add(tempRight++);
		}
	}

	/**
	 * 斐波那契（黄金分割法）查找算法（返回找到的第一个值的坐标或-1）。
	 * <p>
	 * 仅适用于有序数组。使用黄金分割点为中间值（mid=low+F(k-1)-1）。
	 * <p>
	 * 注意事项：
	 * * 在某些情况下需要对原来的数组进行扩容。
	 */
	public static int fibonacciSearch(int[] input, int value) {
		int low = 0;
		int high = input.length - 1;
		int k = 0;// 表示斐波拉契分割点的索引
		int mid;
		int[] fib = fibonacci(input.length);

		//获取到斐波拉契分割点索引
		while(high > fib[k] - 1) {
			k++;
		}

		//因为array[k]的值可能大于指定数组长度，所以需要对数组进行扩容
		int[] tempInput = Arrays.copyOf(input, fib[k]);
		Arrays.fill(tempInput, input.length, tempInput.length, input[high]);

		while(low <= high) {
			mid = low + fib[k - 1] - 1;
			if(value < tempInput[mid]) {
				//继续向数组的左边查找
				high = mid - 1;
				//前面有fib[k-1]个元素
				k--;
			} else if(value > tempInput[mid]) {
				//继续向数组的右边查找
				low = mid + 1;
				//后面有fib[k-2]个元素
				k -= 2;
			} else {
				//返回mid和high中较小的坐标（因为数组可能存在扩容的情况）
				return Math.min(mid, high);
			}
		}
		return -1;
	}

	private static int[] fibonacci(int size) {
		int[] array = new int[size];
		array[0] = 1;
		array[1] = 1;
		for(int i = 2; i < size; i++) {
			array[i] = array[i - 1] + array[i - 2];
		}
		return array;
	}
}
