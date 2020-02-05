package com.windea.study.interview.concurrent.pcp.impl2;

//使用Lock类的await()/signal()方法解决生产者/消费者问题

//在JDK5中，用ReentrantLock和Condition可以实现等待/通知模型，具有更大的灵活性。
//通过在Lock对象上调用newCondition()方法，将条件变量和一个锁对象进行绑定，进而控制并发程序访问竞争资源的安全。

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

