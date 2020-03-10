package com.windea.study.interview.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {
	public static void main(String[] args) {
		//test1();
		test2();
	}

	//验证volatile的可见性
	//假如number没有volatile关键字修饰，由于没有可见性，被修改时其他线程不会得到通知
	private static void test1() {
		MyData myData = new MyData();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " come in");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			myData.changeNumber();
			System.out.println(Thread.currentThread().getName() + "update number: " + myData.number);
		}, "t1").start();

		//等待直到number不为0
		while(myData.number == 0) {
		}

		System.out.println(Thread.currentThread().getName() + " is over, number: " + myData.number);
	}

	//验证volatile不保证原子性
	//线程在做某个具体业务时，要么保证同时成功，要么保证同时失败

	//为什么数字小于20000，出现了丢失的情况：
	private static void test2() {
		MyData myData = new MyData();
		MyData2 myData2 = new MyData2();

		for(int i = 0; i < 20; i++) {
			new Thread(() -> {
				for(int j = 0; j < 1000; j++) {
					myData.unaryPlusNumber();
					myData2.unaryPlusNumber();
				}
			}, String.valueOf(i)).start();
		}

		//需要等待上面的线程全部完成
		//默认后台有两个线程：main线程和gc线程
		while(Thread.activeCount() > 2) {
			Thread.yield();
		}

		System.out.println(Thread.currentThread().getName() + " get result: " + myData.number);
	}
}

class MyData {
	//int number = 0;
	volatile int number = 0;

	public void changeNumber() {
		this.number = 60;
	}

	public void unaryPlusNumber() {
		number++;
	}
}

class MyData2 {
	//默认值是0
	AtomicInteger number = new AtomicInteger();

	public void unaryPlusNumber() {
		number.getAndIncrement();
	}
}
