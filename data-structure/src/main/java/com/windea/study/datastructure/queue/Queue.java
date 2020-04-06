package com.windea.study.datastructure.queue;

/**
 * 队列（遵循FIFO的特性）。
 */
interface Queue<T> extends Iterable<T> {
    /** 判断队列是否为空。 */
    boolean isEmpty();

    /** 判断队列是否已满。 */
    boolean isFull();

    /** 队列的有效元素个数。 */
    int size();

    /** 添加数据到队列。 */
    void add(T value);

    /** 得到队列的头数据。 */
    T get();

    /** 显示队列的头数据（不是取出数据）。 */
    T peek();
}
