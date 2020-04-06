package com.windea.study.designpattern.simplefactory;

import org.junit.Test;

public class FruitFactoryTest {
    @Test
    public void test() {
        var apple = FruitFactory.getFruit("apple");
        apple.eat();

        var orange = FruitFactory.getFruit("orange");
        orange.eat();
    }
}
