package com.windea.study.interview.sort;

/**
 * 快速排序。
 * <p>
 * 选择一个元素最为基数，从后往前找到第一个小于基数的元素，赋值给查找中的第一个元素。
 * 从前往后找到第一个大于等于基数的元素，赋值给查找中的最后一个元素。
 * 重复以上步骤，直到查找中只有一个元素，然后将基数赋值给那个元素。
 * 接着对两个子区间递归重复以上所有步骤。
 * <p>
 * 时间复杂度：O(n*log n)
 */
public class QuickSorter implements Sorter {
	@Override
	public void sort(int[] array) {
		sort0(array, 0, array.length);
	}

	private void sort0(int[] a, int l, int r) {
		if(l < r) {
			int i = l;
			int j = r;
			//如果x需要取中轴值，则应x = a[(l + r) / 2]
			int x = a[l];
			while(i < j) {
				//从右向左找第一个小于x的数，填到前一个坑a[i]中
				while(i < j && a[j] >= x) {
					j--;
				}
				if(i < j) {
					a[i++] = a[j];
				}
				//从左向右找第一个大于等于x的数，填到前一个坑a[j]中
				while(i < j && a[i] < x) {
					i++;
				}
				if(i < j) {
					a[j--] = a[i];
				}
			}
			//重复以上步骤，直到i等于j，然后将基准数填入a[i]中
			a[i] = x;
			//对两个子区间重复以上步骤
			sort0(a, l, i - 1);
			sort0(a, i + 1, r);
		}
	}
}
