package com.windea.study.datastructure.sort;

//排序算法
//* 内部排序（使用内存）
//  * 插入排序
//    * 直接插入排序
//    * 希尔排序（对直接插入排序的改进）
//  * 选择排序
//    * 简单选择排序
//    * 堆排序
//  * 交换排序
//    * 快速交换排序（对冒泡排序的改进）
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

//稳定性：
//可以理解为排序后相同数值位置不变

import java.util.Arrays;

public class SortAlgorithmDemo {
	public static void main(String[] args) {
		var input = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
		System.out.println("排序前结果：" + Arrays.toString(input));
		mergeSort(input);
		System.out.println("排序后结果：" + Arrays.toString(input));
	}

	/**
	 * 冒泡排序。
	 * <p>
	 * 时间复杂度：O(n^2)
	 * <p>
	 * 基本思路：
	 * 通过对待排序序列从前向后依次比较相邻元素的值，若发现逆序则交换，使值较大。
	 * 如果一趟比较下来没有进行过交换，就说明序列有序，
	 * 因此在排序过程中需要设置一个flag判断元素是否进行过交换，从而减少不必要的排序。
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
	 * 快速交换排序。
	 * <p>
	 * 时间复杂度：O(n log~2~n)（性能很高）
	 * <p>
	 * 基本思路：
	 * 通过一趟排序将要排序的数据分割成独立的两部分，
	 * 其中一部分的所有数据都比另一部分的所有数据要小，
	 * 然后再按此方法对这两部分数据分别进行快速排序，
	 * 整个排序过程可以递归进行，以此达到整个数据变成排序。
	 */
	public static void quickSort(int[] input) {
		quickSort0(input, 0, input.length - 1);
	}

