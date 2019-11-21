package com.windea.study.integrated.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaTest {
	@Test
	public void test1() {
		//使用匿名内部类
		//Comparator<Integer> comparator = new Comparator<Integer>() {
		//	@Override
		//	public int compare(Integer o1, Integer o2) {
		//		return Integer.compare(o1,o2);
		//	}
		//};

		//使用lambda
		//Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);

		//使用方法引用
		//Comparator<Integer> comparator = Integer::compare;

		//使用Comparator的静态方法
		Comparator<Integer> comparator = Comparator.comparingInt(o -> o);

		TreeSet<Integer> set = new TreeSet<>(comparator);
		set.add(1);
		set.add(3);
		set.add(2);
		System.out.println(set);
	}

	@Test
	public void test2() {
		var list = List.of("Foo", "Bar", "FooBar");
		var list2 = list.stream()
			.map(String::toUpperCase)
			.filter(e -> e.length() <= 3)
			.collect(Collectors.toList());
		System.out.println(list2);
	}
}
