package com.windea.study.designpattern.decorator;

public class Milk extends DrinkDecorator {
	public Milk(Drink drink) {
		super(drink);
		setDescription("牛奶");
		setPrice(2.0f);
	}
}
