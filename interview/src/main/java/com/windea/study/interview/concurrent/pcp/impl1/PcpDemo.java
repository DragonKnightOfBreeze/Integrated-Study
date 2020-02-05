package com.windea.study.interview.concurrent.pcp.impl1;

//使用Object类的wait()/notify()方法解决生产者/消费者问题

//当缓冲区已满时，生产者线程停止执行，放弃锁，使自己处于等状态，让其他线程执行；
//当缓冲区已空时，消费者线程停止执行，放弃锁，使自己处于等状态，让其他线程执行。
//
//当生产者向缓冲区放入一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态；
//当消费者从缓冲区取出一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态。

public class PcpDemo {
	public static void main(String[] args) {
		Storage storage = new Storage();
		Thread p1 = new Thread(new Producer(storage));
		Thread p2 = new Thread(new Producer(storage));
		Thread p3 = new Thread(new Producer(storage));
		Thread c1 = new Thread(new Consumer(storage));
		Thread c2 = new Thread(new Consumer(storage));
		Thread c3 = new Thread(new Consumer(storage));
		p1.start();
		p2.start();
		p3.start();
		c1.start();
		c2.start();
		c3.start();
	}
}

