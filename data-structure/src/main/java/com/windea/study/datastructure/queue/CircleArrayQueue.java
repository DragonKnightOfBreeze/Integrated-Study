package com.windea.study.datastructure.queue;

/**
 * 环形数组队列。
 */
public class CircleArrayQueue implements Queue {
	//含义调整：
	//front 指向队列的第一个元素，初始值为0
	//rear 指向队列的最后一个元素的后一个位置，初始值为0
	//队列已满：(rear + 1) % maxSize = front（加1是因为需要预留一个空间）
	//队列为空：rear = front
	//有效数据个数：(rear - front + maxSize) % maxSize
	//预留一个空间作为约定

	private int maxSize;
	private int front;
	private int rear;
	private int[] array;

	public CircleArrayQueue(int maxSize) {
		this.maxSize = maxSize;
		this.front = 0;
		this.rear = 0;
		this.array = new int[maxSize];
	}

	@Override
	public boolean isEmpty() {
		return rear == front;
	}

	@Override
	public boolean isFull() {
		return (rear + 1) % maxSize == front;
	}

	@Override
	public int size() {
		return (rear - front + maxSize) % maxSize;
	}

	@Override
	public void add(int value) {
		//如果队列已满，则不添加
		if(isFull()) {
			throw new IndexOutOfBoundsException("队列已满。");
		}

		//直接将数据加入
		array[rear] = value;
		//将rear后移，必须考虑取模
		rear = (rear + 1) % maxSize;
	}

	@Override
	public int get() {
		//如果队列为空，则抛出异常
		if(isEmpty()) {
			throw new IndexOutOfBoundsException("队列为空。");
		}

		//front指向队列第一个元素
		var value = array[front];
		//将front后移
		front = (front + 1) % maxSize;
		return value;
	}

	@Override
	public int peek() {
		//如果队列为空，则抛出异常
		if(isEmpty()) {
			throw new IndexOutOfBoundsException("队列为空。");
		}

		return array[front]; //front指向队列头
	}

	/**
	 * 显示队列的所有数据。
	 */
	public void show() {
		if(isEmpty()) {
			System.out.println("队列为空。");
		}
		//从front开始遍历
		for(int i = front; i < front + size(); i++) {
			//考虑到数组是环形
			System.out.printf("array[%d]=[%d]\n", i % maxSize, array[i % maxSize]);
		}
	}
}