	private static void quickSort0(int[] input, int left, int right) {
		//动态左索引
		int l = left;
		//动态右索引
		int r = right;
		//中轴值
		int pivot = input[(l + r) / 2];
		//用于交换
		int temp;
		//while循环的目的是让比pivot值小的放在左边，大的放在右边
		while(l < r) {
			//在pivot的左边找到一个大于或等于pivot的值才退出
			while(input[l] < pivot) {
				l++;
			}
			//在pivot的右边找到一个小于或等于pivot的值才退出
			while(input[r] > pivot) {
				r--;
			}
			//左索引和右索引已接触
			//说明pivot左边已经全是较小值，右边已经全是较大值
			if(l >= r) {
				break;
			}
			//交换
			temp = input[l];
			input[l] = input[r];
			input[r] = temp;
			//如果交换完成之后，发现左边的值等于中轴值，则直接让右索引-1
			//因为没有必要进行下一次处理
			if(input[l] == pivot) {
				r--;
			}
			if(input[r] == pivot) {
				l++;
			}
		}
		//如果l==r，必须l++,r--，否则会栈溢出
		if(l == r) {
			l++;
			r--;
		}
		//向左递归
		if(left < r) {
			quickSort0(input, left, r);
		}
		//向右递归
		if(right > l) {
			quickSort0(input, l, right);
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
			//循环比较确定最小数（的索引）
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
	 * 把n个待排序元素看成一个有序表和一个无序表，
	 * 开始时有序表中只包含一个元素，无序表中包含n-1个元素。
	 * 排序过程中每次从无序表中取出一个元素，
	 * 把它的排序码依次与有序表的排序码进行比较，插入到适当位置，成为新的有序表。
	 */
	public static void directInsertSort(int[] input) {
		//当需要插入的数是较小的数时，后移的次数明显增多，对效率有影响
		int insertValue;
		int insertIndex;
		for(int i = 1; i < input.length; i++) {
			insertValue = input[i];
			insertIndex = i - 1;
			//如果前面的数不小于要插入的数，则继续循环（因为前面的都是已经排序好的）
			if(input[insertIndex] > insertValue) {
				//直到可插入且已正确插入为止
				while(insertIndex >= 0 && input[insertIndex] > insertValue) {
					//将待插入数前面的数后移
					input[insertIndex + 1] = input[insertIndex];
					insertIndex--;
				}
				//此时插入的位置已找到，insertIndex需要+1，达到修正效果，然后将值插入到这个位置
				input[insertIndex + 1] = insertValue;
			}
		}
	}

	/**
	 * 希尔排序（缩小增量排序）（交换法）。
	 * <p>
	 * 性能没有明显的提高。
	 * <p>
	 * 基本思路：
	 * 把记录按下标的一定增量分组，对每组使用直接插入排序。
	 * 随着增量逐渐减少，每组包含的关键词越来越多。当增量减至1时，整个文件恰被分成一组，算法在此次之后便终止。
	 */
	@Deprecated
	public static void shellSort1(int[] input) {
		int temp;
		//将步长初始化为数组长度的一半，每次折半，直到为0为止
		for(int gap = input.length / 2; gap > 0; gap /= 2) {
			//将数据按照步长折半分组
			for(int i = gap; i < input.length; i++) {
				for(int j = i - gap; j >= 0; j -= gap) {
					//如果当前元素大于加上步长后的那个元素，则交换
					if(input[j] > input[j + gap]) {
						temp = input[j];
						input[j] = input[j + gap];
						input[j + gap] = temp;
					}
				}
			}
		}
	}

	/**
	 * 希尔排序（缩小增量排序）（插入法）。
	 * <p>
	 * 时间复杂度：O(n log~2~n) （性能有明显的提高）
	 * <p>
	 * 基本思路：
	 * 把记录按下标的一定增量分组，对每组使用直接插入排序。
	 * 随着增量逐渐减少，每组包含的关键词越来越多。当增量减至1时，整个文件恰被分成一组，算法在此次之后便终止。
	 */
	public static void shellSort(int[] input) {
		//仅对于每组的第一个元素不需要做任何处理，当步长变小后，以后可能需要处理不止两个元素的组
		//将步长初始化为数组长度的一半，每次折半，直到0为止
		for(int gap = input.length / 2; gap > 0; gap /= 2) {
			//从第步长个元素开始，逐个对其所在的组进行直接插入排序
			for(int i = gap; i < input.length; i++) {
				int temp = input[i];
				int j = i;
				//如果同一组中前面的不小于后面的，则继续循环（因为前面的都是已经排序好的）
				if(input[i - gap] > input[i]) {
					//遍历各组中的所有元素，步长为折半数
					while(j - gap >= 0 && input[j - gap] > temp) {
						//将该组中前面的元素移到后面的元素
						input[j] = input[j - gap];
						j -= gap;
					}
					//当退出循环后，就在合适的位置插入值
					input[j] = temp;
				}
			}
		}
	}

	/**
	 * 归并排序。
	 * <p>
	 * 时间复杂度：O(n log~2~n) （性能很高）
	 * <p>
	 * 基本思路：
	 * 利用归并思想实现的排序方法。该算法采用经典的分治策略。
	 * 分治法将问题分成一些小问题然后递归求解，而治的阶段将分的阶段得到的各答案修补到一起。
	 * 即分而治之。
	 */
	public static void mergeSort(int[] input) {
		//排序过程中的结构很像一课完全二叉树
		mergeSort0(input, 0, input.length - 1, new int[input.length]);
	}

	private static void mergeSort0(int[] input, int left, int right, int[] temp) {
		//保证当子数组长度大于2时才会继续递归
		if(left < right) {
			int mid = (left + right) / 2;
			//向左递归进行分解
			mergeSort0(input, left, mid, temp);
			//向右递归进行分解
			mergeSort0(input, mid + 1, right, temp);
			//合并
			merge(input, left, mid, right, temp);
		}
	}

	private static void merge(int[] input, int left, int mid, int right, int[] temp) {
		//左边有序序列的初始索引
		int i = left;
		//右边有序序列的初始索引
		int j = mid + 1;
		//临时数组的当前索引
		int t = 0;

		//先把左右两边（有序）的数据按规则填充到临时数组
		//直到有一边处理完毕为止
		while(i <= mid && j <= right) {
			//如果左边有序序列的当前元素小于等于右边有序序列的当前元素，
			//则将左边的当前元素填充到临时数组，然后右移对应索引
			if(input[i] <= input[j]) {
				temp[t] = input[i];
				i++;
			} else {
				temp[t] = input[j];
				j++;
			}
			t++;
		}

		//把有剩余数据的一边的数据依次全部填充到临时数组
		while(i <= mid) {
			temp[t] = input[i];
			i++;
			t++;
		}
		while(j <= right) {
			temp[t] = input[j];
			j++;
			t++;
		}
		//将临时数组的元素拷贝到input
		//注意：不是每次都拷贝索引的
		t = 0;
		while(left <= right) {
			//第一次合并时，temp=0，right=1
			input[left] = temp[t];
			left++;
			t++;
		}
	}

	/**
	 * 堆排序。
	 * <p>
	 * 时间复杂度：O(n log~2~n)
	 */
	public static void heapSort(int[] input) {

	}

	/**
	 * 计数排序。
	 * <p>
	 * 时间复杂度：O(n + k)
	 */
	public static void countingSort(int[] input) {

	}

	/**
	 * 桶排序。
	 * <p>
	 * 时间复杂度：O(n^2)
	 */
	public static void bucketSort(int[] input) {

	}

	/**
	 * 基数排序。（桶排序的扩展）
	 * <p>
	 * 时间复杂度：O(n*k)
	 * <p>
	 * 基本思路：
	 * 通过键值的各个位的值，将要排序的元素分配至某些桶中，达到排序的作用。
	 * 属于稳定性的排序，是效率高的稳定性排序法。
	 * 将所有待比较数值统一为同样的数位长度，数位较短的数前面补零，
	 * 然后从最低位开始，依次进行一次排序。
	 * 这样从最低位排序一直到最高位排序完成后，数列就变成一个有序序列。
	 */
	public static void radixSort(int[] input) {

	}
}

