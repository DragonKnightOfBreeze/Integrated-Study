package com.windea.study.integrated.java12;

import org.junit.Test;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatTest {
	@Test
	public void test1() {
		var format = NumberFormat.getCompactNumberInstance(Locale.CHINA, NumberFormat.Style.LONG);
		System.out.println(format.format(123_123_123));
		System.out.println(format.format(123_123));
	}
}
