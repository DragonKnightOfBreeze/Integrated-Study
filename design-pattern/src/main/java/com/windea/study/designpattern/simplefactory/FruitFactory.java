package com.windea.study.designpattern.simplefactory;

class FruitFactory {
	private FruitFactory() {
	}

	static Fruit getFruit(String type) {
		switch(type) {
			case "apple":
				return new Apple();
			case "orange":
				return new Orange();
			case "banana":
				return new Banana();
			default:
				throw new IllegalArgumentException();
		}
	}
}
