package com.windea.study.designpattern.abstractfactory;

import org.junit.Test;

public class FruitFactoryTest {
	@Test
	public void test() {
		var apple = new AppleFactory().getFruit("normal");
		apple.eat();

		var smallOrange = new OrangeFactory().getFruit("small");
		smallOrange.eat();
	}

}
