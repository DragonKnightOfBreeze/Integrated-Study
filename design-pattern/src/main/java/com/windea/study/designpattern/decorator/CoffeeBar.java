package com.windea.study.designpattern.decorator;

public class CoffeeBar {
	public static void main(String[] args) {
		var order = new Milk(new Chocolate(new LongBlackCoffee()));

		System.out.println(order.getDescription());
		System.out.println(order.cost());
	}
}
