package com.windea.study.interview.sort;

public interface Sorter {
    static void swap(int[] array, int index1, int index2) {
        int temp = array[index2];
        array[index2] = array[index1];
        array[index1] = temp;
    }

    void sort(int[] array);
}
