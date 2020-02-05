package com.windea.study.interview.concurrent.pcp.impl4;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/** 仓库。 */
public class Storage {
	/** 仓库容量。 */
	private static final int MAX_SIZE = 10;
	/** 仓库存储。 */
	private final LinkedList<Object> list = new LinkedList<>();
	/** 非空锁。 */
	private final Semaphore notEmpty = new Semaphore(0);
	/** 非满锁。 */
	private final Semaphore notFull = new Semaphore(MAX_SIZE);
	/** 互斥锁。 */
	private final Semaphore mutex = new Semaphore(1);

	/** 生产产品。 */
	public void produce() {
		try {
			notFull.acquire();
			mutex.acquire();
			list.add(new Object());
			System.out.println(String.format("生产者%s生产了一个产品。现在的库存是%d。", Thread.currentThread().getName(), list.size()));
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			notEmpty.release();
			mutex.release();
		}
	}

	/** 消费产品。 */
	public void consume() {
		try {
			notEmpty.acquire();
			mutex.acquire();
			list.remove();
			System.out.println(String.format("消费者%s消费了一个产品，现在的库存是%d", Thread.currentThread().getName(), list.size()));
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			notFull.release();
			mutex.release();
		}
	}
}
