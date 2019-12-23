package com.windea.study.designpattern.decorator;

public class Chocolate extends DrinkDecorator {
	public Chocolate(Drink drink) {
		super(drink);
		setDescription("巧克力");
		setPrice(3.0f);
	}
}
