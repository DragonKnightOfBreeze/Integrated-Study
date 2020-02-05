package com.windea.study.interview.concurrent.pcp.impl2;

import java.util.LinkedList;
import java.util.concurrent.locks.*;

/** 仓库。 */
public class Storage {
	/** 仓库容量。 */
	private static final int MAX_SIZE = 10;
	/** 仓库存储。 */
	private final LinkedList<Object> list = new LinkedList<>();
	/** 锁。 */
	private final Lock lock = new ReentrantLock();
	/** 仓库空的条件变量。 */
	private final Condition empty = lock.newCondition();
	/** 仓库满的条件变量。 */
	private final Condition full = lock.newCondition();

	/** 生产产品。 */
	public void produce() {
		lock.lock();
		while(list.size() >= MAX_SIZE) {
			System.out.println(String.format("生产者%s仓库已满。", Thread.currentThread().getName()));
			try {
				full.await();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		list.add(new Object());
		System.out.println(String.format("生产者%s生产了一个产品。现在的库存是%d。", Thread.currentThread().getName(), list.size()));
		empty.signalAll();
		lock.unlock();
	}

	/** 消费产品。 */
	public void consume() {
		lock.lock();
		while(list.size() <= 0) {
			System.out.println(String.format("消费者%s仓库为空。", Thread.currentThread().getName()));
			try {
				empty.await();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		list.remove();
		System.out.println(String.format("消费者%s消费了一个产品，现在的库存是%d", Thread.currentThread().getName(), list.size()));
		full.signalAll();
		lock.unlock();
	}
}
