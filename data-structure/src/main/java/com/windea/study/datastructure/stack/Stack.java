package com.windea.study.datastructure.stack;

/**
 * 栈（遵循FILO的特性）。
 */
public interface Stack<T> extends Iterable<T> {
    /** 判断栈是否为空。 */
    boolean isEmpty();

    /** 判断栈是否已满。 */
    boolean isFull();

    /** 得到栈的大小 */
    int size();

    /** 出栈。 */
    void push(T value);

    /** 入栈。 */
    T pop();

    /** 查看栈顶元素。 */
    T peek();
}
