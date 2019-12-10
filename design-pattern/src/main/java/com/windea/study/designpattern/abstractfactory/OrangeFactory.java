package com.windea.study.designpattern.abstractfactory;

import java.util.Objects;

class OrangeFactory extends FruitFactory {
	@Override
	public Fruit getFruit(String size) {
		if(Objects.equals(size, "small")) {
			return new SmallOrange();
		} else {
			return new Orange();
		}
	}
}
