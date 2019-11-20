package com.windea.study.integrated.java13;

import org.junit.Test;

public class TextBlockTest {
	@Test
	public void test1() {
		System.out.println("""
123
123
""");
	}

	@Test
	public void test2() {
		//自动删除尾随三引号之前的缩进。
		var str = """
		123
		123
		""";
		System.out.println(str);
	}

	@Test
	public void test3() {
		var str = """
		hello
		%s
		""".formatted("world");
		System.out.println(str);
	}
}
