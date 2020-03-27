package com.windea.study.interview.sort;

public interface Sorter {
	void sort(int[] array);

	static void swap(int[] array, int index1, int index2) {
		int temp = array[index2];
		array[index2] = array[index1];
		array[index1] = temp;
	}
}
