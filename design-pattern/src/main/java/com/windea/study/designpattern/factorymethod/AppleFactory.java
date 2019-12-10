package com.windea.study.designpattern.factorymethod;

class AppleFactory extends FruitFactory {
	@Override
	Fruit getFruit() {
		return new Apple();
	}
}
