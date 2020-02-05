package com.windea.study.interview.concurrent.pcp.impl4;

//使用信号量Semaphore解决生产者/消费者问题。

//Semaphore是一种基于计数的信号量。
//它可以设定一个阈值，基于此，多个线程竞争获取许可信号，做完自己的申请后归还，
//超过阈值后，线程申请许可信号将会被阻塞。Semaphore可以用来构建一些对象池，资源池之类的，
//比如数据库连接池，我们也可以创建计数为1的Semaphore，将其作为一种类似互斥锁的机制，
//这也叫二元信号量，表示两种互斥状态。计数为0的Semaphore是可以release的，
//然后就可以acquire（即一开始使线程阻塞从而完成其他执行。）。

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

