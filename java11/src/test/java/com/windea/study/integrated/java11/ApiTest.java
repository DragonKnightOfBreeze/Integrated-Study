package com.windea.study.integrated.java11;

import org.junit.Test;

import java.util.stream.Stream;

public class ApiTest {
	@Test
	public void test1() {
		Stream.iterate(0, i -> i++)
			.limit(10)
			.forEach(System.out::println);
	}

	@Test
	public void test2() {
		System.out.println("　".isBlank());

	}
}
