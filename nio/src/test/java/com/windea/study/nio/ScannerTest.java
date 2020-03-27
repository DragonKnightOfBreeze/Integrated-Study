package com.windea.study.nio;

import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class ScannerTest {
	@Test
	public void test() throws IOException {
		final var scanner = new Scanner(System.in);
		var a = scanner.nextLine();
		while(scanner.hasNext()) {
			var str = scanner.next();
			System.out.println("output: " + str);
		}
	}
}
