package com.windea.study.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
	@Test
	public void test1() {
		//创建流的几种方法
		var stream1 = Arrays.stream(new String[]{"a", "b", "c"});
		stream1.forEach(System.out::println);

		var stream2 = List.of(1, 2, 3).stream();
		stream2.forEach(System.out::println);

		var stream3 = Map.of("a", 1, "b", 2).entrySet().stream();
		stream3.forEach(System.out::println);

		var stream4 = Stream.of("a", "b", "c");
		stream4.forEach(System.out::println);

		var stream5 = Stream.iterate(1, e -> e + 2);
		stream5.limit(5).forEach(System.out::println);

		var stream6 = Stream.generate(() -> Math.random());
		stream6.limit(5).forEach(System.out::println);
	}

	@Test
	public void test2() {
		List<String> list = Stream.of("a", "b", "c", "d", "")
			.peek(e -> System.out.println(e))
			.map(e -> e.repeat(3))
			.flatMap(e -> Stream.of(e))
			.filter(e -> !e.isEmpty())
			.limit(4)
			.skip(1)
			.distinct()
			.sorted(Comparator.comparingInt(a -> a.charAt(0)))
			.collect(Collectors.toList());
		System.out.println(list);
	}

	@Test
	public void test3() {
		var total = Stream.of(1, 2, 3, 4).reduce(0, (a, b) -> a + b);
		System.out.println(total);
	}
}
