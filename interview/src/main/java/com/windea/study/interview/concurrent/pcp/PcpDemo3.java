package com.windea.study.interview.concurrent.pcp;

//使用BlockingQueue解决生产者/消费者问题

//BlockingQueue是JDK5.0的新增内容，它是一个已经在内部实现了同步的队列，实现方式采用的是我们第2种await() / signal()方法。
//它可以在生成对象时指定容量大小，用于阻塞操作的是put()和take()方法。
//
//put()方法：类似于我们上面的生产者线程，容量达到最大时，自动阻塞。
//take()方法：类似于我们上面的消费者线程，容量为0时，自动阻塞。

import java.util.concurrent.*;

public class PcpDemo3 {
	public static void main(String[] args) {
		Storage storage = new Storage();
		ExecutorService executorService = Executors.newFixedThreadPool(6);
		for(int i = 0; i < 3; i++) {
			executorService.submit(new Producer(storage));
		}
		for(int i = 0; i < 3; i++) {
			executorService.submit(new Consumer(storage));
		}
	}

	public static class Storage {
		private static final int MAX_SIZE = 10;
		private final BlockingQueue<Object> list = new LinkedBlockingQueue<>(MAX_SIZE);

		public void produce() {
			try {
				list.put(new Object());
				System.out
					.println(String.format("生产者%s生产了一个产品。现在的库存是%d。", Thread.currentThread().getName(), list.size()));
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void consume() {
			try {
				list.take();
				System.out
					.println(String.format("消费者%s消费了一个产品，现在的库存是%d", Thread.currentThread().getName(), list.size()));
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static class Consumer implements Runnable {
		private Storage storage;

		public Consumer(Storage storage) {
			this.storage = storage;
		}

		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(3000);
					storage.consume();
				} catch(InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}

	public static class Producer implements Runnable {
		private Storage storage;

		public Producer(Storage storage) {
			this.storage = storage;
		}

		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(1000);
					storage.produce();
				} catch(InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}
}

