package com.windea.study.integrated.java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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

	@Test
	public void test5() {
		//时间校正器
		var time = LocalDateTime.now();
		System.out.println(time);

		var time2 = time.withDayOfMonth(5);
		System.out.println(time2);

		var time3 = time.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		System.out.println(time3);

		//自定义下一个工作日
		var time4 = time.with(temporal -> {
			var ldt = (LocalDateTime) temporal;
			var dow = ldt.getDayOfWeek();
			if(dow.equals(DayOfWeek.FRIDAY)) {
				return ldt.plusDays(3);
			} else if(dow.equals(DayOfWeek.SATURDAY)) {
				return ldt.plusDays(2);
			} else {
				return ldt.plusDays(1);
			}
		});
		System.out.println(time4);
	}

	@Test
	public void test6() {
		var time = LocalDateTime.now();

		var timeString = time.format(DateTimeFormatter.ISO_DATE_TIME);
		System.out.println(timeString);

		var timeString2 = time.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss"));
		System.out.println(timeString2);
	}

	@Test
	public void test7() {
		var zoneIds = ZoneId.getAvailableZoneIds();
		zoneIds.forEach(System.out::println);
	}

	@Test
	public void test8() {
		var zonedTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
		System.out.println(zonedTime);
	}
}
