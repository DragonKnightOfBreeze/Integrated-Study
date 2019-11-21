package com.windea.study.integrated.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.*;

public class TimeTest {
	@Test
	public void test1() throws Exception {
		Callable<Date> task = () -> DateFormatThreadLocal.convert("2012-12-12");

		//创建线程池
		var pool = Executors.newFixedThreadPool(10);
		var results = new ArrayList<Future<Date>>();

		for(int i = 0; i < 10; i++) {
			results.add(pool.submit(task));
		}

		for(var future : results) {
			System.out.println(future.get());
		}

		pool.shutdown();
	}
}
