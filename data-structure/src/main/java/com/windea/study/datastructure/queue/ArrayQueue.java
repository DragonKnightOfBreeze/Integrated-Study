package com.windea.study.datastructure.queue;

//问题：
//不是环形队列，队列为空时可能也无法继续添加数据
//目前数组只能使用一次，没有达到复用的效果

import java.util.Iterator;

/**
 * 数组队列。
 */
class ArrayQueue<T> implements Queue<T> {
	//队列为空：front = rear
	//队列已满：rear = maxSize - 1

	private int maxSize; //数组的最大容量
	private int front; //队列头
	private int rear; //队列尾
	private Object[] array; //用于存储数据的数组

	//构造器，指明队列最大长度
	public ArrayQueue(int maxSize) {
		this.maxSize = maxSize;
		array = new Object[maxSize];
		//指向队列头部，指向队列头的前一个位置
		front = -1;
		//指向队列尾部，指向队列中最后一个数据的位置
		rear = -1;
	}

	@Override
	public boolean isEmpty() {
		return rear == front;
	}

	@Override
	public boolean isFull() {
		return rear == maxSize - 1;
	}

	@Override
	public int size() {
		return rear - front;
	}

	@Override
	public void add(T value) {
		//如果队列已满，则不添加
		if(isFull()) {
			throw new IndexOutOfBoundsException("队列已满。");
		}

		rear++; //rear后移
		array[rear] = value;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get() {
		//如果队列为空，则抛出异常
		if(isEmpty()) {
			throw new IndexOutOfBoundsException("队列为空。");
		}

		front++; //front后移
		return (T) array[front];
	}

	@Override
	@SuppressWarnings("unchecked")
	public T peek() {
		//如果队列为空，则抛出异常
		if(isEmpty()) {
			throw new IndexOutOfBoundsException("队列为空。");
		}

		return (T) array[front + 1]; //front指向队列头的前一个位置
	}

	@Override
	public Iterator<T> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<T> {
		private int currentIndex;

		private Itr() {
			this.currentIndex = front;
		}

		@Override
		public boolean hasNext() {
			return currentIndex < rear;
		}

		@Override
		@SuppressWarnings("unchecked")
		public T next() {
			return (T) array[currentIndex++];
		}
	}
}
