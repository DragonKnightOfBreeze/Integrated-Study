package com.windea.study.interview.concurrent.pcp.impl3;

//使用BlockingQueue解决生产者/消费者问题

//BlockingQueue是JDK5.0的新增内容，它是一个已经在内部实现了同步的队列，实现方式采用的是我们第2种await() / signal()方法。
//它可以在生成对象时指定容量大小，用于阻塞操作的是put()和take()方法。
//
//put()方法：类似于我们上面的生产者线程，容量达到最大时，自动阻塞。
//take()方法：类似于我们上面的消费者线程，容量为0时，自动阻塞。

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

