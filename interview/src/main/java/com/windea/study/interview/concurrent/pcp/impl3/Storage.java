package com.windea.study.interview.concurrent.pcp.impl3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/** 仓库。 */
public class Storage {
	/** 仓库容量。 */
	private static final int MAX_SIZE = 10;
	/** 仓库存储。 */
	private final BlockingQueue<Object> list = new LinkedBlockingQueue<>(MAX_SIZE);

	/** 生产产品。 */
	public void produce() {
		try {
			list.put(new Object());
			System.out.println(String.format("生产者%s生产了一个产品。现在的库存是%d。", Thread.currentThread().getName(), list.size()));
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	/** 消费产品。 */
	public void consume() {
		try {
			list.take();
			System.out.println(String.format("消费者%s消费了一个产品，现在的库存是%d", Thread.currentThread().getName(), list.size()));
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
