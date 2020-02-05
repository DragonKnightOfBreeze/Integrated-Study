package com.windea.study.interview.concurrent.pcp.impl5;

//使用管道解决生产者/消费者问题。

//一种特殊的流，用于不同线程间直接传送数据，一个线程发送数据到输出管道，另一个线程从输入管道中读数据。
//inputStream.connect(outputStream)或outputStream.connect(inputStream)的作用
//是使两个Stream之间产生通信链接，这样才可以将数据进行输出与输入。
//这种方式只适用于两个线程之间通信，不适合多个线程之间通信。

import java.io.IOException;

public class PcpDemo {
	public static void main(String[] args) {
		Producer p = new Producer();
		Consumer c = new Consumer();
		Thread t1 = new Thread(p);
		Thread t2 = new Thread(c);
		try {
			p.getPipedOutputStream().connect(c.getPipedInputStream());
			t1.start();
			t2.start();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

