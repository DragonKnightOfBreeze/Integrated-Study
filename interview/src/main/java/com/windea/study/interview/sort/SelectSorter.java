package com.windea.study.interview.sort;

/**
 * 选择排序。
 * <p>
 * 遍历一次数组，找到其中最小的元素，与遍历中的第一个元素进行交换，保证遍历中的第一个元素有序。
 * 重复以上步骤直到数组全部有序为止。
 * 查找最小的元素时只需找到索引即可。
 * <p>
 * 时间复杂度：O(n^2)
 */
public class SelectSorter implements Sorter {
    @Override
    public void sort(int[] array) {
        for(int i = 0; i < array.length - 1; i++) {
            //嵌套的循环只需找出最小值的索引
            int minIndex = i;
            for(int j = i + 1; j < array.length; j++) {
                if(array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            //进行交换
            if(minIndex != i) {
                Sorter.swap(array, i, minIndex);
            }
        }
    }
}
