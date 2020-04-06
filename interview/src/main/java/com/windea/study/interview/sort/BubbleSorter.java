package com.windea.study.interview.sort;

/**
 * 冒泡排序。
 * <p>
 * 遍历一次数组，如果相邻的两个元素逆序，则对它们进行交换，保证遍历中的最后一个元素有序。
 * 重复以上步骤直到数组全部有序为止。
 * 如果一次遍历过程中没有进行冒泡，则说明数组已经全部有序。
 * <p>
 * 时间复杂度：O(n^2)
 */
public class BubbleSorter implements Sorter {
    @Override
    public void sort(int[] array) {
        for(int i = 0; i < array.length - 1; i++) {
            boolean isSorted = true;
            for(int j = 0; j < array.length - i - 1; j++) {
                //进行冒泡
                if(array[j] > array[j + 1]) {
                    isSorted = false;
                    Sorter.swap(array, j, j + 1);
                }
            }
            //如果一轮冒泡中数组已经有序，则直接跳出循环
            if(isSorted) {
                break;
            }
        }
    }
}
