package com.windea.study.integrated.java9;

import org.junit.Test;

import java.util.*;

public class CollectionFactoryTest {
	@SuppressWarnings("Java9CollectionFactory")
	@Test
	public void test1() {
		//jdk8
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");

		var immutableList = Collections.unmodifiableList(list);
		immutableList.forEach(System.out::println);
	}

	@Test
	public void test2() {
		List<Integer> list = List.of(1, 2, 3);
		list.forEach(System.out::println);
	}

	@Test
	public void test3() {
		var map1 = Map.of("a", 1);
		System.out.println(map1);

		var map2 = Map.ofEntries(Map.entry("a", 1));
		System.out.println(map2);
	}
}
