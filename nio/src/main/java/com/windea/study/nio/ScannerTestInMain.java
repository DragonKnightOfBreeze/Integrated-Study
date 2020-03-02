package com.windea.study.nio;

import java.util.Scanner;

public class ScannerTestInMain {
	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			var str = scanner.next();
			System.out.println("output: " + str);
		}
	}
}
