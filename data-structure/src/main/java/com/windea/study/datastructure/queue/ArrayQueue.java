package com.windea.study.datastructure.queue;

//问题：
//不是环形队列，队列为空时可能也无法继续添加数据
//目前数组只能使用一次，没有达到复用的效果

/**
 * 数组队列。
 */
class ArrayQueue implements Queue {
	//队列为空：front = rear
	//队列已满：rear = maxSize - 1

	private int maxSize; //数组的最大容量
	private int front; //队列头
	private int rear; //队列尾
	private int[] array; //用于存储数据的数组

	//构造器，指明队列最大长度
	public ArrayQueue(int maxSize) {
		this.maxSize = maxSize;
		array = new int[maxSize];
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
	public void add(int value) {
		//如果队列已满，则不添加
		if(isFull()) {
			throw new IndexOutOfBoundsException("队列已满。");
		}

		rear++; //rear后移
		array[rear] = value;
	}

	@Override
	public int get() {
		//如果队列为空，则抛出异常
		if(isEmpty()) {
			throw new IndexOutOfBoundsException("队列为空。");
		}

		front++; //front后移
		return array[front];
	}

	@Override
	public int peek() {
		//如果队列为空，则抛出异常
		if(isEmpty()) {
			throw new IndexOutOfBoundsException("队列为空。");
		}

		return array[front + 1]; //front指向队列头的前一个位置
	}

	/**
	 * 显示队列的所有数据。
	 */
	public void show() {
		if(isEmpty()) {
			System.out.println("队列为空。");
		}
		for(int i = front; i < rear; i++) {
			System.out.printf("array[%d]=%d\n", i, array[i]);
		}
	}
}
