package com.windea.study.interview.concurrent.pcp.impl1;

import java.util.LinkedList;

/** 仓库。 */
public class Storage {
	/** 仓库容量。 */
	private static final int MAX_SIZE = 10;
	/** 仓库存储。 */
	private final LinkedList<Object> list = new LinkedList<>();

	/** 生产产品。 */
	public void produce() {
		synchronized(list) {
			while(list.size() >= MAX_SIZE) {
				System.out.println(String.format("生产者%s仓库已满。", Thread.currentThread().getName()));
				try {
					list.wait();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			list.add(new Object());
			System.out.println(String.format("生产者%s生产了一个产品。现在的库存是%d。", Thread.currentThread().getName(), list.size()));
			list.notifyAll();
		}
	}

	/** 消费产品。 */
	public void consume() {
		synchronized(list) {
			while(list.size() <= 0) {
				System.out.println(String.format("消费者%s仓库为空。", Thread.currentThread().getName()));
				try {
					list.wait();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			list.remove();
			System.out.println(String.format("消费者%s消费了一个产品，现在的库存是%d", Thread.currentThread().getName(), list.size()));
			list.notifyAll();
		}
	}
}
