package com.windea.study.datastructure.stack;

//使用数组模拟栈
//使用top表示栈顶，默认为-1

import java.util.Iterator;

public class ArrayStack<T> implements Stack<T> {
	private int maxSize;
	private int top;
	private Object[] array;

	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		this.top = -1;
		this.array = new Object[maxSize];
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public boolean isFull() {
		return top == maxSize - 1;
	}

	@Override
	public int size() {
		return top + 1;
	}

	@Override
	public void push(T value) {
		if(isFull()) {
			throw new IndexOutOfBoundsException("栈已满。");
		}
		top++;
		array[top] = value;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T pop() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException("栈为空。");
		}
		return (T) array[top--];
	}

	@Override
	@SuppressWarnings("unchecked")
	public T peek() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException("栈为空。");
		}
		return (T) array[top];
	}

	@Override
	public Iterator<T> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<T> {
		private int current = top;

		@Override
		public boolean hasNext() {
			return current >= 0;
		}

		@Override
		@SuppressWarnings("unchecked")
		public T next() {
			return (T) array[current--];
		}
	}
}
