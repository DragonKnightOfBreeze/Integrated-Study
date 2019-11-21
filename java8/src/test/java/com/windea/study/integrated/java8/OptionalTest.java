package com.windea.study.integrated.java8;

import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
	@Test
	public void test() {
		printName1("Windea");
		printName1("Windlow");
		printName1(null);
	}


	private void printName1(String name) {
		var optional = Optional.ofNullable(name);
		System.out.println(optional.orElse("Windea"));
	}
}
