package com.windea.study.designpattern.abstractfactory;

import java.util.Objects;

class BananaFactory extends FruitFactory {
	@Override
	public Fruit getFruit(String size) {
		if(Objects.equals(size, "small")) {
			return new SmallBanana();
		} else {
			return new Banana();
		}
	}
}
